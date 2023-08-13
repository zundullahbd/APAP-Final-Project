package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.RiwayatTambahStokModel;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.RiwayatTambahStokDb;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RiwayatTambahStokServiceImplTest {

    @MockBean
    private RiwayatTambahStokDb riwayatTambahStokDb;

    @Autowired
    private UserService userService;

    @MockBean
    private SecurityContext securityContext;

    @MockBean
    private Authentication authentication;

    @Autowired
    private RiwayatTambahStokService riwayatTambahStokService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testAddRiwayat() {
        // Arrange
        RiwayatTambahStokModel riwayatTambahStok = new RiwayatTambahStokModel();

        // Mock the user service
        UserModel userMock = new UserModel();

        // Capture the argument passed to riwayatTambahStokDb.save
        ArgumentCaptor<RiwayatTambahStokModel> riwayatArgumentCaptor = ArgumentCaptor.forClass(RiwayatTambahStokModel.class);

        // Act
        riwayatTambahStokService.addRiwayat(riwayatTambahStok);

        // Assert
        verify(riwayatTambahStokDb).save(riwayatArgumentCaptor.capture());

        // Verify that the properties of the saved riwayatTambahStok match the expected values
        RiwayatTambahStokModel savedRiwayat = riwayatArgumentCaptor.getValue();
        assertNotNull(savedRiwayat.getCreated_at());

    }
}
