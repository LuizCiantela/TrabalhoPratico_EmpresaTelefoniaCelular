import java.util.Date;

public class PosPago extends Assinante {
    private float assinatura;
    private static final float CUSTO_POR_MINUTO = 1.04f;

    public PosPago(long cpf, String nome, int numero, float assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    public void fazerChamada(Date data, int duracao) {
        if (numChamadas < chamadas.length) {
            chamadas[numChamadas++] = new Chamada(data, duracao);
            System.out.println("Chamada realizada com sucesso!");
        } else {
            System.out.println("Limite de chamadas atingido.");
        }
    }

    public void imprimirFatura(int mes) {
        System.out.println("Fatura do Assinante Pós-Pago:");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + nome);
        System.out.println("Número: " + numero);
        System.out.println("Assinatura: R$ " + assinatura);

        float totalChamadas = 0;
        System.out.println("Chamadas realizadas no mês " + mes + ":");

        for (int i = 0; i < numChamadas; i++) {
            Chamada chamada = chamadas[i];
            if (chamada.getData().getMonth() + 1 == mes) {
                System.out.println(chamada);
                totalChamadas += chamada.getDuracao() * CUSTO_POR_MINUTO;
            }
        }

        float totalFatura = assinatura + totalChamadas;
        System.out.println("Total de chamadas: R$ " + totalChamadas);
        System.out.println("Total da fatura: R$ " + totalFatura);
    }
}
