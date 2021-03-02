package com.practice.webspring.app.models.dao;

import com.practice.webspring.app.models.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
}
