/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.scene.image.Image;

/**
 *
 * @author pedro
 */
public class Aliens extends Atirador {

    public int Direcao;
    public int Vida;
    
    public Aliens(int x, int y, int tipo, int nivel) {
        super(x, y);
        this.Direcao = 1;
        this.ReloadTime = 18 - nivel;
        this.Vida = tipo + (nivel - 1);
        this.Desenho = new Image("alien" + tipo + ".png");
    }
    
    public void MoverAliens() {
        PosX += 1 * Direcao;
    }

    public void trocaDirecao() {
        this.Direcao *= -1;
        this.PosY += 1;
    }
    
    @Override
    public Tiro Atirar(){
        Tiro tiro = new Tiro(this.PosX, this.PosY, 5);
        return tiro;
    }

    public boolean PodeAtirar(int reloading) {
        return (reloading > ReloadTime);
    }

    public boolean Morto() {
        return Vida <= 0;
    }

}
