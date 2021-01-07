/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Itamar Jr
 */
public class InformacoesViagemController implements Initializable {

    private FachadaFuncionario fachadaFuncionario;

    @FXML
    private TextField txtIdJovem;
    @FXML
    private TextField txtParcialIdJovem;
    @FXML
    private TextField txtIdoso;
    @FXML
    private TextField txtParcialIdoso;
    @FXML
    private TextField txtCapacidadeOnibus;
    @FXML
    private TextField txtPoltronasVazias;
    @FXML
    private TextField txtLanche;
    @FXML
    private TextField txtCodigoViagem;

    public InformacoesViagemController() {
        this.fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }

    @FXML
    public void buscarInformacoes(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao procurar a viagem!");

        int codigo = -1;
        try {
            codigo = Integer.parseInt(txtCodigoViagem.getText());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            alertaErro.setContentText("Digite apenas números!");
            alertaErro.show();
        }

        if (codigo != -1) {
            try {
                Viagem viagem = fachadaFuncionario.procurarViagem(codigo);
                if (viagem == null) {
                    alertaErro.setContentText("Viagem não encontrada!");
                    alertaErro.show();
                } else {
                    txtIdJovem.setText("" + viagem.getQuantidadeIdJovem());
                    txtParcialIdJovem.setText("" + viagem.getQuantidadeIdJovemParcial());
                    txtIdoso.setText("" + viagem.getQuantidadeIdoso());
                    txtParcialIdoso.setText("" + viagem.getQuantidadeIdosoParcial());
                    txtCapacidadeOnibus.setText("" + viagem.getOnibus().getQuantidadeAssentos());
                    txtLanche.setText("" + viagem.calculaQuantidadeLanche());
                    txtPoltronasVazias.setText("" + viagem.calculaPoltronasVazias());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        }
    }

    @FXML
    public void voltar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
