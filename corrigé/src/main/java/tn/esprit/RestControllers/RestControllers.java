package tn.esprit.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.Entities.*;
import tn.esprit.Services.IServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class RestControllers {
    @Autowired
    IServices services;

    @PostMapping("/ajoutCentre")
    public void ajoutCentre(@RequestBody CentreCommercial centre) {
        services.ajoutCentre(centre);
    }

    @PostMapping("/ajouterEtAffecterlisteBoutiques")
    public void ajouterEtAffecterlisteBoutiques(@RequestBody  List<Boutique> lb, @RequestParam Long idCentre) {
        services.ajouterEtAffecterlisteBoutiques(lb,idCentre);
    }

    @PostMapping("/ajouterEtAffecterClientBoutiques")
    public void ajouterEtAffecterClientBoutiques(@RequestBody Client client,@RequestParam List<Long> idBoutiques) {
        services.ajouterEtAffecterClientBoutiques(client,idBoutiques);
    }

    @GetMapping("/listeClients")
    public List<Client> listeClients(@RequestParam Long idBoutique) {
        return services.listeClients(idBoutique);
    }

    @GetMapping("/listeBoutiques")
    public List<Boutique> listeBoutiques(@RequestParam Long idCentre) {
       return services.listeBoutiques(idCentre);
    }

    @GetMapping("/listeDeClientsParCategorie")
    public List<Client> listeDeClientsParCategorie(@RequestParam Categorie categorie) {
        return services.listeDeClientsParCategorie(categorie);
    }


}
