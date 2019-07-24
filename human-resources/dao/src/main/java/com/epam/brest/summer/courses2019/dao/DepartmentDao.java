package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Department;

import java.util.List;
import java.util.Optional;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Persist new department.
     *
     * @param department new department
     * @return new department object.
     */
    Department add(Department department);

    /**
     * Update department.
     *
     * @param department department
     */
    void update(Department department);

    /**
     * Delete department with specified id.
     *
     * @param departmentId department id
     */
    void delete(Integer departmentId);

    /**
     * Get departments.
     *
     * @return departments list.
     */
    List<Department> findAll();

    /**
     * Get Department By Id.
     *
     * @param departmentId departmentId
     * @return Department
     */
    Optional<Department> findById(Integer departmentId);

}
