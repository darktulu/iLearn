package com.simu.ilearn.common.shared.vo;

public class TagVO {
    private Long id;
    private String title;
    private String description;
    private String color;
    private TagVO parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TagVO getParent() {
        return parent;
    }

    public void setParent(TagVO parent) {
        this.parent = parent;
    }
}
