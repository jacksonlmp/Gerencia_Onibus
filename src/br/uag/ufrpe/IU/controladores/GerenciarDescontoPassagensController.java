/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Data;
import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.excecoes.viagem.DescontoException;
import br.uag.ufrpe.negocio.excecoes.viagem.ViagemNaoExisteException;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class GerenciarDescontoPassagensController implements Initializable {

    private FachadaGerente fachadaGerente;

    @FXML
    private TextField txtCodigoViagem;
    @FXML
    private TextField txtCodigoViagem2;
    @FXML
    private TextField txtDesconto;
    @FXML
    private TextField txtLucro;

    public GerenciarDescontoPassagensController() {
        this.fachadaGerente = FachadaGerente.getFachadaGerente();
    }

    public void aplicarDesconto(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao aplicar o desconto");

        int codigo = -1;
        double desconto = -1;
        Viagem v;

        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());
            desconto = Double.parseDouble(txtDesconto.getText());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }
        if (codigo != -1 && desconto != -1) {
            try {
                fachadaGerente.aplicarDescontoEmTodasAsPassagens(codigo, desconto);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }

    }

    public void calcularLucro(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao procurar a viagem!");

        int codigo = -1;
        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }
        if (codigo != -1) {
            try {
                double lucro = fachadaGerente.calcularLucroDaViagem(codigo);
                txtLucro.setText(lucro + "");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }

    }

    public void voltar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
