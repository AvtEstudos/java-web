package br.com.andre.financeiro.web.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import br.com.andre.financeiro.util.HibernateUtil;

/*
 * 	Configura qual tipo de requisi��o web esta classe Filter vai interceptar.
 * Na etapa atual, queremos que seja abertas conex�es sempre que for requisitada
 * uma p�gina com extens�o .jsf.
 */
@WebFilter(urlPatterns = {"*.jsf"})
public class ConexaoHinernateFilter implements Filter {

	private SessionFactory sf;
	
	/*
	 * � executado quando o aplicativo web � colocado no ar.
	 */
	public void init(FilterConfig config) throws ServletException {
	
		this.sf = HibernateUtil.getSessionFactory();
	}

	/*
	 * � onde as requisi��es web podem ser interceptada.
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		Session currSession = this.sf.getCurrentSession();		
		Transaction transaction = null;
		
		try {			
			
			transaction = currSession.beginTransaction();
			
			/*
			 * Ponto mais importante. � aqui que o processamento � passado adiante.
			 * Se essa linha n�o for executada, o objetivo original n�o ser�
			 * alcan�ado. 
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
	 * � executado quando o aplicativo web � desativado ou o servidor � retirado do ar
	 */
	@Override
	public void destroy() {	}
	
}
