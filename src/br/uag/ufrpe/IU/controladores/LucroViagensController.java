/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Data;
import br.uag.ufrpe.negocio.excecoes.datas.IntervaloDeDatasInvalidoException;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
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
public class LucroViagensController implements Initializable {

    private FachadaGerente fachadaGerente;

    @FXML
    private TextField txtLucroDatas;
    @FXML
    private TextField txtOrigem;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtHSaida;
    @FXML
    private Label erroHora;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFinal;

    public LucroViagensController() {
        this.fachadaGerente = FachadaGerente.getFachadaGerente();
    }

    public void calcularLucroDatas(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");

        boolean verifica = true;
        double lucro;
        String dataInicio = Data.converterDataParaString(dateInicio.getValue());
        String dataFinal = Data.converterDataParaString(dateFinal.getValue());

        if (txtHSaida.getText().length() != 0) {
            try {
                Data.converteStringEmDataHora(dataFinal + " " + txtHSaida.getText());

                lucro = fachadaGerente.calcularLucroTotalPorDatasEViagens(txtHSaida.getText(), txtOrigem.getText(), txtDestino.getText(), dataInicio, dataFinal);

                txtLucroDatas.setText("" + lucro);
            } catch (Exception ex) {
                erroHora.setText("Hora inválida!");
                verifica = false;
            }
        } else if (txtOrigem.getText().length() != 0) {
            try {
                lucro = fachadaGerente.calcularLucroTotalPorDatasEDestino(txtOrigem.getText(), txtDestino.getText(), dataInicio, dataFinal);
                txtLucroDatas.setText("" + lucro);
            } catch (IntervaloDeDatasInvalidoException ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }

        } else {
            try {
                lucro = fachadaGerente.calcularLucroTotalPorDatas(dataInicio, dataFinal);
                txtLucroDatas.setText("" + lucro);
            } catch (IntervaloDeDatasInvalidoException ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
