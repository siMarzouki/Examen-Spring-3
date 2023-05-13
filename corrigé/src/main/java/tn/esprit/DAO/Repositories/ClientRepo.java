package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Client;

public interface ClientRepo extends JpaRepository<Client,Long> {
}
