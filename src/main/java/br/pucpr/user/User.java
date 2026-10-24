package br.pucpr.user;

import br.pucpr.table.reflection.Column;

public record User(Long id, @Column(header = "Nome") String name, String email, String cpf) {}
