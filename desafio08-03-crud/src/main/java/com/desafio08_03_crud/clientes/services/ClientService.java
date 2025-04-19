package com.desafio08_03_crud.clientes.services;

import com.desafio08_03_crud.clientes.dto.ClientDTO;
import com.desafio08_03_crud.clientes.entities.Client;
import com.desafio08_03_crud.clientes.repositories.ClientRepository;
import com.desafio08_03_crud.clientes.services.exceptions.DatabaseException;
import com.desafio08_03_crud.clientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    /* Parâmetro, Método e Retorno */
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        ); // Usar o "orElseThrow" do Supplier
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado!");
        }
    }

    public void delete(Long id) {

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Cliente não encontrado!");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial!");
        }


    }

    public void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
    }

}
