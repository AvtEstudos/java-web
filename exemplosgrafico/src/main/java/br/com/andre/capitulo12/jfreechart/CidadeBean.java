package br.com.andre.capitulo12.jfreechart;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class CidadeBean {
	
	private StreamedContent grafico;
	private static final Logger log = Logger.getLogger(CidadeBean.class.getName()); //1
	
	public CidadeBean() {
		
		try {
			JFreeChart graficoPizza = ChartFactory.createPieChart("5 cidades mais populosas de SC", 
					this.gerarDados(), true, true, false); //2
			File arquivoGrafico = new File("pizza.png"); //3
			ChartUtilities.saveChartAsPNG(arquivoGrafico, graficoPizza, 500, 300); //4
			this.grafico = new DefaultStreamedContent(new FileInputStream(arquivoGrafico), "image/png"); //5			
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
	}	 

	private DefaultPieDataset  gerarDados() {
		DefaultPieDataset dts = new DefaultPieDataset(); //6
		dts.setValue("Blumenau", new Double(334002.0));		
		dts.setValue("Criciúma", new Double(204667.0));
		dts.setValue("Florianopólis", new Double(461524.0));
		dts.setValue("Joinville", new Double(554601.0));
		dts.setValue("São José", new Double(228561.0));
		return null;
	}
	
	public StreamedContent getGrafico() {
		return this.grafico;
	}

}
