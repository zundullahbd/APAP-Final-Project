package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ResepModel;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.ResepDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ResepServiceImplTest {
    @MockBean
    private ResepDb resepDb;

    @Autowired
    private UserService userService;

    @MockBean
    private SecurityContext securityContext;

    @MockBean
    private Authentication authentication;

    @Autowired
    private ResepService resepService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testAddResep() {
        // Arrange
        ResepModel resep = new ResepModel();
        resep.setNomor(null); // Ensure it's not set before the method call

        // Mock the user service and resepDb
        UserModel userMock = new UserModel();
//        when(userService.getUserByName(userMock.getName())).thenReturn(userMock);
        when(resepDb.count()).thenReturn(1L);

        // Capture the argument passed to resepDb.save
        ArgumentCaptor<ResepModel> resepArgumentCaptor = ArgumentCaptor.forClass(ResepModel.class);

        // Act
        resepService.addResep(resep);

        // Assert
        verify(resepDb, times(1)).count();
        verify(resepDb, times(1)).save(resepArgumentCaptor.capture());

        // Verify that the properties of the saved resep match the expected values
        ResepModel savedResep = resepArgumentCaptor.getValue();
        assertNotNull(savedResep.getNomor());
        assertEquals("R-2", savedResep.getNomor());
        assertNotNull(savedResep.getCreated_at());

    }

}
