/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.negocio.fachada;

import br.uag.ufrpe.negocio.NegocioFuncionario;
import br.uag.ufrpe.negocio.NegocioMotorista;
import br.uag.ufrpe.negocio.NegocioOnibus;
import br.uag.ufrpe.negocio.NegocioPassagem;
import br.uag.ufrpe.negocio.NegocioViagem;
import br.uag.ufrpe.negocio.entidades.Endereco;
import br.uag.ufrpe.negocio.entidades.Funcionario;
import br.uag.ufrpe.negocio.entidades.Motorista;
import br.uag.ufrpe.negocio.entidades.Onibus;
import br.uag.ufrpe.negocio.entidades.Passageiro;
import br.uag.ufrpe.negocio.entidades.Passagem;
import br.uag.ufrpe.negocio.entidades.Viagem;
import br.uag.ufrpe.negocio.excecoes.datas.IntervaloDeDatasInvalidoException;
import br.uag.ufrpe.negocio.excecoes.funcionario.FuncionarioJaExisteException;
import br.uag.ufrpe.negocio.excecoes.funcionario.FuncionarioNaoEncontradoException;
import br.uag.ufrpe.negocio.excecoes.motorista.MotoristaJaExisteException;
import br.uag.ufrpe.negocio.excecoes.motorista.MotoristaNaoDisponivelException;
import br.uag.ufrpe.negocio.excecoes.motorista.MotoristaNaoExisteException;
import br.uag.ufrpe.negocio.excecoes.onibus.OnibusCheioException;
import br.uag.ufrpe.negocio.excecoes.onibus.OnibusJaExisteException;
import br.uag.ufrpe.negocio.excecoes.onibus.OnibusNaoDisponivelException;
import br.uag.ufrpe.negocio.excecoes.onibus.OnibusNaoExisteException;
import br.uag.ufrpe.negocio.excecoes.passageiro.PassageiroJaEstaNaViagemException;
import br.uag.ufrpe.negocio.excecoes.passageiro.PassageiroNaoExisteException;
import br.uag.ufrpe.negocio.excecoes.passagem.PassagemNaoExisteException;
import br.uag.ufrpe.negocio.excecoes.passagem.PassagemNaoPertenceAViagemException;
import br.uag.ufrpe.negocio.excecoes.viagem.DescontoException;
import br.uag.ufrpe.negocio.excecoes.viagem.IndisponibilidadeDeAssentoException;
import br.uag.ufrpe.negocio.excecoes.viagem.IndisponibilidadeTipoDePassagemException;
import br.uag.ufrpe.negocio.excecoes.viagem.ViagemJaExisteException;
import br.uag.ufrpe.negocio.excecoes.viagem.ViagemNaoExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Itamar Jr
 */
public class FachadaGerente extends FachadaFuncionario {

    private static FachadaGerente fachadaGerente;
    private NegocioOnibus negocioOnibus;
    private NegocioMotorista negocioMotorista;

    private FachadaGerente() {
        super();
        this.negocioOnibus = new NegocioOnibus();
        this.negocioMotorista = new NegocioMotorista();
    }

    public static FachadaGerente getFachadaGerente() {
        if (fachadaGerente == null) {
            fachadaGerente = new FachadaGerente();
        }
        return fachadaGerente;
    }

    public int adicionarViagem(String placa, String origem, String destino, String horarioSaida, String horarioChegada, String dataSaida, String dataChegada) throws ViagemJaExisteException, MotoristaNaoDisponivelException, OnibusNaoDisponivelException, OnibusNaoExisteException {

        Onibus onibus = negocioOnibus.procurarOnibus(placa);
        if (onibus == null) {
            throw new OnibusNaoExisteException();
        }
        Viagem viagem = new Viagem(onibus, origem, destino, horarioSaida, horarioChegada, dataSaida, dataChegada);

        getNegocioViagem().adicionar(viagem);
        
        return viagem.getCodigo();
    }

    public void removerViagem(int codigo) throws ViagemNaoExisteException {
        getNegocioViagem().remover(codigo);
    }

    public void alterarViagem(int codigo, String placa, String origem, String destino, String horarioSaida, String horarioChegada, String dataSaida, String dataChegada) throws OnibusNaoExisteException, ViagemNaoExisteException {
        Viagem viagem = getNegocioViagem().procurar(codigo);
        if (viagem == null) {
            throw new ViagemNaoExisteException();
        }
        Onibus onibus = negocioOnibus.procurarOnibus(placa);
        if (onibus == null) {
            throw new OnibusNaoExisteException();
        }
        viagem.setOnibus(onibus);
        viagem.setOrigem(origem);
        viagem.setDestino(destino);
        viagem.setHorarioSaida(horarioSaida);
        viagem.setHorarioChegada(horarioChegada);
        viagem.setDataSaida(dataSaida);
        viagem.setDataChegada(dataChegada);
    }

    public double calcularLucroDaViagem(int codigo) throws ViagemNaoExisteException {
        Viagem viagem = getNegocioViagem().procurar(codigo);
        if (viagem == null) {
            throw new ViagemNaoExisteException();
        }
        return viagem.calculaLucro();
    }
    
    public void aplicarDescontoEmTodasAsPassagens(int codigo, double desconto) throws ViagemNaoExisteException, DescontoException {
        Viagem viagem = getNegocioViagem().procurar(codigo);
        if (viagem == null) {
            throw new ViagemNaoExisteException();
        }
        viagem.aplicarDescontoEmTodasAsPassagens(desconto);
    }

    public double calculaPorcentagemLanchePorDatasEViagens(String horarioSaida, String origem, String destino, String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calculaPorcentagemLanchePorDatasEViagens(horarioSaida, origem, destino, dataInicio, dataFim);
    }

    public double calculaPorcentagemLanchePorDatasEDestino(String origem, String destino, String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calculaPorcentagemLanchePorDatasEDestino(origem, destino, dataInicio, dataFim);
    }

    public double calculaPorcentagemLanchePorDatas(String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calculaPorcentagemLanchePorDatas(dataInicio, dataFim);
    }

    public double calculaPorcentagemPassageirosPorDatas(String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calculaPorcentagemPassageirosPorDatas(dataInicio, dataFim);
    }

    public double calculaPorcentagemPassageirosPorDatasEViagens(String horario, String origem, String destino, String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calculaPorcentagemPassageirosPorDatasEViagens(horario, origem, destino, dataInicio, dataFim);
    }

    public double calculaPorcentagemPassageirosPorDatasEDestino(String origem, String destino, String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calculaPorcentagemPassageirosPorDatasEDestino(origem, destino, dataInicio, dataFim);
    }

    public double calcularLucroTotalPorDatas(String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calcularLucroTotalPorDatas(dataInicio, dataFim);
    }

    public double calcularLucroTotalPorDatasEViagens(String horarioSaida, String origem, String destino, String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calcularLucroTotalPorDatasEViagens(horarioSaida, origem, destino, dataInicio, dataFim);
    }

    public double calcularLucroTotalPorDatasEDestino(String origem, String destino, String dataInicio, String dataFim) throws IntervaloDeDatasInvalidoException {
        return getNegocioViagem().calcularLucroTotalPorDatasEDestino(origem, destino, dataInicio, dataFim);
    }

    public void adicionarMotorista(String nomeCompleto, String cpf, String rg, String telefone, String numeroCarteiraMotorista, String cep, String logradouro, String bairro, String numero, String complemento, String cidade, String estado) throws MotoristaJaExisteException {
        Endereco endereco = new Endereco(cep, logradouro, bairro, numero, complemento, cidade, estado);
        Motorista carteira = negocioMotorista.procurarMotorista(numeroCarteiraMotorista);
        if (carteira != null) {
            throw new MotoristaJaExisteException();
        }
        Motorista motorista = new Motorista(nomeCompleto, cpf, rg, telefone, numeroCarteiraMotorista, endereco);
        negocioMotorista.adicionarMotorista(motorista);
    }

    public void removerMotorista(String numeroCarteiraMotorista) throws MotoristaNaoExisteException {
        Motorista m = negocioMotorista.procurarMotorista(numeroCarteiraMotorista);

        if (m == null) {
            throw new MotoristaNaoExisteException();
        }
        negocioMotorista.removerMotorista(m);

    }

    public void alterarMotorista(String nomeCompleto, String cpf, String rg, String telefone, String numeroCarteiraMotorista, String cep, String logradouro, String bairro, String numero, String complemento, String cidade, String estado) throws MotoristaNaoExisteException {
        Motorista motorista = negocioMotorista.procurarMotorista(numeroCarteiraMotorista);
        if (motorista == null) {
            throw new MotoristaNaoExisteException();
        }
        motorista.setNomeCompleto(nomeCompleto);
        motorista.setCpf(cpf);
        motorista.setRg(rg);
        motorista.setTelefone(telefone);
        motorista.getEndereco().setCep(cep);
        motorista.getEndereco().setLogradouro(logradouro);
        motorista.getEndereco().setBairro(bairro);
        motorista.getEndereco().setNumero(numero);
        motorista.getEndereco().setComplemento(complemento);
        motorista.getEndereco().setCidade(cidade);
        motorista.getEndereco().setEstado(estado);
        
    }
    
public Motorista procurarMotorista(String numeroCarteiraMotorista) throws MotoristaNaoExisteException{
        return negocioMotorista.procurarMotorista(numeroCarteiraMotorista);
    }    

    public void adicionarFuncionario(String nomeCompleto, String cpf, String rg, String telefone, String senha, String email, boolean eGerente, String cep, String logradouro, String bairro, String numero, String complemento, String cidade, String estado) throws FuncionarioJaExisteException, FuncionarioNaoEncontradoException{
        Endereco endereco = new Endereco(cep,logradouro,bairro,numero,complemento,cidade,estado);
        Funcionario funcionario = new Funcionario(nomeCompleto, cpf, rg, telefone, senha, email, eGerente, endereco);
        
        if(procurarFuncionario(funcionario.getCpf()) == null){
            getNegocioFuncionario().adicionarFuncionario(funcionario);    
        }
        else{
            throw new FuncionarioJaExisteException();   
              
        }
        
    }
    
    public void alterarFuncionario(String nomeCompleto, String cpf, String rg, String telefone, String senha, String email, boolean eGerente, String cep, String logradouro, String bairro, String numero, String complemento, String cidade, String estado) throws FuncionarioNaoEncontradoException{
        Funcionario funcionario = getNegocioFuncionario().procurarFuncionario(cpf);        
        if(funcionario == null){
            throw new FuncionarioNaoEncontradoException();
        }
        else{
            getNegocioFuncionario().alterarFuncionario(funcionario);
        }
        
        funcionario.setNomeCompleto(nomeCompleto);
        funcionario.setCpf(cpf);
        funcionario.setRg(rg);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.seteGerente(eGerente);
        funcionario.setSenha(senha);
        funcionario.getEndereco().setCep(cep);
        funcionario.getEndereco().setLogradouro(logradouro);
        funcionario.getEndereco().setBairro(bairro);
        funcionario.getEndereco().setNumero(numero);
        funcionario.getEndereco().setComplemento(complemento);
        funcionario.getEndereco().setCidade(cidade);
        funcionario.getEndereco().setEstado(estado);
    }

    public Funcionario procurarFuncionario(String cpf) throws FuncionarioNaoEncontradoException{
        return getNegocioFuncionario().procurarFuncionario(cpf);
    }

    public void removerFuncionario(String cpf) throws FuncionarioNaoEncontradoException {
        Funcionario funcionario = getNegocioFuncionario().procurarFuncionario(cpf);

        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException();
        } 
        else {
            getNegocioFuncionario().removerFuncionario(funcionario);
        }
    }
    
    public List<Funcionario> listagemFuncionarios() {
        return getNegocioFuncionario().listagemFuncionarios();
    }
    
    public void adicionarOnibus(String numeroCarteira, String placa, int totalPoltronas, List<Integer> poltronasObeso, List<Integer> poltronasTotalReclinavel, List<Integer> poltronasReclinavel) throws OnibusJaExisteException, MotoristaNaoExisteException{
        Motorista verificaMotorista = procurarMotorista(numeroCarteira);
        Onibus verificaOnibus = procurarOnibus(placa);
        
        if(verificaMotorista != null){
            
            if(verificaOnibus != null){                
                Onibus onibus = new Onibus(verificaMotorista, placa, totalPoltronas, poltronasObeso, poltronasTotalReclinavel, poltronasReclinavel);
                negocioOnibus.adicionarOnibus(onibus);
            }
            
            else{
                throw new OnibusJaExisteException();
            }
         }    
            
        else{
            throw new MotoristaNaoExisteException();
            
        }
 
    }
    
   public Onibus procurarOnibus(String placa){
        return negocioOnibus.procurarOnibus(placa);
    }
    
    public void alterarOnibus(String numeroCarteira, String placa, int totalPoltronas, List<Integer> poltronasObeso, List<Integer> poltronasTotalReclinavel, List<Integer> poltronasReclinavel) throws OnibusNaoExisteException, FuncionarioNaoEncontradoException{
        Onibus onibus = negocioOnibus.procurarOnibus(placa);
        Motorista verificaMotorista = negocioMotorista.procurarMotorista(numeroCarteira);
        
        if(onibus != null){
            
            if(verificaMotorista != null){
                onibus.setMotorista(verificaMotorista);
                onibus.setPlaca(placa);
                onibus.setTotalPoltronas(totalPoltronas);
                onibus.setPoltronas(poltronasObeso, poltronasTotalReclinavel, poltronasReclinavel);                
                onibus.inicializaPoltronas(poltronasObeso, poltronasTotalReclinavel, poltronasReclinavel);
            }
            
            else{
                
                throw new FuncionarioNaoEncontradoException();
            }
        }
        else{
            throw new OnibusNaoExisteException();
        }
        
        
    }
       public boolean auntenticar(String cpf, String senha)  throws FuncionarioNaoEncontradoException{
        Funcionario funcionario = getNegocioFuncionario().procurarFuncionario(cpf);
        if(funcionario == null){
             throw new FuncionarioNaoEncontradoException();
        }else{
            if(funcionario.getSenha() == senha){
                if(funcionario.eGerente() == true){
                    return true; 
                }
            }
        }
        return false; 
    }
    
    
    public void removerOnibus(String placa) throws OnibusNaoExisteException{
        Onibus onibus = negocioOnibus.procurarOnibus(placa);
        
        if(onibus != null){
            negocioOnibus.removerOnibus(placa);
        }
        else{
            throw new OnibusNaoExisteException();
        }
    }
    
    public List<Onibus> listagemOnibus(){
        return negocioOnibus.listagemOnibus();
    }


}
