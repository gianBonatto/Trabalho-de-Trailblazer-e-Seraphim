/**
 *
 * o Main é bem simples
 *
 * - criar os agentes;
 * - criar o gerenciador;
 * - iniciar a simulação.
 */
public class Main {

    public static void main(String[] args) {

        //Cria o o trailblazer
        Trailblazer trailblazer =
                new Trailblazer(
                        100,
                        100
                );


        //Cria a Seraphim e "diz" para ela qual Trailblazer acompanhar
        Seraphim seraphim =
                new Seraphim(
                        trailblazer
                );


        //O gerenciador recebe os dois aqui
        GerenciadorSimulacao gerenciador =
                new GerenciadorSimulacao(
                        trailblazer,
                        seraphim
                );


        //liga o loop
        gerenciador.iniciar();
    }
}