package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DepartmentValidatorTest {

    Department department;

    DepartmentValidator departmentValidator = new DepartmentValidator();
    BindingResult result;

    @BeforeEach
    void setup() {
        department = Mockito.mock(Department.class);
        result = new BeanPropertyBindingResult(department, "department");
    }

    @Test
    void shouldRejectNullDepartmentName() {
        // given
        Mockito.when(department.getDepartmentName()).thenReturn(null);

        // when
        departmentValidator.validate(department, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyDepartmentName() {
        // given
        Mockito.when(department.getDepartmentName()).thenReturn("");

        // when
        departmentValidator.validate(department, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeDepartmentName() {

        // given
        String filled = StringUtils.repeat("*", 300);
        Mockito.when(department.getDepartmentName()).thenReturn(filled);

        // when
        departmentValidator.validate(department, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateDepartmentName() {

        // given
        String filled = StringUtils.repeat("*", 250);
        Mockito.when(department.getDepartmentName()).thenReturn(filled);

        // when
        departmentValidator.validate(department, result);

        // then
        assertFalse(result.hasErrors());
    }
}