/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uag.ufrpe.IU;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Itamar Jr
 */
public class GUILogin extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaLogin.fxml"));

        Scene scene = new Scene(root);
        
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Tela Principal");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}
