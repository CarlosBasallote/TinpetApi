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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MATCH")
public class Match {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Long idMatch;
	
	@ManyToOne
    private Pet unaMascota;

    @ManyToOne
    private Pet otraMascota;

	public Match(Long idMatch, Pet unaMascota, Pet otraMascota) {
		this.idMatch = idMatch;
		this.unaMascota = unaMascota;
		this.otraMascota = otraMascota;
	}

	public Match() {
	}

	public Long getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(Long idMatch) {
		this.idMatch = idMatch;
	}

	public Pet getUnaMascota() {
		return unaMascota;
	}

	public void setUnaMascota(Pet unaMascota) {
		this.unaMascota = unaMascota;
	}

	public Pet getOtraMascota() {
		return otraMascota;
	}

	public void setOtraMascota(Pet otraMascota) {
		this.otraMascota = otraMascota;
	}

	@Override
	public String toString() {
		return "Match [idMatch=" + idMatch + ", unaMascota=" + unaMascota + ", otraMascota=" + otraMascota + "]";
	}
    
	
    
	
}
