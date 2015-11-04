package org.spirit.model.dict;

public enum ElementType {
    TEXT("text"),
    VIDEO("video"),
    AUDIO("audio"),
    TEST("test"),
    FILE("file");

    String name;

    ElementType(String typeName) {
        name = typeName;
    }

    @Override
    public String toString() {
        return name;
    }
}
