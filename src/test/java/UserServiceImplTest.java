import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        // Setting up mocks
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    @Test
    public void testGetUsers() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("John");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("Jane");

        List<User> userList = Arrays.asList(user1, user2);
        when(entityManager.createQuery("SELECT u FROM User u", User.class)).thenReturn((TypedQuery<User>) mock(Query.class));
        when(entityManager.createQuery("SELECT u FROM User u", User.class).getResultList()).thenReturn(userList);

        // When
        List<User> result = userService.getUsers();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(entityManager).createQuery("SELECT u FROM User u", User.class);
    }

    @Test
    public void testGetUserById() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("John");

        when(entityManager.find(User.class, 1L)).thenReturn(user);

        // When
        User result = userService.getUser(1L);

        // Then
        assertNotNull(result);
        assertEquals("John", result.getUsername());
        verify(entityManager).find(User.class, 1L);
    }

    @Test
    public void testCreateUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("John");

        doNothing().when(entityManager).getTransaction().begin();
        doNothing().when(entityManager).getTransaction().commit();

        // When
        userService.createUser(user);

        // Then
        verify(entityManager).persist(user);
        verify(entityManager).getTransaction().begin();
        verify(entityManager).getTransaction().commit();
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("John");

        // Mock the necessary methods
        doNothing().when(entityManager).getTransaction().begin();
        doNothing().when(entityManager).getTransaction().commit();

        // When
        userService.updateUser(user);

        // Then
        verify(entityManager).merge(user);
        verify(entityManager).getTransaction().begin();
        verify(entityManager).getTransaction().commit();
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("John");

        when(entityManager.find(User.class, 1L)).thenReturn(user);
        doNothing().when(entityManager).getTransaction();
        doNothing().when(entityManager).getTransaction().begin();
        doNothing().when(entityManager).getTransaction().commit();

        // When
        boolean result = userService.deleteUser(1L);

        // Then
        assertTrue(result);
        verify(entityManager).remove(user);
        verify(entityManager).getTransaction().begin();
        verify(entityManager).getTransaction().commit();
    }

    @Test
    public void testDeleteUserNotFound() {
        // Given
        when(entityManager.find(User.class, 1L)).thenReturn(null);

        // When
        boolean result = userService.deleteUser(1L);

        // Then
        assertFalse(result);
        verify(entityManager, never()).remove(any());
    }
}

