package Classes;

import InterfaceGráfica.Tela;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import space.invaders.SpaceInvaders;

/**
 * A engine do jogo é responsável por toda a criação e controle do jogo. As
 * entidades se movimentam através dos métodos da engine e o construtor define e
 * cria tudo que o jogo usa. As colisões também são verificadas na engine. As
 * velocidades de cada nível são definidas pelo sleeptime de cada thread de
 * comando definas nessa classe.
 *
 * @author pedro
 */
public class Engine {

    /**
     * Score da partida. Só é somado ao total se o jogador vencer o nível.
     */
    private int ScoreTemp = 0;

    /**
     * Moedas da partida. Só é somado ao total se o jogador vencer o nível.
     */
    private int MoedasTemp = 0;

    /**
     * Tela é a interface gráfica do jogo. Tudo que é mostrado na tela vista
     * pelo usuário é construído na classe Tela.
     */
    private Tela tela;

    /**
     * Threads que controla a movimentação dos tiros bem como os conflitos deles
     * com outras entidades.
     */
    private Thread loopTiros;

    /**
     * Thread que define o tempo de movimentação da matriz de aliens.
     */
    private Thread loopAliens;

    /**
     * Thread que define a movimentação dos aliens especiais.
     */
    private Thread loopAlienEspecial;

    /**
     * Inteiro que define o tempo correspondente ao último tiro da matriz de
     * aliens.
     */
    private int ReloadingAliens = 0;

    /**
     * Nave usada pelo usuário.
     */
    private final Nave player;

    /**
     * Lista do alienEspecial. Idealmente, nunca tem mais que um elemento.
     * Optou-se pelo emprego de listas para facilitar as verificações usando a
     * função de isEmpty e a adição e remoção na lista.
     */
    private final ArrayList<AlienEspecial> alienEspecial;

    /**
     * Lista dos meteoros (barreiras).
     */
    private final ArrayList<Barreiras> barreiras;

    /**
     * Lista de tiros. Os tiros dos inimigos e do usuário são armazenados
     * juntos.
     */
    private final ArrayList<Tiro> tiros;

    /**
     * Lista de aliens que constroem a matriz de inimigos
     */
    private final ArrayList<Aliens> aliens;

    /**
     * Booleano que controla o pause.
     */
    private boolean Pause = false;

    /**
     * Booleano que controla a vitória.
     */
    private boolean Ganhou;

    /**
     * Booleano que controla a derrota.
     */
    private boolean Perdeu;

    /**
     * Booleano que controla o início do jogo.
     */
    private boolean EhInicio = true;

    /**
     * Contador de vezes que o alien especial foi criado no jogo.
     */
    private int spawnAlienEsp = 0;

    /**
     * O construtor inicializa todas as listas de entidades com as posições que
     * elas iniciam o jogo, bem como suas características como vida e dano.
     * Muitas dessas têm influência do nível do jogo. O método de inicialização
     * das threads é chamado no construtor do jogo também.
     *
     */
    public Engine() {

        this.tela = new Tela(this);

        this.player = new Nave(SpaceInvaders.TipoNave);
        this.tiros = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.barreiras = new ArrayList<>();
        this.alienEspecial = new ArrayList<>();

        this.Ganhou = false;
        this.Perdeu = false;

        for (int i = 0; i < 11; i++) {
            this.aliens.add(new Aliens(i * SpaceInvaders.TamBase, 1 * SpaceInvaders.TamBase, 3, SpaceInvaders.Nivel));
            this.aliens.add(new Aliens(i * SpaceInvaders.TamBase, 2 * SpaceInvaders.TamBase, 2, SpaceInvaders.Nivel));
            this.aliens.add(new Aliens(i * SpaceInvaders.TamBase, 3 * SpaceInvaders.TamBase, 2, SpaceInvaders.Nivel));
            this.aliens.add(new Aliens(i * SpaceInvaders.TamBase, 4 * SpaceInvaders.TamBase, 1, SpaceInvaders.Nivel));
            this.aliens.add(new Aliens(i * SpaceInvaders.TamBase, 5 * SpaceInvaders.TamBase, 1, SpaceInvaders.Nivel));
        }

        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras((3 + i) * SpaceInvaders.TamBase, SpaceInvaders.Height - 3 * SpaceInvaders.TamBase));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras((10 + i) * SpaceInvaders.TamBase, SpaceInvaders.Height - 3 * SpaceInvaders.TamBase));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras((17 + i) * SpaceInvaders.TamBase, SpaceInvaders.Height - 3 * SpaceInvaders.TamBase));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras((24 + i) * SpaceInvaders.TamBase, SpaceInvaders.Height - 3 * SpaceInvaders.TamBase));
        }

        this.criaLoop();
    }

    /**
     * Método que define as threads e inicializa elas. Optou-se pela definição
     * das threads sem classes externas pois na lista 10, de análise de tempo de
     * execução via Threads, o meu computador apresentou problemas de lag em
     * formatações diferentes da dessa bem como essa forma facilita o acesso das
     * entidades, evitando-se que todas as entidades sejam passadas como
     * paramêtro ou a própria engine.
     */
    private void criaLoop() {

        this.loopTiros = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Perdeu && !Ganhou) {
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!Pause) {
                        AtualizaTiros();
                    }
                }
            }
        });

        this.loopAliens = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Perdeu && !Ganhou) {
                    try {
                        sleep(450 - (SpaceInvaders.Nivel * 25) + aliens.size() * 10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!Pause) {
                        AtualizaAliens();
                    }
                }
            }
        });

        this.loopAlienEspecial = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Perdeu && !Ganhou && spawnAlienEsp <= 2) {
                    try {
                        sleep(225 - (25 * SpaceInvaders.Nivel));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!Pause) {
                        AtualizaAlienEspecial();
                    }
                }
            }
        });

        loopTiros.start();
        loopAliens.start();
        loopAlienEspecial.start();
    }

    /**
     * Método que vincula à cena do jogo os comandos de movimentação do usuário
     * pelo teclado. Pra isso, precisa receber como parâmetro a cena que o jogo
     * foi construído.
     *
     * @param gameScene cena do jogo que receberá os comandos via teclado
     */
    public void MovimentacaoNave(Scene gameScene) {

        gameScene.setOnKeyTyped((KeyEvent e) -> {
            String c = e.getCharacter();
            switch (c) {
                case "p":
                case "P":
                    this.Pause = !Pause;
                    break;
                case "M":
                case "m":
                    tela.encerraJogo(Ganhou, Perdeu);
                default:
                    break;
            }
        });

        gameScene.setOnKeyPressed((KeyEvent event) -> {
            if (!Pause) {
                if (event.getCode() == KeyCode.SPACE && player.PodeAtirar()) {
                    tiros.add(player.Atirar());
                }
                if (event.getCode() == KeyCode.A) {
                    player.AtualizaNave(-1);
                }
                if (event.getCode() == KeyCode.D) {
                    player.AtualizaNave(1);
                }
            }
        });

    }

    /**
     * Método que movimenta os aliens bem como faz verificações relacionadas à
     * essa entidade, como se a lista está vazia, se os aliens atingiram o fim
     * da tela e se podem atirar.
     */
    private void AtualizaAliens() {
        if (aliens.isEmpty()) {
            Ganhou = true;
            SpaceInvaders.Score += this.ScoreTemp;
            SpaceInvaders.Moedas += this.MoedasTemp;
            tela.encerraJogo(Ganhou, Perdeu);

        } else if (EhInicio) {
            TiroAleatorio();
        }

        for (int i = 0; i < aliens.size(); i++) {

            if (aliens.get(i).PodeAtirar(ReloadingAliens)) {
                if (aliens.get(i).estaAlinhado(player)) {
                    tiros.add(aliens.get(i).Atirar());
                    ReloadingAliens = 0;
                } else {
                    TiroAleatorio();
                }
            }
            if (aliens.get(i).getPosY() >= SpaceInvaders.Height - 3 * SpaceInvaders.TamBase) {
                Perdeu = true;
            }
        }

        if (!InverteDirecao()) {
            for (int i = 0; i < aliens.size(); i++) {
                aliens.get(i).MoverAliens();
            }
        }
    }

    /**
     * Método que movimenta os tiros e já verifica os conflitos entre os
     * projéteis e outras entidades, ou o fim da tela em que o jogo está
     * definido. Como a vida dos projéteis é sempre 1, eles são removidos
     * automaticamente após uma colisão, evitando comparações e reduzindo a
     * complexidade do algoritmo, que devido à quantidade de "for", já é alta.
     * Fazendo isso, reduz-se consideravelmente o lag quando muitos projéteis
     * estão na tela. Como nessa implementação o que limita os tiros do usuário
     * é o tempo de recarga, ele pode ter mais de um tiro na tela. Esse fato faz
     * com que ficar parado atirando torne-se uma estratégia muito forte se os
     * tiros do usuário puderem excluir os tiros dos aliens. Por isso,
     * escolheu-se que os tiros do usuário não podem excluir os dos aliens para
     * aumentar a dificuldade. Esse método também verifica se as vidas do
     * usuário acabaram.
     */
    private void AtualizaTiros() {

        for (int i = 0; i < tiros.size(); i++) {
            if (tiros.get(i).getPosY() < 0 || tiros.get(i).getPosY() > SpaceInvaders.Width - SpaceInvaders.TamBase) {
                tiros.remove(i);
                i--;
            }
        }
        for (int i = 0; i < tiros.size(); i++) {
            tiros.get(i).MoveTiro();
        }
        if (!player.PodeAtirar()) {
            player.IncrementaReloading();
        }
        if (!aliens.isEmpty()) {
            if (!aliens.get(0).PodeAtirar(ReloadingAliens)) {
                ReloadingAliens++;
            }
        }

        for (int i = 0; i < tiros.size(); i++) {
            if (tiros.get(i).ehDoBem()) {

                for (int j = 0; j < aliens.size(); j++) {
                    if (tiros.get(i).ColideCom(aliens.get(j))) {
                        aliens.get(j).DiminuiVida(player.getDano());
                        tela.addExplosao(aliens.get(j).getPosX(), aliens.get(j).getPosY());

                        if (aliens.get(j).Destruido()) {
                            this.ScoreTemp += 40 + SpaceInvaders.Nivel * 10;
                            aliens.remove(j);
                            j--;
                        }
                        tiros.remove(i);
                        break;
                    }
                }

            } else if (!tiros.get(i).ehDoBem()) {
                if (tiros.get(i).ColideCom(player)) {
                    tela.addExplosao(player.getPosX(), player.getPosY());
                    player.DiminuiVida(1);
                    tiros.remove(i);
                }
            }
        }

        for (int j = 0; j < alienEspecial.size(); j++) {
            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).ColideCom(alienEspecial.get(j)) && tiros.get(i).ehDoBem()) {
                    tela.addExplosao(alienEspecial.get(j).getPosX(), alienEspecial.get(j).getPosY());
                    alienEspecial.remove(j);
                    this.MoedasTemp++;
                    this.ScoreTemp += 90 + SpaceInvaders.Nivel * 10;
                    tiros.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < tiros.size(); i++) {
            for (int j = 0; j < barreiras.size(); j++) {
                if (tiros.get(i).ColideCom(barreiras.get(j))) {
                    barreiras.get(j).DiminuiVida(1);
                    tela.addExplosao(barreiras.get(j).getPosX(), barreiras.get(j).getPosY());
                    if (barreiras.get(j).Destruido()) {
                        barreiras.remove(j);
                    }
                    tiros.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < barreiras.size(); i++){
            for (int j = 0; j < aliens.size(); j++){
                if(barreiras.get(i).ColideCom(aliens.get(j))){
                    barreiras.remove(i);
                    i--;
                    break;
                }
            }
        }
        
        if (player.Destruido()) {
            this.Perdeu = true;
        }
    }

    /**
     * Método que sorteia um alien para atirar no começo do jogo e em alguns
     * casos em que o tempo de reloading é atingido.
     */
    private void TiroAleatorio() {
        Random gerador = new Random();
        int n = gerador.nextInt(aliens.size());
        tiros.add(aliens.get(n).Atirar());
        ReloadingAliens = 0;
        if (this.EhInicio) {
            this.EhInicio = false;
        }
    }

    /**
     * Método que testa se o alien especial pode ser criado e, se criado,
     * atualiza sua posição e exclui ele quando atinge uma certa quantia de
     * viagens ao longo da extensão horizontal da tela.
     */
    private void AtualizaAlienEspecial() {
        if ((aliens.size() == 20 && spawnAlienEsp < 2) || (aliens.size() == 40 && spawnAlienEsp == 0)) {
            alienEspecial.add(new AlienEspecial());
            spawnAlienEsp++;
        }

        for (int i = 0; i < alienEspecial.size(); i++) {
            alienEspecial.get(i).MoverAlien();
            if (alienEspecial.get(i).getViagens() == 5) {
                alienEspecial.remove(i);
                break;
            }
        }
    }

    /**
     * Método simples que inverte a direção de todos os aliens se eles atingem
     * os limites da tela
     *
     * @return verdadeiro se a direção tiver sido invertida (se os aliens
     * estavam em um dos limites da tela) ou falso caso contrário
     */
    private boolean InverteDirecao() {
        for (int i = 0; i < aliens.size(); i++) {
            if ((aliens.get(i).getPosX() == 0 && aliens.get(i).getDirecao() == - 1) || (aliens.get(i).getPosX() >= SpaceInvaders.Width - SpaceInvaders.TamBase && aliens.get(i).getDirecao() == 1)) {
                for (int j = 0; j < aliens.size(); j++) {
                    aliens.get(j).trocaDirecao();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar que reúne todos as entidades da engine em uma única
     * lista.
     *
     * @param lista lista com todas as entidades da engine.
     */
    public void getEntidades(ArrayList<Entidade> lista) {
        lista.add(player);
        lista.addAll(aliens);
        lista.addAll(alienEspecial);
        lista.addAll(barreiras);
        lista.addAll(tiros);
    }

    /**
     * Método usado pela interface gráfica para ter acesso às vidas do usuário.
     *
     * @return retorna a quantidade de vidas que o usuário possui.
     */
    public int vidasPlayer() {
        return player.getVida();
    }

    /**
     * Retorna o booleano de controle de vitória.
     *
     * @return true se o usuário ganhou o jogo, false caso contrário.
     */
    public boolean isGanhou() {
        return Ganhou;
    }

    /**
     * Retorna o booleano de controle de derrota.
     *
     * @return true se o usuário perdeu o jogo, false caso contrário.
     */
    public boolean isPerdeu() {
        return Perdeu;
    }

    /**
     * Função para pegar o score da partida.
     *
     * @return score ganho durante a partida
     */
    public int getScore() {
        return this.ScoreTemp;
    }

    /**
     * Função para pegar as moedas da partida.
     *
     * @return moedas ganhas durante a partida
     */
    public int getMoedas() {
        return this.MoedasTemp;
    }
}
