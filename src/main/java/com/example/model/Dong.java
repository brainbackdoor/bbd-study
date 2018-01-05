package com.example.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Dong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dongId;

    private String dongName;


}
