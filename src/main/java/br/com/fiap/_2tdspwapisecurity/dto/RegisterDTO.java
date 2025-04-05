package br.com.fiap._2tdspwapisecurity.dto;

import br.com.fiap._2tdspwapisecurity.model.UserRole;

public record RegisterDTO(
        String username,
        String password,
        UserRole role
    ) {
}
