package br.com.andre.financeiro.util;

import br.com.andre.financeiro.bolsa.acao.AcaoDAO;
import br.com.andre.financeiro.bolsa.acao.AcaoDAOHibernate;
import br.com.andre.financeiro.categoria.CategoriaDAO;
import br.com.andre.financeiro.categoria.CategoriaDAOHibernate;
import br.com.andre.financeiro.cheque.ChequeDAO;
import br.com.andre.financeiro.cheque.ChequeDAOHibernate;
import br.com.andre.financeiro.conta.ContaDAO;
import br.com.andre.financeiro.conta.ContaDAOHibernate;
import br.com.andre.financeiro.lancamento.LancamentoDAO;
import br.com.andre.financeiro.lancamento.LancamentoDAOHibernate;
import br.com.andre.financeiro.usuario.UsuarioDAO;
import br.com.andre.financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {	
		
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ContaDAO criarContaDAO() {
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
	}	
	
	public static CategoriaDAO criarCategoriaDAO() {
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}
	
	public static LancamentoDAO criarLancamentoDAO() {
		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
	}
	
	public static ChequeDAO criarChequeDAO() {
		ChequeDAOHibernate chequeDAO = new ChequeDAOHibernate();
		chequeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return chequeDAO;
	}
	
	public static AcaoDAO criarAcaoDAO() {
		AcaoDAOHibernate acaoDAO = new AcaoDAOHibernate();
		acaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return acaoDAO;
	}
	
	
	
}
