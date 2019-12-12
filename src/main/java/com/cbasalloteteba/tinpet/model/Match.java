package com.cbasalloteteba.tinpet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MATCH")
public class Match {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Long idMatch;
	
	@JsonIgnore
    @ManyToMany //(mappedBy = "matches", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pet> couples=new ArrayList<>();
	
	private String interest;
	
	private String category;
	
}
