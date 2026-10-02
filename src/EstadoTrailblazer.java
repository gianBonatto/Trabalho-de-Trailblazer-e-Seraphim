/**
 * Interface que representa um estado possível
 * da máquina de estados do Trailblazer, bem
 * parecida com a da Seraphim inclusive
 *
 * todas as classes que representarem um estado
 * do Trailblazer implementam essa interface.
 *
 * EstadoMinerando
 * EstadoCombatendo
 * EstadoAguardandoExtracao
 */
public interface EstadoTrailblazer {

    /**
     * executa o comportamento correspondente
     * ao estado atual do Trailblazer.
     */
    void executar(
            Trailblazer trailblazer,
            GerenciadorSimulacao gerenciador
    );


    //retorna o nome do estado, pra mostrar no terminal
    String getNome();
}
