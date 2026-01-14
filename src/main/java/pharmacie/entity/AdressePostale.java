package pharmacie.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AdressePostale {
    @NotBlank
    @Size(max = 255)
    private String rue;

    @NotBlank
    @Pattern(regexp = "\\d{4,10}")
    @Size(min = 4, max = 10)
    private String codePostal;

    @NotBlank
    @Size(max = 100)
    private String ville;
}