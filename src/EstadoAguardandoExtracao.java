/**
 * Estado final da operação do Trailblazer.
 *
 * O Trailblazer entra neste estado quando ele
 * já coletou tudo que podia coletar
 *
 * A partir daqui ele só espera a seraphim vir buscar ele
 */
public class EstadoAguardandoExtracao
        implements EstadoTrailblazer {

    /**
     * Executa o comportamento do estado.
     *
     * Como o Trailblazer já terminou a parada dele,
     * não tem nenhuma ação complexa aqui.
     *
     * Ele só fica aguardando.
     *
     * @param trailblazer Trailblazer esperando extração
     * @param gerenciador responsável pela simulação
     */
    @Override
    public void executar(
            Trailblazer trailblazer,
            GerenciadorSimulacao gerenciador
    ) {

        System.out.println(
                "[TRAILBLAZER] Estado atual: AGUARDANDO EXTRAÇÃO"
        );

        System.out.println(
                "[TRAILBLAZER] Mineração encerrada."
        );

        System.out.println(
                "[TRAILBLAZER] Aguardando aproximação do Seraphim."
        );
    }


    // retorna o nome do estado
    @Override
    public String getNome() {

        return "AGUARDANDO EXTRACAO";
    }
}