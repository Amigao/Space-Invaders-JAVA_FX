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
public class AlienEspecial extends Entidade {

    public int Direcao;
    public int Viagens = 0;
    
    public AlienEspecial() {
        super(0, 0);
        this.Direcao = 1;
        this.Desenho = new Image("alienEspecial.png");
    }
    
    public void MoverAlien() {
        if((Direcao == -1 && PosX == 1) || (Direcao == 1 && PosX == SpaceInvaders.MatrizX)) this.trocaDirecao();
        else PosX += Direcao;
    }

    void trocaDirecao() {
        this.Direcao *= -1;
        this.Viagens ++;
    }


}
