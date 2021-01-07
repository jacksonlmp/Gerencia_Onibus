/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Data;
import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
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
 *
 * @author Itamar Jr
 */
public class ProcurarAlterarRemoverViagemController implements Initializable {

    private FachadaGerente fachadaGerente;

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
    private Label erroPlaca;
    @FXML
    private Label erroDatasHoras;
    @FXML
    private Label erroHoraSaida;
    @FXML
    private Label erroHoraChegada;
    @FXML
    private Label erroCodigo;

    public ProcurarAlterarRemoverViagemController() {
        this.fachadaGerente = FachadaGerente.getFachadaGerente();
    }

    @FXML
    private void alterarViagem(ActionEvent event) {

        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");

        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Confirmação ao alterar");

        boolean verifica = true;

        int codigo = 0;
        String placa = txtPlacaOnibus.getText();
        String origem = txtOrigem.getText();
        String destino = txtDestino.getText();
        String horarioSaida = txtHSaida.getText();
        String horarioChegada = txtHChegada.getText();

        String dataSaida = "";
        String dataChegada = "";
        String dataHoraChegada = null;
        String dataHoraSaida = null;

        if (placa.length() < 7) {
            erroPlaca.setText("Placa inválida");
            verifica = false;
        }

        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            erroCodigo.setText("Digite um número inteiro");
            verifica = false;
        }

        //Checa a hora de Saida
        try {
            dataSaida = Data.converterDataParaString(dateSaida.getValue());
            dataHoraSaida = dataSaida + " " + horarioSaida;
            Data.converteStringEmDataHora(dataHoraSaida);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //Label hora saida
            erroHoraSaida.setText("Hora inválida");
            verifica = false;
        }

        //Checa a hora de chegada
        try {
            dataChegada = Data.converterDataParaString(dateChegada.getValue());
            dataHoraChegada = dataChegada + " " + horarioChegada;
            Data.converteStringEmDataHora(dataHoraChegada);

            if (!Data.verificaDataHoraSaidaDataHoraChegadaValida(dataHoraSaida, dataHoraChegada)) {
                erroDatasHoras.setText("Data de Chegada menor do que a data de Saída");
                verifica = false;
            }
        } catch (Exception ex) {
            //Label hora chegada
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            erroHoraChegada.setText("Hora inválida");
            verifica = false;
        }

        if (verifica) {
            //Se entrar aqui, passou por todas as verificações, e vai tentar cadastrar.
            try {
                fachadaGerente.alterarViagem(codigo, placa, origem, destino, horarioSaida, horarioChegada, dataSaida, dataChegada);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Viagem alterada com sucesso!");
                alertaConfirmacao.show();

            } catch (Exception ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();

            }

        } else {
            alertaErro.setContentText("Erro ao preencher os dados!");
            alertaErro.show();
        }

    }

    @FXML
    private void procurarViagem(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Viagem não encontrada");

        int codigo;
        Viagem v;
        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());
            v = fachadaGerente.procurarViagem(codigo);
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

    public void removerViagem(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao remover");

        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Confirmação ao remover");

        boolean verifica = true;

        int codigo = 0;
        Viagem v;
        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());

        } catch (Exception ex) {
            verifica = false;
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            erroCodigo.setText("Digite um número inteiro");
        }
        if (verifica) {
            try {
                fachadaGerente.removerViagem(codigo);
                alertaConfirmacao.setContentText("Viagem removida com sucesso!");
                alertaConfirmacao.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
                
            }
        }
    }

    public void voltar(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
