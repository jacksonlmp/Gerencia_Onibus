/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Emily SAntos
 */
public class CadastrarOnibusController implements Initializable {

    private FachadaGerente fachadaGerente;

    @FXML
    private TextField PlacaOnibus;
    @FXML
    private TextField TotalPoltronas;
    @FXML
    private TextField motorista;

    public CadastrarOnibusController() {
        this.fachadaGerente = FachadaGerente.getFachadaGerente();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    private void CadastrarOnibus(ActionEvent event) {
        
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");

        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Passagem Cadastrada com sucesso!");

        boolean verifica = true;

        String carteiraMotorista = motorista.getText();

        String placa = PlacaOnibus.getText();
        int totalPoltronas = 0;
        try {
            totalPoltronas = Integer.parseInt(TotalPoltronas.getText());
        } catch (Exception ex) {
            verifica = false;
            alertaErro.setContentText("Digite um número inteiro de poltronas!");
            alertaErro.show();

        }

        List<Integer> poltronasObeso = new ArrayList<>();
        poltronasObeso.add(1);
        poltronasObeso.add(5);

        List<Integer> poltronasTotalReclinavel = new ArrayList<>();
        poltronasTotalReclinavel.add(2);
        poltronasTotalReclinavel.add(7);

        List<Integer> poltronasReclinavel = new ArrayList<>();
        poltronasReclinavel.add(8);
        poltronasReclinavel.add(10);

        if (verifica == true) {
            try {
                fachadaGerente.adicionarOnibus(carteiraMotorista, placa, totalPoltronas, poltronasObeso, poltronasTotalReclinavel, poltronasReclinavel);
                alertaConfirmacao.setContentText("Onibus cadastrado com sucesso!");
                alertaConfirmacao.show();
            } catch (Exception ex) {
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }
        else{
            alertaErro.setContentText("Alguns dados estão incorretos!");
            alertaErro.show();
        }
        
    }



}
