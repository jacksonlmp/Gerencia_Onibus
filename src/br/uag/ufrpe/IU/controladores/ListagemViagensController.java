/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Endereco;
import br.uag.ufrpe.negocio.entidades.Motorista;
import br.uag.ufrpe.negocio.entidades.Onibus;
import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.ArrayList;
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
 * @author Itamar Jr
 */
public class ListagemViagensController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;

    @FXML
    private TableView<Viagem> tableViewViagens;

    @FXML
    private TableColumn<Viagem, Integer> colunaCodigo;
    @FXML
    private TableColumn<Viagem, String> colunaDataSaida;
    @FXML
    private TableColumn<Viagem, String> colunaHoraSaida;
    @FXML
    private TableColumn<Viagem, String> colunaDataChegada;
    @FXML
    private TableColumn<Viagem, String> colunaHoraChegada;
    @FXML
    private TableColumn<Viagem, String> colunaOrigem;
    @FXML
    private TableColumn<Viagem, String> colunaDestino;

    private List<Viagem> viagens;

    private ObservableList<Viagem> observableViagens;

    public ListagemViagensController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
    }

    public void preencherTabela() {
        
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaDataSaida.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
        colunaDataChegada.setCellValueFactory(new PropertyValueFactory<>("dataChegada"));
        colunaHoraSaida.setCellValueFactory(new PropertyValueFactory<>("horarioSaida"));
        colunaHoraChegada.setCellValueFactory(new PropertyValueFactory<>("horarioChegada"));
        colunaOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        colunaDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        
        viagens = fachadaFuncionario.listagemViagens();
        
        observableViagens = FXCollections.observableArrayList(viagens);

        tableViewViagens.setItems(observableViagens);
    }

}
