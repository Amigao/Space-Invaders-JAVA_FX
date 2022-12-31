package Menus;

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
import javafx.stage.WindowEvent;
import space.invaders.SpaceInvaders;

/**
 * Classe de controle FXML da tela de ajuda
 *
 * @author pedro
 */
public class AjudaController implements Initializable {

    /**
     * Botão para voltar para o menu principal.
     */
    @FXML
    private Button bSair;

    /**
     * Retorna ao menu principal.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSair(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);

            Stage stage = (Stage) bSair.getScene().getWindow();
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

    /**
     * IInicializa a classe construtora.
     *
     * @param url padrao do metodo
     * @param rb padrao do metodo
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
