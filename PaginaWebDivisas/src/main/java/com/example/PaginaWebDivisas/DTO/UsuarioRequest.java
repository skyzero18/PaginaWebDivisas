package com.example.PaginaWebDivisas.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {
    private String username;
    private String contraseña;
    private String masterKey;

}
