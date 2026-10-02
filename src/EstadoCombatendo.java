/**
 * Estado responsável pelo combate do Trailblazer.
 *
 * O Trailblazer entra neste estado quando tem
 * uma patrulha impedindo ele de minerar.
 *
 * - Ataca a patrulha;
 * - O GerenciadorSimulacao resolve o combate;
 * - Quando a patrulha for eliminada,
 *   o Trailblazer volta para EstadoMinerando.
 */
public class EstadoCombatendo implements EstadoTrailblazer {

    /**
     * Executa um ciclo de combate.
     *
     * @param trailblazer Trailblazer em combate
     * @param gerenciador responsável pela simulação
     */
    @Override
    public void executar(
            Trailblazer trailblazer,
            GerenciadorSimulacao gerenciador
    ) {

        System.out.println(
                "[TRAILBLAZER] Estado atual: COMBATENDO"
        );


        /*
         * Pode acontecer de a patrulha já ter sido
         * eliminada pelo Seraphim.
         *
         * Nesse caso não existe motivo para continuar em combate
         */
        if (!gerenciador.existePatrulha()) {

            System.out.println(
                    "[TRAILBLAZER] Área livre."
            );

            System.out.println(
                    "[TRAILBLAZER] Retomando mineração."
            );


            trailblazer.mudarEstado(
                    new EstadoMinerando()
            );


            return;
        }


        /*
         * Caso ainda existam inimigos,
         * o Trailblazer realiza exerce seu direito de legítima defesa.
         *
         * O GerenciadorSimulacao será responsável
         * por modificar a quantidade de inimigos
         */
        System.out.println(
                "[TRAILBLAZER] Disparando contra a patrulha."
        );


        gerenciador.resolverCombateTrailblazer(
                trailblazer
        );


        /*
         * Depois do combate, verificamos novamente
         * se ainda se ainda sobrou uma patrulha pra contar a história.
         */
        if (!gerenciador.existePatrulha()) {

            System.out.println(
                    "[TRAILBLAZER] Patrulha eliminada."
            );

            System.out.println(
                    "[TRAILBLAZER] Retornando à operação de mineração."
            );


            trailblazer.mudarEstado(
                    new EstadoMinerando()
            );
        }
    }


    //retorna o nome do estado
    @Override
    public String getNome() {

        return "COMBATENDO";
    }
}