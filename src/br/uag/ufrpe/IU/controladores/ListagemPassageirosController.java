package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
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
public class ListagemPassageirosController implements Initializable {
    private final FachadaFuncionario fachadaFuncionario;
    
    @FXML
    private TableColumn<Passageiro, String> colunaNome;

    @FXML
    private TableColumn<Passageiro, String> colunaCpf;

    @FXML
    private TableView<Passageiro> tableViewPassageiros;
    
    private List<Passageiro> passageiros;
    
    private ObservableList<Passageiro> observablePassageiros;
     
    public ListagemPassageirosController() {
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
    }
    
    public void preencherTabela(){
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        passageiros = fachadaFuncionario.listagemPassageiros();
         
        observablePassageiros = FXCollections.observableArrayList(passageiros);
        tableViewPassageiros.setItems(observablePassageiros);
    }
}
    

