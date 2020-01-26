package br.com.andre.financeiro.categoria;

import java.util.List;

import br.com.andre.financeiro.usuario.Usuario;

public interface CategoriaDAO {
	
	public Categoria salvar(Categoria categoria);
	public void excluir(Categoria categoria);
	public Categoria carregar(Integer categoria);
	public List<Categoria> listar(Usuario usuario);	

}
