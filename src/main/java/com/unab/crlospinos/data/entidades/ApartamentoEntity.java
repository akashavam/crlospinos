package com.unab.crlospinos.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "apartamento")
@Table(indexes = {
    @Index(columnList = "idapartamento",name = "index_idApartamento",unique = true),
})
public class ApartamentoEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String idApartamento;

    @ManyToOne
    @JoinColumn(name = "id_usuario") //relacion con usuario
    private UsuarioEntity usuarioEntity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "apartamentoEntity") // relacion con factura
    private List<FacturaEntity> facturaEntityList=new ArrayList<>();

    
}
