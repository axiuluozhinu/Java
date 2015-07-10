package cn.edu.bzu.book.filter;

import java.io.IOException;
import java.util.logging.LogRecord;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter, java.util.logging.Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		//�趨����������Ӧ���룬web.xml������
		servletRequest.setCharacterEncoding("GBK");
		servletResponse.setCharacterEncoding("GBK");
		chain.doFilter(servletRequest, servletResponse);
		}

	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

}
