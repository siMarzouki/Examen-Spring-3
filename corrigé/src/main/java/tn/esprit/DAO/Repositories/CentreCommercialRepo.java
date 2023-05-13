package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.CentreCommercial;

public interface CentreCommercialRepo extends JpaRepository<CentreCommercial,Long> {
}
