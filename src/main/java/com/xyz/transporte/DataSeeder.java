package com.xyz.transporte;

import com.xyz.transporte.model.Rol;
import com.xyz.transporte.model.Usuario;
import com.xyz.transporte.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = Usuario.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Rol.ADMIN)
                    .build();
            usuarioRepository.save(admin);
        }
        if (usuarioRepository.findByUsername("supervisor").isEmpty()) {
            Usuario sup = Usuario.builder()
                    .username("supervisor")
                    .password(passwordEncoder.encode("super123"))
                    .rol(Rol.SUPERVISOR)
                    .build();
            usuarioRepository.save(sup);
        }
    }
}