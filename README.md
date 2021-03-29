o programa deverá ter pelo menos as funções a seguir: game(), initialize(), print(), step() e status(), caso precise você
escrever outras funções.

• game(): Essa função irá executar a lógica do jogo, a função game() chama todos as funções descritas a
seguir. A cada jogada, a função deve imprimir a grade do Sudoku na tela e solicitar uma jogada pelo teclado.
A jogada pode ser colocar um número em determinada posição na matriz ou limpar uma posição, pois
dependendo do estado em que se encontra o jogo, o jogador deverá rever algumas jogadas que o deixaram
sem possibilidade de fazer jogadas válidas.

• initialize(): A função initialize() deverá fazer a leitura da grade armazenada em um arquivo texto e devolver
uma matriz 9x9 já com os valores iniciais. Para que o jogo fique desafiador tente criar arquivos com
configurações com grau de dificuldade diferentes. Abaixo segue um exemplo de arquivo de entrada as
posições estão separadas por espaço em branco e a posição vazia é definida usando o caractere ‘_’ underline.
Exemplo de arquivo de entrada:

![Sudoku](https://user-images.githubusercontent.com/44293659/112774514-51222200-9010-11eb-9b67-406467a5477e.png)

• print(): Essa função imprime o grade do Sudoku na tela em modo texto, não se esqueça que a impressão
deve ser o mais informativa possível, para o que jogador consiga decidir claramente onde e qual será o
próximo número colocado no Sudoku.

• step(): Essa função recebe uma posição e um número que o usuário deseja inserir na solução do Sudoku,
caso a posição esteja ocupada ou inválida ( linha coluna que não existe) a função deve retornar -1, se o valor
ser inserido já estiver presente na linha, coluna ou região a função retorna 0, e por fim, se for possível
colocar o número na posição solicitada a função retorna 1. Ao final a matriz atualizada é retornada. Essa
função também é responsável em limpar uma posição na matriz do Sudoku e retorna 1 caso tenha sucesso,
caso não consiga a função retorna -1.

• status (): A função status() verifica se o jogador já solucionou o quebra-cabeça, ou seja, não existe posições
vazias na matriz. Caso o jogador tenha cumprido o objetivo do jogo a função retorna true, ou false caso
contrário
