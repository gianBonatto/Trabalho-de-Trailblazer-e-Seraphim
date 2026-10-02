/**
 * Representa aSeraphim.
 *
 * Dentro do padrão State, o Seraphim funciona
 * como o "Context".
 *
 * Ela tem um EstadoSeraphim atual e delega
 * o comportamento dele para esse objeto
 *
 * a Seraphim também tem uma referência ao
 * Trailblazer que deve protege, pq se não, não ia conseguir
 */
public class Seraphim {

    //refere ao Trailblazer que a Seraphim deve proteger
    private final Trailblazer trailblazer;


    //Estado atual da máquina de estados
    private EstadoSeraphim estadoAtual;


    /**
     * Construtor
     *
     * a Seraphim precisa saber qual Trailblazer
     * ela tá protegendo.
     *
     * A operação começa em VIGILANCIA.
     *
     *
     */
    public Seraphim(
            Trailblazer trailblazer
    ) {

        if (trailblazer == null) {

            throw new IllegalArgumentException(
                    "Seraphim precisa de um Trailblazer."
            );
        }


        this.trailblazer =
                trailblazer;


        /*
         * Estado inicial.
         */
        this.estadoAtual =
                new EstadoVigilancia();
    }


    /**
     * executa o comportamento correspondente
     * ao estado atual.
     *
     * O objeto de estado executa seu
     * próprio comportamento.
     *
     */
    public void executarEstado(
            GerenciadorSimulacao gerenciador
    ) {

        estadoAtual.executar(
                this,
                gerenciador
        );
    }


    /**
     * muda o estado atual da Seraphim.
     *
     * Todas as trocas passam por esse metodo aqui,
     * para o usuário conseguir visualizar claramente as
     * transições no console.
     *
     */
    public void mudarEstado(
            EstadoSeraphim novoEstado
    ) {

        System.out.println(
                "[SERAPHIM] Estado: "
                        + estadoAtual.getNome()
                        + " -> "
                        + novoEstado.getNome()
        );


        estadoAtual =
                novoEstado;
    }


    //retorna o nome do estado atual pra usar no resumo da simulação
    public String getNomeEstadoAtual() {

        return estadoAtual.getNome();
    }


   //verifica se a Seraphim está em modo de suporte, se sim retorna verdadeiro
    public boolean estaEmSuporte() {

        return estadoAtual instanceof EstadoSuporte;
    }


    //parecido com o último, verifica se a Seraphim está realizando a extração, se sim, retorna verdadeiro
    public boolean estaEmExtracao() {

        return estadoAtual instanceof EstadoExtracao;
    }


    //retorna o trailblazer protegido
    public Trailblazer getTrailblazer() {

        return trailblazer;
    }
}