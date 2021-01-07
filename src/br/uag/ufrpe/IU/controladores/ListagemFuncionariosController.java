/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Funcionario;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
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
 * @author Jackson
 */
public class ListagemFuncionariosController implements Initializable {
    private final FachadaGerente fachadaGerente; 
    
    @FXML
    private TableView<Funcionario> tableViewFuncionarios;
    
    @FXML
    private TableColumn<Funcionario, String> colunaNome;

    @FXML
    private TableColumn<Funcionario, String> colunaCpf;
    
    private List<Funcionario> funcionarios;
    
    private ObservableList<Funcionario> observableFuncionarios;
    
    public ListagemFuncionariosController() {
        fachadaGerente = FachadaGerente.getFachadaGerente();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
    }    
    
    public void preencherTabela(){
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
         
        funcionarios = fachadaGerente.listagemFuncionarios();
         
        observableFuncionarios = FXCollections.observableArrayList(funcionarios);
        tableViewFuncionarios.setItems(observableFuncionarios);
    }
    
}
