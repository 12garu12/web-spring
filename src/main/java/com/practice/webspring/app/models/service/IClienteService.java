package com.practice.webspring.app.models.service;

import com.practice.webspring.app.models.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    void save(Cliente cliente);

    public Cliente findOne(Long id);

    void delete(Long id);

}
