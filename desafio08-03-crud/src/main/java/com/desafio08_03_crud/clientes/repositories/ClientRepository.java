package com.desafio08_03_crud.clientes.repositories;

import com.desafio08_03_crud.clientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
