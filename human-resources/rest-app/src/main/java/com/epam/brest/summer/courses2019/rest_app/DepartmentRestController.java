package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Department;
import com.epam.brest.summer.courses2019.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class DepartmentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestController.class);

    @Autowired
    private DepartmentService service;

    @GetMapping(value = "/departments")
    public Collection<Department> findAll() {
        LOGGER.debug("get all departments");
        return service.findAll();
    }

    @GetMapping(value = "/departments/with_avg_salary")
    public List<Department> findAllStubs() {
        LOGGER.debug("get all departments stubs");
        return service.findAllWithAvgSalary();
    }

    @GetMapping(value = "/departments/{id}")
    public Department findById(@PathVariable Integer id) {
        LOGGER.debug("find department by id({})", id);
        return service.findById(id);
    }
}
