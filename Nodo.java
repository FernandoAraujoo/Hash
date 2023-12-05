import java.io.Serializable;

public class Nodo implements Serializable {
    public String chave;
    public int posição;
    public int status; // 0 – livre, 1 – em uso
    public int próximo;

    public Nodo() {
        this.status = 0;
        this.próximo = -1;
    }
}
