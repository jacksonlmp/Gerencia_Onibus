/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.excecoes.viagem.ViagemNaoExisteException;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;
import javafx.util.Callback;
import static jdk.nashorn.internal.objects.NativeObject.keys;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class ListarPoltronasViagemController implements Initializable {

    public class Poltrona {

        private int codigo;
        private String tipo;

        public Poltrona(int codigo, String tipo) {
            this.codigo = codigo;
            this.tipo = tipo;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return "Poltrona{" + "codigo=" + codigo + ", tipo=" + tipo + '}';
        }

    }

    private FachadaFuncionario fachadaFuncionario;

    @FXML
    private TableView<Poltrona> tableViewPoltronas;

    @FXML
    private TableColumn<Poltrona, Integer> colunaCodigoPoltrona;

    @FXML
    private TableColumn<Poltrona, String> colunaTipoPoltrona;

    @FXML
    private TextField txtCodigoViagem;

    private Map<Integer, String> poltronas;

    private List<Poltrona> poltronasList;

    private ObservableList<Poltrona> observablePoltronas;

    public ListarPoltronasViagemController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
        poltronasList = new ArrayList<>();

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
                
                poltronas = fachadaFuncionario.listaPoltronasDaViagem(codigo);
                
                colunaCodigoPoltrona.setCellValueFactory(new PropertyValueFactory<>("codigo"));
                colunaTipoPoltrona.setCellValueFactory(new PropertyValueFactory<>("tipo"));;

                popularArrayPoltronas();

                observablePoltronas = FXCollections.observableArrayList(poltronasList);
                tableViewPoltronas.setItems(observablePoltronas);
            } catch (Exception ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }

        }
    }

    
    public void popularArrayPoltronas() {
        Poltrona poltrona;

        for (Map.Entry<Integer, String> quantidade : poltronas.entrySet()) {
            poltrona = new Poltrona(quantidade.getKey(), quantidade.getValue());
            poltronasList.add(poltrona);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
