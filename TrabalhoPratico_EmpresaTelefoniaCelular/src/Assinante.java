import java.util.Date;

public abstract class Assinante {
    protected long cpf;
    protected String nome;
    protected int numero;
    protected Chamada[] chamadas;
    protected int numChamadas;

    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.chamadas = new Chamada[100];
        this.numChamadas = 0;
    }

    public long getCpf() {
        return cpf;
    }

    public String toString() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Número: " + numero;
    }
}
