package com.unab.crlospinos.services;

import java.util.List;

import com.unab.crlospinos.shared.FacturaDto;

public interface IFacturaService {
    
    public FacturaDto crearFacturaDto(FacturaDto facturaCrearDto);

    public List<FacturaDto> facturasCreadas();

    public FacturaDto detalleFactura(String id);

    public FacturaDto actualizarFactura(String idFactura,FacturaDto facturaActualizarDto);

    public void eliminarFactura(String id,long idUsuario);
}
