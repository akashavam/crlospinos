package com.unab.crlospinos.data.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.crlospinos.data.entidades.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
    
    public UsuarioEntity findByEmail (String email);

    public UsuarioEntity findByUsername (String username);
}
