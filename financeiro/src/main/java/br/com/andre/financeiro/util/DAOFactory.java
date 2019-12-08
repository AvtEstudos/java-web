package br.com.andre.financeiro.util;

import br.com.andre.financeiro.usuario.UsuarioDAO;
import br.com.andre.financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {	
		
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
}
