package com.itachay.ldapauthentication.config;

import com.itachay.ldapauthentication.domain.LDAPServerConfiguration;
import com.itachay.ldapauthentication.repository.LDAPServerConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LDAPServerConfigurationRepository ldapServerConfigurationRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		List<LDAPServerConfiguration> ldapServerConfigurations = ldapServerConfigurationRepository.getAllConfigurations();

		ldapServerConfigurations.stream().forEach(ldapServerConfiguration -> {
			try {
				configureLdapDynamically(auth, ldapServerConfiguration.getUserDnPatterns(), ldapServerConfiguration.getUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

    }

    public void configureLdapDynamically(AuthenticationManagerBuilder auth, String userDnPatters, String url) throws Exception {
        auth.ldapAuthentication().userDnPatterns(userDnPatters)
                .contextSource()
                .url(url)
                .and().passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }

}
