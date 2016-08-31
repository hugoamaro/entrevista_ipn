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
Mesmo assim, pareceu-me que toda a estrutura necessária estava derterminada, e que, quanto muito iria ter que alterar as regras de construção do grafo.
