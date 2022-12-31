package Classes;

/**
 * Classe abstrata de todas as entidades que atiram; engloba a nave do usuário e
 * os aliens inimigos. Possuem o atributo Tipo pois a nave e os aliens
 * apresentam diferentes tipos
 *
 * @author pedro
 */
public abstract class Atirador extends Entidade {

    /**
     * Atributo que define qual é o tipo de nave ou inimigo
     */
    private int Tipo;

    /**
     * Atributo relacionado à quantidade de vida que o tiro da entidade remove
     * de outra entidade
     */
    private int Dano;

    /**
     * Tempo fixo relacionado à cada tipo de atirador e que define o tempo que
     * este demora para poder dar um novo tiro após realizar um disparo. Não é
     * do tipo final pois só é inicializada após a chamada do super na classe
     * "Nave", já que passa pelo switch case das naves pois cada uma delas tem
     * um tempo de recarga diferente.
     */
    private int ReloadTime;


    /**
     * Construtor da classe atirador.Passa os atributos comuns das entidades e
     * seta o dano, particular da classe dos atiradores.
     *
     * @param x posição horizontal da entidade na tela do jogo
     * @param y posição vertical da entidade na tela do jogo
     * @param vida quantidade de vida que a entidade tem
     * @param dano quantidade de vida que o tiro da entidade remove de outra
     * entidade quando atinge-na
     * @param tipo inteiro que define o tipo do atirador
     */
    protected Atirador(int x, int y, int vida, int dano, int tipo) {
        super(x, y, vida);
        this.Dano = dano;
        this.Tipo = tipo;
    }

    /**
     * Método abstrato de atirar que deve ser configurado pelas entidades pois
     * varia entre cada um dos tipos de atiradores.
     *
     * @return retorna um Tiro relacionado à entidade
     */
    protected abstract Tiro Atirar();

    /**
     * Método que define o tempo mínimo para que uma entidade atiradora possa atirar
     * novamente após um tiro
     * @param i inteiro relacionado ao tempo de recarga
     */
    protected void setReloadTime(int i) {
        this.ReloadTime = i;
    }

    /**
     * Método que retorna o tempo mínimo para que uma entidade atiradora possa atirar
     * novamente após um tiro.
     * @return retorna o tempo de recarga
     */
    protected int getReloadTime() {
        return ReloadTime;
    }

    /**
     * Método que retorna o tipo do atirador. Vai de 1 a 3 para aliens e de 1 a 4
     * para naves de usuário.
     * @return inteiro correspondente ao tipo.
     */
    protected int getTipo() {
        return this.Tipo;
    }

    /**
     * Retorna o dano que o atirador tem.
     * @return inteiro correspondente à vida que o atirador tira quando seu
     * projétil acerta outra entidade.
     */
    protected int getDano() {
        return this.Tipo;
    }

}
