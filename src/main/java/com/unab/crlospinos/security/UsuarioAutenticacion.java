package com.unab.crlospinos.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unab.crlospinos.model.peticiones.UsuarioSignupRequestModel;
import com.unab.crlospinos.services.IUsuarioService;
import com.unab.crlospinos.shared.UsuarioDto;
import com.unab.crlospinos.utils.AppContexto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


public class UsuarioAutenticacion extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public UsuarioAutenticacion(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                    
            try {
                UsuarioSignupRequestModel usuarioLoginRequestModel = new ObjectMapper()
                .readValue(request.getInputStream(), UsuarioSignupRequestModel.class);

                UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(usuarioLoginRequestModel.getUsername(), usuarioLoginRequestModel.getPassword(),new ArrayList<>());

                Authentication authentication=authenticationManager.authenticate(upat);
                
                return authentication;

            } catch (IOException e) {
                
                throw new RuntimeException(e);
            }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String username=((User) authResult.getPrincipal()).getUsername();

        SecretKey key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(ConstantesSecurity.TOKEN_SECRETO));

        String token=Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis()+ConstantesSecurity.FECHA_EXPIRACION))
        .signWith(key)
        .compact();

        IUsuarioService iUsuarioService=(IUsuarioService) AppContexto.getBean("usuarioService");
        UsuarioDto usuarioDto=iUsuarioService.leeUsuarioDto(username);

        response.addHeader("Access-Control-Expose-Headers", "Authorization, IdUsuario");
        response.addHeader("IdUsuario", usuarioDto.getIdUsuario());
        response.addHeader(ConstantesSecurity.HEADER_STRING, ConstantesSecurity.TOKEN_PREFIJO+token);

    }
        
}
