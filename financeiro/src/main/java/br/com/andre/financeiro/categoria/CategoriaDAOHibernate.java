package br.com.andre.financeiro.categoria;

import java.util.List;
import org.hibernate.*;
import br.com.andre.financeiro.usuario.Usuario;

public class CategoriaDAOHibernate implements CategoriaDAO {
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		Categoria merged = (Categoria) this.session.merge(categoria);
		this.session.flush();
		this.session.clear();
		return merged;
	}

	@Override
	public void excluir(Categoria categoria) {
		categoria = (Categoria) this.carregar(categoria.getCodigo());
		this.session.delete(categoria);
		this.session.flush();
		this.session.clear();		
	}

	@Override
	public Categoria carregar(Integer categoria) {
		return (Categoria) this.session.get(Categoria.class, categoria);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar(Usuario usuario) {
		
		String hql = "select c from Categoria c where c.pai is null and c.usuario = :usuario";
		
		Query query = (Query) this.session.createQuery(hql);		
		((org.hibernate.Query) query).setInteger("usuario", usuario.getCodigo());
		
		List<Categoria> lista = ((org.hibernate.Query) query).list();
		
		return lista;
	}

	

}
