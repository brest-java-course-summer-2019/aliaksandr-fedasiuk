package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Department DAO Interface implementation.
 */
@Component
public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${department.findAll}")
    private String findAllSql;

    @Value("${department.findById}")
    private String findByIdSql;

    @Value("${department.insert}")
    private String insertSql;

    @Value("${department.update}")
    private String updateSql;

    @Value("${department.delete}")
    private String deleteSql;

    private static final String DEPARTMENT_ID = "departmentId";

    public DepartmentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Department add(Department department) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departmentName", department.getDepartmentName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        return department;
    }

    @Override
    public void update(Department department) {
        if (namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(department)) < 1) {
            throw new EmptyResultDataAccessException(
                    String.format("Failed to update. '%s' not found in the DB", department), 1);
        }
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer departmentId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(DEPARTMENT_ID, departmentId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from DB"));
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = namedParameterJdbcTemplate.query(findAllSql, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        List<Department> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("department_id"));
            department.setDepartmentName(resultSet.getString("department_name"));
            return department;
        }
    }

}
