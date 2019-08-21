package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Department;
import com.epam.brest.summer.courses2019.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentRestControllerTest {

    @Autowired
    private DepartmentRestController controller;

    @Autowired
    private DepartmentService service;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void after() {
        Mockito.reset(service);
    }

    @Test
    public void departments() throws Exception {

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/departments")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is("def0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is("def1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(1)))
        ;

        Mockito.verify(service).findAll();
    }

    private Department create(int index) {
        Department department = new Department();
        department.setDepartmentName("def" + index);
        department.setDepartmentId(index);
        return department;
    }

    @Test
    public void shouldPersistDepartment() throws Exception {

        Department expectedDepartment = create(3);

        Department inputDepartment = new Department()
                .setDepartmentName(expectedDepartment.getDepartmentName());

        String json = new ObjectMapper().writeValueAsString(inputDepartment);

        Mockito.when(service.add(any(Department.class))).thenReturn(expectedDepartment);

        MockHttpServletResponse response = mockMvc.perform(
                post("/department")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        String content = response.getContentAsString();
        Department result = objectMapper.readValue(content, Department.class);
        assertEquals(expectedDepartment.getDepartmentName(), result.getDepartmentName());
        assertEquals(expectedDepartment.getDepartmentId(), result.getDepartmentId());

    }
}
