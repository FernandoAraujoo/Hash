import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TabelaHash tabelaHash = new TabelaHash();

        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar novo carro");
            System.out.println("2. Exibir dados de um carro");
            System.out.println("3. Listar carros cadastrados");
            System.out.println("4. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarCarro(tabelaHash);
                    break;
                case 2:
                    exibirDadosCarro(tabelaHash);
                    break;
                case 3:
                    listarCarrosCadastrados(tabelaHash);
                    break;
                case 4:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void cadastrarCarro(TabelaHash tabelaHash) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a placa do carro:");
        String placa = scanner.next();

        if (tabelaHash.busca(placa) == -1) {
            System.out.println("Digite a marca do carro:");
            String marca = scanner.next();
            System.out.println("Digite o modelo do carro:");
            String modelo = scanner.next();
            System.out.println("Digite a cor do carro:");
            String cor = scanner.next();
            System.out.println("Digite a matrícula do proprietário:");
            String matricula = scanner.next();

            Veiculo veiculo = new Veiculo(placa, marca, modelo, cor, matricula);
            tabelaHash.inserirVeiculo(veiculo);

            System.out.println("Carro cadastrado com sucesso!");
        } else {
            System.out.println("A placa já existe no cadastro. Cadastro não efetuado.");
        }
    }

    private static void exibirDadosCarro(TabelaHash tabelaHash) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a placa do carro:");
        String placa = scanner.next();

        int posicao = tabelaHash.busca(placa);
        if (posicao != -1) {
            tabelaHash.exibirDados(placa);
        } else {
            System.out.println("A placa fornecida não existe no cadastro.");
        }
    }

    private static void listarCarrosCadastrados(TabelaHash tabelaHash) {
        tabelaHash.listarArquivo();
    }
}
