package br.com.andre.capitulo12.primefaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class VotoBean {
	
	private PieChartModel votos;
	
	public PieChartModel getVotos() {
		int random1 = (int) (Math.random() * 1000);
		int random2 = (int) (Math.random() * 1000);
		int random3 = (int) (Math.random() * 1000);
		
		this.votos = new PieChartModel();
		this.votos.getData().put("Marca 1", random1);
		this.votos.getData().put("Marca 2", random2);
		this.votos.getData().put("Marca 3", random3);
		
		this.votos.setTitle("Votos");
		this.votos.setLegendPosition("ne");
		
		return this.votos;
	}

}
