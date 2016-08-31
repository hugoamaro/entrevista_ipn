Readme file for enterview exercise solution.


Intro

Primeiro que tudo, e para que melhor se compreenda o meu processo de trabalho, gostaria de referir que:
	- O problema em si, ainda que um problema de agendamento de tarefas com dependências entre elas, não inclui as variáveis, comuns a este tipo de problemas, que o tornam "um bixo de sete cabeças". Como por exemplo, a duração das tarefas e dependências OR (além das AND) entre tarefas (tarefa A precisa de B||C). Isto para explicar o próximo ponto.
	- Numa situação real de trabalho, sendo o algoritmo para aplicar num situação de "mundo real" e/ ou crítica e no sentido de não "reinventar a roda" (no que diz respeito a problemas relativamente complexos de programação dinâmica que podem levar a erros difíceis de encontrar), a minha abordagem iria sempre passar por uma pesquisa das soluções já existentes, adequadas ao ambiente de execução em questão, com provas dadas e de boa performance.
	- Pela conversa na entrevista, este não é o caso. E como tal abordei o problema como sendo algo "novo" e limitei qualquer consulta á Reference do Java.




Abordagem ao Problema

Assim que li o exercício vi o problema como sendo um de grafos com os nós a representarem as tarefas e as arestas as dependências.

O meu primeiro passo é, muitas vezes, pegar num papel e num lápis para melhor visualizar o problema.

Para isso, começando com os testes mais simples, simulo a entrada de dados e ao mesmo tempo construo grafos que representem a solução. Construir alguns grafos, diferentes entre si pelas regras que foram usadas para onde e como adicionar os novos nós e as novas dependências, permitiu-me rapidamente tirar algumas conclusões sobre como deveria estar organizada a estrutura de forma a representar as precedências necessárias e permitir também percorrer,no final, todos os nós de uma forma estruturada e imprimí-los de forma a representarem essas mesmas precedências.

Basicamente, um nó dependeria dos seus filhos à esquerda, e os filhos à direita de um pai denpendem desse pai.

Nota: Quando me refiro a regras de construção dos grafos, estou-me a referir a por exemplo:
		- Entrada A=>B. O A não existe, o B também não existe, logo crio ambos e adiciono o B como filho esquerdo do A.
		- Entrada A=>C. O A existe, o C não existe, logo crio o C e faço-o propagar como folha esquerda pelos filhos do A.
		- etc

Efectuar a construção dos grafos com sucesso através de algumas regras simples (isto para os problemas mais simples também), deu-me confiança para implementar uma primeira solução. Ainda que ciente que as regras foram determinadas com muitos poucos exemplos e nenhum deles com situações "complicadas".
Mesmo assim, pareceu-me que toda a estrutura necessária estava derterminada e que, quanto muito, iria ter que alterar as regras de construção do grafo.

A implementação consistiria em:
	- implementação de Nó com dois filhos Left e Right, e funções que facilitassem a introdução de filhos (à esquerda e direita) e que permitissem facilmente se existe precedência e ascendência de um dado nó a partir deste nó. Precedência consiste em descobrir se um dado nó tem na sua dependência outro. Ascendência consiste em descobrir se um dado nó é dependente de outro.
	- implementação de uma classe que mantém e gere os grafos. Esta classe conterá também a função que recebe a String problema e retorna a String solução.
	- implementação de testes
	- implementação de classes auxiliares, nomeadamente as Exceptions

O uso de um HashMap para conter os nós deveu-se à necessidade de uma forma rápida "na maior parte das situações", de saber se um nó já se encontra criado e de aceder directamente a esse nó. Poderia ser feito de outras formas, percorrendo a(s) àrvores por exemplo. Mas como existia a necessidade de manter vários grafos desconexos entre si (conexão implica dependência), isso iria levar a ter que percorrer múltiplas àrvores cada vez que se desse a necessiade de procurar um nó. Fico com dúvida aqui qual seria a solução mais eficiente, teria que ir ler melhor (novamente) questões relacionadas com "velocidade" de diferentes algoritmos.
A nível do acesso final para impressão, uma vez que a ordem de acesso a cada grafo não era importante, grafos diferentes não têm dependências, o HashMap também se mostrou adequado.

Após esta fase, na qual os testes simples passavam com sucesso (testes do PDF do exercício), resolvi voltar ao papel para criar testes que "partissem" o algoritmo.

As regras que eu defini inicialmente revelaram-se erradas (até certo ponto) assim como não previam a necessidade de por vezes ter que dividir um grafo já existente e combinar essas partes num dado ponto de outro grafo.

No final criei mais alguns testes para confirmar que as alterações tinham tido o efeito desejado.

Sou sincero que por mim, caso fosse para aplicar numa situação real, ainda faria mais testes, e testes maiores também.

De qualquer das formas parece-me que a esta altura a solução já é suficiente para o exercício.
