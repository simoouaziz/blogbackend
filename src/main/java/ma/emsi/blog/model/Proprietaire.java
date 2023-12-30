package ma.emsi.blog.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@DiscriminatorValue(value = "Proprietaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Generated
public class Proprietaire extends Utilisateur {

	private String photo;

	private String biographie;

	public Proprietaire(int id, @NotBlank(message = "L'e-mail ne peut pas être vide") String email,
			@Size(min = 8, message = "Le mot de passe doit avoir au moins 8 caractères") String password,
			@NotBlank(message = "Le nom d'utilisateur ne peut pas être vide") String username, String photo,
			String biographie) {
		super(id, email, password, username);
		this.photo = photo;
		this.biographie = biographie;
	}
}
