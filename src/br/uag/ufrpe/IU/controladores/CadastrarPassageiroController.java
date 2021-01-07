package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.fachada.FachadaFuncionario;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class CadastrarPassageiroController implements Initializable {
    private FachadaFuncionario fachadaFuncionario;
    
    @FXML
    private TextField labelPassageiroNome;

    @FXML
    private TextField labelPassageiroTelefone;

    @FXML
    private Text tituloCadastroPassageiro;

    @FXML
    private DatePicker labelPassageiroDataNascimento;

    @FXML
    private TextField labelPassageiroRg;
    
    @FXML
    private Label erroRg;
    
    @FXML
    private RadioButton labelPassageiroIdJovemS;
    
    @FXML
    private RadioButton labelPassageiroIdJovemN;

    @FXML
    private TextField labelPassageiroCpf;
    
    @FXML
    private Label erroCpf;
    
    @FXML
    private Label erroTelefone;
    
    @FXML
    private Button cadastrarPassageiro;
    
    @FXML
    private ToggleGroup IdJovem;

    public CadastrarPassageiroController() {
        fachadaFuncionario = FachadaFuncionario.getFachadaFuncionario();
    }
    
    @FXML
    void cadastrarPassageiro(ActionEvent event){
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Cadastrar");       
        
        String nomeCompleto = labelPassageiroNome.getText();
        String cpf = labelPassageiroCpf.getText();
        String rg = labelPassageiroRg.getText();
        String telefone = labelPassageiroTelefone.getText();
        String dataNascimento = labelPassageiroDataNascimento.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        boolean idJovem = true;
        boolean verificaPassageiro = true;    
    
        if(labelPassageiroIdJovemS.isSelected()){
            idJovem = true;
        }
        else{
            idJovem = false;
        }
        
        if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*") ){
            erroCpf.setText("CPF invalido ou insuficiente");
            verificaPassageiro = false;
        }
        
        if(rg.isEmpty() || !rg.matches("[0-9]*")){
            erroRg.setText("RG invalido");
            verificaPassageiro = false;
        }
        
        if(telefone.isEmpty() || !telefone.matches("[0-9]*") || telefone.length() < 7){
            erroTelefone.setText("Numero Invalido");
            verificaPassageiro = false;
        }
        
        if (verificaPassageiro) {
            try {
                fachadaFuncionario.adicionarPassageiro(nomeCompleto, cpf, rg, telefone, dataNascimento, idJovem);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Passageiro cadastradado com sucesso!");
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
