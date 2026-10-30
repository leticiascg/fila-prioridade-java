public class Main {
    static Chamado[] criarChamados() {
        int[] prioridades = {3, 3, 5, 4, 5, 1, 4, 2};
        Chamado[] chamados = new Chamado[prioridades.length];
        for (int i = 0; i < prioridades.length; i++) {
            chamados[i] = new Chamado(i + 1, prioridades[i]);
        }
        return chamados;
    }

    // Item 2.1
    static void insertionSort(Chamado[] v) {
        int deslocamentos = 0;
        for (int i = 1; i < v.length; i++) {
            Chamado chave = v[i];
            int j = i - 1;
            while (j >= 0 && v[j].prioridade < chave.prioridade) {
                v[j + 1] = v[j];
                deslocamentos++;
                j--;
            }
            v[j + 1] = chave;
        }
        System.out.println("Insertion Sort - deslocamentos: " + deslocamentos);
    }

    // Item 2.2
    static void selectionSort(Chamado[] v) {
        int trocas = 0;
        for (int i = 0; i < v.length - 1; i++) {
            int posMaior = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j].prioridade > v[posMaior].prioridade) {
                    posMaior = j;
                }
            }
            if (posMaior != i) {
                Chamado aux = v[i];
                v[i] = v[posMaior];
                v[posMaior] = aux;
                trocas++;
            }
        }
        System.out.println("Selection Sort - trocas: " + trocas);
    }

    static void imprimirIds(String titulo, Chamado[] v) {
        System.out.print(titulo + ": ");
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i].id + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Chamado[] chamados = criarChamados();

        // Parte 1 (item 1.4)
        FilaPrioridade fila = new FilaPrioridade(chamados.length);
        for (int i = 0; i < chamados.length; i++) {
            fila.inserir(chamados[i]);
        }
        System.out.println("Vetor do heap:");
        fila.imprimirVetor();

        System.out.println("Ordem de atendimento:");
        Chamado[] ordemFila = new Chamado[chamados.length];
        int pos = 0;
        Chamado atual = fila.removerMaior();
        while (atual != null) {
            System.out.print(atual + " ");
            ordemFila[pos] = atual;
            pos++;
            atual = fila.removerMaior();
        }
        System.out.println();
        System.out.println();

        // Parte 2
        Chamado[] copiaInsertion = chamados.clone();
        insertionSort(copiaInsertion);

        Chamado[] copiaSelection = chamados.clone();
        selectionSort(copiaSelection);
        System.out.println();

        // Item 2.3
        imprimirIds("Fila (heap)    ", ordemFila);
        imprimirIds("Insertion Sort ", copiaInsertion);
        imprimirIds("Selection Sort ", copiaSelection);

        /*
         * Item 2.3 - chamados de mesma prioridade fora da ordem de chegada:
         *
         * Fila (heap): nenhum. Como o desempate é pelo menor id, a ordem de
         * chegada sempre é respeitada.
         *
         * Insertion Sort: nenhum. Ele só desloca elementos com prioridade
         * estritamente menor, então os iguais nunca passam um na frente do
         * outro (é estável).
         *
         * Selection Sort: os chamados #2 e #1 (os dois de prioridade 3).
         * O #2 ficou antes do #1, mesmo o #1 tendo chegado primeiro. Isso
         * acontece porque as trocas à distância jogam o #1 para outra
         * posição e ele acaba atrás do #2 (é instável).
         */
    }
}