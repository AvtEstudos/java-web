package br.com.andre.financeiro.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.andre.financeiro.categoria.Categoria;
import br.com.andre.financeiro.categoria.CategoriaRN;

@FacesConverter(forClass = Categoria.class) //1
public class CategoriaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) { //2
		
		if(value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value); //3
			try {
				CategoriaRN categoriaRN = new CategoriaRN();
				return categoriaRN.carregar(codigo); //4				
			}catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar a categoria de código " +
					value + ". " + e.getMessage());
			}
		}		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) { //5
		
		if (value != null) {
			Categoria categoria = (Categoria) value; //6
			return categoria.getCodigo().toString(); //7
		}
		return "";
	}	

}
