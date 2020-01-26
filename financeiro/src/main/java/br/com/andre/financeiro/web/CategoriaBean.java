package br.com.andre.financeiro.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.andre.financeiro.categoria.Categoria;
import br.com.andre.financeiro.categoria.CategoriaRN;

@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean {

	private TreeNode         categoriasTree;
	private Categoria        editada = new Categoria();
	private List<SelectItem> categoriasSelect;
	private boolean          mostraEdicao = false;
	
	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;
	
	public void novo() { //1
		
		Categoria pai = null;
		
		if(this.editada.getCodigo() != null) {
			CategoriaRN categoriaRN = new CategoriaRN();
			pai = categoriaRN.carregar(this.editada.getCodigo());
		}
		
		this.editada = new Categoria();
		this.editada.setPai(pai); 
		this.mostraEdicao = true;
	}
	
	public void selecionar(NodeSelectEvent event) { //2
		this.editada = (Categoria) event.getTreeNode().getData();
		Categoria pai = this.editada.getPai();
		
		if (this.editada != null && pai != null && pai.getCodigo() != null) {
			this.mostraEdicao = true;
		} else {
			this.mostraEdicao = false;
		}
	}
	
	public String salvar() { //3
		CategoriaRN categoriaRN = new CategoriaRN();
		this.editada.setUsuario(this.contextoBean.getUsuarioLogado());
		categoriaRN.salvar(this.editada);
		
		this.editada = null;
		this.mostraEdicao = false;
		this.categoriasTree = null;
		this.categoriasSelect = null;
		return null;
	}
	
	public String excluir() { //4		
		CategoriaRN categoriarRN = new CategoriaRN();
		categoriarRN.excluir(this.editada);
		
		this.editada = null;
		this.mostraEdicao = false;
		this.categoriasTree = null;
		this.categoriasSelect = null;
		return null;
	}
	
	public TreeNode getCategoriasTree() { //5
		if (this.categoriasTree == null) {
			CategoriaRN categoriaRN = new CategoriaRN();
			List<Categoria> categorias = categoriaRN.listar(this.contextoBean.getUsuarioLogado()); //6
			this.categoriasTree = new DefaultTreeNode(null, null);
			this.montaDadosTree(this.categoriasTree, categorias);
		}
		
		return this.categoriasTree;
	}
	
	public void montaDadosTree(TreeNode pai, List<Categoria> lista) { //7
		if(lista != null && lista.size() > 0) {
			TreeNode filho = null;
			for (Categoria categoria  : lista) {
				filho = new DefaultTreeNode(categoria, pai);
				this.montaDadosTree(filho, categoria.getFilhos());
			}
		}
	}
	
	public List<SelectItem> getCategoriasSelect(){ //8
		if(this.categoriasSelect == null) {
			this.categoriasSelect = new ArrayList<SelectItem>();
			CategoriaRN categoriaRN = new CategoriaRN();
			List<Categoria> categorias = categoriaRN.listar(this.contextoBean.getUsuarioLogado()); //9
			this.montaDadosSelect(this.categoriasSelect, categorias, "");
		}
		
		return categoriasSelect;
	}
	
	private void montaDadosSelect(List<SelectItem> select, List<Categoria> categorias, String prefixo) { //10
		SelectItem item = null;
		if(categorias != null) {
			for(Categoria categoria : categorias) {
				item = new SelectItem(categoria, prefixo + categoria.getDescricao());
				item.setEscape(false);
				select.add(item);
				this.montaDadosSelect(select, categoria.getFilhos(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}

	public Categoria getEditada() {
		return editada;
	}

	public void setEditada(Categoria editada) {
		this.editada = editada;
	}

	public boolean isMostraEdicao() {
		return mostraEdicao;
	}

	public void setMostraEdicao(boolean mostraEdicao) {
		this.mostraEdicao = mostraEdicao;
	}

	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public void setCategoriasTree(TreeNode categoriasTree) {
		this.categoriasTree = categoriasTree;
	}

	public void setCategoriasSelect(List<SelectItem> categoriasSelect) {
		this.categoriasSelect = categoriasSelect;
	}	

}
