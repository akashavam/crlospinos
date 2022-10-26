package com.unab.crlospinos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.crlospinos.model.peticiones.FacturaActualizarRequestModel;
import com.unab.crlospinos.model.peticiones.FacturaCrearRequestModel;
import com.unab.crlospinos.model.respuestas.FacturaDataRestModel;
import com.unab.crlospinos.model.respuestas.MensajeRestModel;
import com.unab.crlospinos.services.IFacturaService;
import com.unab.crlospinos.services.IUsuarioService;
import com.unab.crlospinos.shared.FacturaDto;
import com.unab.crlospinos.shared.UsuarioDto;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IFacturaService iFacturaService;

    @Autowired
    IUsuarioService iUsuarioService;

    @PostMapping
    public FacturaDataRestModel crearFactura(@RequestBody FacturaCrearRequestModel facturaCrearRequestModel){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getPrincipal().toString();

        FacturaDto facturacreaDto=modelMapper.map(facturaCrearRequestModel, FacturaDto.class);
        facturacreaDto.setUsername(username);

        FacturaDto facturaDto=iFacturaService.crearFacturaDto(facturacreaDto);

        FacturaDataRestModel facturaDataRestModel=modelMapper.map(facturaDto, FacturaDataRestModel.class);

        return facturaDataRestModel;
    }

    @GetMapping
    public List<FacturaDataRestModel> leerFacturas(){
        
        List<FacturaDto> facturaDtoList=iFacturaService.facturasCreadas();

        List<FacturaDataRestModel> facturaDataRestModelList=new ArrayList<>();

        for(FacturaDto facturaDto: facturaDtoList){
            FacturaDataRestModel facturaDataRestModel=modelMapper.map(facturaDto, FacturaDataRestModel.class);
            facturaDataRestModelList.add(facturaDataRestModel);
        }

        return facturaDataRestModelList;
    }

    @GetMapping(path = "/{id}")
    public FacturaDataRestModel detalleFactura(@PathVariable String id){

        FacturaDto facturaDto=iFacturaService.detalleFactura(id);

        FacturaDataRestModel facturaDataRestModel=modelMapper.map(facturaDto, FacturaDataRestModel.class);

        return facturaDataRestModel;
    }

    @PutMapping(path = "{id}")
    public FacturaDataRestModel actualizarFactura(@PathVariable String id, @RequestBody FacturaActualizarRequestModel facturaActualizarRequestModel){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getPrincipal().toString();

        FacturaDto facturaActualizarDto=modelMapper.map(facturaActualizarRequestModel, FacturaDto.class);
        facturaActualizarDto.setUsername(username);

        FacturaDto facturaDto=iFacturaService.actualizarFactura(id, facturaActualizarDto);

        FacturaDataRestModel facturaDataRestModel=modelMapper.map(facturaDto, FacturaDataRestModel.class);

        return facturaDataRestModel;
    }

    @DeleteMapping(path = "{id}")
    public MensajeRestModel eliminarFactura(@PathVariable String id){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getPrincipal().toString();

        UsuarioDto usuarioDto=iUsuarioService.leeUsuarioDto(username);

        MensajeRestModel mensajeRestModel=new MensajeRestModel();
        mensajeRestModel.setNombre("Eliminar");

        iFacturaService.eliminarFactura(id, usuarioDto.getId());

        mensajeRestModel.setResultado("Factura Eliminada con exito");

        return mensajeRestModel;
    }
    
}
