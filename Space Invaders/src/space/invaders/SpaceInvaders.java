/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author pedro
 */
public class SpaceInvaders extends Application {

    public static int Width = 1920;
    public static int Height = 1080;

    public static int MatrizX = 31;
    public static int MatrizY = 16;

    public int Nivel = 1;

    public void AbreMenu(Button b) throws IOException {
        // carrega FXML e monta cena
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);

        // acessa palco corrente
        Stage stage = (Stage) b.getScene().getWindow();

        // troca a cena: de login para principal
        stage.setTitle("Space Invaders - Menu Principal");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    void CriaSave() {
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
            String aux = "1|10|1";
            myWriter.write(aux);
            myWriter.close();
            System.out.println("Jogo aberto com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao abrir o save.");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        CriaSave();
        
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Space Invaders - Menu Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
