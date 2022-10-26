package com.unab.crlospinos.data.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unab.crlospinos.data.entidades.FacturaEntity;

@Repository
public interface IFacturaRepository extends PagingAndSortingRepository<FacturaEntity,Long>{

    List<FacturaEntity> getByUsuarioEntityIdOrderByCreadoDesc(long usuarioEntityId);

    @Query(nativeQuery = true,value = "SELECT * FROM factura ORDER BY fecha_factura DESC LIMIT 10")
    List<FacturaEntity> facturasCreadas();

    FacturaEntity findByIdFactura(String id);
    
    
}
