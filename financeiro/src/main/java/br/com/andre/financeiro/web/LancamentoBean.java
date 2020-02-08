package br.com.andre.financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.andre.financeiro.categoria.Categoria;
import br.com.andre.financeiro.conta.Conta;
import br.com.andre.financeiro.lancamento.Lancamento;
import br.com.andre.financeiro.lancamento.LancamentoRN;

@ManagedBean
@ViewScoped //1
public class LancamentoBean implements Serializable { // 2 
	
	private static final long serialVersionUID = 1143706168643717139L;	

	private List<Lancamento> lista;
	private Conta            conta;
	private List<Double>     saldos;
	private float            saldoGeral;
	private Lancamento       editado = new Lancamento();
	
	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean; 
	
	public LancamentoBean() { //3
		this.novo();
	}
	
	public String novo() { // 4
		this.editado = new Lancamento();
		this.editado.setData(new Date());
		return null;
	}
	
	public void editar() { // 5
		
	}
	
	public void excluir() {
		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.excluir(this.editado);
		this.lista = null;
	}
	
	public void salvar() {
		this.editado.setUsuario(this.contextoBean.getUsuarioLogado());
		this.editado.setConta(this.contextoBean.getContaAtiva());
		
		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.salvar(this.editado);
		
		this.novo(); //6
		this.lista = null;
		
	}
	
	public List<Lancamento> getLista(){ //7
		
		if (this.lista == null || this.conta != this.contextoBean.getContaAtiva()) {
			this.conta = this.contextoBean.getContaAtiva();
			
			Calendar dataSaldo = new GregorianCalendar(); //8
			dataSaldo.add(Calendar.MONTH, -1);
			dataSaldo.add(Calendar.DAY_OF_MONTH, -1);
			
			Calendar inicio = new GregorianCalendar(); //9
			inicio.add(Calendar.MONTH, -1);
			
			LancamentoRN lancamentoRN = new LancamentoRN();
			this.saldoGeral = lancamentoRN.saldo(this.conta, dataSaldo.getTime()); //10
			this.lista = lancamentoRN.listar(this.conta, inicio.getTime(), null);
			
			Categoria categoria = null;
			double saldo = this.saldoGeral;
			this.saldos = new ArrayList<Double>();
			
			for (Lancamento lancamento : this.lista) {
				categoria = lancamento.getCategoria();
				saldo = saldo + (lancamento.getValor().floatValue() * categoria.getFator());
				this.saldos.add(saldo);
			}			
		}
		
		return this.lista;		
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Double> getSaldos() {
		return saldos;
	}

	public void setSaldos(List<Double> saldos) {
		this.saldos = saldos;
	}

	public float getSaldoGeral() {
		return saldoGeral;
	}

	public void setSaldoGeral(float saldoGeral) {
		this.saldoGeral = saldoGeral;
	}

	public Lancamento getEditado() {
		return editado;
	}

	public void setEditado(Lancamento editado) {
		this.editado = editado;
	}

	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}	
	
}
