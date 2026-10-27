package com.autorent.domain.model;

import com.autorent.domain.exception.DatosInvalidosException;

public class Cliente {

    private Long id;
    private String nombre;
    private String documento;
    private String telefono;
    private String email;

    // Constructor para registrar un cliente nuevo.
    public Cliente(String nombre, String documento,
                   String telefono, String email) {
        this(null, nombre, documento, telefono, email);
    }

    // Constructor para reconstruir un cliente guardado.
    public Cliente(Long id, String nombre, String documento,
                   String telefono, String email) {

        if (nombre == null || nombre.isBlank()) {
            throw new DatosInvalidosException(
                    "El nombre es obligatorio"
            );
        }

        if (documento == null || documento.isBlank()) {
            throw new DatosInvalidosException(
                    "El documento es obligatorio"
            );
        }

        if (email == null || email.isBlank()) {
            throw new DatosInvalidosException(
                    "El correo electrónico es obligatorio"
            );
        }

        String emailLimpio = email.trim();

        if (!emailLimpio.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new DatosInvalidosException(
                    "El correo electrónico debe tener un formato válido"
            );
        }

        this.id = id;
        this.nombre = nombre.trim();
        this.documento = documento.trim();
        this.telefono = telefono == null ? null : telefono.trim();
        this.email = emailLimpio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
}