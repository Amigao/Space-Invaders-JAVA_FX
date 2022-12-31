package Classes;

import javafx.scene.image.Image;

/**
 * Essa classe define um único alien da matriz de 55 aliens. Dessa forma,
 * quem constrói a lista de aliens é a engine do jogo e cada um deles é tratado
 * de forma individual.
 * @author pedro
 */
public class Aliens extends Atirador {

    /**
     * Direção que o alien está se movendo; Sempre começa se movimentando para
     * direta; 1 para direita e -1 para esquerda.
     */
    private int Direcao = 1;

    /**
     * O construtor passa para a classe que é extendida (atirador) as posições
     * vertical e horizontal do alien, a vida - que aumenta a cada fase -, o dano
     * que sempre é 1 e define a imagem do alien e seu tempo de recarga, que
     * diminui a cada fase.
     * @param x posição horizontal que o alien está na tela
     * @param y posição vertical que o alien está na tela
     * @param tipo tipo do alien; existem 3 tipos, fator que define a vida,
     * o tempo de recarga e o estilo do alien
     * @param nivel nível que o jogo está; define a vida e o tempo de reload
     * (quanto maior o nível, menos tempo o alien leva para recarregar)
     */
    protected Aliens(int x, int y, int tipo, int nivel) {
        super(x, y, tipo + (nivel - 1), 1, tipo);
        super.setReloadTime(21 - nivel);
        setDesenho(new Image("alien" + tipo + ".png"));
    }

    /**
     * Método de atirar da entidade "atirador"; gera um
     * novo tiro vinculado ao alien
     *
     * @return um novo tiro de tipo 5 - tipo de tiro vinculado aos
     * inimigos
     */
    @Override
    protected Tiro Atirar() {
        Tiro tiro = new Tiro(super.getPosX(), super.getPosY(), 5);
        return tiro;
    }

    /**
     * Método que movimenta os aliens; os aliens se movimentam de forma
     * matricial, ou seja, cada vez que eles se movimentam, saltam seu próprio
     * tamanho na tela, como se ela fosse dividada em uma matriz e os aliens
     * saltassem nas posições dessa matriz - cada vez que eles se movem, somam
     * horizontalmente seu próprio tamanho na direção em que estão nacegando.
     */
    protected void MoverAliens() {
        super.AlteraPosX((int) super.getDesenho().getWidth() * Direcao);
    }

    /**
     * Método que inverte a direção dos aliens; é chamada pela engine quando algum
     * dos aliens atinge um dos limites da tela e inverte a direção de navegação
     * bem como movimenta verticalmente o alien seguindo o formato de
     * movimentação matricial - cada vez que eles alteram a direção, somam
     * verticalmente seu próprio tamanho
     */
    protected void trocaDirecao() {
        this.Direcao *= -1;
        super.AlteraPosY((int) super.getDesenho().getHeight());
    }

    /**
     * Método usado para verificar se o alien já pode atirar. O tempo de recarga
     * é particular de cada alien e o valor desde o último tiro é controlado
     * pela engine do jogo.
     * @param reloading o tempo de recarga que é controlado pela engine do
     * jogo, já que todos os aliens precisam ter um mesmo tempo de recarga, ou
     * seja, a variável não pode ser interna ao objeto alien e sim uma comum ao
     * conjunto de aliens.
     * @return verdadeiro se o tempo superar o tempo mínimo de recarga do alien e
     * falso caso contrário
     */
    protected boolean PodeAtirar(int reloading) {
        return (reloading > getReloadTime());
    }

    /**
     * Método que retorna a direção (esquerda ou direita, -1 ou 1) que o alien
     * está se movendo
     * @return inteiro correspondente à direção
     */
    protected int getDirecao() {
        return Direcao;
    }

    /**
     * Método que verifica se o centro do alien está alinhado ao player para atirar nele.
     * @param player entidade da nave do jogador
     * @return true se estiver alinhado, false caso contrário.
     */
    boolean estaAlinhado(Nave player) {
        return (this.getPosX() + (int) this.getDesenho().getWidth()/2 >= player.getPosX() && 
                this.getPosX() + (int) this.getDesenho().getWidth()/2 <= player.getPosX() + (int) player.getDesenho().getWidth());
    }
}
