package com.unab.crlospinos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unab.crlospinos.data.entidades.FacturaEntity;
import com.unab.crlospinos.data.entidades.UsuarioEntity;
import com.unab.crlospinos.data.repositorios.IFacturaRepository;
import com.unab.crlospinos.data.repositorios.IUsuarioRepository;
import com.unab.crlospinos.shared.FacturaDto;
import com.unab.crlospinos.shared.UsuarioDto;


@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IFacturaRepository iFacturaRepository;

    @Override
    public UsuarioDto crearUsuarioDto(UsuarioDto usuarioCrearDto){

        if(iUsuarioRepository.findByEmail(usuarioCrearDto.getEmail())!= null){
            throw new RuntimeException("Este correo ya esta en uso");
        }

        if(iUsuarioRepository.findByUsername(usuarioCrearDto.getUsername()) != null){
            throw new RuntimeException("Este usuario ya esta en uso");
        }
        
        UsuarioEntity usuarioEntity=modelMapper.map(usuarioCrearDto, UsuarioEntity.class);
        usuarioEntity.setIdUsuario(UUID.randomUUID().toString());
        usuarioEntity.setPasswordEncriptada(bCryptPasswordEncoder.encode(usuarioCrearDto.getPassword()));

        UsuarioEntity usuarioCreado=iUsuarioRepository.save(usuarioEntity);

        UsuarioDto usuarioDto= modelMapper.map(usuarioCreado, UsuarioDto.class);

        return usuarioDto;

    }

    public UsuarioDto leeUsuarioDto(String username){

        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUsername(username);

        if(usuarioEntity==null){
            throw new UsernameNotFoundException(username);
        }

        UsuarioDto usuarioDto=modelMapper.map(usuarioEntity, UsuarioDto.class);

        return usuarioDto;
    }

    @Override
    public List<FacturaDto> leerMisFacturas(String userName) {
        
        UsuarioEntity usuarioEntity=iUsuarioRepository.findByUsername(userName);

        List<FacturaEntity> FacturaEntityList=iFacturaRepository.getByUsuarioEntityIdOrderByCreadoDesc(usuarioEntity.getId());

        List<FacturaDto> facturaDtoList=new ArrayList<FacturaDto>();

        for(FacturaEntity facturaEntity: FacturaEntityList){
            FacturaDto facturaDto=modelMapper.map(facturaEntity, FacturaDto.class);
            facturaDtoList.add(facturaDto);
        }

        return facturaDtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UsuarioEntity usuarioEntity=iUsuarioRepository.findByUsername(username);

        if(usuarioEntity==null){
            throw new UsernameNotFoundException(username);
        }

        User usuario =new User(usuarioEntity.getUsername(), usuarioEntity.getPasswordEncriptada(), new ArrayList<>());

        return usuario;
    }
    
}
