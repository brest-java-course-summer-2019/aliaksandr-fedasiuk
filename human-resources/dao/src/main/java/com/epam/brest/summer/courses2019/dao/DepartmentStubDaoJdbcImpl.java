package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.DepartmentStub;
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

    private static final String SELECT_ALL_WITH_AVG_SALARY =
            "select d.department_id as departmentId,"
                    + " d.department_name as departmentName,"
                    + " avg(e.salary) as avgSalary"
                    + " from department d"
                    + " left join employee e on d.department_id = e.department_id"
                    + " group by d.department_id, d.department_name"
                    + " order by department_name";


    public DepartmentStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<DepartmentStub> findAllWithAvgSalary() {
        List<DepartmentStub> departments = namedParameterJdbcTemplate.query(SELECT_ALL_WITH_AVG_SALARY,
                BeanPropertyRowMapper.newInstance(DepartmentStub.class));
        return departments;
    }

}
