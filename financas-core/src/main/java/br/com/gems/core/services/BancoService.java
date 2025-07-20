package br.com.gems.core.services;

import br.com.gems.core.repository.BancoRepository;
import br.com.gems.base.model.dto.BancoDTO;
import br.com.gems.exception.BusinessException;
import br.com.gems.exception.enums.ErrorTypeEnum;
import br.com.gems.utils.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BancoService {

    private final BancoRepository repository;
    private final ModelMapper mapper;

    public BancoDTO findById(UUID id) {
        return repository.findById(id)
                .map( entity -> mapper.map(entity, BancoDTO.class) )
                .orElseThrow(() -> new BusinessException( ErrorTypeEnum.ALERTA , "Banco não encontrado" ) );
    }

    public BancoDTO findByCodigo( String codigo ) {
        if(ObjectUtil.isNullOrEmpty( codigo ) ){
            throw new BusinessException( ErrorTypeEnum.FALHA , "Código do banco não informado" );
        }

        return repository.findByCodigo( codigo )
                .map( entity -> mapper.map(entity, BancoDTO.class) )
                .orElseThrow(() -> new BusinessException( ErrorTypeEnum.ALERTA , "Banco não encontrado para o código " + codigo ) );
    }

    public List<BancoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map( entity -> mapper.map(entity, BancoDTO.class) )
                .collect( Collectors.toList() );
    }

}
