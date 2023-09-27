package com.example.demo.Domain.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "Transacao")

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;
    private double valor;
    private Date data_transacao;
    private String tipo_transacao;

    public Transacao(long id, String descricao, double valor, String data_transacao, String tipo_transacao) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data_transacao = formato.parse(data_transacao);
        this.tipo_transacao = tipo_transacao;
    }

    public Transacao(){};
}
