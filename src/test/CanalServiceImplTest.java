package test;


import models.Canal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.impl.CanalServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CanalServiceImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CanalServiceImpl canalService;

    @BeforeEach
    public void setUp() {
        // Setting up mocks
    }

    @Test
    public void testAddCanal() {
        // Given
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName("Test Canal");

        // When
        doNothing().when(entityManager).persist(canal);

        Canal result = canalService.addCanal(canal);

        // Then
        assertNotNull(result);
        assertEquals("Test Canal", result.getName());
        verify(entityManager).persist(canal);
    }

    @Test
    public void testAddCanalPersistenceException() {
        // Given
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName("Test Canal");

        // Simulating persistence exception
        doThrow(new PersistenceException()).when(entityManager).persist(canal);

        // When
        Canal result = canalService.addCanal(canal);

        // Then
        assertNull(result);
        verify(entityManager).persist(canal);
    }

    @Test
    public void testUpdateCanal() {
        // Given
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName("Test Canal");

        // When
        canalService.updateCanal(canal);

        // Then
        verify(entityManager).merge(canal);
    }

    @Test
    public void testUpdateCanalPersistenceException() {
        // Given
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName("Test Canal");

        // Simulating persistence exception
        doThrow(new PersistenceException()).when(entityManager).merge(canal);

        // When
        canalService.updateCanal(canal);

        // Then
        verify(entityManager).merge(canal);
    }

    @Test
    public void testDeleteCanal() {
        // Given
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName("Test Canal");

        when(entityManager.find(Canal.class, 1L)).thenReturn(canal);

        // When
        boolean result = canalService.deleteCanal(1L);

        // Then
        assertTrue(result);
        verify(entityManager).remove(canal);
    }

    @Test
    public void testDeleteCanalNotFound() {
        // Given
        when(entityManager.find(Canal.class, 1L)).thenReturn(null);

        // When
        boolean result = canalService.deleteCanal(1L);

        // Then
        assertFalse(result);
        verify(entityManager, never()).remove(any());
    }

    @Test
    public void testGetAllCanals() {
        // Given
        Canal canal1 = new Canal();
        canal1.setId(1L);
        canal1.setName("Canal 1");

        Canal canal2 = new Canal();
        canal2.setId(2L);
        canal2.setName("Canal 2");

        List<Canal> canals = Arrays.asList(canal1, canal2);
        when(entityManager.createQuery("SELECT c FROM Canal c", Canal.class)).thenReturn(mock(javax.persistence.TypedQuery.class));
        when(entityManager.createQuery("SELECT c FROM Canal c", Canal.class).getResultList()).thenReturn(canals);

        // When
        List<Canal> result = canalService.getAllCanals();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(entityManager).createQuery("SELECT c FROM Canal c", Canal.class);
    }

    @Test
    public void testGetCanalById() {
        // Given
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName("Test Canal");

        when(entityManager.find(Canal.class, 1L)).thenReturn(canal);

        // When
        Canal result = canalService.getCanal(1L);

        // Then
        assertNotNull(result);
        assertEquals("Test Canal", result.getName());
        verify(entityManager).find(Canal.class, 1L);
    }

    @Test
    public void testGetCanalByIdNotFound() {
        // Given
        when(entityManager.find(Canal.class, 1L)).thenReturn(null);

        // When
        Canal result = canalService.getCanal(1L);

        // Then
        assertNull(result);
        verify(entityManager).find(Canal.class, 1L);
    }
}

