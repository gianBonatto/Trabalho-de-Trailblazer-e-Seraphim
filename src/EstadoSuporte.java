/**
 * Estado de suporte
 *
 * a Seraphim entra nesse estado quando
 * o Trailblazer está lutando.
 *
 * Enquanto a patrulha não for obliterada (existir),
 * ela fornece cobertura aérea.
 *
 * Quando a ameaça for neutralizada,
 * retorna para EstadoVigilancia.
 */
public class EstadoSuporte
        implements EstadoSeraphim {

   //Executa um ciclo de uporte
    @Override
    public void executar(
            Seraphim seraphim,
            GerenciadorSimulacao gerenciador
    ) {

        System.out.println(
                "[SERAPHIM] Estado atual: SUPORTE"
        );


        Trailblazer trailblazer =
                seraphim.getTrailblazer();


        /*
         * pode acontecer de o Trailblazer terminar
         * o combate antes da Seraphim executar
         * neste ciclo.
         *
         * nesse caso, não existe mais motivo
         * para continuar em suporte, ou seja, Seraphim pode se atrasar
         */
        if (!gerenciador.existePatrulha()) {

            System.out.println(
                    "[SERAPHIM] Patrulha eliminada."
            );

            System.out.println(
                    "[SERAPHIM] Apoio aéreo encerrado."
            );


            seraphim.mudarEstado(
                    new EstadoVigilancia()
            );


            return;
        }


        //Se o Trailblazer parar de combater, Seraphim também para
        if (!trailblazer.estaCombatendo()) {

            System.out.println(
                    "[SERAPHIM] Trailblazer fora de combate."
            );

            System.out.println(
                    "[SERAPHIM] Retornando à vigilância."
            );


            seraphim.mudarEstado(
                    new EstadoVigilancia()
            );


            return;
        }


        //se o combate continuar Seraphim continua na briga até acabar
        System.out.println(
                "[SERAPHIM] Mantendo cobertura aérea."
        );

        System.out.println(
                "[SERAPHIM] Monitorando patrulha inimiga."
        );
    }


   //Retorna o nome desse estado
    @Override
    public String getNome() {

        return "SUPORTE";
    }
}