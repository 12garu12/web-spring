package com.practice.webspring.app.models.dao;

import com.practice.webspring.app.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
