package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchAddress {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="`key`")
    private long key;
    
    @Column(name="`value`")
    private String value;

    
    
}

