/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGráfica;

import Classes.Engine;
import Classes.Entidade;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import space.invaders.SpaceInvaders;

/**
 * Toda a interface gráfica do jogo é construída nessa classe.
 *
 * @author pedro
 */
public class Tela {

    /**
     * Fundo do jogo que se movimenta de acordo com o nível.
     */
    private final PlanoDeFundo fundo;

    /**
     * Lista de explosões do jogo. Acontecem quando um projétil se choca com uma
     * entidade.
     */
    private ArrayList<Explosao> explosoes;

    /**
     * Thread responsável por criar e apagar as explosões.
     */
    private Thread loopExplosoes;

    /**
     * Laço responsável por atualizar periodicamente a tela do jogo.
     */
    private AnimationTimer loopAnimacao;

    /**
     * Cena do jogo
     */
    private Scene gameScene;

    /**
     * Estágio (janela) do jogo
     */
    private Stage gameStage;

    /**
     * Grupo de elementos do JavaFX que compõem a cena
     */
    private Group grupoRaiz;

    /**
     * Tela onde pinta-se as imagens
     */
    private Canvas gameCanvas;

    /**
     * Ferramenta para pintura do canvas
     */
    private GraphicsContext gameGC;

    /**
     * Fonte padrão do jogo
     */
    private Font font = Font.font("Century Gothic", FontWeight.EXTRA_BOLD, SpaceInvaders.TamBase - 19);

    /**
     * Engine do jogo que se comunica periodicamente com a interface para
     * atualizar as posições das entidades e pegar as informações necessárias.
     */
    private Engine engine;

    /**
     * Construtor da interface vincula a engine do jogo à engine de controle
     * local da interface, salva a tela do menu e inicia os loops de controle da
     * interface.
     *
     * @param engine engine em que o jogo está rodando
     */
    public Tela(Engine engine) {
        this.engine = engine;
        this.criaStage();
        this.criaLoop();

        this.fundo = new PlanoDeFundo();
        this.explosoes = new ArrayList<>();
    }

    /**
     * Método de criação da janela do jogo.
     */
    private void criaStage() {
        gameStage = new Stage();
        gameStage.setTitle("Space Invaders");
        gameStage.setMaximized(true);
        gameStage.setResizable(false);

        grupoRaiz = new Group();

        gameScene = new Scene(grupoRaiz);
        gameStage.setScene(gameScene);
        gameCanvas = new Canvas(SpaceInvaders.Width, SpaceInvaders.Height);
        grupoRaiz.getChildren().add(gameCanvas);
        gameGC = gameCanvas.getGraphicsContext2D();

        gameStage.setOnCloseRequest((WindowEvent e) -> {
            SpaceInvaders.AtualizaDados();
            System.exit(0);
        });

        engine.MovimentacaoNave(gameScene);
        gameStage.show();
    }

    /**
     * Método chamado ao fim de jogo. Fecha a janela do jogo, abre a seguinte e
     * salva o progresso do jogador
     *
     * @param Ganhou booleano de vitória útil para abrir a tela de vencedor
     * @param Perdeu booleano de derrota útil para abrir a tela de perdedor
     */
    public void encerraJogo(boolean Ganhou, boolean Perdeu) {
        SpaceInvaders.AtualizaDados();
        loopAnimacao.stop();
        loopExplosoes.stop();
        gameStage.close();

        Parent root;
        Scene scene;
        Stage stage = new Stage();

        if (Ganhou) {
            SpaceInvaders.Nivel++;
            SpaceInvaders.Moedas++;
            try {
                root = FXMLLoader.load(getClass().getResource("/Menus/TelaVencedor.fxml"));
                scene = new Scene(root);
                stage.setTitle("Space Invaders - VOCÊ VENCEU!");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setOnCloseRequest((WindowEvent e) -> {
                    SpaceInvaders.AtualizaDados();
                    System.exit(0);
                });
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Perdeu) {
            try {
                root = FXMLLoader.load(getClass().getResource("/Menus/TelaPerdedor.fxml"));
                scene = new Scene(root);
                stage.setTitle("Space Invaders - Você perdeu :( ");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setOnCloseRequest((WindowEvent e) -> {
                    SpaceInvaders.AtualizaDados();
                    System.exit(0);
                });
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                root = FXMLLoader.load(getClass().getResource("/Menus/Menu.fxml"));
                scene = new Scene(root);
                stage.setTitle("Space Invaders - Menu Principal");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setOnCloseRequest((WindowEvent e) -> {
                    SpaceInvaders.AtualizaDados();
                    System.exit(0);
                });
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                Alert mensagem = new Alert(Alert.AlertType.ERROR);
                mensagem.setTitle("Erro de interface");
                mensagem.setHeaderText(null);
                mensagem.setContentText("Não foi possível carregar o menu principal.");
                mensagem.showAndWait();
            }

        }
    }

    /**
     * Método que cria e inicia os laços de atualização da interface gráfica.
     */
    private void criaLoop() {
        this.loopExplosoes = new Thread(() -> {
            while (true) {
                try {
                    sleep(65);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < explosoes.size(); i++) {
                    explosoes.get(i).atualiza();
                    if (explosoes.get(i).fimAnimacao()) {
                        explosoes.remove(i);
                        i--;
                    }
                }
            }
        });

        loopAnimacao = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                AtualizaTela();
                if (engine.isGanhou() || engine.isPerdeu()) {
                    encerraJogo(engine.isGanhou(), engine.isPerdeu());
                }
            }
        };

        loopAnimacao.start();
        loopExplosoes.start();

    }

    /**
     * Método que adiciona a animação de explosão.
     *
     * @param PosX posição horizontal onde a explosão vai ser adicionada.
     * @param PosY posição vertical onde a explosão vai ser adicionada.
     */
    public void addExplosao(int PosX, int PosY) {
        explosoes.add(new Explosao(PosX, PosY));
    }

    /**
     * Método que faz a atualização de toda a tela do jogo. Atualiza a posição
     * do plano de fundo, desenha as explosões e, através da função de pegar as
     * entidades da engine, desenha todas elas em suas respectivas posições.
     * Escreve também as informações de nível e do usuário nos cantos da tela.
     */
    public void AtualizaTela() {

        fundo.AtualizaFundo();
        gameGC.drawImage(fundo.Desenho, 0, fundo.PosY);
        gameGC.drawImage(fundo.Desenho, 0, fundo.PosY - SpaceInvaders.Height);

        //desenha as entidades
        ArrayList<Entidade> lista = new ArrayList<>();
        engine.getEntidades(lista);
        for (int i = 0; i < lista.size(); i++) {
            gameGC.drawImage(lista.get(i).getDesenho(), lista.get(i).getPosX(), lista.get(i).getPosY());
        }

        //desenha os explosoes
        for (int i = 0; i < explosoes.size(); i++) {
            gameGC.drawImage(explosoes.get(i).Desenho, explosoes.get(i).PosX, explosoes.get(i).PosY);
        }

        //escreve os dados na tela
        gameGC.setFill(Color.WHITESMOKE);
        gameGC.setFont(font);
        gameGC.fillText("Nível " + SpaceInvaders.Nivel, 0, 45);
        gameGC.fillText("Score: " + (SpaceInvaders.Score + engine.getScore()), SpaceInvaders.Width - 250, 45);
        int vidas = engine.vidasPlayer();
        for (int i = 0; i < vidas; i++) {
            gameGC.drawImage(new Image("vida.png", 100, 100, true, true), i * 30 - 20, SpaceInvaders.Height - 40);
        }

        gameGC.drawImage(new Image("moeda2.png", 110, 110, true, true), SpaceInvaders.Width - 325, SpaceInvaders.Height - 60);
        gameGC.fillText((SpaceInvaders.Moedas + engine.getMoedas()) + " moedas", SpaceInvaders.Width - 250, SpaceInvaders.Height - 2);

    }

}
