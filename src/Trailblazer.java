/**
 * Representa o Trailblazer.
 *
 * O Trailblazer funciona como o "Context"
 * dentro do padrão State.
 *
 * - Vida;
 * - Carga;
 * - Um estado atual.
 *
 */
public class Trailblazer {

  //Vida máxima
    private final int vidaMaxima;


    //Vida atual
    private int vidaAtual;


    //quantidade máxima de recursos
    private final int capacidadeCarga;


  //Quantidade de recursos carregada no momento
    private int cargaAtual;


    //estado atual da máquina, um objeto que implementa EstadoTrailblazer
    private EstadoTrailblazer estadoAtual;


   //Construtor do trailblazer (polo de engenharia da R&A kkkkk)
    public Trailblazer(
            int vidaMaxima,
            int capacidadeCarga
    ) {

        this.vidaMaxima = vidaMaxima;

        this.vidaAtual = vidaMaxima;

        this.capacidadeCarga =
                capacidadeCarga;

        this.cargaAtual = 0;


       //Começa a operação minerando
        this.estadoAtual =
                new EstadoMinerando();
    }


    /**
     * Executa o comportamento correspondente
     * ao estado atual.
     *
     * O Trailblazer não precisa verificar:
     * Ele simplesmente manda o objeto de estado
     * executar o comportamento.
     *
     * S T A T E   P A T T E R N
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
     * troca o estado atual do Trailblazer.
     *
     * Todas as mudanças de estado passam
     * por este método.
     *
     * melhora a clareza das impressões no teminal
     */
    public void mudarEstado(
            EstadoTrailblazer novoEstado
    ) {

        System.out.println(
                "[TRAILBLAZER] Estado: "
                        + estadoAtual.getNome()
                        + " -> "
                        + novoEstado.getNome()
        );


        estadoAtual =
                novoEstado;
    }


    //adiciona recursos à carga (quem diria)
    public void adicionarCarga(
            int quantidade
    ) {

        cargaAtual += quantidade;


        //Evita que a capacidade máxima seja excedida
        if (cargaAtual > capacidadeCarga) {

            cargaAtual =
                    capacidadeCarga;
        }


        System.out.println(
                "[TRAILBLAZER] Recursos coletados: "
                        + quantidade
        );

        System.out.println(
                "[TRAILBLAZER] Carga: "
                        + cargaAtual
                        + "/"
                        + capacidadeCarga
        );
    }


    //faz o Trailblazer receber dano
    public void receberDano(
            int dano
    ) {

        vidaAtual -= dano;


        if (vidaAtual < 0) {

            vidaAtual = 0;
        }


        System.out.println(
                "[TRAILBLAZER] Dano recebido: "
                        + dano
        );

        System.out.println(
                "[TRAILBLAZER] Vida: "
                        + vidaAtual
                        + "/"
                        + vidaMaxima
        );
    }


    //Verifica se a capacidade de carga foi atingida ou não
    public boolean cargaCheia() {

        return cargaAtual >= capacidadeCarga;
    }


    //Verifica se o Trailblazer tá vivo ou se virou saudade
    public boolean estaDestruido() {

        return vidaAtual <= 0;
    }


    //retorna o nome do estado atual
    public String getNomeEstadoAtual() {

        return estadoAtual.getNome();
    }


    // GETTERS


    public int getVidaAtual() {

        return vidaAtual;
    }


    public int getVidaMaxima() {

        return vidaMaxima;
    }


    public int getCargaAtual() {

        return cargaAtual;
    }


    public int getCapacidadeCarga() {

        return capacidadeCarga;
    }

    /**
     * Verifica se o Trailblazer está lutando.
     *
     * é utilizado pelo Seraphim para decidir
     * se precisa ajudar ou não.
     *
     * @return true caso esteja no EstadoCombatendo
     */
    public boolean estaCombatendo() {

        return estadoAtual instanceof EstadoCombatendo;
    }


    /**
     * Verifica se o Trailblazer está aguardando extração.
     *
     * é utilizado pro seraphim saber quando deve extrair
     *
     * @return true caso esteja aguardando extração
     */
    public boolean estaAguardandoExtracao() {

        return estadoAtual instanceof EstadoAguardandoExtracao;
    }
}