package com.unab.crlospinos.model.respuestas;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDataRestModel {
    
    private String idUsuario;
    private String nombre;
    private String email;
    private String username;
    private List<FacturaDataRestModel> facturaDataRestModelList;
    private List<ApartamentoDataRestModel> apartamentoDataRestModelList;

}
