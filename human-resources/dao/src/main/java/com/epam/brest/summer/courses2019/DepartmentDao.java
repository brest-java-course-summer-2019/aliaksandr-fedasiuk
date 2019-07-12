package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.model.Department;

import java.util.List;

public interface DepartmentDao {

    Department add(Department department);

    void update(Department department);

    void delete(Integer departmentId);

    List<Department> findAll();

}
