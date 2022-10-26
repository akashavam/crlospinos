package com.unab.crlospinos.model.peticiones;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacturaCrearRequestModel {

    private Date fechaFactura;
    private Date fechaVencimiento;
    private float valorPagar;
    private float valorMora;
    private float valorFactura;
    private String idApartamento;
    
}
