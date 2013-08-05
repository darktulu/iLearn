package com.simu.ilearn.server.business;

import java.util.List;

//@Entity
public class MyEntityCollection {
//    @Id
    private Long id;

//    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTest() {
        return test;
    }

    public void setTest(List<String> test) {
        this.test = test;
    }
}
