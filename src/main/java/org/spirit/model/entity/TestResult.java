package org.spirit.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "test_results")
public class TestResult implements Serializable{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "main_seq")
    @SequenceGenerator(name = "main_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_question", nullable = false)
    private TestQuestion testQuestion;

    @ManyToOne
    @JoinColumn(name = "element_test", nullable = false)
    private ElementTest elementTest;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "max_score")
    private Integer maxScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestQuestion getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(TestQuestion testQuestion) {
        this.testQuestion = testQuestion;
    }

    public ElementTest getElementTest() {
        return elementTest;
    }

    public void setElementTest(ElementTest elementTest) {
        this.elementTest = elementTest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
