/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import space.invaders.SpaceInvaders;
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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class LojaController implements Initializable {

    /**
     * Imagem de plano de fundo
     */
    @FXML
    private ImageView iFundo;

    /**
     * Texto que diz as moedas do jogador
     */
    @FXML
    private Text tMoedas;

    /**
     * Botao de saída pro menu
     */
    @FXML
    private Button bSair;

    /**
     * Botão para selecionar a nave 1
     */
    @FXML
    private Button bSel1;

    /**
     * Botão para selecionar a nave 2
     */
    @FXML
    private Button bSel2;

    /**
     * Botão para selecionar a nave 3
     */
    @FXML
    private Button bSel3;

    /**
     * Botão para selecionar a nave 4
     */
    @FXML
    private Button bSel4;

    /**
     * Botão para comprar nave 2
     */
    @FXML
    private Button bComprarN2;

    /**
     * Botão para comprar nave 3
     */
    @FXML
    private Button bComprarN3;

    /**
     * Botão para comprar nave4
     */
    @FXML
    private Button bComprarN4;

    /**
     * Retângulo da nave 2.
     */
    @FXML
    private Rectangle ret2;

    /**
     * Retângulo da nave 3.
     */
    @FXML
    private Rectangle ret3;

    /**
     * Retângulo da nave 4.
     */
    @FXML
    private Rectangle ret4;

    /**
     * Seleciona a nave 1 para o usuário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSel1(ActionEvent event) {
        SpaceInvaders.TipoNave = 1;
        AtualizaLoja();
    }

    /**
     * Seleciona a nave 2 para o usuário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSel2(ActionEvent event) {
        SpaceInvaders.TipoNave = 2;
        AtualizaLoja();
    }

    /**
     * Seleciona a nave 3 para o usuário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSel3(ActionEvent event) {
        SpaceInvaders.TipoNave = 3;
        AtualizaLoja();
    }

    /**
     * Seleciona a nave 4 para o usuário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSel4(ActionEvent event) {
        SpaceInvaders.TipoNave = 4;
        AtualizaLoja();
    }

    /**
     * Retorna ao menu inicial.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickSair(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) bSair.getScene().getWindow();
            stage.setOnCloseRequest((WindowEvent e) -> {
                System.exit(0);
            });
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

    /**
     * Compra a nave 2 se há moedas disponíveis. Printa erro caso contrário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickN2(ActionEvent event) {
        if (SpaceInvaders.Moedas >= 2) {
            if (SpaceInvaders.Naves[1] == 1) {
                Alert mensagem = new Alert(Alert.AlertType.ERROR);
                mensagem.setTitle("Nave possuída");
                mensagem.setHeaderText(null);
                mensagem.setContentText("Você já tem essa nave!");
                mensagem.showAndWait();
            } else {
                SpaceInvaders.Naves[1] = 1;
                SpaceInvaders.Moedas -= 2;
                SpaceInvaders.TipoNave = 2;
                AtualizaLoja();
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
     * Compra a nave 3 se há moedas disponíveis. Printa erro caso contrário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickN3(ActionEvent event) {
        if (SpaceInvaders.Moedas >= 3) {
            if (SpaceInvaders.Naves[2] == 1) {
                Alert mensagem = new Alert(Alert.AlertType.ERROR);
                mensagem.setTitle("Nave possuída");
                mensagem.setHeaderText(null);
                mensagem.setContentText("Você já tem essa nave!");
                mensagem.showAndWait();
            } else {
                SpaceInvaders.Naves[2] = 1;
                SpaceInvaders.Moedas -= 3;
                SpaceInvaders.TipoNave = 3;
                AtualizaLoja();
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
     * Compra a nave 4 se há moedas disponíveis. Printa erro caso contrário.
     *
     * @param event ação de clicar no botão.
     */
    @FXML
    private void clickN4(ActionEvent event) {
        if (SpaceInvaders.Moedas >= 4) {
            SpaceInvaders.Naves[3] = 1;
            SpaceInvaders.Moedas -= 4;
            SpaceInvaders.TipoNave = 4;
            AtualizaLoja();
        } else {
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Dinheiro insuficiente");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Você não tem moedas para comprar essa nave! Acerte mais aliens especiais para ganhar moedas.");
            mensagem.showAndWait();
        }
    }

    /**
     * Inicializa a loja com o escrito das moedas do jogador e configurando as
     * naves disponíveis de acordo com a lista de naves possuídas.
     *
     * @param url padrao do metodo
     * @param rb padrao do metodo
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AtualizaLoja();
        tMoedas.setText("Moedas: " + SpaceInvaders.Moedas);
    }

    /**
     * Função que verifica quais as naves já adquiridas pelo jogador e modifica
     * a loja de acordo com isso.
     */
    private void AtualizaLoja() {
        bSel1.setDisable(false);
        bSel3.setVisible(false);
        bSel4.setVisible(false);
        bSel2.setVisible(false);

        tMoedas.setText("Moedas: " + SpaceInvaders.Moedas);
        if (SpaceInvaders.Naves[1] == 1) {
            bComprarN2.setVisible(false);
            bSel2.setVisible(true);
            bSel2.setDisable(false);
            ret2.setFill(Color.valueOf("#a4ffad40"));
            ret2.setStroke(Color.valueOf("#26ff00"));
        }
        if (SpaceInvaders.Naves[2] == 1) {
            bSel3.setVisible(true);
            bSel3.setDisable(false);
            bComprarN3.setVisible(false);
            ret3.setFill(Color.valueOf("#a4ffad40"));
            ret3.setStroke(Color.valueOf("#26ff00"));
        }
        if (SpaceInvaders.Naves[3] == 1) {
            bSel4.setVisible(true);
            bSel4.setDisable(false);
            bComprarN4.setVisible(false);
            ret4.setFill(Color.valueOf("#a4ffad40"));
            ret4.setStroke(Color.valueOf("#26ff00"));
        }

        switch (SpaceInvaders.TipoNave) {
            case 1:
                bSel1.setDisable(true);
                break;
            case 2:
                bSel2.setDisable(true);
                break;
            case 3:
                bSel3.setDisable(true);
                break;
            case 4:
                bSel4.setDisable(true);
                break;
            default:
                break;
        }

        SpaceInvaders.AtualizaDados();

    }

}
