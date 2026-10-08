package com.mycompany.pietapa9.repository;

import com.mycompany.pietapa9.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
