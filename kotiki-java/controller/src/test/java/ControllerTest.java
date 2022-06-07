
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmo.kotiki.entity.CatServiceImpl;
import ru.itmo.kotiki.entity.OwnerServiceImpl;
import ru.itmo.kotiki.interfaces.CatController;
import ru.itmo.kotiki.tools.ServiceException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Qualifier("serviceCatImpl")
    private CatServiceImpl serviceCat;

    @MockBean
    @Qualifier("serviceOwnerImpl")
    private OwnerServiceImpl serviceOwner;

    @Test
    void getOwnerByUnrealId() throws Exception {
        Mockito.when(serviceOwner.getOwnerById(10000)).thenThrow(ServiceException.class);
        String url = "http://localhost:8080/owners/getOwnerById/10000";
        mockMvc.perform(get(url)).andExpect(status().is4xxClientError());
    }

    @Test
    void findCatsByUnrealBreed() throws Exception {
        Mockito.when(serviceCat.getAllCatsByBreed("Sobaka")).thenThrow(ServiceException.class);
        String url = "http://localhost:8080/cats/getCatsByBreed/Sobaka";
        mockMvc.perform(get(url)).andExpect(status().is4xxClientError());
    }

    @Test
    void findCatsByUnrealColor() throws Exception {
        Mockito.when(serviceCat.getAllCatsByColor("Indigo")).thenThrow(ServiceException.class);
        String url = "http://localhost:8080/cats/getCatsByColor/Indigo";
        mockMvc.perform(get(url)).andExpect(status().is4xxClientError());
    }
}
