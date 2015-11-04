package org.spirit.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("file")
public class ElementFile extends Element implements Serializable {

    public ElementFile() {
    }

    public ElementFile(Element element) {
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
