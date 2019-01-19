package com.daou.pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
@RestController
public class PatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}

	public class ServletRegistrationConfig {
		public ServletRegistrationBean worldServletBean()
		{
			return new ServletRegistrationBean(new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					String requestURL = req.getRequestURI();
					String substring = requestURL.substring(1, requestURL.length());
					System.out.println(substring);
					try {
						HttpServlet httpServlet = (HttpServlet)Class.forName("com.daou.pattern.Servletword").newInstance();
						httpServlet.service(req,resp);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

//					if("/world".equalsIgnoreCase(requestURL)){
//						resp.setContentType("text/html; charset=UTF-8");
//						PrintWriter out = resp.getWriter();
//						out.println("World");
//					}else{
//						resp.setContentType("text/html; charset=UTF-8");
//						PrintWriter out = resp.getWriter();
//						out.println("no");
//					}

				}
			}, "/");
		}
	}

	@RequestMapping("/test")
	public String test(){
		return "";
	}


}

