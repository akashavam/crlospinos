package com.unab.crlospinos.data.entidades;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "factura")
//@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
    @Index(columnList = "idfactura",name = "index_idFactura",unique = true),
})
public class FacturaEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String idFactura;

    @Column(nullable = false)
    private Date fechaFactura;

    @Column(nullable = false)
    private Date fechaVencimiento;

    @Column(nullable = false)
    private float valorPagar;

    @Column(nullable = false)
    private float valorMora;

    @Column(nullable = false)
    private float valorFactura;

    @Column(nullable = false)
    private boolean estado;

    @CreatedDate
    private Date creado;

    @ManyToOne
    @JoinColumn(name = "id_usuario") // relacion con usuario
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "id_apartamento") // relacion con apartamento
    private ApartamentoEntity apartamentoEntity;
    
}
