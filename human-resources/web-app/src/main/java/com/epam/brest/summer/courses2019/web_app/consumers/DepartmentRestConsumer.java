package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Department;
import com.epam.brest.summer.courses2019.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DepartmentRestConsumer implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public DepartmentRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public List<Department> findAllWithAvgSalary() {
        LOGGER.debug("findAllWithAvgSalary()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/with_avg_salary", List.class);
        return  (List<Department>) responseEntity.getBody();
    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void add(Department... departments) {

    }
}