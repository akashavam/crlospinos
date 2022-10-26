package com.unab.crlospinos.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "usuario")
@Table(indexes = {
    @Index(columnList = "idusuario",name = "index_idUsuario",unique = true),
    @Index(columnList = "email",name = "index_email",unique = true),
    @Index(columnList = "username",name = "index_username",unique = true)
})
public class UsuarioEntity implements Serializable {
  
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String idUsuario;

    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false,length = 100)
    private String username;

    @Column(nullable = false)
    private String passwordEncriptada;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "usuarioEntity") //relacion con factura
    private List<FacturaEntity> facturaEntityList=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "usuarioEntity") // relacion con apartamento
    private List<ApartamentoEntity> apartamentoEntityList=new ArrayList<>();
    
}
