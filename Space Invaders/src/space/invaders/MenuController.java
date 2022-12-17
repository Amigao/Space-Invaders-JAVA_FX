/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class MenuController implements Initializable {

    @FXML
    public Button bJogar;

    @FXML
    private Button bLoja;

    @FXML
    private Button bAjuda;

    @FXML
    private Button bSair;

    @FXML
    private ImageView iFundo;

    @FXML
    private ImageView iNave;

    private int Nivel;
    public int Moedas;
    private int TipoNave;
    private Parent prox;

    @FXML
    private void clickJogar(ActionEvent event) {
        Stage stage = (Stage) bJogar.getScene().getWindow();
        stage.close();
        try {
            CarregaSave();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Engine jogo = new Engine(TipoNave, Nivel, Moedas, stage);
    }

    @FXML
    private void clickLoja(ActionEvent event) {
        try {
            // carrega FXML e monta cena
            prox = FXMLLoader.load(getClass().getResource("Loja.fxml"));
            Scene scene = new Scene(prox);

            // acessa palco corrente
            Stage stage = (Stage) bLoja.getScene().getWindow();

            // troca a cena: de login para principal
            stage.setTitle("Space Invaders - Loja");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro de interface");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Não foi possível carregar a loja.");
            mensagem.showAndWait();
        }
    }

    @FXML
    private void clickAjuda(ActionEvent event) {
        try {
            // carrega FXML e monta cena
            prox = FXMLLoader.load(getClass().getResource("Ajuda.fxml"));
            Scene scene = new Scene(prox);

            // acessa palco corrente
            Stage stage = (Stage) bAjuda.getScene().getWindow();

            // troca a cena: de login para principal
            stage.setTitle("Space Invaders - Ajuda");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro de interface");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Não foi possível carregar a tela de ajuda.");
            mensagem.showAndWait();
        }
    }

    @FXML
    private void clickSair(ActionEvent event) {
        Stage stage = (Stage) bSair.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.iFundo = new ImageView("fundoMenu.gif");
        try {
            CarregaSave();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.iNave = new ImageView("nave" + this.TipoNave + "menu.png");

    }

    private void CarregaSave() throws IOException {
        try {
            FileInputStream stream = new FileInputStream("LoadProgress.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            System.out.println(linha);

            while (linha != null) {
                String nivel = linha.substring(0, linha.indexOf('|'));
                String moedas = linha.substring(linha.indexOf('|') + 1, linha.lastIndexOf('|'));
                String nave = linha.substring(linha.lastIndexOf('|') + 1, linha.length());
                this.Nivel = Integer.parseInt(nivel);
                this.Moedas = Integer.parseInt(moedas);
                this.TipoNave = Integer.parseInt(nave);

                System.out.println("Nivel: " + this.Nivel);
                System.out.println("Moedas: " + this.Moedas);
                System.out.println("Moedas: " + this.TipoNave);

                linha = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao abrir o save.");
            e.printStackTrace();
        }
    }

}
