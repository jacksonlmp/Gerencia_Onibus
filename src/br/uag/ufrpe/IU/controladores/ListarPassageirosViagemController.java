/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.excecoes.viagem.ViagemNaoExisteException;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class ListarPassageirosViagemController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;

    @FXML
    private TableView<Passageiro> tableViewPassageiros;

    @FXML
    private TableColumn<Passageiro, String> colunaNome;

    @FXML
    private TableColumn<Passageiro, String> colunaCpf;

    @FXML
    private TableColumn<Passageiro, String> colunaRg;

    @FXML
    private TableColumn<Passageiro, String> colunaTelefone;

    @FXML
    private TextField txtCodigoViagem;

    private List<Passageiro> passageiros;

    private ObservableList<Passageiro> observablePassageiros;

    public ListarPassageirosViagemController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    public void preencherTabela(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        int codigo = -1;

        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }
        if (codigo != -1) {
            try {
                colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
                colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                colunaRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
                colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

                passageiros = fachadaFuncionario.listaPassageirosNaViagem(codigo);

                observablePassageiros = FXCollections.observableArrayList(passageiros);
                tableViewPassageiros.setItems(observablePassageiros);
            } catch (ViagemNaoExisteException ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
