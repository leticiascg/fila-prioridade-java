public class FilaPrioridade {
    private Chamado[] heap;
    private int tamanho;

    public FilaPrioridade(int capacidade) {
        heap = new Chamado[capacidade];
        tamanho = 0;
    }

    // Item 1.1
    private boolean temPrioridadeSobre(Chamado a, Chamado b) {
        if (a.prioridade != b.prioridade) {
            return a.prioridade > b.prioridade;
        }
        return a.id < b.id;
    }

    private void trocar(int i, int j) {
        Chamado aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    // Item 1.2
    public void inserir(Chamado c) {
        if (tamanho == heap.length) {
            System.out.println("Fila cheia! Chamado " + c + " não foi inserido.");
            return;
        }
        heap[tamanho] = c;
        subir(tamanho);
        tamanho++;
    }

    private void subir(int i) {
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (temPrioridadeSobre(heap[i], heap[pai])) {
                trocar(i, pai);
                i = pai;
            } else {
                break;
            }
        }
    }

    // Item 1.3
    public Chamado removerMaior() {
        if (tamanho == 0) {
            return null;
        }
        Chamado maior = heap[0];
        heap[0] = heap[tamanho - 1];
        heap[tamanho - 1] = null;
        tamanho--;
        if (tamanho > 0) {
            descer(0);
        }
        return maior;
    }

    private void descer(int i) {
        while (true) {
            int esq = 2 * i + 1;
            int dir = 2 * i + 2;
            int maior = i;

            if (esq < tamanho && temPrioridadeSobre(heap[esq], heap[maior])) {
                maior = esq;
            }
            if (dir < tamanho && temPrioridadeSobre(heap[dir], heap[maior])) {
                maior = dir;
            }

            if (maior == i) {
                break;
            }
            trocar(i, maior);
            i = maior;
        }
    }

    public void imprimirVetor() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}