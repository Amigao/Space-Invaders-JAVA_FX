/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGráfica;

import javafx.scene.image.Image;
import space.invaders.SpaceInvaders;

/**
 * Classe do plano de fundo do jogo, que se movimenta.
 * @author pedro
 */
public class PlanoDeFundo extends Elemento{

    public PlanoDeFundo() {
        super(new Image("fundo.png"), 0, 0);
    }
    
    /**
     * Função que altera a posição do fundo e reseta se ele passou por toda a tela.
     */
    protected void AtualizaFundo() {
        if (PosY == SpaceInvaders.Height) {
            PosY = 0;
        } else {
            PosY += SpaceInvaders.Nivel;
        }
    }

}
