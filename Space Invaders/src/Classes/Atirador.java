/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author pedro
 */
public abstract class Atirador extends Entidade {
        
    public Atirador(int x, int y) {
        super(x, y);
    }
        
    public int Reloading = 0;
    public int ReloadTime;
    public boolean PodeAtirar = true;
    
    public abstract Tiro Atirar();

}
