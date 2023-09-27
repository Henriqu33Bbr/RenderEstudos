package com.example.demo.Domain.Controllers;

import com.example.demo.Domain.Models.Transacao;
import com.example.demo.Domain.Repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransacaoController {
    private final TransacaoRepository transacaoRepo;

    @Autowired
    public TransacaoController(TransacaoRepository transacaoRepo){
        this.transacaoRepo = transacaoRepo;
    };

    // Inserir
    @PostMapping("/inserir")
    public ResponseEntity<String> InserirTransacao(@RequestBody Transacao transacao){
        transacaoRepo.save(transacao);
        return ResponseEntity.ok("Transacao Feita!");
    }

    // ListarTodos
    @GetMapping("/ListarTodas")
    public List<Transacao> ListarTodasTransacao(){
        return transacaoRepo.findAll();
    }

    // BuscarPorTipo
    @GetMapping("/buscarPorTipo/{tipo}")
    public List<Transacao> ListarPorTipoTransacao(@PathVariable String tipo){
        List<Transacao> listaTransacoes = transacaoRepo.findAll();
        listaTransacoes.removeIf(transacao -> (!Objects.equals(transacao.getTipo_transacao(), tipo)));
        return listaTransacoes;
    }

    // AlterarPorId
    @PutMapping("/AlterarTransacaoPorId/{id}")
    public ResponseEntity<String> AlterarInformacoesTransacao(@PathVariable Long id, @RequestBody Transacao transacaoAlterar) {
        if (transacaoRepo.findById(id).isPresent()) {
            Transacao transacao = transacaoRepo.findById(id).get();

            transacao.setDescricao(transacaoAlterar.getDescricao());
            transacao.setValor(transacaoAlterar.getValor());
            transacao.setTipo_transacao(transacaoAlterar.getTipo_transacao());

            transacaoRepo.save(transacao);

            return ResponseEntity.ok("transacao atualizada!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("transacao não encontrada");
        }
    }

    //Excluir
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirTransacao(@PathVariable Long id){
        transacaoRepo.deleteById(id);
        return ResponseEntity.ok("transacao excluida!");
    }
}
