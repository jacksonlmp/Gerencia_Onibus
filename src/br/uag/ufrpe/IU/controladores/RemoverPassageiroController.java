/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.excecoes.passageiro.PassageiroNaoExisteException;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class RemoverPassageiroController implements Initializable {
    private FachadaFuncionario fachadaFuncionario;
    
    @FXML
    private TextField labelPassageiroNome;

    @FXML
    private Button removerPassageiro;

    @FXML
    private TextField labelPassageiroCpf;
    
    @FXML
    private Text erroCpf;
    
    @FXML
    private Text erroNome;

    public RemoverPassageiroController() {
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }
    
    @FXML
    void removerPassageiro(ActionEvent event) throws PassageiroNaoExisteException {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Remover"); 
        
        String nome = labelPassageiroNome.getText();
        String cpf = labelPassageiroCpf.getText();
        boolean verificaPassageiro = true;
        
        if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*") ){
            erroCpf.setText("CPF invalido ou insuficiente");
            verificaPassageiro = false;
        }
        
        if(nome.isEmpty() || nome.matches("[0-9]*")){
            erroNome.setText("Nome invalido ou insuficiente");
            verificaPassageiro = false;
        }
        
        if(verificaPassageiro){
            String codigo;
            Passageiro pass;
            codigo = labelPassageiroCpf.getText();
            pass = fachadaFuncionario.procurarPassageiro(codigo);
            
            
            try {              
                fachadaFuncionario.removerPassageiro(pass);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Passageiro removido com sucesso!");
                alertaConfirmacao.show();  
            } 
            catch (Exception ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        } 
        
        else {
            alertaErro.setContentText("Dados invalidos ou Passageiro Inexistente!");
            alertaErro.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
