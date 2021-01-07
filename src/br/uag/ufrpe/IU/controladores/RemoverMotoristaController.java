/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Motorista;
import br.uag.ufrpe.negocio.excecoes.motorista.MotoristaNaoExisteException;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author GABRIEL
 */
public class RemoverMotoristaController implements Initializable {
    private  FachadaGerente fachadaGerente;  

    @FXML
    private Label cnhMotorista;

    @FXML
    private TextField txtCnhMotorista;

    @FXML
    private Label erroCnh;

    @FXML
    private Button removerMotorista;

 

    @FXML
    private void removerMotorista(ActionEvent event) throws MotoristaNaoExisteException {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Remover");
        
        String cnh = txtCnhMotorista.getText();
        boolean verifica = true; 
        
         if(!cnh.matches(("[0-9]*"))){
            erroCnh.setText("Apenas numeros");
            verifica= false; 
        } 
           if(verifica){
            String codigo;
            Motorista motorista;
            codigo = txtCnhMotorista.getText();
            motorista = fachadaGerente.procurarMotorista(codigo);
            
            try {              
                fachadaGerente.removerMotorista(codigo);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Funcionario removido com sucesso!");
                alertaConfirmacao.show();  
            } 
            catch (Exception ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        } 
        
        else {
            alertaErro.setContentText("Dados invalidos ou Motorista Inexistente!");
            alertaErro.show();
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    
}
