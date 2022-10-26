package com.unab.crlospinos.data.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.crlospinos.data.entidades.ApartamentoEntity;

@Repository
public interface IApartamentoRepository extends CrudRepository<ApartamentoEntity,Long> {

    public ApartamentoEntity findById(long id);

}
