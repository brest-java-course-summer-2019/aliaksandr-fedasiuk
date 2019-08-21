package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Department;
import com.epam.brest.summer.courses2019.model.stub.DepartmentStub;
import com.epam.brest.summer.courses2019.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<DepartmentStub> findAllStubs() {
        LOGGER.debug("get all departments stubs");
        return service.findAllWithAvgSalary();
    }

    @GetMapping(value = "/departments/{id}")
    public Department findById(@PathVariable Integer id) {
        LOGGER.debug("find department by id({})", id);
        return service.findById(id);
    }

    @PutMapping()
    public void update(@RequestBody Department department) {
        LOGGER.debug("update department ({})", department);
        service.update(department);
    }

    @DeleteMapping(value = "/departments/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete department ({})", id);
        service.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Department> add(@RequestBody Department department) {

        LOGGER.debug("add department({})", department);
        Department result = service.add(department);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
