package io.github.saviomisael.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.saviomisael.dto.CreatePersonDto;
import io.github.saviomisael.models.Person;
import io.github.saviomisael.repositories.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository repository;

    @Test
    public void create_ShouldReturn201() throws Exception {
        var dto = new CreatePersonDto();
        dto.setAddress("Campinas");
        dto.setGender("Male");
        dto.setFirstName("Savio");
        dto.setLastName("Moreira");

        var mapper = new ObjectMapper();

        var json = mapper.writeValueAsString(dto);

        var result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)).andExpect(MockMvcResultMatchers.status().isCreated())
                        .andReturn();

        var body = mapper.readValue(result.getResponse().getContentAsString(), Person.class);

        Assert.assertEquals("Campinas", body.getAddress());
        Assert.assertEquals("Male", body.getGender());
        Assert.assertEquals("Savio", body.getFirstName());
        Assert.assertEquals("Moreira", body.getLastName());

        repository.deleteAllInBatch();
    }
}
