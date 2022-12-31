package Menus;

import Classes.Engine;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import space.invaders.SpaceInvaders;

/**
 * Tela para quando o jogador vence a partida
 *
 * @author pedro
 */
public class TelaPerdedorController implements Initializable {

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
     * @throws java.io.IOException exceçao de erro
     */
    @FXML
    private void clickSim(ActionEvent event) throws IOException {
        Stage stage = (Stage) bSim.getScene().getWindow();
        stage.close();
        Engine jogo = new Engine();
    }

    /**
     * Cria um save do arquivo de progresso.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickNao(ActionEvent event) {
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
     *
     * @param url padrao
     * @param rb padrao
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método para abrir o menu principal.
     *
     * @param b botão que foi escolhido.
     */
    private void AbreMenu(Button b) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) b.getScene().getWindow();
            stage.setTitle("Space Invaders - Menu Principal");
            stage.setResizable(false);
            stage.setOnCloseRequest((WindowEvent e) -> {
                SpaceInvaders.AtualizaDados();
                System.exit(0);
            });
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
