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
public class PorcentagemPassageirosViagensController implements Initializable {

    private FachadaGerente fachadaGerente;

    @FXML
    private TextField txtPorcentagem;
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

    public PorcentagemPassageirosViagensController() {
        this.fachadaGerente = FachadaGerente.getFachadaGerente();
    }

    public void calcularPorcentagem(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");

        boolean verifica = true;
        double porcentagem;
        String dataInicio = Data.converterDataParaString(dateInicio.getValue());
        String dataFinal = Data.converterDataParaString(dateFinal.getValue());

        if (txtHSaida.getText().length() != 0) {
            try {
                Data.converteStringEmDataHora(dataFinal + " " + txtHSaida.getText());

                porcentagem = fachadaGerente.calculaPorcentagemPassageirosPorDatasEViagens(txtHSaida.getText(), txtOrigem.getText(), txtDestino.getText(), dataInicio, dataFinal);

                txtPorcentagem.setText(porcentagem + " %");
            } catch (Exception ex) {
                erroHora.setText("Hora inválida!");
                verifica = false;
            }
        } else if (txtOrigem.getText().length() != 0) {
            try {
                porcentagem = fachadaGerente.calculaPorcentagemPassageirosPorDatasEDestino(txtOrigem.getText(), txtDestino.getText(), dataInicio, dataFinal);
                txtPorcentagem.setText(porcentagem + " %");
            } catch (IntervaloDeDatasInvalidoException ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }

        } else {
            try {
                porcentagem = fachadaGerente.calculaPorcentagemPassageirosPorDatas(dataInicio, dataFinal);
                txtPorcentagem.setText(porcentagem + " %");
            } catch (IntervaloDeDatasInvalidoException ex) {
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
