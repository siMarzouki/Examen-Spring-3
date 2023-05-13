package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Boutique;

public interface BoutiqueRepo extends JpaRepository<Boutique,Long> {
}
