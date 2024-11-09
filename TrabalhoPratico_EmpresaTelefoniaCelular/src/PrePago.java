import java.util.Date;

public class PrePago extends Assinante {
    private float creditos;
    private Recarga[] recargas;
    private int numRecargas;
    private static final float CUSTO_POR_MINUTO = 1.45f;

    public PrePago(long cpf, String nome, int numero) {
        super(cpf, nome, numero);
        this.creditos = 0.0f;
        this.recargas = new Recarga[100];
        this.numRecargas = 0;
    }

    public void fazerChamada(Date data, int duracao) {
        float custoChamada = duracao * CUSTO_POR_MINUTO;

        if (numChamadas < chamadas.length && creditos >= custoChamada) {
            creditos -= custoChamada;
            chamadas[numChamadas++] = new Chamada(data, duracao);
            System.out.println("Chamada realizada com sucesso!");
        } else if (creditos < custoChamada) {
            System.out.println("Créditos insuficientes para realizar a chamada!");
        } else {
            System.out.println("Limite de chamadas atingido!");
        }
    }

    public void recarregar(Date data, float valor) {
        if (numRecargas < recargas.length) {
            recargas[numRecargas++] = new Recarga(data, valor);
            creditos += valor;
            System.out.println("Recarga realizada com sucesso!");
        } else {
            System.out.println("Limite de recargas atingido.");
        }
    }

    public void imprimirFatura(int mes) {
        System.out.println("Fatura do Assinante Pré-Pago:");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + nome);
        System.out.println("Número: " + numero);

        float totalChamadas = 0;
        System.out.println("Chamadas realizadas no mês " + mes + ":");

        for (int i = 0; i < numChamadas; i++) {
            Chamada chamada = chamadas[i];
            if (chamada.getData().getMonth() + 1 == mes) {
                System.out.println(chamada);
                totalChamadas += chamada.getDuracao() * CUSTO_POR_MINUTO;
            }
        }

        float totalRecargas = 0;
        System.out.println("Recargas realizadas no mês " + mes + ":");

        for (int i = 0; i < numRecargas; i++) {
            Recarga recarga = recargas[i];
            if (recarga.getData().getMonth() + 1 == mes) {
                System.out.println(recarga);
                totalRecargas += recarga.getValor();
            }
        }

        System.out.println("Total de chamadas: R$ " + totalChamadas);
        System.out.println("Total de recargas: R$ " + totalRecargas);
        System.out.println("Créditos restantes: R$ " + creditos);
    }
}
