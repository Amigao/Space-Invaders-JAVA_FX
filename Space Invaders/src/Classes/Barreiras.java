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
public class Barreiras extends Entidade{

    public int Vida;
    
    public Barreiras(int x, int y) {
        super(x, y);
        this.Vida = 2;
        this.Desenho = new Image("barreira.png");
    }
    
    public void DiminuiVida(){
        this.Vida--;
    }
    
    public boolean Destruido(){
        return Vida == 0;
    }
}
