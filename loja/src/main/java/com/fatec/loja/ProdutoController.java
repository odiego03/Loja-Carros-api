package com.fatec.loja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
    @Autowired
    ProdutoRepository bd;

    @Autowired
    ClienteRepository cd;

    @Autowired
    LojaService service;

    @PostMapping("/api/produto")
    public void gravar(@RequestBody Produto obj){
        bd.save(obj);
        System.out.println("Produto gravado com sucesso!");
    }

    @GetMapping("/api/produto/{codigo}")
    public Produto carregar(@PathVariable int codigo){
        return bd.findById(codigo).orElse(new Produto());
    }

    @PutMapping("/api/produto")
    public void alterar(@RequestBody Produto obj){
        if(bd.existsById(obj.getCodigo())){
            bd.save(obj);
            System.out.println("Produto alterado com sucesso!");
        }
    }

    @DeleteMapping("/api/produto/{codigo}/cliente/{id}")
    public void remover(@PathVariable int codigo, @PathVariable int id){
        var cliente = cd.findById(id);
        if (cliente.isPresent() && cliente.get().getPermissao() > 1){
            bd.deleteById(codigo);
            System.out.println("Produto removido com sucesso!");
        }
    }

    @GetMapping("/api/produtos")
    public List<Produto> listar(){ return bd.findAll();}

    @GetMapping("/api/produtos/vitrine")
    public List<Produto> carregarVitrine(){ return bd.listarVitrine(); }

    @GetMapping("/api/produtos/busca/{termo}")
    public List<Produto> fazerBuscar(@PathVariable String termo){ return bd.fazerBusca("%"+termo+"%"); }
}
