/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.scene.image.Image;
import space.invaders.SpaceInvaders;

/**
 *
 * @author pedro
 */
public class PlanoDeFundo extends Entidade {
    
    
    public PlanoDeFundo(){
        super(0, 0);
        this.Desenho = new Image("fundo.png");
    }

    public void AtualizaFundo() {
        if(PosY == SpaceInvaders.Height) PosY = 0;
        else PosY += 2;
    }
    
}
