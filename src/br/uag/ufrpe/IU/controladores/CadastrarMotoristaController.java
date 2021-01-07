package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author GABRIEL
 */
public class CadastrarMotoristaController implements Initializable {
    private final FachadaGerente fachadaGerente; 
    
    @FXML
    private Label cpfMotorista;

    @FXML
    private Label cidadeMotorista;

    @FXML
    private TextField txtComplementoMotorista;

    @FXML
    private Label erroCnh;

    @FXML
    private TextField txtNumeroMotorista;

    @FXML
    private Label rgMotorista;

    @FXML
    private TextField txtNomeMotorista;

    @FXML
    private TextField txtCidadeMotorista;

    @FXML
    private TextField txtLogradouroMotorista;

    @FXML
    private Label lagradouroMotorista;

    @FXML
    private Label erroCep;

    @FXML
    private Label cnhMotorista;

    @FXML
    private TextField txtEstadoMotorista;

    @FXML
    private Label erroCpf;

    @FXML
    private Label complementoMotorista;

    @FXML
    private Label bairroMotorista;

    @FXML
    private TextField txtRgMotorista;

    @FXML
    private Label cepMotorista;

    @FXML
    private Label erroTelefone;

    @FXML
    private TextField txtCepMotorista;

    @FXML
    private Label nomeMotorista;

    @FXML
    private Label erroRg;

    @FXML
    private AnchorPane NomeMotorista;

    @FXML
    private Label erroNumero;

    @FXML
    private Label telefoneMotorsita;

    @FXML
    private Label numeroMotorista;

    @FXML
    private TextField txtBairroMotorista;

    @FXML
    private TextField txtCnhMotorista;

    @FXML
    private TextField txtTelefoneMotorista;

    @FXML
    private Label estadoMotorista;

    @FXML
    private TextField txtCpfMotorista;

    @FXML
    private Button cadastratMotorista;
    
     public CadastrarMotoristaController(){
        fachadaGerente = FachadaGerente.getFachadaGerente();
    }
     

    @FXML
    void cadastrarMotorista(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Cadastrar");
        
        String nome = txtNomeMotorista.getText();
        String cpf = txtCpfMotorista.getText();
        String rg = txtRgMotorista.getText();
        String telefone = txtTelefoneMotorista.getText();
        String cnh = txtCnhMotorista.getText();
        String cep = txtCepMotorista.getText();
        String logradouro = txtLogradouroMotorista.getText();
        String bairro = txtBairroMotorista.getText();
        String numero = txtNumeroMotorista.getText();
        String complemento = txtComplementoMotorista.getText();
        String cidade = txtCidadeMotorista.getText();
        String estado = txtEstadoMotorista.getText();
        boolean verifica = true; 
        
         if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*")){
            erroCpf.setText("CPF invalido");
            verifica = false;
        }   
          if(rg.isEmpty() || !rg.matches("[0-9]*")){
            erroRg.setText("RG invalido");
            verifica = false;
        }
         if(!cep.matches("[0-9]*")){
            erroCep.setText("Cep Invalido");
            verifica = false;
        } 
        
         if(!numero.matches(("[0-9]*"))){
            erroNumero.setText("Apenas numeros");
            verifica= false; 
        } 
          if(!telefone.matches(("[0-9]*"))){
            erroTelefone.setText("Apenas numeros");
            verifica= false; 
        } 
         if(!cnh.matches(("[0-9]*"))){
            erroCnh.setText("Apenas numeros");
            verifica= false; 
        } 
        
        if (verifica) {
            try {
                fachadaGerente.adicionarMotorista(nome,cpf,rg,telefone,cnh,cep,logradouro,bairro,numero,complemento,cidade,estado);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Motorista cadastradado com sucesso!");
                alertaConfirmacao.show();

            } 
            catch (Exception ex) {
                ex.printStackTrace();
                alertaErro.setContentText(ex.getMessage());
                alertaErro.show();
            }
        } 
        
        else {
            alertaErro.setContentText("Erro ao preencher os dados!");
            alertaErro.show();
        }
    
    }
    
    
    
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
