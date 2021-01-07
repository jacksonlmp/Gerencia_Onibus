package br.uag.ufrpe.IU.controladores;

import br.uag.ufrpe.negocio.fachada.FachadaGerente;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class CadastrarFuncionarioController implements Initializable {
    private final FachadaGerente fachadaGerente;  

    @FXML
    private TextField labelFuncionarioCep;

    @FXML
    private PasswordField labelFuncionarioSenha;

    @FXML
    private TextField labelFuncionarioBairro;

    @FXML
    private RadioButton labelFuncionarioeGerente;

    @FXML
    private TextField labelFuncionarioNumero;

    @FXML
    private TextField labelFuncionarioTelefone;

    @FXML
    private ToggleGroup eGerente;

    @FXML
    private TextField labelFuncionarioNome;

    @FXML
    private Button cadastrarFuncionario;

    @FXML
    private TextField labelFuncionarioRg;

    @FXML
    private TextField labelFuncionarioLogradouro;

    @FXML
    private TextField labelFuncionarioComplemento;

    @FXML
    private TextField labelFuncionarioCpf;

    @FXML
    private TextField labelFuncionarioCidade;

    @FXML
    private TextField labelFuncionarioEmail;

    @FXML
    private TextField labelFuncionarioEstado;
    
    @FXML
    private Label erroRg;
    
    @FXML
    private Label erroCpf;
    
    @FXML
    private Label erroSenha;
    
    @FXML
    private Label erroEmail;
    
    @FXML
    private Label erroCep;
    
    @FXML
    private Label erroNumero;
    
    
    public CadastrarFuncionarioController() {
        fachadaGerente = FachadaGerente.getFachadaGerente();
    }
    
    public static boolean validaEmail(String email) {
        boolean emailValido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            }
        }
        return emailValido;
    }

    @FXML
    void cadastrarFuncionario(ActionEvent event) {
        Alert alertaErro = new Alert(Alert.AlertType.ERROR);
        alertaErro.setTitle("Erro");
        alertaErro.setHeaderText("Erro ao preencher os dados");
        
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("CONFIRMAÇÃO");
        alertaConfirmacao.setHeaderText("Confirmação ao Cadastrar");
        
        String nome = labelFuncionarioNome.getText();
        String cpf = labelFuncionarioCpf.getText();
        String rg = labelFuncionarioRg.getText();
        String telefone = labelFuncionarioTelefone.getText();
        String email = labelFuncionarioEmail.getText();
        String senha = labelFuncionarioSenha.getText();
        boolean gerente = true;
        String cep = labelFuncionarioCep.getText();
        String logradouro = labelFuncionarioLogradouro.getText();
        String bairro = labelFuncionarioBairro.getText();
        String numero = labelFuncionarioNumero.getText();
        String complemento = labelFuncionarioComplemento.getText();
        String cidade = labelFuncionarioCidade.getText();
        String estado = labelFuncionarioEstado.getText();
        boolean verificaFuncionario = true;
        
        
        if(labelFuncionarioeGerente.isSelected()){
            gerente = true;
        }        
        
         if(cpf.length() < 11 || cpf.isEmpty() || !cpf.matches("[0-9]*")){
            erroCpf.setText("CPF invalido");
            verificaFuncionario = false;
        }
        
        if(rg.isEmpty() || !rg.matches("[0-9]*")){
            erroRg.setText("RG invalido");
            verificaFuncionario = false;
        }
        
        if(senha.length() < 3 || senha.isEmpty()){
            erroSenha.setText("Senha invalida ou muito curta");
            verificaFuncionario = false;
        }
        
        if(!cep.matches("[0-9]*")){
            erroCep.setText("Cep Invalido");
            verificaFuncionario = false;
        }
        
        if(!numero.matches(("[0-9]*"))){
            erroNumero.setText("Apenas numeros");
        }
        
        if(validaEmail(email) == false){
            erroEmail.setText("Email invalido");
            verificaFuncionario = false;
        }       
        
        if (verificaFuncionario) {
            try {
                fachadaGerente.adicionarFuncionario(nome, cpf, rg, telefone, email, senha, gerente, cep, logradouro, bairro, numero, complemento, cidade, estado);
                alertaConfirmacao.setAlertType(Alert.AlertType.CONFIRMATION);
                alertaConfirmacao.setContentText("Funcionario cadastradado com sucesso!");
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
