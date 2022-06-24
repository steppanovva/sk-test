import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sk.controller.Controller;
import org.sk.entity.Entity;
import org.sk.service.SkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = Controller.class)
public class ControllerTest {
    @MockBean
    private SkService skService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void modifyExistingEntity_HttpStatus_isOk() throws Exception {
        var entity = new Entity();
        entity.setId(1);
        Map<String, Integer> obj = new HashMap<>();
        obj.put("current", 0);
        entity.setObj(obj);
        Mockito.when(skService.findById(anyInt())).thenReturn(entity);
        Mockito.when(skService.update(anyInt(), Mockito.any())).thenReturn(entity);
        mvc.perform(post("/modify").content("{\n\"id\": 1,\n\"add\": 3\n}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current").value(3));
    }

    @Test
    public void modifyNonExistingEntity_HttpStatus_isIAmATeapot() throws Exception {
        Mockito.when(skService.findById(anyInt())).thenReturn(null);
        mvc.perform(post("/modify").content("{\n\"id\": 1,\n\"add\": 3\n}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot());
    }

}
