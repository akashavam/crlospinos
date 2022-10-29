package com.unab.crlospinos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.crlospinos.data.entidades.ApartamentoEntity;
import com.unab.crlospinos.data.entidades.FacturaEntity;
import com.unab.crlospinos.data.entidades.UsuarioEntity;
import com.unab.crlospinos.data.repositorios.IApartamentoRepository;
import com.unab.crlospinos.data.repositorios.IFacturaRepository;
import com.unab.crlospinos.data.repositorios.IUsuarioRepository;
import com.unab.crlospinos.shared.FacturaDto;

@Service
public class FacturaService implements IFacturaService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    IFacturaRepository iFacturaRepository;

    @Autowired
    IApartamentoRepository iApartamentoRepository;

    @Override
    public FacturaDto crearFacturaDto(FacturaDto facturaCrearDto) {

        UsuarioEntity usuarioEntity = iUsuarioRepository.findByUsername(facturaCrearDto.getUsername());

        ApartamentoEntity apartamentoEntity=iApartamentoRepository.findById(facturaCrearDto.getIdApartamento());

        FacturaEntity facturaEntity=new FacturaEntity();
        facturaEntity.setIdFactura(UUID.randomUUID().toString()); 
        facturaEntity.setFechaFactura(facturaCrearDto.getFechaFactura());
        facturaEntity.setFechaVencimiento(facturaCrearDto.getFechaVencimiento());
        facturaEntity.setCreado(facturaCrearDto.getCreado());
        facturaEntity.setValorFactura(facturaCrearDto.getValorFactura());
        facturaEntity.setValorMora(facturaCrearDto.getValorMora());
        facturaEntity.setValorPagar(facturaCrearDto.getValorPagar());
        facturaEntity.setUsuarioEntity(usuarioEntity);
        facturaEntity.setApartamentoEntity(apartamentoEntity);

        FacturaEntity facturaCreada=iFacturaRepository.save(facturaEntity);

        FacturaDto facturaDto=modelMapper.map(facturaCreada, FacturaDto.class);
        
        return facturaDto;
    }

    @Override
    public List<FacturaDto> facturasCreadas() {
        
        List<FacturaEntity> facturaEntityList=iFacturaRepository.facturasCreadas();

        List<FacturaDto> facturaDtoList=new ArrayList<>();

        for(FacturaEntity facturaEntity:facturaEntityList){
            FacturaDto facturaDto=modelMapper.map(facturaEntity, FacturaDto.class);
            facturaDtoList.add(facturaDto);
        }
        return facturaDtoList;
    }

    @Override
    public FacturaDto detalleFactura(String id) {
        FacturaEntity facturaEntity=iFacturaRepository.findByIdFactura(id);

        FacturaDto facturaDto=modelMapper.map(facturaEntity, FacturaDto.class);
        
        return facturaDto;
    }

    @Override
    public FacturaDto actualizarFactura(String idFactura, FacturaDto facturaActualizarDto) {
        
        FacturaEntity facturaEntity=iFacturaRepository.findByIdFactura(idFactura);

        UsuarioEntity usuarioEntity=iUsuarioRepository.findByUsername(facturaActualizarDto.getUsername());

        if(facturaEntity.getUsuarioEntity().getId() != usuarioEntity.getId()){
            throw new RuntimeException("No se puede realizar esta accion");
        }

        facturaEntity.setEstado(true);

        FacturaEntity facturaEntityActualizado=iFacturaRepository.save(facturaEntity);

        FacturaDto facturaDto=modelMapper.map(facturaEntityActualizado, FacturaDto.class);
        
        return facturaDto;
    }

    @Override
    public void eliminarFactura(String id, long idUsuario) {
        
        FacturaEntity facturaEntity=iFacturaRepository.findByIdFactura(id);

        if(facturaEntity.getUsuarioEntity().getId() != idUsuario){
            throw new RuntimeException("No se puede eliminar la factura");
        }

        iFacturaRepository.delete(facturaEntity);
        
    }
    
}
