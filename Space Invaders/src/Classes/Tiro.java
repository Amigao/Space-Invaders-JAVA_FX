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
public class Tiro extends Entidade {

    //private final int velocidade = 1;
    private final int Tipo;       // de 1 a 4, do bem. 5, do mal
    private final boolean doBem;

    public Tiro(int x, int y, int tipo) {
        super(x, y);
        this.Tipo = tipo;
        this.Desenho = new Image("tiro" + tipo + ".png") {
        };
        doBem = (tipo != 5);
    }

    public void MoveTiro() {
        if (doBem) {
            this.PosY -= 1;
        } else {
            this.PosY += 1;
        }
    }

    public boolean ehDoBem() {
        return this.doBem;
    }

    public boolean ColideCom(Entidade entidade) {
        return (PosX == entidade.PosX && PosY == entidade.PosY);
    }
}
