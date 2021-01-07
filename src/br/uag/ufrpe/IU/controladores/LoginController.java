/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.IU.GUIFuncionario;
import br.uag.ufrpe.negocio.entidades.Funcionario;
import br.uag.ufrpe.negocio.excecoes.funcionario.FuncionarioNaoEncontradoException;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GABRIEL
 */
public class LoginController {

    private FachadaGerente fachadaGerente;
    private FachadaFuncionario fachadaFuncionario;


    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtLogin;

    @FXML
    private RadioButton labelGerente;

    @FXML
    private Button entrar;

    @FXML
    private ToggleGroup grup;

    @FXML
    private Label erroCpf;

    @FXML
    private Label erroSenha;

    public LoginController() {
        fachadaGerente = FachadaGerente.getFachadaGerente();
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    @FXML
    private void entrar(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");

        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Autenticado!");

        String cpf = txtLogin.getText();
        String senha = txtSenha.getText();
        RadioButton gerente = (RadioButton) grup.getSelectedToggle();
        boolean egerente = true;
        boolean verifica = true;

        if (gerente.equals("não")) {
            egerente = false;
        } else {
        }

        if (cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*")) {
            erroCpf.setText("Login invalido");
            verifica = false;
        }
        if (senha.length() < 3 || senha.isEmpty()) {
            erroSenha.setText("Senha invalida ou muito curta");
            verifica = false;
        }
        try {
            if (cpf.equals("12345678910") && senha.equals("admin")) {
                Parent root = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaGerente.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("Tela Principal");
                stage.show();
            } else if (egerente == true) {
                if (fachadaGerente.auntenticar(cpf, senha) == true) {
                    Parent root = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaGerente.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("Tela Principal");
                    stage.show();
                } else {
                    alertaErro.setContentText("Usuário não encontrada");
                    alertaErro.show();
                }
            } else {
                if (fachadaFuncionario.auntenticar(cpf, senha) == true) {
                    Parent root = FXMLLoader.load(getClass().getResource("/br/uag/ufrpe/IU/telas/TelaFuncionario.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("Tela Principal");
                    stage.show();
                } else {
                    alertaErro.setContentText("Usuário não encontrada");
                    alertaErro.show();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            erroCpf.setText("Entrada invalida");
        }

    }
}
