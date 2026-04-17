package br.com.gems.core.service;

import br.com.gems.base.model.dto.ClienteDTO;
import br.com.gems.base.model.dto.RegisterClientDTO;
import br.com.gems.base.model.entity.Cliente;
import br.com.gems.base.model.entity.Pessoa;
import br.com.gems.core.repository.ClienteRepository;
import br.com.gems.core.repository.PessoaRepository;
import br.com.gems.exception.BusinessException;
import br.com.gems.exception.enums.ErrorTypeEnum;
import br.com.gems.security.model.dto.RegisterDTO;
import br.com.gems.security.service.AuthenticationService;
import br.com.gems.utils.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;
    private final AuthenticationService authenticationService;
    private final ModelMapper mapper;

    @Transactional
    public ClienteDTO create(RegisterClientDTO dto) {
        if (ObjectUtil.isNullOrEmpty(dto.getPessoa())) {
            throw new BusinessException(ErrorTypeEnum.FALHA, "Dados da pessoa não informados");
        }
        
        if (ObjectUtil.isNullOrEmpty(dto.getPessoa().getCpf())) {
            throw new BusinessException(ErrorTypeEnum.FALHA, "CPF não informado");
        }

        clienteRepository.findByPessoaCpf(dto.getPessoa().getCpf())
                .ifPresent(c -> {
                    throw new BusinessException(ErrorTypeEnum.FALHA, "Cliente já cadastrado para o CPF informado");
                });

        var pessoa = pessoaRepository.findByCpf(dto.getPessoa().getCpf())
                                     .orElseGet(() -> pessoaRepository.save(mapper.map(dto.getPessoa(), Pessoa.class)));

        
        var cliente = clienteRepository.save(
                Cliente.builder()
                        .pessoa(pessoa)
                        .dataCriacao(LocalDateTime.now())
                        .build()
        );

        authenticationService.registerUser(
            RegisterDTO.builder()
                    .username(dto.getPessoa().getCpf())
                    .email(dto.getPessoa().getEmail())
                    .roles(List.of("BANCO"))
                    .password(dto.getPassword())
                    .build()
        );
        
        return mapper.map(cliente, ClienteDTO.class);
    }
    
    @Transactional
    public ClienteDTO update(UUID id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorTypeEnum.ALERTA, "Cliente não encontrado"));
                
        if (ObjectUtil.isNullOrEmpty(dto.getPessoa())) {
            throw new BusinessException(ErrorTypeEnum.FALHA, "Dados da pessoa não informados");
        }

        Pessoa pessoa = cliente.getPessoa();
        pessoa.setNome(dto.getPessoa().getNome());
        pessoa.setTelefone(dto.getPessoa().getTelefone());
        pessoa.setEmail(dto.getPessoa().getEmail());
        
        pessoaRepository.save(pessoa);
        
        return mapper.map(cliente, ClienteDTO.class);
    }
    
    @Transactional
    public void delete(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorTypeEnum.ALERTA, "Cliente não encontrado"));
                
        cliente.setDataExclusao(LocalDateTime.now());
        clienteRepository.save(cliente);
    }

    public ClienteDTO findById(UUID id) {
        return clienteRepository.findById(id)
                .map(entity -> mapper.map(entity, ClienteDTO.class))
                .orElseThrow(() -> new BusinessException(ErrorTypeEnum.ALERTA, "Cliente não encontrado"));
    }

    public ClienteDTO findByCpf(String cpf) {
        if (ObjectUtil.isNullOrEmpty(cpf)) {
            throw new BusinessException(ErrorTypeEnum.FALHA, "CPF não informado");
        }

        return clienteRepository.findByPessoaCpf(cpf)
                .map(entity -> mapper.map(entity, ClienteDTO.class))
                .orElseThrow(() -> new BusinessException(ErrorTypeEnum.ALERTA, "Cliente não encontrado para o CPF " + cpf));
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(entity -> mapper.map(entity, ClienteDTO.class))
                .collect(Collectors.toList());
    }

}