package com.daou.pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class PatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}
	@Configuration
	public class ServletRegistrationConfig {
		@Bean
		public ServletRegistrationBean worldServletBean()
		{
			return new ServletRegistrationBean(new WorldServlet(),"/world");
		}
	}

	public class WorldServlet extends HttpServlet {
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("World");
		}
	}
}

