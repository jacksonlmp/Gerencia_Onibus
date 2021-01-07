/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Motorista;
import br.uag.ufrpe.negocio.entidades.Onibus;
import br.uag.ufrpe.negocio.excecoes.motorista.MotoristaNaoExisteException;
import br.uag.ufrpe.negocio.excecoes.onibus.OnibusNaoExisteException;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Emily Santos
 */
public class AlterarOnibusController implements Initializable {
    
    private FachadaGerente fachadaGerente;

    @FXML
    private TextField placaOnibus;
    @FXML
    private Label erroEntrada;
    @FXML
    private TextField totalPoltronas;
    @FXML
    private TextField cnhMotorista;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public AlterarOnibusController(){
        fachadaGerente = fachadaGerente.getFachadaGerente();
    }
    
    boolean verificaDados = true;
    int poltronaConverter = 0;

    @FXML
    private void procurarOnibus(ActionEvent event) {
        
         Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        
        verificaDados = true;
        
        
        String placaO = placaOnibus.getText();
        
        if(placaOnibus.getText().isEmpty()){
            erroEntrada.setText("Campo Placa vazia");
            verificaDados = false;
        }
        else{
            erroEntrada.setText("");
            verificaDados = true;
            
        }
        
        if(verificaDados){
            try{
                Onibus verificaOnibus = fachadaGerente.procurarOnibus(placaO);
                
                if(verificaOnibus != null){
                    
                    String cnh = verificaOnibus.getMotorista().getNumeroCarteiraMotorista();
                    cnhMotorista.setText(cnh);
                    int totalInt = verificaOnibus.getTotalPoltronas();
                    String totalPltronas = Integer.toString(totalInt);
                    totalPoltronas.setText(totalPltronas);
                }
                else{
                    
                }
            }
            catch(Exception ex){
                alertaErro.setContentText("Erro!");
                alertaErro.show(); 
            }
        }
               
    
}

    @FXML
    private void alterarOnibus(ActionEvent event) throws MotoristaNaoExisteException {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Onibus Alterado com sucesso!");
        
        verificaDados = true;
        
        if(placaOnibus.getText().isEmpty() || totalPoltronas.getText().isEmpty() || cnhMotorista.getText().isEmpty()){
            alertaErro.setContentText("Erro Entradas em Branco");
            alertaErro.show(); 
            verificaDados = false;
            return;
        }
        
        String novaPlaca = placaOnibus.getText();
        String novoCNH = cnhMotorista.getText();
        String notoPoltronas = totalPoltronas.getText();
        
        if(verificaDados){
            
        
            List<Integer> poltronasObeso = new ArrayList<>();
            poltronasObeso.add(1);
            poltronasObeso.add(3);


            List<Integer> poltronasReclinavel = new ArrayList<>();
            poltronasReclinavel.add(9);
            poltronasReclinavel.add(10);
            poltronasReclinavel.add(11);
            poltronasReclinavel.add(12);

            List<Integer> poltronasTotalReclinavel = new ArrayList<>();
            poltronasTotalReclinavel.add(17);
            poltronasTotalReclinavel.add(18);
            poltronasTotalReclinavel.add(19);
            poltronasReclinavel.add(20);
            
        
            try{
                int novoP = Integer.parseInt(notoPoltronas);
                
                fachadaGerente.alterarOnibus(novoCNH, novaPlaca, novoP, poltronasObeso, poltronasTotalReclinavel, poltronasReclinavel);
                
                alertaConfirmacao.show();
                exit();
            }
            catch(Exception ex){
                alertaErro.setHeaderText("Erro ao alterar os dados de Onibus");
                alertaErro.show();
            }
        }
        
        
    }

    @FXML
    private void removerOnibus(ActionEvent event) throws OnibusNaoExisteException {
        
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Onibus removido com sucesso!");
        
        
        String novaPlaca = placaOnibus.getText();
        
        if(novaPlaca.isEmpty()){
            verificaDados = false;
            alertaErro.setHeaderText("Placa vazia");
            alertaErro.show();
            return;
            
        }
        verificaDados = true;
        
        Onibus exOnibus = fachadaGerente.procurarOnibus(novaPlaca);
        
        if(exOnibus != null){
            fachadaGerente.removerOnibus(novaPlaca);
            alertaConfirmacao.setHeaderText("");
            alertaConfirmacao.show();
        }
        else{
            alertaErro.setHeaderText("Erro ao remover os dados de Onibus");
            alertaErro.show();
        }
        
    }
    
}
