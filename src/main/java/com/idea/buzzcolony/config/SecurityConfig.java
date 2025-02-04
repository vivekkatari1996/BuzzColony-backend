package com.idea.buzzcolony.config;

import com.idea.buzzcolony.repo.AppUserRepo;
import com.idea.buzzcolony.security.VerifyTokenFilter;
import com.idea.buzzcolony.util.AppMessage;
import com.idea.buzzcolony.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppMessage appMessage;

	@Autowired
	private AppUserRepo appUserRepo;

	@Override
	public void configure(WebSecurity web) {
		// Filters will not get executed for the resources
		web.ignoring().antMatchers("/", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.woff",
				"/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**",
				"/**/*.pdf", "/**/*.jpeg", "/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(Constants.permitURLs).permitAll().and()
				.exceptionHandling().and().csrf().disable()
				.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
				.addFilterBefore(new VerifyTokenFilter(appUserRepo), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().anyRequest().authenticated();
	}

}
