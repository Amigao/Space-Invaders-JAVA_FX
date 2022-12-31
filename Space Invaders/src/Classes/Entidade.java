package Classes;

import javafx.scene.image.Image;

/**
 * Entidades são classes generalistas para elementos do jogo que possuem vida,
 * posição e imagem. Elas não necessariamente se movem.
 *
 * @author pedro
 */
public abstract class Entidade {

    /**
     * Construtor define as posições horizontal e vertical conforme a definida
     * na criação da entidade e a quantidade de dano necessária para que a
     * entidade seja eliminada - a vida.
     *
     * @param x posição horizontal da entidade na tela do jogo
     * @param y posição vertical da entidade na tela do jogo
     * @param vida vida da entidade
     */
    protected Entidade(int x, int y, int vida) {
        this.PosX = x;
        this.PosY = y;
        this.Vida = vida;
    }

    /**
     * Imagem da entidade.
     */
    private Image Desenho;

    /**
     * Posição horizontal da entidade.
     */
    private int PosX;

    /**
     * Posição vertical da entidade.
     */
    private int PosY;

    /**
     * Vida da entidade.
     */
    private int Vida;

    /**
     * Método que reduz a vida da entidade baseado no dano passado como parâmetro
     * @param dano quantidade que a vida será removida
     */
    protected void DiminuiVida(int dano) {
        this.Vida -= dano;
    }
    
    /**
     * Método que verifica se a entidade está viva ou foi destruída                           
     * @return verdadeiro se a vida da entidade for igual ou menor que 0
     */
    protected boolean Destruido(){
        return Vida <= 0;
    }

    /**
     * Método para definir a imagem da entidade
     * @param Desenho imagem que será definida como desenho da entidade
     */
    protected void setDesenho(Image Desenho) {
        this.Desenho = Desenho;
    }

    /**
     * Método para pegar o desenho, usada para consultar o hitbox (tamanho da imagem)
     * ou para construir a interface gráfica
     * @return imagem da entidade
     */
    public Image getDesenho() {
        return Desenho;
    }

    /**
     * Método para pegar a posição horizontal da entidade
     * @return inteiro x da entidade na tela
     */
    public int getPosX() {
        return PosX;
    }

    /**
     * Método para pegar a posição vertical da entidade
     * @return inteiro y da entidade na tela
     */
    public int getPosY() {
        return PosY;
    }
    
    /**
     * Método para acessar a vida da entidade
     * @return inteiro equivalente à vida da entidade
     */
    protected int getVida(){
        return Vida;
    }
    
    /**
     * Método para variar a posição horizontal da entidade
     * @param X variação da posição x da entidade na tela do jogo
     */
    protected void AlteraPosX(int X){
        this.PosX += X;
    }
    
    /**
     * Método para variar a posição vertical da entidade
     * @param Y variação da posição x da entidade na tela do jogo
     */
    protected void AlteraPosY(int Y){
        this.PosY += Y;
    }
}
