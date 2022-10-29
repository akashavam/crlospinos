package com.unab.crlospinos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.crlospinos.model.peticiones.UsuarioCrearRequestModel;
import com.unab.crlospinos.model.respuestas.FacturaDataRestModel;
import com.unab.crlospinos.model.respuestas.UsuarioDataRestModel;
import com.unab.crlospinos.services.IUsuarioService;
import com.unab.crlospinos.shared.FacturaDto;
import com.unab.crlospinos.shared.UsuarioDto;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioService iUsuarioService;

    @GetMapping
    public UsuarioDataRestModel leerUsuario(){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getPrincipal().toString();

        UsuarioDto usuarioDto= iUsuarioService.leeUsuarioDto(username);

        UsuarioDataRestModel usuarioDataRestModel=modelMapper.map(usuarioDto, UsuarioDataRestModel.class);

        return usuarioDataRestModel;
    }

    @PostMapping
    public UsuarioDataRestModel crearUsuario(@RequestBody UsuarioCrearRequestModel usuarioCrearRequestModel){

       UsuarioDto usuarioCrearDto= modelMapper.map(usuarioCrearRequestModel, UsuarioDto.class);

       UsuarioDto usuarioDto= iUsuarioService.crearUsuarioDto(usuarioCrearDto);

       UsuarioDataRestModel usuarioDataRestModel= modelMapper.map(usuarioDto, UsuarioDataRestModel.class);
        
        return usuarioDataRestModel;
    }

    @GetMapping(path = "/misfacturas")
    public List<FacturaDataRestModel> leerMisFacturas(){
        
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getPrincipal().toString();

        List<FacturaDto> facturaDtoList=iUsuarioService.leerMisFacturas(username);

        List<FacturaDataRestModel> facturaDataRestModelList=new ArrayList<>();

        for(FacturaDto facturaDto:facturaDtoList){
            FacturaDataRestModel facturaDataRestModel=modelMapper.map(facturaDto, FacturaDataRestModel.class);
            /*if(facturaDataRestModel.getFechaFactura().compareTo(new Date(System.currentTimeMillis())) < 0){
                facturaDataRestModel.setEstado(true);
            }*/
            facturaDataRestModelList.add(facturaDataRestModel);
    }

    return facturaDataRestModelList;
    
}
}
