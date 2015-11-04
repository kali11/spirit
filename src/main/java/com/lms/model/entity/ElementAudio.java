package com.lms.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("audio")
public class ElementAudio extends Element implements Serializable {

    public ElementAudio() {
    }

    public ElementAudio(Element element) {
        super(element);
    }

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
