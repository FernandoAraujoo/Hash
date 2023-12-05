import java.io.*;

public class ArquivoCarros {
    private static final String NOME_ARQUIVO = "carros.dat";
    private static ArquivoCarros instancia;
    private RandomAccessFile arquivo;

    private ArquivoCarros() {
        try {
            arquivo = new RandomAccessFile(NOME_ARQUIVO, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ArquivoCarros obterInstancia() {
        if (instancia == null) {
            instancia = new ArquivoCarros();
        }
        return instancia;
    }

    public int obterPosiçãoInserção() {
        try {
            return (int) arquivo.length();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void escreverRegistro(Veiculo veiculo) {
        try {
            arquivo.seek(arquivo.length());
            arquivo.writeUTF(veiculo.obterPlaca());
            arquivo.writeUTF(veiculo.obterMarca());
            arquivo.writeUTF(veiculo.obterModelo());
            arquivo.writeUTF(veiculo.obterCor());
            arquivo.writeUTF(veiculo.obterMatricula());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Veiculo lerRegistro(int posição) {
        Veiculo veiculo = null;
        try {
            arquivo.seek(posição);
            String placa = arquivo.readUTF();
            String marca = arquivo.readUTF();
            String modelo = arquivo.readUTF();
            String cor = arquivo.readUTF();
            String matricula = arquivo.readUTF();

            veiculo = new Veiculo(placa, marca, modelo, cor, matricula);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return veiculo;
    }
}
