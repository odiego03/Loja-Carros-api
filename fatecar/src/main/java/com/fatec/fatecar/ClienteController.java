package com.fatec.fatecar;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {
     @Autowired
    private ClienteRepository bd;

    @Autowired
    private LojaService util;

    @PostMapping("/api/cliente")
    public void gravar(@RequestBody Cliente obj){
        bd.save(obj);
        String email = "<b>Email de confirmação de cadastro</b><br><br>" +
                "Seja bem-vindo, "+ obj.getNome() + "! Clique no link abaixo para confirmar seu cadastro:<br>" +
                "<a href='http://localhost:8080/api/cliente/efetivar/"+ util.md5(obj.getEmail()) +"'>Clique aqui</a>";
        util.enviaEmailHTML(obj.getEmail(), "Confirmação de cadastro", email);
        System.out.println("Cliente gravado com sucesso!");
    }

    @PutMapping("/api/cliente")
    public void alterar(@RequestBody Cliente obj){
        if(bd.existsById(obj.getCodigo())) bd.save(obj);
        System.out.println("Cliente alterado com sucesso!");
    }

    @PatchMapping("/api/cliente/redefinir-senha")
    public String redefinirSenha(@RequestBody Cliente obj) {
        Optional<Cliente> cliente = bd.findByEmail(obj.getEmail());
        if(cliente.isEmpty()) {
            return "Cliente não encontrado";
        }
        Cliente c = cliente.get();
        c.setSenha(obj.getSenha()); 
        bd.save(c);
        return "Senha atualizada com sucesso!";
    }
    

    @GetMapping("/api/clientes")
    public List<Cliente> listarTodos() {
        return bd.findAll();
    }

    @GetMapping("/api/cliente/{codigo}")
    public Cliente carregar(@PathVariable("codigo") int id){
        return bd.findById(id).orElse(new Cliente());
    }

    @DeleteMapping("/api/cliente/{codigo}/removido-por/{idAdmin}")
    public void remover(@PathVariable int codigo,@PathVariable int idAdmin){
        Optional<Cliente> admin = bd.findById(idAdmin);
        if (admin.isPresent() && admin.get().getPermissao() >= 1) {
            bd.deleteById(codigo);
            System.out.println("Cliente removido com sucesso!");
        }
    }

    @PostMapping("/api/cliente/login")
    public Cliente fazerLogin(@RequestBody Cliente obj){
        obj.setSenha(util.md5(obj.getSenha()));
        Optional<Cliente> retorno = bd.fazerLogin(obj.getEmail(), obj.getSenha());
        return retorno.orElse(new Cliente());
    }

    @GetMapping("/api/cliente/inativos")
    public List<Cliente> listarInativos(){
        return bd.listaInativos();
    }

    @PatchMapping("/api/cliente/efetivar/{codigo}")
    public void efetivar(@PathVariable int codigo){
        Cliente obj = bd.findById(codigo).orElse(null);
        if(obj != null){
            obj.setAtivo(true);
            bd.save(obj);
            System.out.println("Cliente ativado!");
        }
    }
}
