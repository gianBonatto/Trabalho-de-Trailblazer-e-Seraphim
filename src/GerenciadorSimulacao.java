import java.util.Scanner;

/**
 * Classe que gerencia a execução da simulação.
 *
 * Esta classe tem o loop principal das máquinas de estados.
 *
 * ela tem como função:
 *
 * - Controlar os ciclos;
 * - Atualizar o ambiente;
 * - Gerenciar a patrulha inimiga;
 * - Executar o Trailblazer;
 * - Executar o Seraphim;
 * - Resolver o combate;
 * - Exibir os logs;
 * - Encerrar a operação após a extração.
 *
 * O Gerenciador não decide os estados dos agentes.
 *
 * Cada agente possui seu próprio objeto de estado,
 * seguindo o padrão State.
 */
public class GerenciadorSimulacao {

    //Primeiro agente
    private final Trailblazer trailblazer;


    //Segundo agente
    private final Seraphim seraphim;


    //número do ciclo(rodada, turno)
    private int ciclo;


  //quantidade de inimigos, se for zero, não tem patrulha
    private int inimigosPatrulha;


    //Controla o loop principal
    private boolean simulacaoEncerrada;


    /**
     * Construtor do GerenciadorSimulacao.
     *
     * recebe os dois agentes
     */
    public GerenciadorSimulacao(
            Trailblazer trailblazer,
            Seraphim seraphim
    ) {

        //impede que a simulação seja criada sem um dos agentes
        if (trailblazer == null) {

            throw new IllegalArgumentException(
                    "O Gerenciador precisa de um Trailblazer."
            );
        }


        if (seraphim == null) {

            throw new IllegalArgumentException(
                    "O Gerenciador precisa de um Seraphim."
            );
        }


        this.trailblazer =
                trailblazer;

        this.seraphim =
                seraphim;


        //Começa no ciclo zero
        this.ciclo = 0;


        //E nenhuma patrulha deveria existir logo de cara (mas isso já aconteceu se eu não me engano)
        this.inimigosPatrulha = 0;


        //a simulação começa ativa
        this.simulacaoEncerrada = false;
    }


    /**
     * Inicia o loop principal da simulação.
     * 1 = executa um ciclo
     * 0 = encerra manualmente
     */
    public void iniciar() {

        Scanner scanner =
                new Scanner(System.in);


        exibirCabecalho();


        /*
         * ======================================================
         * LOOP PRINCIPAL
         * ======================================================
         *
         * é o loop responsável por executar
         * as duas máquinas de estados.
         */
        while (!simulacaoEncerrada) {

            System.out.println();

            System.out.println(
                    "[1] Avançar ciclo"
            );

            System.out.println(
                    "[0] Encerrar simulação"
            );

            System.out.print(
                    "Comando: "
            );


            String entrada =
                    scanner.nextLine();


            //Encerramento manual
            if (entrada.equals("0")) {

                simulacaoEncerrada = true;

                System.out.println();

                System.out.println(
                        "[SIMULAÇÃO] Encerrada pelo usuário."
                );

                continue;
            }


            //entrada inválida
            if (!entrada.equals("1")) {

                System.out.println(
                        "[ERRO] Digite 1 para avançar ou 0 para sair."
                );

                continue;
            }


            //executa exatamente um ciclo
            executarCiclo();
        }


        scanner.close();
    }


    /**
     * Executa um ciclo completo da simulação.
     *
     * 1 - Atualiza o número do ciclo;
     * 2 - Atualiza o ambiente;
     * 3 - Executa o estado do Trailblazer;
     * 4 - Executa o estado do Seraphim;
     * 5 - Exibe o resumo;
     * 6 - Verifica se a operação terminou.
     */
    private void executarCiclo() {

        ciclo++;


        System.out.println();
        System.out.println(
                "=================================================="
        );

        System.out.println(
                "                    CICLO " + ciclo
        );

        System.out.println(
                "=================================================="
        );


        //o mundo é atualizado primeiro
        atualizarAmbiente();


        /*
         * ======================================================
         * AGENTE A - TRAILBLAZER
         * ======================================================
         */

        System.out.println();
        System.out.println(
                "--------------- TRAILBLAZER ----------------"
        );


        /*
         * O Gerenciador não precisa saber
         * qual estado está ativo.
         *
         * O Trailblazer delega a execução
         * para o objeto EstadoTrailblazer.
         */
        trailblazer.executarEstado(
                this
        );


        /*
         * ======================================================
         * AGENTE B - SERAPHIM
         * ======================================================
         */

        System.out.println();
        System.out.println(
                "----------------- SERAPHIM -----------------"
        );


        /*
         * Guarda esta informação ANTES
         * de executar o Seraphim.
         *
         * Isso é importante porque:
         *
         * VIGILANCIA -> EXTRACAO
         *
         * é apenas só uma troca de estado.
         *
         * O comportamento do EstadoExtracao
         * só é executado no próximo ciclo.
         */
        boolean jaEstavaEmExtracao =
                seraphim.estaEmExtracao();


        /*
         * a Seraphim também delega a execução
         * para o objeto EstadoSeraphim.
         */
        seraphim.executarEstado(
                this
        );


        //mostra o resultado do ciclo
        exibirResumo();


        /*
         * ======================================================
         * FIM DA OPERAÇÃO
         * ======================================================
         *
         * Se a Seraphim já estava no EstadoExtracao
         * antes deste ciclo, significa que neste ciclo
         * o comportamento de extração foi executado.
         *
         * e então pode finalizar a execução.
         */
        if (jaEstavaEmExtracao) {

            System.out.println();
            System.out.println(
                    "=================================================="
            );

            System.out.println(
                    "             OPERAÇÃO FINALIZADA"
            );

            System.out.println(
                    "=================================================="
            );

            System.out.println(
                    "[SIMULAÇÃO] Trailblazer recuperado."
            );

            System.out.println(
                    "[SIMULAÇÃO] Carga recuperada: "
                            + trailblazer.getCargaAtual()
                            + "/"
                            + trailblazer.getCapacidadeCarga()
            );

            System.out.println(
                    "[SIMULAÇÃO] Ciclos executados: "
                            + ciclo
            );

            System.out.println(
                    "=================================================="
            );


            simulacaoEncerrada = true;
        }
    }


    /**
     * Atualiza os eventos do ambiente.
     *
     * - Uma patrulha aparece a cada 3 ciclos;
     * - A patrulha sempre possui 3 inimigos.
     *
     * Exemplos:
     *
     * Ciclo 3
     * Ciclo 6
     * Ciclo 9
     *
     * Desde que não exista outra patrulha ativa.
     */
    private void atualizarAmbiente() {

        //descobre se o ciclo é um múltiplo de três
        boolean momentoDaPatrulha =
                ciclo % 3 == 0;


        /*
         * A patrulha só aparece se:
         *
         * - É o momento certo;
         * - Não existe outra patrulha;
         * - o Trailblazer não terminou a minerar.
         */
        if (momentoDaPatrulha
                && !existePatrulha()
                && !trailblazer.estaAguardandoExtracao()) {

            inimigosPatrulha = 3;


            System.out.println();

            System.out.println(
                    "[AMBIENTE] Uma patrulha inimiga apareceu."
            );

            System.out.println(
                    "[AMBIENTE] Inimigos detectados: "
                            + inimigosPatrulha
            );
        }
    }


    /**
     * Verifica se tem uma patrulha ativa.
     *
     * Esse metodo pode ser consultado pelos estados
     * dos dois agentes.
     *
     * @return true caso tenham inimigos
     */
    public boolean existePatrulha() {

        return inimigosPatrulha > 0;
    }


    /**
     * Resolve um ciclo de combate do Trailblazer.
     *
     * - Elimina 1 inimigo por ciclo;
     * - Trailblazer recebe 5 de dano.
     *
     * @param trailblazer veículo que está combatendo
     */
    public void resolverCombateTrailblazer(
            Trailblazer trailblazer
    ) {

        //Não existe combate sem que hajam patrulhas inimigas
        if (!existePatrulha()) {

            System.out.println(
                    "[COMBATE] Nenhuma patrulha ativa."
            );

            return;
        }


        System.out.println();

        System.out.println(
                "[COMBATE] Trailblazer atacando patrulha."
        );


       //inimigo eliminado
        inimigosPatrulha--;


        System.out.println(
                "[COMBATE] Um inimigo foi eliminado."
        );


        //Trailblazer levando dano
        trailblazer.receberDano(
                5
        );


        System.out.println(
                "[COMBATE] Inimigos restantes: "
                        + inimigosPatrulha
        );


        //se não tiver sobrado ninguém, a patrulha inimiga foi neutralizada
        if (inimigosPatrulha == 0) {

            System.out.println(
                    "[COMBATE] Patrulha completamente neutralizada."
            );
        }
    }


    //Resumo final de cada ciclo mostrando os estados dos dois agentes
    private void exibirResumo() {

        System.out.println();
        System.out.println(
                "================ RESUMO ================="
        );


        System.out.println(
                "Ciclo:              "
                        + ciclo
        );


        System.out.println();


        //Trailblazer

        System.out.println(
                "Trailblazer:"
        );

        System.out.println(
                "  Estado:           "
                        + trailblazer.getNomeEstadoAtual()
        );

        System.out.println(
                "  Vida:             "
                        + trailblazer.getVidaAtual()
                        + "/"
                        + trailblazer.getVidaMaxima()
        );

        System.out.println(
                "  Carga:            "
                        + trailblazer.getCargaAtual()
                        + "/"
                        + trailblazer.getCapacidadeCarga()
        );


        System.out.println();


        //Seraphim

        System.out.println(
                "Seraphim:"
        );

        System.out.println(
                "  Estado:           "
                        + seraphim.getNomeEstadoAtual()
        );


        System.out.println();


        //Ambiente

        System.out.println(
                "Ambiente:"
        );

        System.out.println(
                "  Inimigos:         "
                        + inimigosPatrulha
        );


        System.out.println(
                "=========================================="
        );
    }


    //Cabeçalho inicial
    private void exibirCabecalho() {

        System.out.println();

        System.out.println(
                "=================================================="
        );

        System.out.println(
                "     RAYMOND & ABRAMOVICH - SIMULAÇÃO"
        );

        System.out.println(
                "=================================================="
        );

        System.out.println(
                "Modo observador."
        );

        System.out.println(
                "Dois agentes utilizando o padrão State."
        );

        System.out.println();

        System.out.println(
                "Digite 1 para executar um ciclo."
        );
    }


   //retorna o ciclo atual
    public int getCiclo() {

        return ciclo;
    }


    //retorna quantidade de inimigos na patrulha
    public int getInimigosPatrulha() {

        return inimigosPatrulha;
    }
}