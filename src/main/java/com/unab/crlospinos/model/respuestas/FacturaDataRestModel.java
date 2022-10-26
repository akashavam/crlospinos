package com.unab.crlospinos.model.respuestas;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacturaDataRestModel {

    private String idFactura;
    private Date fechaFactura;
    private Date fechaVencimiento;
    private float valorPagar;
    private float valorMora;
    private float valorFactura;
    private boolean estado;
    private UsuarioDataRestModel usuarioEntity;
    private ApartamentoDataRestModel apartamentoEntity;
    
}
