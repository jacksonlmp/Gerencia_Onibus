/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class InformacoesViagensPassageiroController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtCpf2;
    @FXML
    private TextArea txtArea;
    @FXML
    private TextField txtResultado;

    public InformacoesViagensPassageiroController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
        
    }

    @FXML
    public void ultimasViagens(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao buscar as viagens!");

        if (txtCpf2.getText().length() >= 11) {
            try {
                String ultimas = fachadaFuncionario.procurarUltimasViagensDeUmPassageiro(txtCpf2.getText());
                txtArea.setText(ultimas);
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
    public void estaNaViagem(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao buscar as viagens!");

        Alert alertaInformacao = new Alert(Alert.AlertType.INFORMATION);
        alertaInformacao.setTitle("Informação");
        alertaInformacao.setHeaderText("Informação Sobre o Passageiro!");
        int codigo = -1;

        try {
            codigo = Integer.parseInt(txtCodigo.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }

        if (codigo != -1 && txtCpf.getText().length() >= 11) {
            try {
                boolean estaNaViagem = fachadaFuncionario.passageiroEstaNaViagem(codigo, txtCpf.getText());
                if (estaNaViagem) {
                    alertaInformacao.setContentText("O passageiro está na viagem!");
                    alertaInformacao.show();
                } else {
                    alertaInformacao.setContentText("Passageiro NÃO está na viagem!");
                    alertaInformacao.show();
                }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
