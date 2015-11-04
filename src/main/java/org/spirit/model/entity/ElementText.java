package org.spirit.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class ElementText extends Element implements Serializable {

    public ElementText() {
    }

    public ElementText(Element element) {
        super(element);

    }

    @Column(name = "text", length = 10485760)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
