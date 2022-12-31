/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import Classes.Engine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main do jogo. Abre o menu e seta variáveis gerais do jogo e úteis para
 * diversas classes.
 *
 * @author pedro
 */
public class SpaceInvaders extends Application {

    /**
     * Largura da tela que o jogo funciona.
     */
    public static int Width = 1920;

    /**
     * Altura da tela que o jogo funciona.
     */
    public static int Height = 1050;

    /**
     * Tamanho base das imagens usadas no jogo. A maioria das imagens tem 64
     * pixels de altura e largura, mas algumas podem ter metade ou o dobro
     * disso.
     */
    public static int TamBase = 64;

    /**
     * Vetor das naves obtidas. 1 para obtida, 0 para não obtida. Jogos novos
     * começam somente com a nave 1 obtidade.
     */
    public static int Naves[] = {1, 0, 0, 0};

    /**
     * Nível que o jogador está. Jogos novos começam no nível 1.
     */
    public static int Nivel = 1;

    /**
     * Tipo da nave que o jogador está usando. Jogos novos começam com a nave 1.
     */
    public static int TipoNave = 1;

    /**
     * Quantidade de moedas do jogador. Moedas são obtidas ao atingir os aliens
     * especiais e passar de nível. Jogos novos começam com 0 moedas;
     */
    public static int Moedas = 10;

    /**
     * Pontuação do jogador.
     */
    public static int Score = 0;

    /**
     * Função que abre o menu principal e inicia, portanto, o jogo.
     *
     * @param primaryStage estágio inicial do programa; estágio vinculado ao
     * menu principal
     * @throws IOException exceção de erro
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Menus/CarregaSave.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Space Invaders - Carregar progresso");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método que salva no documento de progresso as informações do jogo.
     */
    public static void AtualizaDados() {
        try {
            String aux = SpaceInvaders.Nivel + "|" + SpaceInvaders.Moedas + "/" + SpaceInvaders.Score + "|" + SpaceInvaders.Naves[0] + SpaceInvaders.Naves[1] + SpaceInvaders.Naves[2] + SpaceInvaders.Naves[3];
            FileWriter myWriter = new FileWriter("LoadProgress.txt");
            myWriter.write(aux);
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
