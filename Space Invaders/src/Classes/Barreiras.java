package Classes;

import javafx.scene.image.Image;
import space.invaders.SpaceInvaders;

/**
 * Classe dos meteoros que funcionam como barreira no jogo.
 *
 * @author pedro
 */
public class Barreiras extends Entidade {

    /**
     * Construtor posiciona as barreiras e define sua vida como 3. Inicializa a
     * imagem das barreiras.
     *
     * @param x posição x na tela do jogo que a barreira será inserida
     * @param y posição y na tela do jogo que a barreira será inserida
     */
    protected Barreiras(int x, int y) {
        super(x, y, 3);
        this.setDesenho(new Image("barreira3.png"));
    }

    /**
     * Reduz a vida da barreira. O dano dos projéteis não pesa na vida da
     * barreira. Altera a imagem da barreira que perde a vida para a imagem da
     * barreira danificada. Existem 3 tipos de imagem de barreira, um para cada
     * vida. Sobrepõe o método original das entidades que somente reduzia vida.
     *
     * @param dano quantidade de vida a ser removida da barreira
     */
    @Override
    protected void DiminuiVida(int dano) {
        super.DiminuiVida(dano);
        if (!Destruido()) {
            setDesenho(new Image("barreira" + super.getVida() + ".png"));
        }
    }

    /**
     * Função que confere se o centro da barreira está dentro da área de uma
     * entidade.
     *
     * @param entidade entidade da qual o método compara a posição do tiro com.
     * @return verdadeiro se o centro da barreira está dentro da área da imagem da
     * entidade, falso caso contrário.
     */
    protected boolean ColideCom(Entidade entidade) {
        return ((super.getPosX() + SpaceInvaders.TamBase / 2) >= entidade.getPosX()
                && (super.getPosX() + SpaceInvaders.TamBase / 2) <= entidade.getPosX() + entidade.getDesenho().getWidth()
                && super.getPosY() <= entidade.getPosY() + entidade.getDesenho().getHeight()
                && super.getPosY() >= entidade.getPosY());
    }
}
