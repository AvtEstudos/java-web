package br.com.andre.financeiro.web.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import br.com.andre.financeiro.util.HibernateUtil;

/*
 * 	Configura qual tipo de requisição web esta classe Filter vai interceptar.
 * Na etapa atual, queremos que seja abertas conexões sempre que for requisitada
 * uma página com extensão .jsf.
 */
@WebFilter(urlPatterns = {"*.jsf"})
public class ConexaoHinernateFilter implements Filter {

	private SessionFactory sf;
	
	/*
	 * É executado quando o aplicativo web é colocado no ar.
	 */
	public void init(FilterConfig config) throws ServletException {
	
		this.sf = HibernateUtil.getSessionFactory();
	}

	/*
	 * É onde as requisições web podem ser interceptada.
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		Session currSession = this.sf.getCurrentSession();		
		Transaction transaction = null;
		
		try {			
			
			transaction = currSession.beginTransaction();
			
			/*
			 * Ponto mais importante. É aqui que o processamento é passado adiante.
			 * Se essa linha não for executada, o objetivo original não será
			 * alcançado. 
			 */
			chain.doFilter(servletRequest, servletResponse);
			
			transaction.commit();
			
			if(currSession.isOpen()) {
				currSession.close();
			}
			
		} catch (Throwable ex) {
			try {
				if (transaction.isActive()) {
					transaction.rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			
			throw new ServletException(ex);
		}
	}

	/*
	 * É executado quando o aplicativo web é desativado ou o servidor é retirado do ar
	 */
	@Override
	public void destroy() {	}
	
}
