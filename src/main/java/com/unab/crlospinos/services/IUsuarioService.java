package com.unab.crlospinos.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.unab.crlospinos.shared.FacturaDto;
import com.unab.crlospinos.shared.UsuarioDto;

    public interface IUsuarioService extends UserDetailsService {

    public UsuarioDto crearUsuarioDto(UsuarioDto usuarioCrearDto);
    public UsuarioDto leeUsuarioDto(String username);
    public List<FacturaDto> leerMisFacturas(String userName);
    
}
