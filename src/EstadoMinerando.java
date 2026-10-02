/**
 * Estado responsável pelo comportamento de mineração
 * do Trailblazer.
 *
 * - Verifica se existe uma patrulha;
 * - se existe, muda para EstadoCombatendo;
 * - Caso contrário, coleta recursos;
 * - Se a carga atingir o máximo, muda para
 *   EstadoAguardandoExtracao.
 */
public class EstadoMinerando implements EstadoTrailblazer {

    /**
     * Executa o comportamento do estado MINERANDO.
     *
     * @param trailblazer Trailblazer que está executando o estado
     * @param gerenciador responsável pela simulação
     */
    @Override
    public void executar(
            Trailblazer trailblazer,
            GerenciadorSimulacao gerenciador
    ) {

        System.out.println(
                "[TRAILBLAZER] Estado atual: MINERANDO"
        );


        /*
         * PRIMEIRA VERIFICAÇÃO:
         *
         * Existe uma patrulha bloqueando a operação?
         *
         * Se existir, o Trailblazer interrompe a mineração
         * e muda para combate.
         */
        if (gerenciador.existePatrulha()) {

            System.out.println(
                    "[TRAILBLAZER] Patrulha detectada."
            );

            System.out.println(
                    "[TRAILBLAZER] Interrompendo mineração."
            );


            trailblazer.mudarEstado(
                    new EstadoCombatendo()
            );


            /*
             * O return encerra a execução deste estado
             * neste ciclo.
             *
             * O EstadoCombatendo é executado
             * só no próximo ciclo.
             */
            return;
        }


        /*
         * se não existir ameaça, o Trailblazer
         * continua fazendo a parada dele furar chão e pegar minério.
         */
        System.out.println(
                "[TRAILBLAZER] Extraindo recursos..."
        );


       //cada ciclo de mineração fornece exatamente 25 unidades
        trailblazer.adicionarCarga(
                25
        );


        // Depois de minerar ele verifica se a capacidade máxima foi atingida ou não
        if (trailblazer.cargaCheia()) {

            System.out.println(
                    "[TRAILBLAZER] Capacidade máxima atingida."
            );

            System.out.println(
                    "[TRAILBLAZER] Solicitando extração."
            );


            trailblazer.mudarEstado(
                    new EstadoAguardandoExtracao()
            );
        }
    }


    //Retorna o nome desse estado pra mostrar no terminal
    @Override
    public String getNome() {

        return "MINERANDO";
    }
}