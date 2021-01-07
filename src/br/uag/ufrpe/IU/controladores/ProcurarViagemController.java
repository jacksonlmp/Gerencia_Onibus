/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Data;
import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.time.LocalDate;
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
public class ProcurarViagemController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;

    @FXML
    private TextField txtPlacaOnibus;
    @FXML
    private TextField txtOrigem;
    @FXML
    private TextField txtDestino;
    @FXML
    private DatePicker dateSaida;
    @FXML
    private TextField txtHSaida;
    @FXML
    private DatePicker dateChegada;
    @FXML
    private TextField txtHChegada;
    @FXML
    private TextField txtCodigoViagem;

    @FXML
    private Label erroCodigo;

    public ProcurarViagemController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    @FXML
    private void procurarViagem(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao procurar viagem");

        int codigo;
        Viagem v;
        String dataSaidaProcura;
        
        if (txtCodigoViagem.getText().length() != 0) {

            try {
                codigo = Integer.parseInt(txtCodigoViagem.getText());
                v = fachadaFuncionario.procurarViagem(codigo);
                if (v != null) {
                    txtPlacaOnibus.setText(v.getOnibus().getPlaca());
                    txtDestino.setText(v.getDestino());
                    txtOrigem.setText(v.getOrigem());
                    txtHChegada.setText(v.getHorarioChegada());
                    txtHSaida.setText(v.getHorarioSaida());

                    LocalDate dataCheg = Data.converterDataParaLocalDate(v.getDataChegada());
                    LocalDate dataSaida = Data.converterDataParaLocalDate(v.getDataSaida());
                    dateChegada.setValue(dataCheg);
                    dateSaida.setValue(dataSaida);
                } else {
                    alertaErro.setContentText("Viagem não encontrada!");
                    alertaErro.show();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                erroCodigo.setText("Digite um número inteiro");
            }
        } 
        
        else {
            try {
                dataSaidaProcura = Data.converterDataParaString(dateSaida.getValue());

                Data.converteStringEmDataHora(dataSaidaProcura + " " + txtHSaida.getText());

                v = fachadaFuncionario.procurarViagem(dataSaidaProcura, txtHSaida.getText(), txtOrigem.getText(), txtDestino.getText());
                
                if (v != null) {
                    txtPlacaOnibus.setText(v.getOnibus().getPlaca());
                    txtHChegada.setText(v.getHorarioChegada());
                    
                    LocalDate dataCheg = Data.converterDataParaLocalDate(v.getDataChegada());
                    dateChegada.setValue(dataCheg);
                    
                } else {
                    alertaErro.setContentText("Viagem não encontrada!");
                    alertaErro.show();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }
    }

    @FXML
    public void voltar(ActionEvent event){
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
