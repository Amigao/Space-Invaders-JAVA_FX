package Classes;

import javafx.scene.image.Image;
import space.invaders.SpaceInvaders;

/**
 * A nave é o objeto usado pelo usuário. Existem 4 tipos de nave, que podem ser
 * compradas pela loja do jogo usando as moedas adquiridas acertando aliens
 * especiais. Cada tipo possui uma imagem e características diferentes. Níveis
 * mais altos devem exigir naves melhores para serem superados.
 *
 * @author pedro
 */
public class Nave extends Atirador {

    /**
     * Característica partiular das naves, já que somente essa classe é
     * movimentada fora de um ciclo. A velocidade é, basicamente, a quantidade
     * de pixels que a nave vai saltar para a esquerda ou direita. Quanto maior
     * essa quantidade, mais rapidamente a nave pode se deslocar pela tela.
     * Todos os possíveis valores permitem que qualquer ponto da tela seja
     * acertado por um projétil.
     */
    private int Velocidade;

    /**
     * Atributo que é incrementado rotineiramente e representa um tempo de
     * recarga da nave. Quando este supera o tempo mínimo de recarga
     * "ReloadTime", quer dizer que o atirador já pode atirar novamente; O tempo
     * de reloading dos atiradores é 0 quando este é criado.
     */
    private int Reloading = 0;

    /**
     * Construtor passa a posição horizontal, vertical, a vida, o dano e o tipo
     * da nave. O dano e a vida dependem do tipo.
     *
     * @param TipoNave inteiro que define qual o tipo da nave, vai de 1 a 4
     */
    protected Nave(int TipoNave) {
        super(SpaceInvaders.Width / 2, SpaceInvaders.Height - 3 * SpaceInvaders.TamBase / 2, TipoNave + 2, TipoNave, TipoNave);
        setDesenho(new Image("nave" + TipoNave + ".png"));

        switch (super.getTipo()) {
            case 1:
                this.Velocidade = 16;
                super.setReloadTime(8);
                break;

            case 2:
                this.Velocidade = 16;
                super.setReloadTime(7);
                break;

            case 3:
                this.Velocidade = 24 ;
                super.setReloadTime(7);
                break;

            case 4:
                this.Velocidade = 20;
                super.setReloadTime(4);
                break;

            default:
                break;
        }
        this.Reloading = this.getReloadTime();
    }

    /**
     * Método que verifica se a nave não atingiu os limites da tela para se movimentar.
     * Se não tiver atingido, chama o método @see AlteraPosX para alterara a posição
     * horizontal na direção pedida pelo usuário vezes a velocidade vinculada à nave
     * usada pelo usuário. A velocidade é um inteiro correspondente ao deslocamento
     * em x da tela do jogo que o usuário se movimenta em cada click do teclado.
     * @param Direção inteiro correspondente à esquerda ou direita. 1 para direita, 
     * -1 para esquerda.
     */
    protected void AtualizaNave(int Direção) {
        if (super.getPosX() + Velocidade * Direção >= 0 && super.getPosX() + Velocidade * Direção < SpaceInvaders.Width - SpaceInvaders.TamBase) {
            super.AlteraPosX(Direção * Velocidade);
        }
    }

    /**
     * Método que define o método abstrato da classe atiradora.
     * @return retorna um tiro na posição da frente da nave e do tipo igual ao da nave.
     */
    @Override
    protected Tiro Atirar() {
        ResetaReloading();
        Tiro tiro = new Tiro(super.getPosX(), super.getPosY() - (int) super.getDesenho().getHeight() / 2, super.getTipo());
        return tiro;
    }

    /**
     * Método usado para verificar se a nave já pode atirar. 
     *
     * @return verdadeiro se o tempo de reloading superar o tempo mínimo de 
     * recarga da nave e falso caso contrário
     */
    protected boolean PodeAtirar() {
        return (this.Reloading >= getReloadTime());
    }
    
    /**
     * Incrementa o tempo atual da recarga do tiro da nave. Quando atinge o tempo
     * de recarga, resta.
     */
    protected void IncrementaReloading(){
        this.Reloading++;
     }

    /**
     * Método chamado quando o Reloading atinge o tempo de recarga.
     */
    private void ResetaReloading(){
        this.Reloading = 0;
    }

}
