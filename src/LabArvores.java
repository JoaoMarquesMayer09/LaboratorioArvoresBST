import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * LABORATÓRIO: Cadastro de alunos por matrícula (Árvore Binária de Busca)
 * Projeto de Análise de Algoritmos
 *
 * Grupo: ______________________________________________
 *
 * O que fazer: complete os 4 TODOs (marcados com "TODO") e rode o programa.
 * Não altere o resto do arquivo. Compile e rode com:
 *     javac -encoding UTF-8 LabArvores.java
 *     java LabArvores
 *
 * Convenções deste laboratório:
 *  - As matrículas são todas distintas (não existem repetidas).
 *  - Altura: uma folha tem altura 0; uma árvore vazia (null) tem altura -1.
 *  - "Passos" de uma busca = quantos nós foram visitados (comparados) até achar o alvo.
 */
public class LabArvores {

    // ------------------------------------------------------------------
    // O nó da árvore (já pronto): guarda a matrícula de um aluno
    // ------------------------------------------------------------------
    static class No {
        int matricula;
        No esq;
        No dir;

        No(int matricula) {
            this.matricula = matricula;
        }
    }

    // ------------------------------------------------------------------
    // TODO 1: inserir uma matrícula na BST
    // Devolve a raiz da árvore depois da inserção.
    // Dicas:
    //  1) Se raiz == null, achamos o ponteiro vazio (NIL): devolva new No(matricula).
    //  2) Se matricula < raiz.matricula, o lugar dela é na subárvore ESQUERDA:
    //         raiz.esq = inserir(raiz.esq, matricula);
    //  3) Caso contrário, é na subárvore DIREITA (faça o mesmo com raiz.dir).
    //  4) No fim, devolva a raiz.
    // ------------------------------------------------------------------
    static No inserir(No raiz, int matricula) {
        // TODO 1: caso a arvore esteja vazia, cria-se um novo nó
        if (raiz == null){
            return new No(matricula);
        }

        // Se a matricula for menor, caminha-se para a esquerda
        if (matricula < raiz.matricula){
            raiz.esq = inserir(raiz.esq, matricula);
        }else{
            // Se for maior, caminha-se para a direita
            raiz.dir = inserir(raiz.dir, matricula);
        }
        return raiz;
    }

    // ------------------------------------------------------------------
    // TODO 2: buscar uma matrícula
    // Devolve o NÚMERO DE PASSOS (nós visitados) até encontrar o alvo,
    // ou -1 se o alvo não estiver na árvore.
    // Dicas:
    //  - Comece com passos = 0 e com o nó atual = raiz.
    //  - Enquanto o nó atual não for null: conte 1 passo e compare.
    //      * igual: devolva passos
    //      * alvo menor: vá para a esquerda
    //      * alvo maior: vá para a direita
    //  - Se saiu do laço (chegou em null), o alvo não existe: devolva -1.
    // ------------------------------------------------------------------
    static int buscar(No raiz, int alvo) {
        // TODO 2: Localizar a matricula do aluno dentro da arvore
        // Comecamos a busca pela raiz
        No atual = raiz;

        // Contador de nós visitados
        int passos = 0;

        // Percorre a arvore enquanto existir um nó
        while (atual != null){
            // Conta a visita ao nó atual
            passos++;

            // Verifica se encontrou a matricula do aluno
            if (alvo == atual.matricula){
                return passos;
            }

            // Decide para qual lado continuar
            if (alvo < atual.matricula){
                atual = atual.esq;
            }else{
                atual = atual.dir;
            }
        }
        // Matricula não encontrada
        return -1;
    }

    // ------------------------------------------------------------------
    // TODO 3: percurso em ordem (esquerda, raiz, direita)
    // Adiciona as matrículas em "saida" na ordem do percurso.
    // Dicas:
    //  - Caso base: se raiz == null, apenas return.
    //  - Chame emOrdem(raiz.esq, saida); depois saida.add(raiz.matricula);
    //    depois emOrdem(raiz.dir, saida).
    // ------------------------------------------------------------------
    static void emOrdem(No raiz, List<Integer> saida) {
        // TODO 3: Se o nó estiver vazio, encerra o processo
        if (raiz == null){
            return;
        }

        // 1 - Percorre a subarvore da esquerda
        emOrdem(raiz.esq, saida);

        // 2 - Adiciona a matricula do nó atual
        saida.add(raiz.matricula);

        // 3 - Percorre a subarvore da direita
        emOrdem(raiz.dir, saida);
    }

    // ------------------------------------------------------------------
    // TODO 4: altura da árvore
    // Folha = 0. Árvore vazia (null) = -1.
    // Dicas:
    //  - Caso base: se raiz == null, devolva -1.
    //  - A altura é 1 + o MAIOR entre a altura da esquerda e a da direita.
    //    Use Math.max(...).
    // ------------------------------------------------------------------
    static int altura(No raiz) {
        // TODO 4: Se a arvore estiver vazia, sua altura é -1
        if (raiz == null){
            return -1;
        }

        // Calcula a altura da subarvore esquerda
        int alturaEsquerda = altura(raiz.esq);

        // Calcula a altura da subarvore direita
        int alturaDireita = altura(raiz.dir);

        // Retorna a maior altura encontrada + 1
        return 1 + Math.max(alturaDireita, alturaEsquerda);
    }

    // ------------------------------------------------------------------
    // BÔNUS (+1 ponto): menor e maior matrícula da árvore
    // minimo: siga só o ponteiro esquerdo até não haver mais filho esquerdo.
    // maximo: siga só o ponteiro direito até não haver mais filho direito.
    // Devolva -1 se a árvore for vazia.
    // ------------------------------------------------------------------
    static int minimo(No raiz) {
        // Verifica se a arvore esta vazia
        if (raiz == null){
            return -1;
        }

        // Comeca a busca pela raiz
        No atual = raiz;

        // Caminha até o nó  mais a esquerda
        while (atual.esq != null){
            atual = atual.esq;
        }

        // Retorna a menor matricula
        return atual.matricula;
    }

    static int maximo(No raiz) {
        // Verifica se a arvore está vazia
        if (raiz == null){
        return -1;
    }
        // Comeca pela raiz
        No atual = raiz;

        // Caminha até o nó mais a direita
        while (atual.dir != null){
            atual = atual.dir;
        }

        // Retorna a maior matricula
        return atual.matricula;
    }

    // ==================================================================
    // DAQUI PARA BAIXO: código pronto. Não altere.
    // ==================================================================

    static No construir(int[] matriculas) {
        No raiz = null;
        for (int m : matriculas) {
            raiz = inserir(raiz, m);
        }
        return raiz;
    }

    // Matrículas geradas em sequência: 2026001, 2026002, ...
    static int[] crescentes(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = 2026001 + i;
        }
        return v;
    }

    // As mesmas matrículas, em ordem aleatória (semente fixa: todos obtêm o mesmo resultado)
    static int[] embaralhados(int n, long semente) {
        List<Integer> lista = new ArrayList<>();
        for (int m : crescentes(n)) {
            lista.add(m);
        }
        Collections.shuffle(lista, new Random(semente));
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = lista.get(i);
        }
        return v;
    }

    // Desenha a árvore "deitada": a raiz fica à esquerda, o filho DIREITO aparece
    // em cima e o filho ESQUERDO embaixo. Cada nível avança 6 colunas.
    static void desenhar(No raiz) {
        if (raiz == null) {
            System.out.println("  (arvore vazia: confira o TODO 1)");
            return;
        }
        desenharRec(raiz, 0);
    }

    static void desenharRec(No no, int nivel) {
        if (no == null) {
            return;
        }
        desenharRec(no.dir, nivel + 1);
        System.out.println("  " + " ".repeat(6 * nivel) + no.matricula);
        desenharRec(no.esq, nivel + 1);
    }

    static boolean verifica(String nome, boolean ok) {
        System.out.println((ok ? "  [OK]     " : "  [FALHOU] ") + nome);
        return ok;
    }

    static boolean testesBasicos() {
        System.out.println("== Testes automaticos ==");
        boolean tudo = true;

        // Árvore do slide: 50, 30, 70, 20, 40 (números fictícios, só para testar)
        No a = construir(new int[] {50, 30, 70, 20, 40});
        List<Integer> saida = new ArrayList<>();
        emOrdem(a, saida);
        tudo &= verifica("inserir + emOrdem: [20, 30, 40, 50, 70]",
                saida.equals(Arrays.asList(20, 30, 40, 50, 70)));
        tudo &= verifica("altura da arvore 50,30,70,20,40 e 2", altura(a) == 2);
        tudo &= verifica("buscar(40) = 3 passos (50, 30, 40)", buscar(a, 40) == 3);
        tudo &= verifica("buscar(99) = -1 (nao existe)", buscar(a, 99) == -1);
        tudo &= verifica("altura da arvore vazia e -1", altura(null) == -1);

        // Cadeia: inserção em ordem crescente
        No b = construir(new int[] {1, 2, 3, 4, 5});
        tudo &= verifica("insercao 1..5 gera altura 4", altura(b) == 4);
        tudo &= verifica("buscar(5) na cadeia = 5 passos", buscar(b, 5) == 5);

        System.out.println(tudo
                ? "Todos os testes passaram. Pode ver os desenhos e o experimento.\n"
                : "Ha testes falhando: corrija os TODOs antes de interpretar o experimento.\n");

        // Bônus (não entra na condição acima)
        boolean bonus = minimo(a) == 20 && maximo(a) == 70 && minimo(null) == -1;
        System.out.println(bonus ? "BONUS: minimo e maximo corretos.\n"
                : "BONUS: nao implementado (ou incorreto).\n");
        return tudo;
    }

    static void desenhos() {
        System.out.println("== Desenhos: as MESMAS 7 matriculas (1 a 7), em ordens diferentes ==");
        System.out.println("(raiz a esquerda; filho direito em cima; filho esquerdo embaixo)\n");
        System.out.println("Ordem de chegada: 1, 2, 3, 4, 5, 6, 7  (crescente)");
        desenhar(construir(new int[] {1, 2, 3, 4, 5, 6, 7}));
        System.out.println("\nOrdem de chegada: 4, 2, 6, 1, 3, 5, 7  (embaralhada)");
        desenhar(construir(new int[] {4, 2, 6, 1, 3, 5, 7}));
        System.out.println();
    }

    static double passosMedios(No raiz, int[] matriculas) {
        long total = 0;
        for (int m : matriculas) {
            total += buscar(raiz, m);
        }
        return (double) total / matriculas.length;
    }

    static void experimento() {
        System.out.println("== Experimento: matriculas CRESCENTES x EMBARALHADAS ==");
        System.out.printf("%-6s | %-22s | %-22s | %-6s%n", "n",
                "CRESCENTES (alt | med)", "EMBARALH. (alt | med)", "log2 n");
        System.out.println("-------+------------------------+------------------------+-------");
        int[] tamanhos = {100, 200, 400};
        for (int n : tamanhos) {
            int[] c = crescentes(n);
            No ac = construir(c);

            int[] e = embaralhados(n, 42);
            No ae = construir(e);

            double lg = Math.log(n) / Math.log(2);
            System.out.printf("%-6d | %8d | %-11.1f | %8d | %-11.1f | %6.1f%n",
                    n, altura(ac), passosMedios(ac, c),
                    altura(ae), passosMedios(ae, e), lg);
        }
        System.out.println("\nalt = altura da arvore; med = passos medios para buscar TODAS as matriculas.");
    }

    public static void main(String[] args) {
        testesBasicos();
        desenhos();
        experimento();
    }
}
