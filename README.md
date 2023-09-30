# AdaDesafioBackEnd
Desafios propostos pela ada 
## Observações para os avaliadores: 
##### Quando for rodar o código peço que use a mesma string de cnpj que cadastrou pra pesquisar na hora de pesquisar uma pessoa jurídica
##### No desafio 03 a v1 é referente ao desafio 02, fiz uma v2 para a nova versão com sqs da AWS 


## Desafio 04
##### O débito técnico identificado nessa API é a capacidade de um cliente qualquer cadastrar na nossa base de dados uma pessoa jurídica e uma pessoa física, isso poderia acarretar em ataques que populem nossos bancos e que quebrem nossa fila do sqs, somente rodando um endpoint, 
e poderia pegar os cadastros das filas nos fazendo perder um cliente que precisava de atendimento.
##### Gostaria de desenvolver uma área de login para ser capaz de bloquear as chamadas da api e exigir um token para que essas chamadas possam ser feitas
##### Infelizmente não implementei
