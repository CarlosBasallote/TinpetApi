package com.cbasalloteteba.tinpet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cbasalloteteba.tinpet.utility.View;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "PET")
public class Pet {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long idPet;

	@JsonView(View.Summary.class)
	@Column(name = "NAME", length = 50)
	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	@Column(name = "CATEGORY", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoryName category;

	@Column(name = "SEX", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private SexoName sex;

	@Column(name = "INTEREST", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private InterestName interest;

	@Column(name = "DESCRIPTION", length = 200)
	@Size(min = 0, max = 200)
	private String description;

	@Column(name = "MONTH")
	@NotNull
	@Min(1)
	@Max(12)
	private int birthMonth;

	@Column(name = "YEAR")
	@NotNull
	@Min(1985)
	@Max(2999)
	private int birthYear;
	
	@Column(name = "PHOTOS", length = 50)
	@OneToMany(targetEntity=Pet.class, mappedBy="photos", fetch=FetchType.LAZY)
	private List<String> photos=new ArrayList<>();
	
	@Column(name = "LIKES", length = 50)
	@OneToMany(targetEntity=Pet.class, mappedBy="likes", fetch=FetchType.LAZY)
	private List<Pet> likes=new ArrayList<>();
	
	@Column(name = "DISLIKES", length = 50)
	@OneToMany(targetEntity=Pet.class, mappedBy="dislikes", fetch=FetchType.LAZY)
	private List<Pet> dislikes=new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "OWNER")
	private User owner;
	

	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PET_MATCH", joinColumns = {
			@JoinColumn(name = "PET_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "MATCH_ID", referencedColumnName = "ID") })*/
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "couples")
	private List<Match> matches=new ArrayList<>();
	
	public Long getIdPet() {
		return idPet;
	}

	public void setIdPet(Long idPet) {
		this.idPet = idPet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryName getCategory() {
		return category;
	}

	public void setCategory(CategoryName category) {
		this.category = category;
	}

	public SexoName getSex() {
		return sex;
	}

	public void setSex(SexoName sex) {
		this.sex = sex;
	}

	public InterestName getInterest() {
		return interest;
	}

	public void setInterest(InterestName interest) {
		this.interest = interest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public List<Pet> getLikes() {
		return likes;
	}

	public void setLikes(List<Pet> likes) {
		this.likes = likes;
	}

	public List<Pet> getDislikes() {
		return dislikes;
	}

	public void setDislikes(List<Pet> dislikes) {
		this.dislikes = dislikes;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Pet [idPet=" + idPet + ", name=" + name + ", category=" + category + ", sex=" + sex + ", interest="
				+ interest + ", description=" + description + ", birthMonth=" + birthMonth + ", birthYear=" + birthYear
				+ ", photos=" + photos + ", likes=" + likes + ", dislikes=" + dislikes + ", owner=" + owner
				+ ", matches=" + matches + "]";
	}
	
	
	
}
