package br.com.andre.financeiro.cheque;

import java.util.List;

import br.com.andre.financeiro.conta.Conta;

public interface ChequeDAO {
	
	public void salvar(Cheque cheque);
	public void excluir(Cheque cheque);
	public Cheque carregar(ChequeId chequeId);
	public List<Cheque> listar(Conta conta);	

}
