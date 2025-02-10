import models.Groupe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.impl.GroupeServiceImpl;

import javax.persistence.EntityManager;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GroupeServiceImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private GroupeServiceImpl groupeService;

    @BeforeEach
    public void setUp() {
        // Setting up mock objects, no need to manually mock EntityManager as it is handled by Mockito
    }

    @Test
    public void testAddGroupe() {
        // Given
        Groupe groupe = new Groupe();
        groupe.setId(1L);
        groupe.setName("Admin Group");

        // When
        doNothing().when(entityManager).persist(groupe);  // Mock void method
        Groupe result = groupeService.addGroupe(groupe);

        // Then
        assertNotNull(result);
        assertEquals("Admin Group", result.getName());
        verify(entityManager).persist(groupe);  // Verify that persist() was called
    }

    @Test
    public void testDeleteGroupe() {
        // Given
        Long groupeId = 1L;
        Groupe groupe = new Groupe();
        groupe.setId(groupeId);
        groupe.setName("Admin Group");

        when(entityManager.find(Groupe.class, groupeId)).thenReturn(groupe);  // Mocking entityManager.find()

        // When
        doNothing().when(entityManager).remove(groupe);  // Mock void method
        boolean result = groupeService.deleteGroupe(groupeId);

        // Then
        assertTrue(result);
        verify(entityManager).remove(groupe);  // Verify that remove() was called
    }

    @Test
    public void testUpdateGroupe() {
        // Given
        Groupe groupe = new Groupe();
        groupe.setId(1L);
        groupe.setName("Admin Group");

        // When
        doNothing().when(entityManager).merge(groupe);  // Mock void method
        groupeService.updateGroupe(groupe);

        // Then
        verify(entityManager).merge(groupe);  // Verify that merge() was called
    }

    @Test
    public void testGetAllGroupes() {
        // Given
        Groupe groupe1 = new Groupe();
        groupe1.setId(1L);
        groupe1.setName("Admin Group");

        Groupe groupe2 = new Groupe();
        groupe2.setId(2L);
        groupe2.setName("User Group");

        when(entityManager.createQuery("SELECT g FROM Groupe g", Groupe.class))
                .thenReturn(mock(javax.persistence.TypedQuery.class));
        when(entityManager.createQuery("SELECT g FROM Groupe g", Groupe.class).getResultList())
                .thenReturn(List.of(groupe1, groupe2));

        // When
        List<Groupe> result = groupeService.getAllGroupes();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(entityManager).createQuery("SELECT g FROM Groupe g", Groupe.class);
    }

    @Test
    public void testGetGroupeById() {
        // Given
        Long groupeId = 1L;
        Groupe groupe = new Groupe();
        groupe.setId(groupeId);
        groupe.setName("Admin Group");

        when(entityManager.find(Groupe.class, groupeId)).thenReturn(groupe);

        // When
        Groupe result = groupeService.getGroupe(groupeId);

        // Then
        assertNotNull(result);
        assertEquals("Admin Group", result.getName());
        verify(entityManager).find(Groupe.class, groupeId);
    }
}
