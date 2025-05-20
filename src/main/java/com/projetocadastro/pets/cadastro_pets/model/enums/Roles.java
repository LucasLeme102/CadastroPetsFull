package com.projetocadastro.pets.cadastro_pets.model.enums;

public enum Roles {
    TUTOR("tutor"),
    ADMIN("admin");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
