package br.com.andre.financeiro.bolsa.acao;

import java.util.List;

import br.com.andre.financeiro.usuario.Usuario;

public interface AcaoDAO {
	
	public void salvar(Acao acao);
	public void excluir(Acao acao);
	public List<Acao> listar(Usuario usuario);
	
}
