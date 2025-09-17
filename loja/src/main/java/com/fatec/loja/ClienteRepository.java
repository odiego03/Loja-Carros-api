package com.fatec.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends 
JpaRepository<Cliente, Integer>{

    @Query(value = "select * from cliente where email=?1 and senha=?2 and ativo=1"
    , nativeQuery = true)
    public Optional<Cliente> fazerLogin(String email, String senha);

    @Query(value = "select * from cliente where ativo=0",
     nativeQuery=true)
     public List<Cliente> listaInativos();

  
}
