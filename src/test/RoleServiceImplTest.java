package test;

import models.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.impl.RoleServiceImpl;

import javax.persistence.EntityManager;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    public void setUp() {
        // No need to set up the EntityManager manually since it's already mocked.
    }

    @Test
    public void testAddRole() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");

        // When
        doNothing().when(entityManager).persist(role);  // Mock void method
        Role result = roleService.addRole(role);

        // Then
        assertNotNull(result);
        assertEquals("Admin", result.getName());
        verify(entityManager).persist(role);  // Verifying that persist() was called
    }

    @Test
    public void testDeleteRole() {
        // Given
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);
        role.setName("Admin");

        when(entityManager.find(Role.class, roleId)).thenReturn(role);  // Mocking entityManager.find()

        // When
        doNothing().when(entityManager).remove(role);  // Mock void method
        roleService.deleteRole(roleId);

        // Then
        verify(entityManager).remove(role);  // Verifying that remove() was called
    }

    @Test
    public void testUpdateRole() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");

        // When
        doNothing().when(entityManager).merge(role);  // Mock void method
        roleService.updateRole(role);

        // Then
        verify(entityManager).merge(role);  // Verifying that merge() was called
    }

    @Test
    public void testGetRoles() {
        // Given
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("Admin");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("User");

        List<Role> roleList = List.of(role1, role2);
        when(entityManager.createQuery("SELECT r FROM Role r", Role.class)).thenReturn(mock(javax.persistence.TypedQuery.class));
        when(entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList()).thenReturn(roleList);

        // When
        List<Role> result = roleService.getRoles();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(entityManager).createQuery("SELECT r FROM Role r", Role.class);
    }

    @Test
    public void testGetRoleById() {
        // Given
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);
        role.setName("Admin");

        when(entityManager.find(Role.class, roleId)).thenReturn(role);

        // When
        Role result = roleService.getRole(roleId);

        // Then
        assertNotNull(result);
        assertEquals("Admin", result.getName());
        verify(entityManager).find(Role.class, roleId);
    }
}
