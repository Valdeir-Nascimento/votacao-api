# Como fazer o versionamento da API? Que estratégia utilizar?
Uma boa API é versionada: Mudanças e novos recursos são implementados em novas
versões da API em vez de alterar continuamente somente na mesma versão. Nesse caso,
é necessário, inserir uma nova versão da aplicação e incrementar o número da versão.

Assim, os consumidores da API existentes podem continuar usando a versão anterior, e
caso seja necessário atualizar para versão mais atual, e com isso podem consumir os novos
recursos implementados.

## V1 e V2
Em cada versão da API é implementado em um módulo separado cuja a identificação
é o número da versão principal (Ex.: v1 e v2).

## Cabeçalho HTTP Accept V1 e V2
Em cada versão utilizar o cabeçalho _accept_ da requisição para informar
o número da versão e com isso saber se estar consumindo a versão atual ou antiga.
