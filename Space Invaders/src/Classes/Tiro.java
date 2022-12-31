package Classes;

import javafx.scene.image.Image;
import space.invaders.SpaceInvaders;

/**
 * Os projéteis que os aliens e o usuário atiram.
 * @author pedro
 */
public class Tiro extends Entidade {

    /**
     * O tipo do tiro altera a velocidade da sua navegação na tela, seu desenho
     *  e corresponde a algum dos tipos de nave ou aos inimigos.
     * De 1 a 4, corresponde às naves do usuário, 5 é o tiro do inimigo.
     */
    private int Tipo;       
    
    /**
     * Booleano que torna-se false para tiros do tipo 5 e true caso contrário.
     */
    private boolean doBem;

    /**
     * Construtor define o tipo, o booleano doBem e passa os parametros posicionais
     * e de vida para a entidade.
     * @param x inteiro correspondente à posição x do tiro na tela do jogo
     * @param y inteiro correspondente à posição x do tiro na tela do jogo
     * @param tipo tipo de tiro, varia de 1 a 5
     */
    protected Tiro(int x, int y, int tipo) {
        super(x, y, 1);
        this.Tipo = tipo;
        super.setDesenho(new Image("tiro" + tipo + ".png"));
        doBem = (tipo != 5);
    }

    /**
     * Método que altera a posição do tiro. A posição em y é incrementada quando
     * o tiro é nao é doBem, ou seja, é dos aliens, ou decrementado caso seja do 
     * usuário, para que possa subir na tela. Essa função também define a velocidade
     * dos tiros (distância que se desloca a cada chamada)
     */
    protected void MoveTiro() {
        if (doBem) {
            super.AlteraPosY( - Tipo * 4 - 40);
        } else {
            super.AlteraPosY(16 + 2 * SpaceInvaders.Nivel);
        }
    }

    /**
     * Retorna o booleano doBem.
     * @return true se o tiro for atirado pelo usuário e false caso seja dos
     * inimigos
     */
    protected boolean ehDoBem() {
        return this.doBem;
    }

    /**
     * Função que confere se o centro do tiro está dentro da área de uma entidade.
     * @param entidade entidade da qual o método compara a posição do tiro com.
     * @return verdadeiro se o centro do tiro está dentro da área da imagem da entidade,
     * falso caso contrário.
     */
    protected boolean ColideCom(Entidade entidade) {
        return ((super.getPosX() + SpaceInvaders.TamBase / 2) >= entidade.getPosX()
                && (super.getPosX() + SpaceInvaders.TamBase / 2) <= entidade.getPosX() + entidade.getDesenho().getWidth()
                && super.getPosY() <= entidade.getPosY() + entidade.getDesenho().getHeight()
                && super.getPosY() >= entidade.getPosY());
    }
}
