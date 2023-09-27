package com.example.demo.Domain.Repositories;

import com.example.demo.Domain.Models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
