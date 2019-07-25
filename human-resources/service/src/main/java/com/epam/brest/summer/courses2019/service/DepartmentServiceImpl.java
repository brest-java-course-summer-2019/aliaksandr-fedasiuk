package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.DepartmentDao;
import com.epam.brest.summer.courses2019.model.Department;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao dao;

    public DepartmentServiceImpl(DepartmentDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Department> findAll() {
        LOGGER.debug("Find all departments");
        return dao.findAll();
    }

    @Override
    public Department findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return dao.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get department from DB"));
    }

    @Override
    public void update(Department department) {
        LOGGER.debug("update({})", department);
        dao.update(department);
    }

    @Override
    public void delete(int id) {
        LOGGER.debug("delete({})", id);
        dao.delete(id);
    }
}