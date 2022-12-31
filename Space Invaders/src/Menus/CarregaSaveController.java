/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import space.invaders.SpaceInvaders;

/**
 * Classe que controla o menu de carregamento de progresso anterior
 *
 * @author pedro
 */
public class CarregaSaveController implements Initializable {

    /**
     * Botao para carregar progresso antigo.
     */
    @FXML
    private Button bSim;

    /**
     * Botao para começar um novo progresso.
     */
    @FXML
    private Button bNao;

    /**
     * Carrega um save do arquivo de progresso.
     *
     * @param event ação de clicar no botão.
     * @throws java.io.IOException ex exceção de erro
     */
    @FXML
    private void clickSim(ActionEvent event) throws IOException {
        this.CarregaSave();
        this.AbreMenu(bSim);
    }

    /**
     * Cria um save do arquivo de progresso.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickNao(ActionEvent event) {
        this.CriaSave();
        this.AbreMenu(bNao);
    }

    /**
     * Ação de passar o mouse por cima do botão de sim.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverExitSim(MouseEvent event) {
        this.bSim.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    /**
     * Ação de passar o mouse por cima do botão de sim.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverSim(MouseEvent event) {
        this.bSim.setStyle("-fx-background-color: transparent; -fx-text-fill: #f26d21");
    }

    /**
     * Ação de passar o mouse por cima do botão de sim.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverExitNao(MouseEvent event) {
        this.bNao.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    /**
     * Ação de passar o mouse por cima do botão de sim.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverNao(MouseEvent event) {
        this.bNao.setStyle("-fx-background-color: transparent; -fx-text-fill: #f26d21");
    }

    /**
     * Initializes the controller class.
     * @param url padrao
     * @param rb padrao
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Função usada quando o jogador opta por começar um novo jogo.
     */
    private void CriaSave() {
        try {
            File myObj = new File("LoadProgress.txt");
            if (myObj.createNewFile()) {
                System.out.println("Save do jogo criado: " + myObj.getName());
            } else {
                System.out.println("Save do jogo já existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro para abrir o save.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("LoadProgress.txt");
            String aux = SpaceInvaders.Nivel + "|" + SpaceInvaders.Moedas + "/" + SpaceInvaders.Score + "|" + SpaceInvaders.Naves[0] + SpaceInvaders.Naves[1] + SpaceInvaders.Naves[2] + SpaceInvaders.Naves[3];
            myWriter.write(aux);
            myWriter.close();
            System.out.println("Jogo aberto com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao abrir o save.");
            e.printStackTrace();
        }
    }

    /**
     * Função usada para carregar o progresso de um jogo salvo anteriormente.
     *
     * @throws IOException exceção de erro
     */
    private void CarregaSave() throws IOException {
        try {
            FileInputStream stream = new FileInputStream("LoadProgress.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            System.out.println(linha);

            while (linha != null) {
                String nivel = linha.substring(0, linha.indexOf('|'));
                String moedas = linha.substring(linha.indexOf('|') + 1, linha.indexOf('/'));
                String score = linha.substring(linha.indexOf('/') + 1, linha.lastIndexOf('|'));
                String naves = linha.substring(linha.lastIndexOf('|') + 1, linha.length());
                SpaceInvaders.Nivel = Integer.parseInt(nivel);
                SpaceInvaders.Moedas = Integer.parseInt(moedas);
                SpaceInvaders.Score = Integer.parseInt(score);
                
                if(naves.charAt(0) == '1') SpaceInvaders.Naves[0] = 1;
                if(naves.charAt(1) == '1') SpaceInvaders.Naves[1] = 1;
                if(naves.charAt(2) == '1') SpaceInvaders.Naves[2] = 1;
                if(naves.charAt(3) == '1') SpaceInvaders.Naves[3] = 1;

                linha = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao abrir o save.");
            e.printStackTrace();
        }
    }

    /**
     * Método para abrir o menu principal.
     *
     * @param b botão que foi escolhido.
     */
    private void AbreMenu(Button b) {
        try {
            // carrega FXML e monta cena
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);

            // acessa palco corrente
            Stage stage = (Stage) b.getScene().getWindow();

            // troca a cena: de login para principal
            stage.setTitle("Space Invaders - Menu Principal");
            stage.setResizable(false);
            stage.setScene(scene);
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
