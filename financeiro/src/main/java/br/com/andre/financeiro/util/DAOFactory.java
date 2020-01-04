package br.com.andre.financeiro.util;

import br.com.andre.financeiro.conta.ContaDAO;
import br.com.andre.financeiro.conta.ContaDAOHibernate;
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
	
}
