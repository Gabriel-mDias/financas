package br.com.gems.security.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RegisterDTO(String username, String password, String email, List<String> roles) {
}
