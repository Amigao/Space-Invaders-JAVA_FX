/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import java.io.IOException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class AjudaController implements Initializable {

    @FXML
    private Button bSair;

    @FXML
    private void clickSair(ActionEvent event) {
        try {
//            AbreMenu(bSair);
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
        } catch (IOException e) {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro de interface");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Não foi possível carregar o menu principal.");
            mensagem.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
