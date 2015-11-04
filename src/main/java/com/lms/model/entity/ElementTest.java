package com.lms.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("test")
public class ElementTest extends Element{
    public ElementTest() {}

    public ElementTest(Element element) {
        super(element);
    }

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "elementTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestQuestion> testQuestions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }
}
