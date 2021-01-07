/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class AlterarPassageiroController implements Initializable {
    private FachadaFuncionario fachadaFuncionario;
    
    @FXML
    private TextField labelPassageiroNome;

    @FXML
    private RadioButton labelPassageiroIdJovemS;

    @FXML
    private TextField labelPassageiroTelefone;

    @FXML
    private DatePicker labelPassageiroDataNascimento;

    @FXML
    private Button alterarPassageiro;

    @FXML
    private TextField labelPassageiroRg;

    @FXML
    private Button procurarPassageiro;

    @FXML
    private RadioButton labelPassageiroIdJovemN;

    @FXML
    private TextField labelPassageiroCpf;
    
    @FXML
    private Text erroCpf;

    @FXML
    private Text erroRg;
    
    @FXML
    private Text erroTelefone;
    
    public AlterarPassageiroController(){
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    @FXML
    void alterarPassageiro(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Alterar");
        
        String nome = labelPassageiroNome.getText();
        String cpf = labelPassageiroCpf.getText();
        String rg = labelPassageiroRg.getText();
        String telefone = labelPassageiroTelefone.getText();
        String dataNascimento = labelPassageiroDataNascimento.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        boolean idJovem;
        boolean verificaPassageiro = true;
        
        if(labelPassageiroIdJovemS.isSelected()){
            idJovem = true;
        }
        idJovem = false;
        
        if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*") ){
            erroCpf.setText("CPF invalido ou insuficiente");
            verificaPassageiro = false;
        }
        
        if(rg.isEmpty() || !rg.matches("[0-9]*")){
            erroRg.setText("RG invalido");
            verificaPassageiro = false;
        }
        
        if(telefone.isEmpty() || !telefone.matches("[0-9]*") || telefone.length() < 7){
            erroTelefone.setText("Numero Invalido");
            verificaPassageiro = false;
        }
        
        if (verificaPassageiro) {
            try {
                fachadaFuncionario.adicionarPassageiro(nome, cpf, rg, telefone, dataNascimento, idJovem);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Passageiro alterado com sucesso!");
                alertaConfirmacao.show();

            } 
            catch (Exception ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        } 
        
        else {
            alertaErro.setContentText("Erro ao preencher os dados!");
            alertaErro.show();
        }
    }
    
    @FXML
    void procurarPassageiro(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Funcionario não encontrado");
        
        String codigo;
        Passageiro pass;
        
        try {
            codigo = labelPassageiroCpf.getText();
            pass = fachadaFuncionario.procurarPassageiro(codigo);
            if (pass != null) {
                labelPassageiroNome.setText(pass.getNomeCompleto());
                labelPassageiroCpf.setText(pass.getCpf());
                labelPassageiroRg.setText(pass.getRg());
                labelPassageiroTelefone.setText(pass.getTelefone());
                                    
            } else {
                alertaErro.setContentText("Passageiro não encontrada!");
                alertaErro.show();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            erroCpf.setText("Entrada invalida");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
