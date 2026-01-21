package pharmacie.entity;


import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString

public class Dispensaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est autogénérée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Size(max = 255)
    @Column(length = 255, unique = true)
    @NotBlank // pour éviter les libellés vides
    private String code;

    @NonNull
    @Size(max = 255)
    @Column(length = 255)
    @NotBlank // pour éviter les libellés vides
    private String nom;

    @NonNull
    @NotBlank
    @Size(max = 255)
    @Column(length = 255)
    private String adresse;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String ville;

    @NonNull
    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String code_postal;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String pays;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String region;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String fax;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String telephone;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String contact;

    @NonNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String fonction;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dispensaire")
    private List<Commande> commandes = new LinkedList<>();
}