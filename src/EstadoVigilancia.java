/**
 * Estado padrão da Seraphim
 *
 * enquanto estiver em VIGILANCIA,
 * o Seraphim observa o Trailblazer
 *
 * Existem duas transições que podem acontecer:
 *
 * Trailblazer combatendo
 * EstadoSuporte
 *
 * Trailblazer aguardando extração
 * EstadoExtracao
 */
public class EstadoVigilancia
        implements EstadoSeraphim {

    /**
     * executa um ciclo de vigilância.
     *
     * @param seraphim Seraphim executando o estado
     * @param gerenciador responsável pela simulação
     */
    @Override
    public void executar(
            Seraphim seraphim,
            GerenciadorSimulacao gerenciador
    ) {

        System.out.println(
                "[SERAPHIM] Estado atual: VIGILANCIA"
        );


        /*
         * Recuperamos o Trailblazer protegido.
         */
        Trailblazer trailblazer =
                seraphim.getTrailblazer();


        /*
         * PRIMEIRA PRIORIDADE:
         *
         * se o Trailblazer terminou a mineração
         * e está esperando retirada, a Seraphim
         * vai iniciar a extração.
         */
        if (trailblazer.estaAguardandoExtracao()) {

            System.out.println(
                    "[SERAPHIM] Solicitação de extração recebida."
            );

            System.out.println(
                    "[SERAPHIM] Abandonando vigilância."
            );


            seraphim.mudarEstado(
                    new EstadoExtracao()
            );


            return;
        }


        /*
         * SEGUNDA POSSIBILIDADE:
         *
         * Se o Trailblazer entrou em combate
         * e ainda existe uma patrulha,
         * a Seraphim ainda fornece suporte.
         */
        if (trailblazer.estaCombatendo()
                && gerenciador.existePatrulha()) {

            System.out.println(
                    "[SERAPHIM] Trailblazer entrou em combate."
            );

            System.out.println(
                    "[SERAPHIM] Preparando apoio aéreo."
            );


            seraphim.mudarEstado(
                    new EstadoSuporte()
            );


            return;
        }


        /*
         * se nenhuma condição especial existir,
         * a Seraphim continua apenas vigiando.
         */
        System.out.println(
                "[SERAPHIM] Área sob vigilância."
        );

        System.out.println(
                "[SERAPHIM] Nenhuma intervenção necessária."
        );
    }


   //retorna o nome desse estado
    @Override
    public String getNome() {

        return "VIGILANCIA";
    }
}