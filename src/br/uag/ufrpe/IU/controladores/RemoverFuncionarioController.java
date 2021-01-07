/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Funcionario;
import br.uag.ufrpe.negocio.excecoes.funcionario.FuncionarioNaoEncontradoException;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
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
public class RemoverFuncionarioController implements Initializable {
    private final FachadaGerente fachadaGerente;  
    
    @FXML
    private TextField labelFuncionarioCpf;

    @FXML
    private Text erroNome;

    @FXML
    private Button removerFuncionario;

    @FXML
    private Text erroCpf;

    @FXML
    private TextField labelFuncionarioNome;
    
    public RemoverFuncionarioController() {
        fachadaGerente = FachadaGerente.getFachadaGerente();
    }

    @FXML
    void removerFuncionario(ActionEvent event) throws FuncionarioNaoEncontradoException {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Remover"); 
        
        String nome = labelFuncionarioNome.getText();
        String cpf = labelFuncionarioCpf.getText();
        boolean verificaFuncionario = true;
        
        if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*") ){
            erroCpf.setText("CPF invalido ou insuficiente");
            verificaFuncionario = false;
        }
        
        if(nome.isEmpty() || nome.matches("[0-9]*")){
            erroNome.setText("Nome invalido ou insuficiente");
            verificaFuncionario = false;
        }
        
        if(verificaFuncionario){
            String codigo;
            Funcionario func;
            codigo = labelFuncionarioCpf.getText();
            func = fachadaGerente.procurarFuncionario(codigo);
            
            try {              
                fachadaGerente.removerFuncionario(codigo);
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
            alertaErro.setContentText("Dados invalidos ou Funcionario Inexistente!");
            alertaErro.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
