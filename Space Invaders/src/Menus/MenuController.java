/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import space.invaders.SpaceInvaders;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Classe de controle do menu principal.
 *
 * @author pedro
 */
public class MenuController implements Initializable {

    /**
     * Imagem de nave do menu.
     */
    @FXML
    public ImageView iNave;

    /**
     * Botão para jogar.
     */
    @FXML
    public Button bJogar;

    /**
     * Botão para abria a loja.
     */
    @FXML
    private Button bLoja;

    /**
     * Botão para Abrir a ajuda.
     */
    @FXML
    private Button bAjuda;

    /**
     * Botão para Sair do jogo.
     */
    @FXML
    private Button bSair;

    /**
     * Auxiliar para abrir a próxima cena.
     */
    private Parent prox;

    /**
     * Método para abrir o jogo.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickJogar(ActionEvent event) {
        Stage stage = (Stage) bJogar.getScene().getWindow();
        stage.close();
        Engine jogo = new Engine();
    }

    /**
     * Método para abrir o a loja.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickLoja(ActionEvent event) {
        try {
            prox = FXMLLoader.load(getClass().getResource("Loja.fxml"));
            Scene scene = new Scene(prox);

            Stage stage = (Stage) bLoja.getScene().getWindow();

            stage.setTitle("Space Invaders - Loja");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro de interface");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Não foi possível carregar a loja.");
            mensagem.showAndWait();
        }
    }

    /**
     * Método para abrir o menu de ajuda.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickAjuda(ActionEvent event) {
        try {
            prox = FXMLLoader.load(getClass().getResource("Ajuda.fxml"));
            Scene scene = new Scene(prox);

            Stage stage = (Stage) bAjuda.getScene().getWindow();

            stage.setTitle("Space Invaders - Ajuda");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro de interface");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Não foi possível carregar a tela de ajuda.");
            mensagem.showAndWait();
        }
    }

    /**
     * Método para sair do jogo.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSair(ActionEvent event) {
        Stage stage = (Stage) bSair.getScene().getWindow();
        stage.close();
    }

    /**
     * Ação de passar o mouse por cima do botão de jogar.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverExitJogar(MouseEvent event) {
        this.bJogar.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
        this.bJogar.setGraphic(null);
    }

    /**
     * Ação de passar o mouse por cima do botão de jogar.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverJogar(MouseEvent event) {
        this.bJogar.setStyle("-fx-background-color: transparent; -fx-text-fill: #f26d21");
        this.bJogar.setGraphic(new ImageView("nave" + SpaceInvaders.TipoNave + ".png"));
    }

    /**
     * Ação de passar o mouse por cima do botão da loja.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverExitLoja(MouseEvent event) {
        this.bLoja.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
        this.bLoja.setGraphic(null);
    }

    /**
     * Ação de passar o mouse por cima do botão da loja.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverLoja(MouseEvent event) {
        this.bLoja.setStyle("-fx-background-color: transparent; -fx-text-fill: #f26d21");
        this.bLoja.setGraphic(new ImageView(new Image("/Menus/Imagens/moeda.gif", SpaceInvaders.TamBase, SpaceInvaders.TamBase, true, true)));

    }

    /**
     * Ação de passar o mouse por cima do botão de ajuda.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverExitAjuda(MouseEvent event) {
        this.bAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
        this.bAjuda.setGraphic(null);
    }

    /**
     * Ação de passar o mouse por cima do botão de ajuda.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void hoverAjuda(MouseEvent event) {
        this.bAjuda.setStyle("-fx-background-color: transparent; -fx-text-fill: #f26d21");
        this.bAjuda.setGraphic(new ImageView(new Image("vida.png", SpaceInvaders.TamBase, SpaceInvaders.TamBase, true, true)));
    }

    /**
     * Inicialização da imagem do menu correspondente à nave do usuário.
     *
     * @param url padrao do metodo
     * @param rb padrao do metodo
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iNave.setImage(new Image("/Menus/Imagens/nave" + SpaceInvaders.TipoNave + "menu.png"));
    }

}
