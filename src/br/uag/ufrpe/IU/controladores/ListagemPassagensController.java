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
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author EMily SAntos
 */
public class ListagemPassagensController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;
    
    @FXML
    private TableColumn<Passagem, String> colunaCodigo;
    @FXML
    private TableColumn<Passageiro, String> colunaPassageiro;
    @FXML
    private TableColumn<Passagem, String> colunaPreco;
    @FXML
    private TableColumn<Passagem, String> colunaTarifa;
    @FXML
    private TableColumn<Passagem, String> colunaNumeroPoltrona;
    @FXML
    private TableColumn<Passagem, String> colunaTipoAssento;
    @FXML
    private TableColumn<Passagem, String> colunaTipoPassagem;
    @FXML
    private TableColumn<Passagem, String> colunaLanche;
    @FXML
    private TableColumn<Passagem, String> colunaDependentes;
    @FXML
    private TableView<Passagem> tablePassagem;
    
    private List<Passagem> passagens;
    
    private ObservableList<Passagem> observablePassagens;

    
    public ListagemPassagensController(){
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
    }    
    
    public void preencherTabela() {
        
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaPassageiro.setCellValueFactory(new PropertyValueFactory<>("passageiro"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaTarifa.setCellValueFactory(new PropertyValueFactory<>("eDentroDoEstado"));
        colunaNumeroPoltrona.setCellValueFactory(new PropertyValueFactory<>("codigoPoltrona"));
        colunaTipoAssento.setCellValueFactory(new PropertyValueFactory<>("tipoDeAssento"));
        colunaTipoPassagem.setCellValueFactory(new PropertyValueFactory<>("tipoDePassagem"));;
        colunaLanche.setCellValueFactory(new PropertyValueFactory<>("lanche"));
        colunaDependentes.setCellValueFactory(new PropertyValueFactory<>("criancaColo"));
        
        
        passagens = fachadaFuncionario.listagemPassagem();
        
        observablePassagens = FXCollections.observableArrayList(passagens);

        tablePassagem.setItems(observablePassagens);
        
    }
    
}
