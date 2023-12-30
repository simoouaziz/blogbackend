package ma.emsi.blog.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class VisiteurTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testVisiteurValidation() {
		// Given
		Visiteur visiteur = new Visiteur();
		visiteur.setNom("Test");
		visiteur.setEmail("");
		visiteur.setNombreCommentaire(-1);

		// When
		Set<ConstraintViolation<Visiteur>> violations = validator.validate(visiteur);

		// Then
		assertFalse(violations.isEmpty());
		assertEquals(2, violations.size());

		for (ConstraintViolation<Visiteur> violation : violations) {
			System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
		}
	}
}
