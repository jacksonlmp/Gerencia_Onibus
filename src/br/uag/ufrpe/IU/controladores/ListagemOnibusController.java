/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Onibus;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
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
public class ListagemOnibusController implements Initializable {
    
    private FachadaGerente fachadaGerente;

    @FXML
    private TableView<Onibus> tableOnibus;
    @FXML
    private TableColumn<Onibus, String> colunaCodigo;
    @FXML
    private TableColumn<Onibus, String> colunaMotorista;
    @FXML
    private TableColumn<Onibus, String> colunaPlaca;
    @FXML
    private TableColumn<Onibus, String> colunaTotalPoltronas;
    @FXML
    private TableColumn<Onibus, String> colunaConvencional;
    @FXML
    private TableColumn<Onibus, String> colunaObeso;
    @FXML
    private TableColumn<Onibus, String> colunaParcialReclinavel;
    @FXML
    private TableColumn<Onibus, String> colunaTotalReclinavel;
    
     private List<Onibus> onibus;
    
    private ObservableList<Onibus> observableOnibus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public ListagemOnibusController(){
        fachadaGerente = FachadaGerente.getFachadaGerente();
    }
    
    public void preencherTabela(){
        
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaMotorista.setCellValueFactory(new PropertyValueFactory<>("motorista"));
        colunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colunaTotalPoltronas.setCellValueFactory(new PropertyValueFactory<>("totalPoltronas"));
        colunaConvencional.setCellValueFactory(new PropertyValueFactory<>("Convencional"));
        colunaObeso.setCellValueFactory(new PropertyValueFactory<>("poltronasObeso"));
        colunaParcialReclinavel.setCellValueFactory(new PropertyValueFactory<>("poltronasReclinavel"));;
        colunaTotalReclinavel.setCellValueFactory(new PropertyValueFactory<>("poltronasTotalReclinavel"));
        
        onibus = fachadaGerente.listagemOnibus();
        
        observableOnibus = FXCollections.observableArrayList(onibus);
        tableOnibus.setItems(observableOnibus);
    }
    
}
