package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.DepartmentStub;

import java.util.List;

/**
 * DepartmentStub DAO Interface.
 */
public interface DepartmentStubDao {

    /**
     * Get all departments with avg salary by department.
     *
     * @return departments list.
     */
    List<DepartmentStub> findAllWithAvgSalary();

}
