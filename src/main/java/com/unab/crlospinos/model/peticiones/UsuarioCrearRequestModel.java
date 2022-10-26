package com.unab.crlospinos.model.peticiones;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioCrearRequestModel {

    private String nombre;
    private String email;
    private String username;
    private String password;
    
}
