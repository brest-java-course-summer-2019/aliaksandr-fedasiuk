package com.epam.brest.summer.courses2019.model;

/**
 * POJO Department for model.
 */
public class Department {

    /**
     * Department Id.
     */
    private Integer departmentId;

    /**
     * Department Name.
     */
    private String departmentName;

    /**
     * Constructor without arguments.
     */
    public Department() {
    }

    /**
     * Constructor with department name.
     *
     * @param departmentName department name
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Returns <code>Integer</code> representation of this departmentId.
     *
     * @return departmentId Department Id.
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the department's identifier.
     *
     * @param departmentId Department Id.
     */
    public void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Returns <code>String</code> representation of this departmentName.
     *
     * @return departmentName Department Name.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the department's name.
     *
     * @param departmentName Department Name.
     */
    public Department setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Department{"
                + "departentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + '}';
    }
}
