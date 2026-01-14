package com.designpattern.webmotosystem.Controller;

import com.designpattern.webmotosystem.DTO.CommandeResponse;
import com.designpattern.webmotosystem.Entities.Commande.Commande;
import com.designpattern.webmotosystem.Entities.Commande.EnumCommande;
import com.designpattern.webmotosystem.Entities.Commande.EtatCommande;
import com.designpattern.webmotosystem.Mappers.CommandeMapper;
import com.designpattern.webmotosystem.Services.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commandes")
@AllArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    /**
     * Creer une nouvelle commande
     */
    @PostMapping
    public ResponseEntity<CommandeResponse> creerCommande(
            @RequestParam EnumCommande type,
            @RequestParam int clientId,
            @RequestParam Long vehiculeId,
            @RequestParam String paysLivraison) {

        Commande created = commandeService.creerCommande(type, clientId, vehiculeId, paysLivraison);
        return ResponseEntity.ok(CommandeMapper.toDto(created));
    }

    /**
     * Recuperer une commande par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommandeResponse> getCommande(@PathVariable Long id) {
        return commandeService.getCommandeById(id)
                .map(c -> ResponseEntity.ok(CommandeMapper.toDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Changer l'etat d'une commande
     */
    @PutMapping("/{id}/etat")
    public ResponseEntity<Void> changerEtat(@PathVariable Long id,
                                            @RequestParam EtatCommande etat) {
        commandeService.changerEtat(id, etat);
        return ResponseEntity.noContent().build();
    }
}
