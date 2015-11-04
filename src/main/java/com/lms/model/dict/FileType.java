package com.lms.model.dict;

public enum FileType {
    IMAGE("image"),
    AUDIO("audio"),
    OTHER("other");

    String name;

    FileType(String typeName) {
        name = typeName;
    }

    @Override
    public String toString() {
        return name;
    }
}
