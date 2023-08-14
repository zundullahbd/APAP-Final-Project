package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.repository.ObatAlkesDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ObatAlkesServiceImplTest {

    @MockBean
    private ObatAlkesDb obatAlkesDb;

    @Autowired
    private ObatAlkesService obatAlkesService;

    @Test
    public void testGetListObatAlkes() {
        List<ObatAlkesModel> mockObatAlkesList = new ArrayList<>();
        // Add mock ObatAlkesModel objects to the list
        mockObatAlkesList.add(new ObatAlkesModel());

        when(obatAlkesDb.findAll()).thenReturn(mockObatAlkesList);

        List<ObatAlkesModel> result = obatAlkesService.getListObatAlkes();

        assertEquals(mockObatAlkesList.size(), result.size());
        assertEquals(mockObatAlkesList.get(0), result.get(0));
    }

    @Test
    public void testGetObatAlkesById() {
        int obatAlkesId = 1;
        ObatAlkesModel mockObatAlkes = new ObatAlkesModel();
        when(obatAlkesDb.findById(obatAlkesId)).thenReturn(Optional.of(mockObatAlkes));

        ObatAlkesModel result = obatAlkesService.getObatAlkesById(obatAlkesId);

        assertEquals(mockObatAlkes, result);
    }
    @Test
    public void testAddObatAlkes() {
        ObatAlkesModel mockObatAlkes = new ObatAlkesModel();
        when(obatAlkesDb.save(mockObatAlkes)).thenReturn(mockObatAlkes);

        ObatAlkesModel result = obatAlkesService.addObatAlkes(mockObatAlkes);

        assertEquals(mockObatAlkes, result);
    }

    @Test
    public void testDeleteObatAlkes() {
        int obatAlkesId = 1;
        obatAlkesService.deleteObatAlkes(obatAlkesId);

        verify(obatAlkesDb, times(1)).deleteById(obatAlkesId);
    }


    @Test
    public void testReduceStock() {
        ObatAlkesModel obatAlkes = new ObatAlkesModel();
        obatAlkes.setStok(10);

        obatAlkesService.reduceStock(obatAlkes, 3);

        assertEquals(7, obatAlkes.getStok());
        verify(obatAlkesDb, times(1)).save(obatAlkes);
    }

    @Test
    public void testIncreaseStock() {
        ObatAlkesModel obatAlkes = new ObatAlkesModel();
        obatAlkes.setStok(5);

        obatAlkesService.increaseStock(obatAlkes, 2);

        assertEquals(7, obatAlkes.getStok());
        verify(obatAlkesDb, times(1)).save(obatAlkes);
    }
}
