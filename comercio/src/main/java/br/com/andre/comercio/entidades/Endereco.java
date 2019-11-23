package br.com.andre.comercio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Parameter;

import br.com.andre.comercio.entidades.Cliente;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {	
	
	private static final long serialVersionUID = 8235672944945121087L;
	
	@Id
	@GeneratedValue(generator = "fk_endereco_cod_cliente")
	@org.hibernate.annotations.GenericGenerator(
			name = "fk_endereco_cod_cliente", 
			strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "cliente"))
	@Column(name = "cod_cliente")
	private Integer endereco;
	
	@OneToOne(mappedBy="endereco")
	private Cliente cliente;
	
	private String rua;
	
	private String cidade;

	public Integer getEndereco() {
		return endereco;
	}

	public void setEndereco(Integer endereco) {
		this.endereco = endereco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}	

}
