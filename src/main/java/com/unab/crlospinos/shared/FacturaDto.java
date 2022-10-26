package com.unab.crlospinos.shared;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class FacturaDto implements Serializable {

    private static final long serialVersionUID=1L;

    private long id;
    private String idFactura;
    private String username;
    private long idApartamento;
    private Date fechaFactura;
    private Date fechaVencimiento;
    private float valorPagar;
    private float valorMora;
    private float valorFactura;
    private Date creado;
    private boolean estado;
    private UsuarioDto usuarioDto;
    private ApartamentoDto apartamentoDto;
    
}
