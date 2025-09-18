package com.fatec.fatecar;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository 
extends JpaRepository<Produto, Integer> {

    @Query(value = "select * from produto where destaque>0 order by destaque"
    , nativeQuery = true)
    public List<Produto> listarVitrine();

    @Query(value = "SELECT * FROM produto WHERE modelo LIKE ?1 OR marca LIKE ?1 OR descritivo LIKE ?1 OR placa LIKE ?1", nativeQuery = true)
    public List<Produto> fazerBusca(String termo);

}
