package com.github.xuqplus2.authserver.config;

import com.github.xuqplus2.authserver.config.kz.AppRememberMeServices;
import com.github.xuqplus2.authserver.config.kz.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
public class PersistentTokenRepositoryConfig {

    @Bean
    @Autowired
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Bean
    @Autowired
    public AppRememberMeServices rememberMeServices(PersistentTokenRepository repository, AppUserDetailsService userDetailsService) {
        AppRememberMeServices appRememberMeServices = new AppRememberMeServices(UUID.randomUUID().toString(), userDetailsService, repository);
        return appRememberMeServices;
    }
}
