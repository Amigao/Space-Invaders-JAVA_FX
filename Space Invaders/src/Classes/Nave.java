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
public class Nave extends Atirador {

    public int Dano;
    public int Vida;
    public int tipo;


    public Nave(int TipoNave) {

        super(SpaceInvaders.MatrizX / 2, SpaceInvaders.MatrizY - 1);
        this.Desenho = new Image("nave" + TipoNave + ".png");
        this.tipo = TipoNave;
        switch (tipo) {
            case 1:
                this.Dano = 1;
                this.Vida = 3;
                this.ReloadTime = 8;
                break;
                
            case 2:
                this.Dano = 2;
                this.Vida = 4;
                this.ReloadTime = 7;
                break;
                
            case 3:
                this.Dano = 2;
                this.Vida = 5;
                this.ReloadTime = 7;
                break;
                
            case 4:
                this.Dano = 3;
                this.Vida = 5;
                this.ReloadTime = 5;
                break;
                
            default:
                break;
        }
    }

    public void AtualizaNave(int Direção) {

        if (PosX + Direção >= 0 && PosX + Direção < SpaceInvaders.MatrizX - 1) {
            PosX += Direção;
        }

    }

    public int getTipo(){
        return this.tipo;
    }
    
    @Override
    public Tiro Atirar() {
        PodeAtirar = false;
        Reloading = 0;
        Tiro tiro = new Tiro(this.PosX, this.PosY - 1, this.tipo);
        return tiro;
    }

}
