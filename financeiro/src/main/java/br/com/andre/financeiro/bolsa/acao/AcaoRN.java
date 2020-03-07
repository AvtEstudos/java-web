package br.com.andre.financeiro.bolsa.acao;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.financeiro.usuario.Usuario;
import br.com.andre.financeiro.util.DAOFactory;
import br.com.andre.financeiro.util.UtilException;
import br.com.andre.financeiro.web.util.YahooFinanceUtil;

public class AcaoRN {
	
	private AcaoDAO acaoDAO;
	
	public AcaoRN() {
		this.acaoDAO = DAOFactory.criarAcaoDAO();
	}
	
	public void salvar(Acao acao) {
		this.acaoDAO.salvar(acao);
	}
	
	public void excluir(Acao acao) {
		this.acaoDAO.excluir(acao);
	}
	
	public List<Acao> listar(Usuario usuario){
		return this.acaoDAO.listar(usuario);
	}
	
	public List<AcaoVirtual> listarAcaoVirtual(Usuario usuario) throws UtilException { //1
		
		List<AcaoVirtual> listaAcaoVirtual = new ArrayList<AcaoVirtual>();
		AcaoVirtual acaoVirtual = null;
		String cotacao = null;
		float ultimoPreco = 0.0f;
		
		for (Acao acao : this.listar(usuario)) { 
			acaoVirtual = new AcaoVirtual();
			acaoVirtual.setAcao(acao);
			cotacao = YahooFinanceUtil.getInfoCotacao(YahooFinanceUtil.INDICE_ULTIMO_PRECO_DIA_ACAO, acao); //2
			
			if(cotacao != null) {
				ultimoPreco = new Float(cotacao).floatValue();//3
				acaoVirtual.setUltimoPreco(ultimoPreco); 
				acaoVirtual.setTotal(ultimoPreco * acao.getQuantidade());
				listaAcaoVirtual.add(acaoVirtual);
			}
		}
		
		return listaAcaoVirtual;
	}

}
