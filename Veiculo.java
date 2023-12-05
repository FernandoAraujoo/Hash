import java.io.Serializable;

public class Veiculo implements Serializable {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private String matricula;

    public Veiculo(String placa, String marca, String modelo, String cor, String matricula) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.matricula = matricula;
    }

    public String obterPlaca() {
        return placa;
    }

    public String obterMarca() {
        return marca;
    }

    public String obterModelo() {
        return modelo;
    }

    public String obterCor() {
        return cor;
    }

    public String obterMatricula() {
        return matricula;
    }
}
