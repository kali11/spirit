package org.spirit.model.dict;

public enum QuestionType {
    SINGLE("single"),
    MULTI("multi"),
    OPEN("open");

    String name;

    QuestionType(String typeName) {
        this.name = typeName;
    }

    @Override
    public String toString(){
        return name;
    }
}
