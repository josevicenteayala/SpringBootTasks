package com.task.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebSecurityConfigurer {


    /**
     * Initialize the {@link SecurityBuilder}. Here only shared state should be created
     * and modified, but not properties on the {@link SecurityBuilder} used for building
     * the object. This ensures that the {@link #configure(SecurityBuilder)} method uses
     * the correct shared objects when building. Configurers should be applied here.
     *
     * @param builder
     * @throws Exception
     */
    @Override
    public void init(SecurityBuilder builder) throws Exception {
        System.out.println("1111111111 Builder " + builder);
    }

    /**
     * Configure the {@link SecurityBuilder} by setting the necessary properties on the
     * {@link SecurityBuilder}.
     *
     * @param builder
     * @throws Exception
     */
    @Override
    public void configure(SecurityBuilder builder) throws Exception {
        System.out.println("222222222222 Builder " + builder);

    }
}
