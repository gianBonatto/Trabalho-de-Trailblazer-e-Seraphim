/**
 * Estado responsável pela extração
 * do Trailblazer.
 *
 * a Seraphim entra neste estado quando
 * o Trailblazer termina sua operação
 * de mineração e solicita retirada.
 *
 * esse é o estado final da missão.
 */
public class EstadoExtracao
        implements EstadoSeraphim {

    /**
     * faz o procedimento de extração.
     *
     *
     * O GerenciadorSimulacao é responsável
     * por detectar que o Seraphim chegou
     * nesse estado também e encerrar a missão.
     *
     */
    @Override
    public void executar(
            Seraphim seraphim,
            GerenciadorSimulacao gerenciador
    ) {

        Trailblazer trailblazer =
                seraphim.getTrailblazer();


        System.out.println(
                "[SERAPHIM] Estado atual: EXTRACAO"
        );


        System.out.println(
                "[SERAPHIM] Aproximando-se do Trailblazer."
        );


        System.out.println(
                "[SERAPHIM] Realizando procedimento de retirada."
        );


        System.out.println(
                "[SERAPHIM] Carga recuperada: "
                        + trailblazer.getCargaAtual()
                        + "/"
                        + trailblazer.getCapacidadeCarga()
        );


        System.out.println(
                "[SERAPHIM] Extração concluída."
        );
    }


    //retorna o nome do estado
    @Override
    public String getNome() {

        return "EXTRACAO";
    }
}