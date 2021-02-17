package com.practice.webspring.app.models.service;

import com.practice.webspring.app.models.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    void save(Cliente cliente);

    public Cliente findOne(Long id);

    void delete(Long id);

}
