/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uag.ufrpe.negocio.excecoes.passagem;

/**
 *
 * @author EMily SAntos
 */
public class PassagemNaoExisteException extends Exception {
    
    public PassagemNaoExisteException(){
        super("Passagem não existe no repositório.");
    }
    
}
