package com.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class MyFile {

    public String name;
    public String type;

    private MyFile(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static MyFile createNew(final String name, final String type) {
        return new MyFile(name, type);
    }
}
