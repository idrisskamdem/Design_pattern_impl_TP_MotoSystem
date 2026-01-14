package com.designpattern.webmotosystem.Security;

import com.designpattern.webmotosystem.Entities.Utilisateur;
import com.designpattern.webmotosystem.Repositories.UtilisateurRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRespository utilisateurRespository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // On cherche l'utilisateur par email
        Utilisateur utilisateur = utilisateurRespository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        // Verification si le compte est active
        if (!utilisateur.isActif()) {
            throw new RuntimeException("Compte non activé");
        }

        // Construction de l'objet UserDetails que Spring Security comprend
        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getPassword()) // déjà encodé avec BCrypt
                .roles(utilisateur.getRole().name()) // ex: USER, ADMIN
                .build();
    }
}

