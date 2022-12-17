/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class LojaController implements Initializable {

    @FXML
    private ImageView iFundo;
    @FXML
    private Text tMoedas;
    @FXML
    private Button bSair;
    @FXML
    private Button bComprarN1;
    @FXML
    private Button bComprarN2;
    @FXML
    private Button bComprarN3;
    @FXML
    private Button bComprarN4;

    private int Moedas;
    private int tipoNave;
    private int Nivel;

    private int Naves[] = {1, 0, 0, 0};

    @FXML
    private void clickSair(ActionEvent event) {
        try {
            // carrega FXML e monta cena
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);

            // acessa palco corrente
            Stage stage = (Stage) bSair.getScene().getWindow();

            // troca a cena: de login para principal
            stage.setTitle("Space Invaders - Menu Principal");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro de interface");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Não foi possível carregar o menu principal.");
            mensagem.showAndWait();
        }
    }

    @FXML
    private void clickN2(ActionEvent event) {
        if (this.Moedas >= 2) {
            bComprarN2.setDisable(true);
            if (Naves[1] == 1) {
                Alert mensagem = new Alert(Alert.AlertType.ERROR);
                mensagem.setTitle("Nave possuída");
                mensagem.setHeaderText(null);
                mensagem.setContentText("Você já tem essa nave!");
                mensagem.showAndWait();
            } else {
                Naves[1] = 1;
                this.Moedas -= 2;
                try {
                    String aux = this.Nivel + "|" + this.Moedas + "|" + 2;
                    FileWriter myWriter = new FileWriter("LoadProgress.txt");
                    myWriter.write(aux);
                    myWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Dinheiro insuficiente");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Você não tem moedas para comprar essa nave! Acerte mais aliens especiais para ganhar moedas.");
            mensagem.showAndWait();
        }
    }

    @FXML
    private void clickN3(ActionEvent event) {
        if (this.Moedas >= 3) {
            bComprarN3.setDisable(true);
            if (Naves[2] == 1) {
                Alert mensagem = new Alert(Alert.AlertType.ERROR);
                mensagem.setTitle("Nave possuída");
                mensagem.setHeaderText(null);
                mensagem.setContentText("Você já tem essa nave!");
                mensagem.showAndWait();
            } else {
                Naves[2] = 1;
                this.Moedas -= 3;
                try {
                    String aux = this.Nivel + "|" + this.Moedas + "|" + 3;
                    FileWriter myWriter = new FileWriter("LoadProgress.txt");
                    myWriter.write(aux);
                    myWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Dinheiro insuficiente");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Você não tem moedas para comprar essa nave! Acerte mais aliens especiais para ganhar moedas.");
            mensagem.showAndWait();
        }
    }

    @FXML
    private void clickN4(ActionEvent event) {
        if (this.Moedas >= 5) {
            bComprarN4.setDisable(true);

            if (Naves[3] == 1) {
                Alert mensagem = new Alert(Alert.AlertType.ERROR);
                mensagem.setTitle("Nave possuída");
                mensagem.setHeaderText(null);
                mensagem.setContentText("Você já tem essa nave!");
                mensagem.showAndWait();
            } else {
                Naves[3] = 1;
                this.Moedas -= 5;
                try {
                    String aux = this.Nivel + "|" + this.Moedas + "|" + 4;
                    FileWriter myWriter = new FileWriter("LoadProgress.txt");
                    myWriter.write(aux);
                    myWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Dinheiro insuficiente");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Você não tem moedas para comprar essa nave! Acerte mais aliens especiais para ganhar moedas.");
            mensagem.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CarregaSave();
        } catch (IOException ex) {
            Logger.getLogger(LojaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tMoedas = new Text("Moedas: " + this.Moedas);
        Image i = new Image("fundo_loja.jpg");
        iFundo.setImage(i);
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
                this.tipoNave = Integer.parseInt(nave);

                System.out.println("Moedas: " + this.Moedas);

                linha = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao abrir o save.");
            e.printStackTrace();
        }
    }

}
