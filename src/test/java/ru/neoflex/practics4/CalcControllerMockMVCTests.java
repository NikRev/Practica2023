package ru.neoflex.practics4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.neoflex.practics4.model.Result;
import ru.neoflex.practics4.repository.ResultRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CalcControllerMockMVCTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResultRepository resultRepository;

    @Test
    public void shouldReturnSumOfTwoNumbers() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/plus/1/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2"));
    }

    @Test
    public void shouldReturnErrorMessageIfInvalidNumbersInSum() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/plus/as/sd"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect numbers!"));
    }

    @Test
    public void shouldReturnErrorMessageIfInvalidNumbersInDiff() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/minus/as/sd"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect numbers!"));
    }

    @Test
    public void shouldReturnDiffOfTwoNumbers() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/minus/1/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    @Test
    public void shouldReturnAllResults() throws Exception {
        resultRepository.save(new Result("1", "1", "plus", 2));
        resultRepository.save(new Result("1", "1", "minus", 0));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/getAllResults"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"firstNumber\":\"1\",\"" +
                        "secondNumber\":\"1\",\"result\":2,\"action\":\"plus\"},{\"id\":2,\"firstNumber\":\"1\",\"" +
                        "secondNumber\":\"1\",\"result\":0,\"action\":\"minus\"}]"));
    }
}