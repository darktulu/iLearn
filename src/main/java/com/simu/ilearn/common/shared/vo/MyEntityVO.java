package com.simu.ilearn.common.shared.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class MyEntityVO {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
