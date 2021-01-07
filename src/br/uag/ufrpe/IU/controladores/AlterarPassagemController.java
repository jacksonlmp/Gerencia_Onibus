/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.entidades.Passagem;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Emily Santos
 */
public class AlterarPassagemController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;
    

    @FXML
    private TextField novoPrecoFinal;
    @FXML
    private TextField novoTipoTarifa;
    @FXML
    private TextField novoCodigoPoltrona;
    @FXML
    private TextField novoTipoAssento;
    @FXML
    private TextField novoTipoGratuidade;
    @FXML
    private TextField novoServicoBordo;
    @FXML
    private TextField novoDependentes;
    @FXML
    private TextField codigoPassagem;
    @FXML
    private Label erroEntrada;
    @FXML
    private Label nomePassageiro;
    
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    public AlterarPassagemController(){
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }
    boolean verificaDados = true;
    int codigoConvertido = 0;
    String cpfPass;
 
    @FXML
    private void alterarPassagem(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        //alertaErro.setHeaderText("Passagem não encontrada");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("Passagem Alterada com sucesso!");
        
        verificaDados = true;
        
        //-----

        
        //-----

        if(novoTipoTarifa.getText().isEmpty() || novoTipoAssento.getText().isEmpty() || novoTipoGratuidade.getText().isEmpty() || novoServicoBordo.getText().isEmpty() || novoDependentes.getText().isEmpty()){
                alertaErro.setHeaderText("Alguns campos se encontram vazios.");
                alertaErro.show();
                verificaDados = false;
                
                return;
        }

        
        String codigo = codigoPassagem.getText();
        String preco = novoPrecoFinal.getText();
        String tipoTarifa = novoTipoTarifa.getText().toUpperCase();
        String codigoPoltrona = novoCodigoPoltrona.getText();
        String tipoAssento = novoTipoAssento.getText().toUpperCase();
        String tipoGratuidade = novoTipoGratuidade.getText().toUpperCase();
        String servicoBordo = novoServicoBordo.getText().toUpperCase();
        String dependentes = novoDependentes.getText().toUpperCase();
            
        double precoConverter = 50;
        int codigoP = 80;
        
        try{
            boolean isLanche = false, isDependentes = false, eDentroEstado = false;
            
            //--------------------------------------------------------
            try{
                precoConverter = Double.parseDouble(preco);
            }
            catch(Exception ex){
                alertaErro.setHeaderText("Insira apenas numeros no campo Preco Final da Passagem.");
                alertaErro.show();
                verificaDados = false;
            }

            try{
                codigoP = Integer.parseInt(codigoPoltrona);
            }
            catch(Exception ex){
                alertaErro.setHeaderText("Insira apenas numeros no campo Código da Poltrona.");
                alertaErro.show();
                verificaDados = false;
            }

            //---------------------------------------------------------
            
            
            switch(tipoTarifa){
                case "INTERMUNICIPAL":
                    eDentroEstado = true;
                    break;
                case "INTERESTADUAL":
                    eDentroEstado = false;
                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas INTERMUNICIPAL ou INTERESTADUAL no campo Tipo de Tarifa.");
                    alertaErro.show(); 
                    verificaDados = false;
            }
            
            switch(tipoGratuidade){
                case "IDOSO":
                    break;
                case "IDJOVEM":
                    break;
                case "PARCIAL IDOSO":
                    break;
                case "PARCIAL IDJOVEM":
                    break;
                case "NORMAL":
                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas IDOSO, IDJOVEM, PARCIAL IDOSO, PARCIAL IDJOVEM ou NORMAL no campo Tipo de Gratuidade.");
                    alertaErro.show();
                    verificaDados = false;
            }
            
            switch(tipoAssento){
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
                    verificaDados = false;
            }
            
            switch (servicoBordo) {
                case "SIM":
                  isLanche = true;

                  break;

                case "NÃO":
                  isLanche = false;

                  break;
                case "NAO":
                    isLanche = false;

                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas SIM ou NÃO no campo Serviço de Bordo.");
                    alertaErro.show();
                    verificaDados = false;
            }
                   
                              
            switch (dependentes) {
                case "SIM":
                  isDependentes = true;
                  break;

                case "NÃO":
                  isDependentes = false;
                  break;
                case "NAO":
                    isDependentes = false;
                    break;
                default:
                    alertaErro.setHeaderText("Insira apenas SIM ou NÃO no campo Dependentes.");
                    alertaErro.show();
                    verificaDados = false;
            }

            
            if(verificaDados){
                codigoConvertido = Integer.parseInt(codigo);

                
                fachadaFuncionario.alterarPassagem(codigoConvertido, cpfPass, precoConverter, eDentroEstado, codigoP, tipoAssento, tipoGratuidade, isLanche, isDependentes);
                
                alertaConfirmacao.show();
            }
          
        }catch(Exception ex){
            alertaErro.setHeaderText("Erro ao alterar os dados de Passagem");
            alertaErro.show();
        }
        

       
    }

    @FXML
    private void procurarPassagem(ActionEvent event) {
                
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Passagem não existe");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Confirmação");
        alertaConfirmacao.setHeaderText("ALterado com sucesso!");
        
             
      
        boolean verificaAlterarPassagem;
        
        String codigo = codigoPassagem.getText();
        

       if(codigo.isEmpty()){
            erroEntrada.setText("Entrada Vazia");
            verificaAlterarPassagem = false;
           
        }
       else{
           erroEntrada.setText("");
           verificaAlterarPassagem = true;

       }
       
        
        if(verificaAlterarPassagem){
            try{        
                codigoConvertido = Integer.parseInt(codigo);
                
                Passagem verificaPassagem = fachadaFuncionario.procurarPassagem(codigoConvertido);
            
                if(verificaPassagem != null){
                        Passageiro nome = verificaPassagem.getPassageiro();
                        cpfPass = nome.getCpf();
                        nomePassageiro.setText(nome.getNomeCompleto());
                        
                        
                        
                        novoPrecoFinal.setText(Double.toString(verificaPassagem.getPrecoTotal()));
                        if(verificaPassagem.iseDentroDoEstado() == true){
                            novoTipoTarifa.setText("Intermunicipal");
                        }
                        else{
                          novoTipoTarifa.setText("Interestadual");  
                        }
                        
                        novoCodigoPoltrona.setText(Integer.toString(verificaPassagem.getCodigoPoltrona()));
                        novoTipoAssento.setText(verificaPassagem.getTipoDeAssento());
                        novoTipoGratuidade.setText(verificaPassagem.getTipoDePassagem());

                        if(verificaPassagem.isLanche() == true){
                            novoServicoBordo.setText("Sim");
                        }
                        else{
                          novoServicoBordo.setText("Não");  
                        }

                        if(verificaPassagem.isCriancaColo() == true){
                            novoDependentes.setText("Sim");
                        }
                        else{
                          novoDependentes.setText("Não");  
                        }
                        
                    }
                else{
                    alertaErro.setContentText("");
                    alertaErro.show(); 

                }
            }
            catch(Exception ex){
                erroEntrada.setText("Digite apenas numeros");
            }

        }

    }

    @FXML
    private void removerPassagem(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacao de Remover");
        alert.setHeaderText("Você tem certeza que deseja remover a Passagem?");
        
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        

       
        String codigo = codigoPassagem.getText();
        boolean verificaAlterarPassagem = true;
        
        if(codigo.isEmpty()){
                erroEntrada.setText("Entrada Vazia");
                verificaAlterarPassagem = false;

            }
        else{
               erroEntrada.setText("");
               verificaAlterarPassagem = true;

           }
        
        if(verificaAlterarPassagem){
            
            try{
                 codigoConvertido = Integer.parseInt(codigo);
                 
                 Passagem passagem = fachadaFuncionario.procurarPassagem(codigoConvertido);
                 
                 
                 if(passagem != null){
                       Optional<ButtonType> result = alert.showAndWait();
                       if (result.get() == ButtonType.OK){
                           fachadaFuncionario.removerPassagem(codigoConvertido);
                       } else {
                           return;
                       }              
                     
                 }
                 else{
                    alertaErro.setHeaderText("Passagem não existe");
                    alertaErro.setContentText("");
                    alertaErro.show(); 
                 }
            }
            catch(Exception ex){
                alertaErro.setHeaderText("Campo Código da Passagem incorreto.");
                alertaErro.setContentText("Insira apenas números.");
                alertaErro.show(); 
                
            }
        }
    }
}
