package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.DepartmentStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Department DAO Interface implementation.
 */
@Component
public class DepartmentStubDaoJdbcImpl implements DepartmentStubDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${departmentStub.findAllWithAvgSalary}")
    private String findAllWithAvgSalarySql;


    public DepartmentStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<DepartmentStub> findAllWithAvgSalary() {
        List<DepartmentStub> departments = namedParameterJdbcTemplate.query(findAllWithAvgSalarySql,
                BeanPropertyRowMapper.newInstance(DepartmentStub.class));
        return departments;
    }

}
