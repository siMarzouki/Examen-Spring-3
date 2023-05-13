package tn.esprit.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.DAO.Entities.*;
import tn.esprit.DAO.Repositories.BoutiqueRepo;
import tn.esprit.DAO.Repositories.CentreCommercialRepo;
import tn.esprit.DAO.Repositories.ClientRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class Services implements IServices {
    CentreCommercialRepo centreCommercialRepo;
    BoutiqueRepo boutiqueRepo;
    ClientRepo clientRepo;
    @Override
    public void ajoutCentre(CentreCommercial centre) {
        centreCommercialRepo.save(centre);
        centre.getBoutiques().forEach(boutique ->{
            boutique.setCentreCommercial(centre);
            boutiqueRepo.save(boutique);
        });

    }

    @Override
    public void ajouterEtAffecterlisteBoutiques(List<Boutique> lb, Long idCentre) {
        CentreCommercial cc = centreCommercialRepo.findById(idCentre).get();
        lb.forEach(boutique -> {
            boutique.setCentreCommercial(cc);
            boutiqueRepo.save(boutique);
            cc.getBoutiques().add(boutique);
        });
        centreCommercialRepo.save(cc);

    }

    @Override
    public void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques) {
        List<Boutique> boutiques=new ArrayList<Boutique>();
        idBoutiques.forEach(id -> {
            Boutique b = boutiqueRepo.findById(id).get();
            boutiques.add(b);
            List<Client> clients = b.getClients();
            clients.add(client);
            b.setClients(clients);
            boutiqueRepo.save(b);
        });
        client.setBoutiques(boutiques);
        clientRepo.save(client);

    }

    @Override
    public List<Client> listeClients(Long idBoutique) {
        Boutique b = boutiqueRepo.findById(idBoutique).get();
        return clientRepo.findAll()
                .stream()
                .filter(client -> client.getBoutiques().contains(b))
                .collect(Collectors.toList());
    }

    @Override
    public List<Boutique> listeBoutiques(Long idCentre) {
        CentreCommercial cc = centreCommercialRepo.findById(idCentre).get();
        return cc.getBoutiques();
    }

    @Override
    public List<Client> listeDeClientsParCategorie(Categorie categorie) {
        Set<Client> res = new HashSet<Client>();
        boutiqueRepo.findAll()
                .stream()
                .filter(boutique -> boutique.getCategorie().equals(categorie))
                .forEach(boutique -> res.addAll(boutique.getClients()));
        return res.stream().collect(Collectors.toList());
    }

    @Override
    @Scheduled(fixedRate = 30000)
    public void nbreClientParGenre() {
        long f = clientRepo.findAll()
                .stream()
                .filter(client -> client.getGenre().equals(Genre.FEMININ))
                .count();
        long m = clientRepo.findAll()
                .stream()
                .filter(client -> client.getGenre().equals(Genre.MASCULIN))
                .count();
        System.out.println("MASCULIN : "+m);
        System.out.println("FEMININ : "+f);
    }
}
