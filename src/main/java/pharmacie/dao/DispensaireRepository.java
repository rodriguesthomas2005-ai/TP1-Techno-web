package pharmacie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacie.entity.Dispensaire;

public interface DispensaireRepository extends JpaRepository<Dispensaire, Integer> {
    /**
     * Trouve tous les dispensaires dont le code postal commence par la valeur donnée (ex: "75" pour Paris)
     */
    List<Dispensaire> findByAdressePostaleCodePostalStartingWith(String prefix);
}
