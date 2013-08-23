package com.simu.ilearn.common.shared.vo;

import com.simu.ilearn.common.shared.type.LearnStatus;

import java.util.Date;
import java.util.List;

public class LearnVO {
    private Long id;
    private String title;
    private String content;
    private Double note;
    private Date created;
    private UserVO owner;
    private List<TagVO> tags;
    private LearnStatus status;
    private LocationVO location;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserVO getOwner() {
        return owner;
    }

    public void setOwner(UserVO owner) {
        this.owner = owner;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }

    public LearnStatus getStatus() {
        return status;
    }

    public void setStatus(LearnStatus status) {
        this.status = status;
    }

    public LocationVO getLocation() {
        return location;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }
}
