/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class VenderCancelarPassagemController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;

    @FXML
    private TextField txtCodigoViagem;
    @FXML
    private TextField txtCodigoPassagem;
    @FXML
    private TextField txtCodigoViagem2;
    @FXML
    private TextField txtCpf;

    public VenderCancelarPassagemController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    @FXML
    public void vender(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao vender a passagem!");

        int codigo = -1;
        int codigoPassagem = -1;
        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());
            codigoPassagem = Integer.parseInt(txtCodigoPassagem.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }

        if (codigo != -1 && codigoPassagem != -1) {
            try {
                fachadaFuncionario.venderPassagem(codigo, codigoPassagem);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao cancelar a passagem!");

        int codigo = -1;

        try {
            codigo = Integer.parseInt(txtCodigoViagem2.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }

        if (codigo != -1 && txtCpf.getText().length() >= 11) {
            try {
                fachadaFuncionario.cancelarPassagem(codigo, txtCpf.getText());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        } else {
            alertaErro.setContentText("Digite um CPF válido!");
            alertaErro.show();
        }
    }

    @FXML
    public void voltar(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
