package br.com.andre.capitulo3.crudannotations;

import java.sql.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.andre.capitulo3.conexao.HibernateUtil;

public class ContatoCrudAnnotations {
	
	private Session sessao;
	
	public ContatoCrudAnnotations(Session sessao) {
		this.sessao = sessao;
	}
	
	public void salvar(Contato contato) {
		sessao.save(contato);
	}
	
	public void atualizar(Contato contato) {
		sessao.update(contato);
	}
	
	public void excluir(Contato contato) {
		sessao.delete(contato);
	}
	
	public List<Contato> listar() {
		Query consulta = sessao.createQuery("from Contato");
		return consulta.list();
	}
	
	public Contato buscaContato(int valor) {
		Query consulta = sessao.createQuery("from Contato where codigo =:parametro");
		consulta.setInteger("parametro", valor);
		return (Contato) consulta.uniqueResult();
	}
	
	public static void main(String[] args) {
		
		try {
			
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transacao = sessao.beginTransaction();
			ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
			
			Contato contato1 = new Contato();
			contato1.setNome("Solanu");
			contato1.setTelefone("(99) 3333-4444");
			contato1.setEmail("solanu@javaparaweb.com.br");
			contato1.setDataCadastro(new Date(System.currentTimeMillis()));
			contato1.setObservacao("Novo cliente");
			contatoCrud.salvar(contato1);
			contato1.setObservacao("Novo cliente");
			contatoCrud.atualizar(contato1);
			
			Contato contato2 = new Contato();
			contato2.setNome("Lunare");
			contato2.setTelefone("(99) 7777-5555");
			contato2.setEmail("lunare@javaparaweb.com.br");
			contato2.setDataCadastro(new Date(System.currentTimeMillis()));
			contato2.setObservacao("Cliente em dia");
			contatoCrud.salvar(contato2);
						
			System.out.println("Total de registros cadastrados: " + contatoCrud.listar().size());
			contatoCrud.excluir(contato2);
			transacao.commit();
			System.out.println("Total de registros cadastrados: " + contatoCrud.listar().size());			
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}				
	}
}
