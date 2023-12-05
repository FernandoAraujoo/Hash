

public class TabelaHash {
    private static final int N = 101; // número primo não próximo de uma potência de 2
    private Nodo[] vetorHash;

    public TabelaHash() {
        vetorHash = new Nodo[N];
        for (int i = 0; i < N; i++) {
            vetorHash[i] = new Nodo();
        }
    }

    public int funçãoHash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = hash + (((int) chave.charAt(i) << i % 8));
        }
        return (hash % N);
    }

    public int busca(String placa) {
        int posiçãoPlaca = funçãoHash(placa);
        while (!vetorHash[posiçãoPlaca].chave.equals(placa)) {
            posiçãoPlaca = vetorHash[posiçãoPlaca].próximo;
            if (posiçãoPlaca == -1) {
                return -1;
            }
        }
        return posiçãoPlaca;
    }

    public void inserirVeiculo(Veiculo veiculo) {
        String chave = veiculo.obterPlaca();
        int hash = funçãoHash(chave);

        if (busca(chave) == -1) {
            if (vetorHash[hash].status == 0) {
                vetorHash[hash].chave = chave;
                vetorHash[hash].posição = ArquivoCarros.obterInstancia().obterPosiçãoInserção();
                vetorHash[hash].status = 1;
                vetorHash[hash].próximo = -1;
            } else {
                int posiçãoZonaSinônimo = encontrarPróximaPosiçãoZonaSinônimo(hash);
                vetorHash[últimaPosiçãoHash(hash)].próximo = posiçãoZonaSinônimo;
                vetorHash[posiçãoZonaSinônimo].chave = chave;
                vetorHash[posiçãoZonaSinônimo].posição = ArquivoCarros.obterInstancia().obterPosiçãoInserção();
                vetorHash[posiçãoZonaSinônimo].status = 1;
                vetorHash[posiçãoZonaSinônimo].próximo = -1;
            }

            ArquivoCarros.obterInstancia().escreverRegistro(veiculo);
        }
    }

    public int encontrarPróximaPosiçãoZonaSinônimo(int hash) {
        int posiçãoAtual = hash;
        if (vetorHash[posiçãoAtual].próximo == -1) {
            return N;
        } else {
            while (vetorHash[posiçãoAtual].próximo != -1) {
                posiçãoAtual = vetorHash[posiçãoAtual].próximo;
            }
            while (vetorHash[posiçãoAtual].status != 0) {
                posiçãoAtual++;
            }
            return posiçãoAtual;
        }
    }

    public int últimaPosiçãoHash(int posiçãoAtual) {
        while (vetorHash[posiçãoAtual].próximo != -1) {
            if (vetorHash[posiçãoAtual].próximo == -1) {
                return posiçãoAtual;
            }
        }
        return posiçãoAtual;
    }

    public void exibirDados(String placa) {
        int posiçãoPlaca = busca(placa);
        if (posiçãoPlaca != -1) {
            Veiculo veiculo = ArquivoCarros.obterInstancia().lerRegistro(posiçãoPlaca);
            System.out.println("Modelo: " + veiculo.obterModelo());
            System.out.println("Placa: " + veiculo.obterPlaca());
            System.out.println("Cor: " + veiculo.obterCor());
            System.out.println("Matrícula do Proprietário: " + veiculo.obterMatricula());
        }
    }

    public void listarArquivo() {
        for (int i = 0; i < N; i++) {
            if (vetorHash[i].status == 1) {
                int posiçãoCarro = vetorHash[i].posição;
                Veiculo veiculo = ArquivoCarros.obterInstancia().lerRegistro(posiçãoCarro);
                System.out.println("Modelo: " + veiculo.obterModelo());
                System.out.println("Placa: " + veiculo.obterPlaca());
                System.out.println("Cor: " + veiculo.obterCor());
                System.out.println("Matrícula do Proprietário: " + veiculo.obterMatricula());
                System.out.println("----------");
            }
        }
    }
}
