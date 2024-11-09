import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Telefonia {
    private PrePago[] prePagos;
    private int numPrePagos;
    private PosPago[] posPagos;
    private int numPosPagos;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Telefonia() {
        this.prePagos = new PrePago[100];
        this.numPrePagos = 0;
        this.posPagos = new PosPago[100];
        this.numPosPagos = 0;
    }

    public void cadastrarAssinante() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tipo de assinante (1 para Pré-Pago, 2 para Pós-Pago): ");
        int tipo = scanner.nextInt();

        System.out.print("Digite o CPF: ");
        long cpf = scanner.nextLong();

        scanner.nextLine();

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o número: ");
        int numero = scanner.nextInt();

        if (tipo == 1) {
            if (numPrePagos < prePagos.length) {
                prePagos[numPrePagos++] = new PrePago(cpf, nome, numero);
                System.out.println("Assinante Pré-Pago cadastrado com sucesso!");
            } else {
                System.out.println("Limite de assinantes Pré-Pagos atingido!");
            }
        } else if (tipo == 2) {
            System.out.print("Digite o valor da assinatura: ");
            float assinatura = scanner.nextFloat();

            if (numPosPagos < posPagos.length) {
                posPagos[numPosPagos++] = new PosPago(cpf, nome, numero, assinatura);
                System.out.println("Assinante Pós-Pago cadastrado com sucesso!");
            } else {
                System.out.println("Limite de assinantes Pós-Pagos atingido!");
            }
        } else {
            System.out.println("Tipo de assinante inválido!");
        }
    }

    public void listarAssinante() {
        System.out.println("Assinantes Pré-Pagos:");

        for (int i = 0; i < numPrePagos; i++) {
            System.out.println(prePagos[i].toString());
        }

        System.out.println("Assinantes Pós-Pagos:");

        for (int i = 0; i < numPosPagos; i++) {
            System.out.println(posPagos[i].toString());
        }
    }

    public void fazerChamada() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tipo de assinante (1 para Pré-Pago, 2 para Pós-Pago): ");
        int tipo = scanner.nextInt();

        System.out.print("Digite o CPF do assinante: ");
        long cpf = scanner.nextLong();

        scanner.nextLine();
        System.out.print("Digite a data da chamada (dd/MM/yyyy): ");
        String dataString = scanner.nextLine();

        System.out.print("Digite a duração da chamada em minutos: ");
        int duracao = scanner.nextInt();

        try {
            Date data = dateFormat.parse(dataString);

            if (tipo == 1) {
                PrePago assinante = localizarPrePago(cpf);
                if (assinante != null) {
                    assinante.fazerChamada(data, duracao);
                } else {
                    System.out.println("Assinante Pré-Pago não encontrado!");
                }
            } else if (tipo == 2) {
                PosPago assinante = localizarPosPago(cpf);
                if (assinante != null) {
                    assinante.fazerChamada(data, duracao);
                } else {
                    System.out.println("Assinante Pós-Pago não encontrado!");
                }
            } else {
                System.out.println("Tipo de assinante inválido!");
            }
        } catch (ParseException e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
        }
    }

    public void fazerRecarga() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o CPF do assinante Pré-Pago: ");
        long cpf = scanner.nextLong();

        scanner.nextLine();
        System.out.print("Digite a data da recarga (dd/MM/yyyy): ");
        String dataString = scanner.nextLine();

        System.out.print("Digite o valor da recarga: ");
        float valor = scanner.nextFloat();

        try {
            Date data = dateFormat.parse(dataString);

            PrePago assinante = localizarPrePago(cpf);
            if (assinante != null) {
                assinante.recarregar(data, valor);
            } else {
                System.out.println("Assinante Pré-Pago não encontrado!");
            }
        } catch (ParseException e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
        }
    }

    public void imprimirFaturas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o mês (1 a 12) para imprimir as faturas: ");
        int mes = scanner.nextInt();

        System.out.println("Faturas Pré-Pagos:");
        for (int i = 0; i < numPrePagos; i++) {
            prePagos[i].imprimirFatura(mes);
        }

        System.out.println("Faturas Pós-Pagos:");
        for (int i = 0; i < numPosPagos; i++) {
            posPagos[i].imprimirFatura(mes);
        }
    }

    public PrePago localizarPrePago(long cpf) {
        for (int i = 0; i < numPrePagos; i++) {
            if (prePagos[i].getCpf() == cpf) return prePagos[i];
        }

        return null;
    }

    public PosPago localizarPosPago(long cpf) {
        for (int i = 0; i < numPosPagos; i++) {
            if (posPagos[i].getCpf() == cpf) return posPagos[i];
        }

        return null;
    }

    public static void main(String[] args) {
        Telefonia sistema = new Telefonia();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar assinante");
            System.out.println("2. Listar assinantes");
            System.out.println("3. Fazer chamada");
            System.out.println("4. Fazer recarga");
            System.out.println("5. Imprimir faturas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    sistema.cadastrarAssinante();
                    break;
                case 2:
                    sistema.listarAssinante();
                    break;
                case 3:
                    sistema.fazerChamada();
                    break;
                case 4:
                    sistema.fazerRecarga();
                    break;
                case 5:
                    sistema.imprimirFaturas();
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 6);

        scanner.close();
    }
}
