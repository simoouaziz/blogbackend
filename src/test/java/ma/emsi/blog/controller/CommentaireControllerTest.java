package ma.emsi.blog.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ma.emsi.blog.model.Commentaire;
import ma.emsi.blog.service.CommentaireService;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentaireControllerTest {
	@InjectMocks
	private CommentaireController commentaireController;

	@Mock
	private CommentaireService commentaireService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {
		Commentaire commentaire1 = new Commentaire(1, "Comment 1", LocalDate.now(), null, null, null, null);
		Commentaire commentaire2 = new Commentaire(2, "Comment 2", LocalDate.now(), null, null, null, null);
		List<Commentaire> comments = new ArrayList<>();
		comments.add(commentaire1);
		comments.add(commentaire2);

		when(commentaireService.findAll()).thenReturn(comments);

		ResponseEntity<List<Commentaire>> response = commentaireController.findAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());

		List<Commentaire> returnedComments = response.getBody();
		assertEquals(2, returnedComments.size());
		assertEquals("Comment 1", returnedComments.get(0).getTexte());
		assertEquals("Comment 2", returnedComments.get(1).getTexte());
	}

	@Test
	public void testFindCommentaireById() {
		Commentaire commentaire = new Commentaire(1, "Test Comment", LocalDate.now(), null, null, null, null);
		when(commentaireService.findById(1)).thenReturn(commentaire);

		ResponseEntity<Commentaire> response = commentaireController.findcommentaire(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		Commentaire returnedCommentaire = response.getBody();
		assertNotNull(returnedCommentaire);
		assertEquals("Test Comment", returnedCommentaire.getTexte());
	}

	@Test
    public void testFindCommentaireByIdNotFound() {
        when(commentaireService.findById(999)).thenReturn(null);

        ResponseEntity<Commentaire> response = commentaireController.findcommentaire(999);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
	public void testUpdateCommentaire() {
		Commentaire updatedCommentaire = new Commentaire(1, "Updated Comment", LocalDate.now(), null, null, null, null);
		when(commentaireService.findById(1)).thenReturn(updatedCommentaire);

		ResponseEntity<Void> response = commentaireController.update(updatedCommentaire);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testUpdateCommentaireNotFound() {
		Commentaire updatedCommentaire = new Commentaire(999, "Updated Comment", LocalDate.now(), null, null, null,
				null);
		when(commentaireService.findById(999)).thenReturn(null);

		ResponseEntity<Void> response = commentaireController.update(updatedCommentaire);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	    public void testDeleteCommentaire() {
	        when(commentaireService.findById(1)).thenReturn(new Commentaire(1, "Test Comment", LocalDate.now(), null, null, null, null));

	        ResponseEntity<Void> response = commentaireController.delete(1);

	        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    }

	@Test
	    public void testDeleteCommentaireNotFound() {
	        when(commentaireService.findById(999)).thenReturn(null);

	        ResponseEntity<Void> response = commentaireController.delete(999);

	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    }
}