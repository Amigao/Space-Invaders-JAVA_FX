/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGráfica;

import javafx.scene.image.Image;

/**
 * Classes de elementos visuais que se alteram passado determinado tempo.
 *
 * @author pedro
 */
public abstract class Efeito extends Elemento {

    /**
     * Todos os efeitos possuem uma váriavel tempo que diz quanto tempo passou
     * desde que eles foram criados. Útil no controle dos métodos fimAnimacao()
     * e atualiza();
     */
    private int tempo = 0;

    public Efeito(Image Desenho, int PosX, int PosY) {
        super(Desenho, PosX, PosY);
    }

    /**
     * Método que verifica se a animação já acabou ou não.
     * @return verdadeiro se o tempo for maior que um dado inteiro, falso caso
     * contrário.
     */
    protected abstract boolean fimAnimacao();

    /**
     * Método que altera as imagens para gerar a animação conforme o tempo passa.
     */
    protected abstract void atualiza();

    /**
     * Método para aumentar o tempo de animação transcorrido.
     */
    protected void aumentaTempo(){
        this.tempo++;
    }
    
    /**
     * Função para acessar o tempo de animação.
     * @return tempo transcorrido da animação.
     */
    protected int getTempo(){
        return tempo;
    }
}
