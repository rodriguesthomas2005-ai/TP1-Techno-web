package pharmacie.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pharmacie.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RepositoryCustomMethodsTest {

    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private MedicamentRepository medicamentRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private DispensaireRepository dispensaireRepository;


    @Test // Ce test se base uniquement sur les données définies dans data.sql
    public void testMedicamentCustomMethods() {    
        Medicament indisponible = medicamentRepository.findByNom("Lévofloxacine 500mg").orElseThrow();
        Medicament disponible   = medicamentRepository.findByNom("Doliprane Effervescent 1g").orElseThrow();
    
        // Trouve tous les médicaments disponibles
        List<Medicament> disponibles = medicamentRepository.findByIndisponibleFalse();

        assertTrue(disponibles.contains(disponible));
        assertFalse(disponibles.contains(indisponible));        
        assertFalse(disponibles.isEmpty());
    }

    @Test // Ce test crée les enregistrements nécessaires
    public void testCategorieCustomMethods() {
        Categorie c1 = new Categorie();
        c1.setLibelle("AnalgesiquesTest");
        categorieRepository.save(c1);

        Categorie c2 = new Categorie();
        c2.setLibelle("AntibiotiquesTest");
        categorieRepository.save(c2);

        // findByLibelle
        Categorie found = categorieRepository.findByLibelle("AnalgesiquesTest");
        assertNotNull(found);
        assertEquals("AnalgesiquesTest", found.getLibelle());

        // findByLibelleContaining
        List<Categorie> list = categorieRepository.findByLibelleContaining("iquesTest");
        assertEquals(2, list.size());
        assertTrue(list.stream().anyMatch(cat -> cat.getLibelle().equals("AntibiotiquesTest")));
        assertTrue(list.stream().anyMatch(cat -> cat.getLibelle().equals("AnalgesiquesTest")));
    }

    @Test
    public void testCommandeFindBySaisieAfter() {
        // create dispensaire for linking
        Dispensaire d = new Dispensaire();
        d.setNom("CentreTest");
        d.setContact("Ressources");
        d.setTelephone("0102030405");
        d.setFonction("Client");
        dispensaireRepository.save(d);

        Commande oldCmd = new Commande();
        oldCmd.setSaisieLe(java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(10)));
        oldCmd.setDestinataire("Ancien");
        oldCmd.setAdressePostale(new AdressePostale("Rue A","75001","Paris"));
        oldCmd.setDispensaire(d);
        commandeRepository.save(oldCmd);

        Commande newCmd = new Commande();
        newCmd.setSaisieLe(java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(1)));
        newCmd.setDestinataire("Récent");
        newCmd.setAdressePostale(new AdressePostale("Rue B","75002","Paris"));
        newCmd.setDispensaire(d);
        commandeRepository.save(newCmd);

        List<Commande> found = commandeRepository.findBySaisieLeAfter(java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(5)));
        assertTrue(found.stream().anyMatch(c -> c.getDestinataire().equals("Récent")));
        assertFalse(found.stream().anyMatch(c -> c.getDestinataire().equals("Ancien")));
    }

    @Test
    public void testFindDispensaireByRegion() {
        Dispensaire d1 = new Dispensaire();
        d1.setNom("Dispensaire75");
        d1.setContact("Contact1");
        d1.setTelephone("0101010101");
        d1.setFonction("Ho");
        d1.setAdressePostale(new AdressePostale("Rue C","75010","Paris"));
        dispensaireRepository.save(d1);

        Dispensaire d2 = new Dispensaire();
        d2.setNom("Dispensaire92");
        d2.setContact("Contact2");
        d2.setTelephone("0202020202");
        d2.setFonction("Ho");
        d2.setAdressePostale(new AdressePostale("Rue D","92000","Nanterre"));
        dispensaireRepository.save(d2);

        List<Dispensaire> result = dispensaireRepository.findByAdressePostaleCodePostalStartingWith("75");
        assertTrue(result.stream().anyMatch(dd -> dd.getNom().equals("Dispensaire75")));
        assertFalse(result.stream().anyMatch(dd -> dd.getNom().equals("Dispensaire92")));
    }


}
