package com.unab.crlospinos.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ApartamentoDto implements Serializable {

    private static final long serialVersionUID=1L;

    private long id;
    private String idApartamento;
    

}
