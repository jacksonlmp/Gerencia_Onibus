/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.NegocioPassageiro;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import br.uag.ufrpe.negocio.NegocioPassagem;
import br.uag.ufrpe.negocio.NegocioPassageiro;
import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.entidades.Passagem;
import br.uag.ufrpe.negocio.excecoes.passageiro.PassageiroNaoExisteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Emily Santos
 */
public class CadastrarPassagemController implements Initializable {
    
    
    private FachadaFuncionario fachadaFuncionario;
    
    
    @FXML
    private TextField IdentificarPassageiro;
    @FXML
    private TextField PrecoPassagem;
    @FXML
    private TextField TipoDeAssento;
    @FXML
    private TextField numeroPoltrona;
    @FXML
    private TextField TipoGratuidade;
    @FXML
    private TextField tipoTarifa;
    @FXML
    private TextField servicoBordo;
    @FXML
    private TextField dependentes;
           
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     

    }
        
    
    public CadastrarPassagemController() {
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }
    
    int codigoPoltrona;
    double precoDouble;


    @FXML
    private void CadastrarPassagem(ActionEvent event) throws PassageiroNaoExisteException {
        
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Passagem Cadastrada com sucesso!");
        
        boolean verificaPassagem = true;
        
        boolean eDentroDoEstado = false;
        boolean possuiServicoBordo = false;
        boolean possuiCriancaColo = false;

        // -----------------
        
        String cpf = IdentificarPassageiro.getText();
        String preco = PrecoPassagem.getText();
        String assentoTipo = TipoDeAssento.getText().toUpperCase();
        String codigoP = numeroPoltrona.getText();
        String tipoDeGratuidade = TipoGratuidade.getText().toUpperCase();
        String tarifa = tipoTarifa.getText().toUpperCase();
        String lanche = servicoBordo.getText().toUpperCase();
        String criancaColo = dependentes.getText().toUpperCase();
        
        
        if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*") ){
            alertaErro.setContentText("Campo CPF vazio.");
            alertaErro.show();
            verificaPassagem = false;
        }
        
         
        if(preco.isEmpty()){
            alertaErro.setContentText("Erro! Campo preço de passagem vazio");
            alertaErro.show();
            verificaPassagem = false;
            return;
        }
        
        try{
            preco.replaceAll(",", ".");
            precoDouble = Double.parseDouble(preco);
            verificaPassagem = true;
        }
        catch(Exception ex){
                alertaErro.setHeaderText("Insira apenas numeros no campo Preço");
                alertaErro.show();
                verificaPassagem = false;
        }
        
        try{
            codigoPoltrona = Integer.parseInt(codigoP);
            verificaPassagem = true;
        }
        catch(Exception ex){
                alertaErro.setHeaderText("Insira apenas numeros no campo Número do Assento.");
                alertaErro.show();
                verificaPassagem = false;
            }       

        
        switch(assentoTipo){
                case "TOTAL RECINAVEL":
                case "TOTAL RECLINÁVEL":
                case "PARCIAL RECLINAVEL":
                case "PARCIAL RECLINÁVEL":
                case "OBESO":
                case "CONVENCIONAL":
                    break;
                    
                default:
                    alertaErro.setHeaderText("Insira apenas TOTAL RECLINAVEL, PARCIAL RECLINAVEL, OBESO ou CONVENCIONAL no campo Tipo de Assento.");
                    alertaErro.show(); 
                    verificaPassagem = false;
        }
        
        switch(tarifa){
                case "INTERMUNICIPAL":
                    eDentroDoEstado = true;
                    break;
                case "INTERESTADUAL":
                    eDentroDoEstado = false;
                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas INTERMUNICIPAL ou INTERESTADUAL no campo Tipo de Tarifa.");
                    alertaErro.show(); 
                    verificaPassagem = false;
            }
        
        switch(tipoDeGratuidade){
                case "IDOSO":
                    break;
                case "IDJOVEM":
                    break;
                case "PARCIAL IDOSO":
                    break;
                case "PARCIAL IDJOVEM":
                    break;
                case "NORMAL":
                    verificaPassagem = true;
                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas IDOSO, IDJOVEM, PARCIAL IDOSO, PARCIAL IDJOVEM ou NORMAL no campo Tipo de Gratuidade.");
                    alertaErro.show();
                    verificaPassagem = false;
            }
        
        switch (lanche) {
                case "SIM":
                  possuiServicoBordo = true;

                  break;

                case "NÃO":
                  possuiServicoBordo = false;

                  break;
                case "NAO":
                    possuiServicoBordo = false;

                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas SIM ou NÃO no campo Serviço de Bordo.");
                    alertaErro.show();
                    verificaPassagem = false;
            }
                   
                              
            switch (criancaColo) {
                case "SIM":
                  possuiCriancaColo = true;
                  break;

                case "NÃO":
                  possuiCriancaColo = false;
                  break;
                case "NAO":
                    possuiCriancaColo = false;
                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas SIM ou NÃO no campo Dependentes.");
                    alertaErro.show();
                    verificaPassagem = false;
            }

        if (verificaPassagem) {
            try {
                int codigo;
                
                codigo = fachadaFuncionario.adicionarPassagem(cpf, precoDouble, eDentroDoEstado, codigoPoltrona, assentoTipo, tipoDeGratuidade, possuiCriancaColo, possuiServicoBordo);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Passagem cadastrada com sucesso!\nCódigo da Passagem: " + codigo);
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


}

