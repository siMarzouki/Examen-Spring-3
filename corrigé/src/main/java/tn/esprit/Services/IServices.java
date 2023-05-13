package tn.esprit.Services;
import tn.esprit.DAO.Entities.*;

import java.util.List;

public interface IServices {
    void ajoutCentre(CentreCommercial centre);
    void ajouterEtAffecterlisteBoutiques (List<Boutique> lb, Long idCentre);
    void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques);
    List<Client> listeClients(Long idBoutique);
    List<Boutique> listeBoutiques(Long idCentre);
    List<Client> listeDeClientsParCategorie(Categorie categorie);
    void nbreClientParGenre();

}
