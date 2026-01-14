package pharmacie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class Ligne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Positive
    private int quantite = 1;

    @ManyToOne(optional = false)
    @NotNull
    @NonNull
    @ToString.Exclude
    private Medicament medicament;

    @ManyToOne(optional = false)
    @NotNull
    @NonNull
    @ToString.Exclude
    private Commande commande;
}