package com.lms.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("video")
public class ElementVideo extends Element implements Serializable {

    public ElementVideo() {
    }

    public ElementVideo(Element element) {
        super(element);
    }

    @Column(name = "description")
    private String description;

    @Column(name = "src")
    private String src;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
