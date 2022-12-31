package Classes;

import javafx.scene.image.Image;
import space.invaders.SpaceInvaders;

/**
 * Classe do allien especial que, ao ser atingido, premia o jogador com uma moeda.
 * @author pedro
 */
public class AlienEspecial extends Entidade {

    /**
     * Direção que o alien especial está se movendo; 
     * Sempre começa se movimentando para direta;
     * 1 para direita e -1 para esquerda.
     */
    private int Direcao = 1;

    /**
     * Quantidade de vezes que o alien já atravessou a tela toda. Variável útil
     * para fazer com que o alien desapareça depois de determinada quantia de 
     * viagens.
     */
    private int Viagens = 0;
    
    /**
     * Construtor, define o desenho do alien especial, as posições como 0
     * (canto superior esquerdo da tela) e a vida como 1;
     */
    protected AlienEspecial() {
        super(0, 0, 1);
        setDesenho(new Image("alienEspecial.png"));
    }
    
    /**
     * Função que movimenta o alien para os lados e, quando chega nos limites da
     * tela, inverte a direção.
     * A movimentação do alien salta com a metade de seu tamanho, que é o 
     * tamanho base do jogo. Ou seja, cada vez que ele se move, ele salta
     * 32 pixels para esquerda ou direita.
     */
    protected void MoverAlien() {
        if((Direcao == -1 && super.getPosX() == 0) || (Direcao == 1 && super.getPosX() == SpaceInvaders.Width - SpaceInvaders.TamBase)) this.trocaDirecao();
        else super.AlteraPosX(Direcao * (int) super.getDesenho().getWidth() / 2);
    }

    /**
     * Função que inverte a direção do alien e aumenta a quantidade de viagens.
     */
    private void trocaDirecao() {
        this.Direcao *= -1;
        this.Viagens ++;
    }
    
    /**
     * Função para pegar o número de viagens do alien.
     * @return númwro de viagens
     */
    protected int getViagens(){
        return this.Viagens;
    }

}
