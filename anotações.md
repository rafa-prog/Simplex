na hora de trocar as colunas, colocar um sistema de trocas iterativo, onde precisa guardar os valores de i e f (provavelmente outro hashset), para banir trocas redundantes e op inválidas



MDS OQ EU TO FAZENDO

Deque<Troca> pilha = new ArrayDeque<>();
Troca troca = new Troca(1, 2, null);
pilha.push(troca);

Troca result = pilha.pop();

acho q funciona, pq faço isso n sei