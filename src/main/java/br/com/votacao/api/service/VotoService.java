package br.com.votacao.api.service;

import br.com.votacao.api.dto.StatusCpfDTO;
import br.com.votacao.api.exception.*;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.model.Voto;
import br.com.votacao.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static br.com.votacao.api.util.MessagesUtil.CPF_UNABLE_TO_VOTE;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.validar.cpf}")
    private String validarCPF;

    public List<Voto> listar() {
        return votoRepository.findAll();
    }

    public List<Voto> buscarVotosPorPauta(Long id) {
        var votos = votoRepository.findByPautaId(id);
        if (!votos.isPresent()) {
            throw new VotoNaoEncontradoException(id);
        }
        return votos.get();
    }

    public void excluir(Long idVoto) {
        votoRepository.deleteById(idVoto);
    }

    public List<Voto> obterVotosPorPauta(Long id) {
        return votoRepository
                .findByPautaId(id)
                .orElseThrow(() -> new VotoNaoEncontradoException(id));
    }

    public Voto salvarVoto(Long idPauta, Long idSessao, Voto voto) {
        Sessao sessaoAtual = sessaoService.buscarSessaoPorPauta(idSessao, idPauta);
        if (idPauta.equals(sessaoAtual.getPauta().getId())) {
            throw new SessaoInvalidaException("Sessão Inválida");
        }
        voto.setPauta(sessaoAtual.getPauta());
        getValidarVoto(voto);
        return votoRepository.save(voto);
    }

    public void excluirPautaDaVotacao(Long id) {
        var votos = votoRepository.findByPautaId(id);
        votos.ifPresent(v -> v.forEach(votoRepository::delete));
    }

    protected void getVarificarVoto(Sessao sessao, Voto voto) {
        var dataLimite = sessao.getDataInicio().plusMinutes(sessao.getMinutos());
        if (LocalDateTime.now().isAfter(dataLimite)) {
            //TODO: Enviar Menssagem
            throw new SessaoFinalizadaException(sessao.getId());
        }

        getValidarCPF(voto);
        jaVotou(voto);
    }

    protected void jaVotou(Voto voto) {
        var votou = votoRepository.findByCpfAndPautaId(voto.getCpf(), voto.getPauta().getId());
        if (votou.isPresent()) {
            throw new VotoRegistradoException(voto.getId());
        }
    }

    protected void getValidarVoto(Voto voto) {
        var cpf = getValidarCPF(voto);
        if (HttpStatus.OK.equals(cpf.getStatusCode())) {
            if (CPF_UNABLE_TO_VOTE.equalsIgnoreCase(cpf.getBody().getStatus())) {
                throw new UnableCpfException("Unable CPF");
            }
        } else {
            throw new CpfInvalidoException("CPF Inválido");
        }
    }

    private ResponseEntity<StatusCpfDTO> getValidarCPF(Voto voto) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        var httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate
                .exchange(
                        validarCPF.concat("/").concat(voto.getCpf()),
                        HttpMethod.GET,
                        httpEntity,
                        StatusCpfDTO.class);

    }
}
