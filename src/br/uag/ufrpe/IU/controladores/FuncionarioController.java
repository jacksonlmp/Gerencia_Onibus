/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class FuncionarioController implements Initializable {

    private Pane pane;

    @FXML
    private Pane paneTelas;
    @FXML
    private AnchorPane paneTelaAtual;

    @FXML
    public void procurarViagem() {
        paneTelas.getChildren().clear();

        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaProcurarViagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void listarViagens() {
        paneTelas.getChildren().clear();

        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaListagemViagens.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void buscarInformacoesPassageiro() {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaInformacoesViagensPassageiro.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    public void informacoesViagem() {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaInformacoesViagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    public void venderCancelarPassagem() {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaVenderCancelarPassagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    public void listarPassageirosViagem() {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaListarPassageirosViagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    public void listarPoltronasViagem() {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaListarPoltronasViagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void cadastrarPassageiro(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaCadastrarPassageiro.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void procurarPassageiro(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaAlterarPassageiro.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void removerPassageiro(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaRemoverPassageiro.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void listagemPassageiros(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaListagemPassageiros.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void cadastrarPassagem(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaCadastrarPassagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void alterarPassagem(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaAlterarPassagem.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void listagemPassagens(ActionEvent event) {
        paneTelas.getChildren().clear();            
            
        try {
            pane = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaListagemPassagens.fxml"));
            paneTelas.getChildren().addAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
