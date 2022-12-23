/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import Classes.AlienEspecial;
import Classes.Aliens;
import Classes.Barreiras;
import Classes.Entidade;
import Classes.Nave;
import Classes.PlanoDeFundo;
import Classes.Tiro;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import space.invaders.SpaceInvaders;

/**
 *
 * @author pedro
 */
public class Engine {

    private int Nivel;
    private int Moedas;

    //Variaveis relacionadas à interface gráfica
    private Stage menuStage;
    private Scene gameScene;
    private Stage gameStage;
    private Group grupoRaiz;
    private Canvas gameCanvas;
    private GraphicsContext gameGC;
    private Font font = Font.font("Century Gothic", FontWeight.EXTRA_BOLD, 30);

    //Variaveis de thread
    private Thread loopTiros;
    private Thread loopAliens;
    private Thread loopAlienEspecial;
    private AnimationTimer loopAnimacao;
    private int ReloadingAliens = 0;

    //Entidades
    private final Nave player;
    private final ArrayList<AlienEspecial> alienEspecial;
    private final ArrayList<Barreiras> barreiras;
    private final ArrayList<Tiro> tiros;
    private final ArrayList<Aliens> aliens;
    private final PlanoDeFundo fundo;

    //Booleanos
    private boolean Ganhou;
    private boolean Perdeu;
    private boolean EhInicio = true;
    public boolean Rodando;
    private int spawnAlienEsp = 0;

    public Engine(int TipoNave, int nivel, int moedas, Stage menu) {

        this.menuStage = menu;
        this.Nivel = nivel;
        this.Moedas = moedas;

        this.fundo = new PlanoDeFundo();
        this.player = new Nave(TipoNave);
        this.tiros = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.barreiras = new ArrayList<>();
        this.alienEspecial = new ArrayList<>();

        this.Ganhou = false;
        this.Perdeu = false;
        this.Rodando = true;

        for (int i = 0; i < 11; i++) {
            this.aliens.add(new Aliens(i, 0, 3, Nivel));
            this.aliens.add(new Aliens(i, 1, 2, Nivel));
            this.aliens.add(new Aliens(i, 2, 2, Nivel));
            this.aliens.add(new Aliens(i, 3, 1, Nivel));
            this.aliens.add(new Aliens(i, 4, 1, Nivel));
        }

        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(3 + i, SpaceInvaders.MatrizY - 3));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(10 + i, SpaceInvaders.MatrizY - 3));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(17 + i, SpaceInvaders.MatrizY - 3));
        }
        for (int i = 0; i < 3; i++) {
            this.barreiras.add(new Barreiras(24 + i, SpaceInvaders.MatrizY - 3));
        }

        this.criaStage();
        this.criaLoop();
    }

    private void criaStage() {
        gameStage = new Stage();
        gameStage.setTitle("Space Invaders");
        gameStage.setMaximized(true);

        grupoRaiz = new Group();

        gameScene = new Scene(grupoRaiz);
        gameStage.setScene(gameScene);
        gameCanvas = new Canvas(SpaceInvaders.Width, SpaceInvaders.Height);
        grupoRaiz.getChildren().add(gameCanvas);
        gameGC = gameCanvas.getGraphicsContext2D();

        gameStage.setOnCloseRequest((WindowEvent e) -> {
            System.exit(0);
        });

        MovimentacaoNave();

        gameStage.show();
    }

    private void criaLoop() {

        this.loopTiros = new Thread(() -> {
            while (!Perdeu && !Ganhou) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < tiros.size(); i++) {
                    tiros.get(i).MoveTiro();
                }
                if (!player.PodeAtirar) {
                    player.Reloading++;
                }
                if (player.Reloading >= player.ReloadTime) {
                    player.PodeAtirar = true;
                    player.Reloading = 0;
                }
                if (!aliens.get(0).PodeAtirar(ReloadingAliens)) {
                    ReloadingAliens++;
                }
            }

        });

        this.loopAliens = new Thread(() -> {
            while (!Perdeu && !Ganhou) {
                try {
                    sleep(300 - (Nivel * 50) + aliens.size() * 10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
                AtualizaAliens();
            }
        });

        this.loopAlienEspecial = new Thread(() -> {
            while (!Perdeu && !Ganhou && spawnAlienEsp <= 2) {
                try {
                    sleep(225 - (25 * Nivel));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
                AtualizaAlienEspecial();
            }
        });

        loopAnimacao = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // "limpar" tela
                fundo.AtualizaFundo();
                gameGC.drawImage(fundo.Desenho, 0, fundo.PosY);
                gameGC.drawImage(fundo.Desenho, 0, fundo.PosY - 1080);
                for (int i = 0; i < tiros.size(); i++) {
                    if (tiros.get(i).PosY < 0 || tiros.get(i).PosY > SpaceInvaders.MatrizY - 1) {
                        tiros.remove(i);
                        i--;
                    }
                }
                if (Ganhou || Perdeu) {
                    AtualizaDados();
                    loopAnimacao.stop();
                    gameStage.close();
                    menuStage.show();
                }
                AtualizaTela();
                AtualizaTiros();
            }
        };
        loopTiros.start();
        loopAliens.start();
        loopAlienEspecial.start();
        loopAnimacao.start();
    }

    private void MovimentacaoNave() {

        gameScene.setOnKeyTyped((KeyEvent e) -> {
            String c = e.getCharacter();
            switch (c) {
                case "a":
                case "A":
                    player.AtualizaNave(-1);
                    //System.out.print("esquerda");
                    break;
                case "d":
                case "D":
                    player.AtualizaNave(1);
                    //System.out.print("direita");
                    break;
                case "p":
                case "P":
                    loopAnimacao.stop();
                    gameStage.close();
                    menuStage.show();
                default:
                    break;
            }
        });

        gameScene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.SPACE && player.PodeAtirar) {
                //System.out.print("tiro");
                tiros.add(player.Atirar());
            }
        });

    }

    private void AtualizaAliens() {
        if (aliens.isEmpty()) {
            Ganhou = true;
            Nivel++;
        } else if (EhInicio) {
            TiroAleatorio();
        }

        for (int i = 0; i < aliens.size(); i++) {

            if (aliens.get(i).PodeAtirar(ReloadingAliens)) {
                if (this.EstaAlinhado(aliens.get(i), player)) {
                    tiros.add(aliens.get(i).Atirar());
                    ReloadingAliens = 0;
                } else {
                    TiroAleatorio();
                }
            }
            if (aliens.get(i).PosY == SpaceInvaders.MatrizY - 3) {
                Perdeu = true;
            }

            if ((aliens.get(i).PosX == 0 && aliens.get(i).Direcao == -1) || (aliens.get(i).PosX == SpaceInvaders.MatrizX - 1 && aliens.get(i).Direcao == 1)) {
                for (int j = 0; j < aliens.size(); j++) {
                    aliens.get(j).trocaDirecao();
                }
                break;

            } else {
                aliens.get(i).MoverAliens();
            }
        }

    }

    private void AtualizaTiros() {
        for (int i = 0; i < tiros.size(); i++) {
            if (tiros.get(i).ehDoBem()) {

                for (int j = 0; j < aliens.size(); j++) {
                    if (tiros.get(i).ColideCom(aliens.get(j))) {
                        aliens.get(j).Vida -= player.Dano;
                        if (aliens.get(j).Morto()) {
                            aliens.remove(j);
                            j--;
                        }
                        tiros.remove(i);
                        break;
                    }
                }

            } else if (!tiros.get(i).ehDoBem()) {
                if (tiros.get(i).ColideCom(player)) {
                    player.Vida--;
                    tiros.remove(i);
                }
            }
        }

        for (int j = 0; j < alienEspecial.size(); j++) {
            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).ColideCom(alienEspecial.get(j)) && tiros.get(i).ehDoBem()) {
                    alienEspecial.remove(j);
                    this.Moedas++;
                    tiros.remove(i);
                    break;
                }
                break;
            }
        }

        for (int i = 0; i < tiros.size(); i++) {
            for (int j = 0; j < barreiras.size(); j++) {
                if (tiros.get(i).ColideCom(barreiras.get(j))) {
                    barreiras.get(j).DiminuiVida();
                    if (barreiras.get(j).Destruido()) {
                        barreiras.remove(j);
                    }
                    tiros.remove(i);
                    break;
                }
            }
        }

        if (player.Vida <= 0) {
            this.Perdeu = true;
        }
    }

    public void AtualizaTela() {

        //desenha os tiros
        for (int i = 0; i < tiros.size(); i++) {
            gameGC.drawImage(tiros.get(i).Desenho, tiros.get(i).PosX * aliens.get(i).Desenho.getWidth(), tiros.get(i).PosY * tiros.get(i).Desenho.getHeight());
        }

        //desenha os inimigos
        for (int i = 0; i < aliens.size(); i++) {
            gameGC.drawImage(aliens.get(i).Desenho, aliens.get(i).PosX * aliens.get(i).Desenho.getWidth(), aliens.get(i).PosY * aliens.get(i).Desenho.getHeight());
        }
        if (!alienEspecial.isEmpty()) {
            gameGC.drawImage(alienEspecial.get(0).Desenho, alienEspecial.get(0).PosX * alienEspecial.get(0).Desenho.getWidth(), alienEspecial.get(0).PosY * alienEspecial.get(0).Desenho.getHeight());
        }

        //desenha os barreiras
        for (int i = 0; i < barreiras.size(); i++) {
            gameGC.drawImage(barreiras.get(i).Desenho, barreiras.get(i).PosX * barreiras.get(i).Desenho.getWidth(), barreiras.get(i).PosY * barreiras.get(i).Desenho.getHeight());
        }

        //desenha a nave
        gameGC.drawImage(player.Desenho, player.PosX * 64, player.PosY * 64);

        //escreve os dados na tela
        gameGC.setFill(Color.WHITESMOKE);
        gameGC.setFont(font);
        gameGC.fillText("Nível: " + this.Nivel + "\nVidas: " + player.Vida + "\nMoedas: " + this.Moedas, 0, SpaceInvaders.Height - 100);
        if (Ganhou) {
            gameGC.fillText("VOCE GANHOU", SpaceInvaders.Width / 2, SpaceInvaders.Height / 2);
        }
    }

    //FUNCOES AUXILIARES
    private boolean EstaAlinhado(Entidade entidade1, Entidade entidade2) {
        return (entidade1.PosX == entidade2.PosX);
    }

    private void TiroAleatorio() {
        Random gerador = new Random();
        int n = gerador.nextInt(aliens.size());
        tiros.add(aliens.get(n).Atirar());
        ReloadingAliens = 0;
        if (this.EhInicio) {
            this.EhInicio = false;
        }
    }

    private void AtualizaDados() {
        try {
            String aux = this.Nivel + "|" + this.Moedas + "|" + this.player.getTipo();
            FileWriter myWriter = new FileWriter("LoadProgress.txt");
            myWriter.write(aux);
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AtualizaAlienEspecial() {
        if ((aliens.size() == 20 && spawnAlienEsp < 2) || (aliens.size() == 40 && spawnAlienEsp == 0)) {
            alienEspecial.add(new AlienEspecial());
            spawnAlienEsp++;
        }

        for (int i = 0; i < alienEspecial.size(); i++) {
            alienEspecial.get(i).MoverAlien();
            if (alienEspecial.get(i).Viagens == 5) {
                alienEspecial.remove(i);
                break;
            }
        }
    }

}
