package com.example.drools.entity;

import lombok.Data;

import java.util.List;
@Data
public class School {
    private List<String> classNames;
    private Integer classCount;
}
