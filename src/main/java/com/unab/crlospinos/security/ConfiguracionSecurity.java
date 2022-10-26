package com.unab.crlospinos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unab.crlospinos.services.IUsuarioService;

@Configuration
@EnableWebSecurity
public class ConfiguracionSecurity extends WebSecurityConfigurerAdapter{

    private final IUsuarioService iUsuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ConfiguracionSecurity(IUsuarioService iUsuarioService,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.iUsuarioService=iUsuarioService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST,"/usuario").permitAll()
        .antMatchers(HttpMethod.GET,"/factura").permitAll()
        .antMatchers(HttpMethod.GET,"/factura/{id}").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(getUsuarioAutenticacion())
        .addFilter(new TokenAutorizacion(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(iUsuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

    public UsuarioAutenticacion getUsuarioAutenticacion() throws Exception{

        final UsuarioAutenticacion filtroAutenticacion = new UsuarioAutenticacion(authenticationManager());

        filtroAutenticacion.setFilterProcessesUrl("/usuario/login");

        return filtroAutenticacion;
    }

   // @Override
    //public void addCorsMappings(CorsRegistry registry) {
      //  registry.addMapping("/**")
        //.allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    //}
    
}
