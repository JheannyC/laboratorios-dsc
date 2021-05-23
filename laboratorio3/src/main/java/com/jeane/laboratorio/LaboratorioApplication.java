package com.jeane.laboratorio;

import com.jeane.laboratorio.filters.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LaboratorioApplication {

	@Bean
	public FilterRegistrationBean<TokenFilter> tokenFilter(){
		FilterRegistrationBean<TokenFilter> filterJwt = new FilterRegistrationBean<>();
		filterJwt.setFilter(new TokenFilter());
		filterJwt.addUrlPatterns("/v1/api/disciplinas/nota/{id}", "/v1/api/disciplinas/likes/{id}", "/v1/api/disciplinas/comentarios/{id}");
		return filterJwt;
	}

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioApplication.class, args);
	}

}
