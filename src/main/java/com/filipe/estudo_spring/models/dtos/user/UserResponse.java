package com.filipe.estudo_spring.models.dtos.user;

import java.time.LocalDateTime;

import com.filipe.estudo_spring.models.entities.User;

public record UserResponse(
        Long id,
        String name,
        Boolean isActive,
        Integer age,
        String email,
        String login,
        String cpf,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public UserResponse(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getIsActive(),
                user.getAge(),
                user.getEmail(),
                user.getLogin(),
                maskCpf(user.getCpf()),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    private static String maskCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        String digits = cpf.replaceAll("\\D", "");

        if (digits.length() != 11) {
            return "***.***.***-**";
        }

        return String.format("***.***.**%c-%s", digits.charAt(8), digits.substring(9));
    }
}
