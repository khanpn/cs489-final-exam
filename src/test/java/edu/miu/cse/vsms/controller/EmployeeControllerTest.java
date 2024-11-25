package edu.miu.cse.vsms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cse.vsms.config.JacksonConfig;
import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest(controllers = EmployeeController.class)
@Import(JacksonConfig.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    void addEmployee() throws Exception {
        EmployeeRequestDto requestDto = new EmployeeRequestDto("John", "john.dove@gmail.com", "+1 (641) 452-3333", LocalDate.now());
        EmployeeResponseDto responseDto = new EmployeeResponseDto(1L, requestDto.name(), requestDto.email(), requestDto.phone(), requestDto.hireDate(), null);
        String requestJson = objectMapper.writeValueAsString(requestDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        Mockito.when(employeeService.addEmployee(requestDto)).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}