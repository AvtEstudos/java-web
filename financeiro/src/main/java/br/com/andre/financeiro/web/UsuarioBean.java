package br.com.andre.financeiro.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import br.com.andre.financeiro.usuario.Usuario;
import br.com.andre.financeiro.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@RequestScoped

public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	private String confirmarSenha;
	
	public String novo() {
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/usuario";
	}
	
	public String salvar() {
		
		/*
		 *		Instancia para adicionar erros, instacia será utilizada caso de erro na
		 *	confirmação de senha 		
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		
		String senha = this.usuario.getSenha();
		if(!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		
		/*
		 *		O retorno "usuariosucesso" é diferente do retorno no método novo(), ou seja, 
		 *	ele não tem o /publico na frente. Isso é possível porque a chamada do método
		 *	salvar() da classe UsuarioBean sempre vai acontecer da página /publico/usuario.jsf, 
		 *	ou seja, já está no contexto da pasta /publico. Pelas regras de navegação implícita,
		 *	o retorno "usuariosucesso" vai exibir a página /publico/usuariosucesso.  
		 */
		return "usuariosucesso";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}	
	
}
