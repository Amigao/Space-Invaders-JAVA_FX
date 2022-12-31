/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGráfica;

import javafx.scene.image.Image;

/**
 * Classe que define elementos unicamente gráficos, que tem imagem e posição.
 * @author pedro
 */
public abstract class Elemento {

    protected Image Desenho;
    protected int PosX;
    protected int PosY;

    public Elemento(Image Desenho, int PosX, int PosY) {
        this.Desenho = Desenho;
        this.PosX = PosX;
        this.PosY = PosY;
    }
}
