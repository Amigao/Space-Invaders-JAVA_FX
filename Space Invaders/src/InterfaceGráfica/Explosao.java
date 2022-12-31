/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGráfica;

import javafx.scene.image.Image;

/**
 * Classe relacionada às explosões, que acontecem quando projéteis acertam
 * entidades.
 * @author pedro
 */
public class Explosao extends Efeito {

    
    /**
     * Construtor passa para a classe efeito a imagem inicial e a posição
     * horizontal e vertical da explosão.
     * @param x posicao x na tela do jogo da explosão
     * @param y posicao y na tela do jogo da explosão
     */
    protected Explosao(int x, int y) {
        super(new Image("explosao.png"), x, y);
    }

    /**
     * Quando passa-se um tempo, a imagem é atualizada e acrescenta-se 1 ao contador
     * de tempo da animação.
     */
    @Override
    protected void atualiza() {
        super.aumentaTempo();
        this.Desenho = new Image("explosao2.png");
    }

    /**
     * A animação acaba ao fim do tempo.
     * @return true se o tempo de animação for maior que 1 e false caso contrário.
     */
    @Override
    protected boolean fimAnimacao() {
        return super.getTempo() > 1;
    }

}
