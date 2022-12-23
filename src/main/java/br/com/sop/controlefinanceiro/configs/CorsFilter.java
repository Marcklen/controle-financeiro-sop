package br.com.sop.controlefinanceiro.configs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

	// Para usar no front com Angular e Swagger
	// private static final String ENDERECO_FRONT = "http://localhost:3000";
	
	// Liberado para todas as origens
    private static final String FRONT_ADRESS_ALLOWED = "*";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Access-Control-Allow-Origin", FRONT_ADRESS_ALLOWED);
        resp.setHeader("Access-Control-Allow-Credentials","true");

        if("OPTIONS".equals(req.getMethod()) && isValidOrigin(req.getHeader("Origin"))){
            resp.setHeader("Access-Control-Allow-Methods","GET, POST, DELETE, PUT, OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers","Authorization, Content-Type, Accept");
            resp.setHeader("Access-Control-Max-Age", "3600");
            resp.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, response);
        }
    }

    public Boolean isValidOrigin(String originInReqHeader) {
        return (FRONT_ADRESS_ALLOWED == "*") ? 
        		true : 
        			FRONT_ADRESS_ALLOWED.equals(originInReqHeader);
    }
}