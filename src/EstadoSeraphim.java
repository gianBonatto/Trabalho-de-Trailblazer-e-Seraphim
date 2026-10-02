/**
 * interface que representa os estados da Seraphim
 *
 * eles são:
 *
 * EstadoVigilancia
 * EstadoSuporte
 * EstadoExtracao
 */
public interface EstadoSeraphim {

    //Executa o corpontamento correspondente ao estado atual
    void executar(
            Seraphim seraphim,
            GerenciadorSimulacao gerenciador
    );


    //Retorna o nome do estado, e é usado pra mostrar no terminal
    String getNome();
}