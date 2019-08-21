package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.DepartmentDao;
import com.epam.brest.summer.courses2019.dao.DepartmentStubDao;
import com.epam.brest.summer.courses2019.model.Department;
import java.util.List;

import com.epam.brest.summer.courses2019.model.stub.DepartmentStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Department Service Interface implementation.
 */
@Component
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao departmentDao;

    private DepartmentStubDao departmentStubDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao, DepartmentStubDao departmentStubDao) {
        this.departmentDao = departmentDao;
        this.departmentStubDao = departmentStubDao;
    }

    @Override
    public List<Department> findAll() {
        LOGGER.debug("Find all departments");
        return departmentDao.findAll();
    }

    @Override
    public List<DepartmentStub> findAllWithAvgSalary() {
        LOGGER.debug("Find all departments with filled avg salary");
        return departmentStubDao.findAllWithAvgSalary();
    }

    @Override
    public Department findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return departmentDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get department from DB"));
    }

    @Override
    public void update(Department department) {
        LOGGER.debug("update({})", department);
        departmentDao.update(department);
    }

    @Override
    public void delete(int id) {
        LOGGER.debug("delete({})", id);
        departmentDao.delete(id);
    }

    @Override
    public void add(Department... departments) {
        for (Department department : departments) {
            departmentDao.add(department);
        }
    }

    @Override
    public Department add(Department department) {
        return departmentDao.add(department);
    }
}
