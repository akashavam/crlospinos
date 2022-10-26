package com.unab.crlospinos.shared;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDto implements Serializable {
    
    private static final long serialVersionUID=1L;

    private long id;
    private String idUsuario;
    private String nombre;
    private String email;
    private String username;
    private String password;
    private String passwordEncriptada;
    private List<FacturaDto> facturaDtoList;
    private List<ApartamentoDto> apartamentoDtoList;

}
