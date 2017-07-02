package projekat.osa.osa532015.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Item;


public class ItemDTO implements Serializable{

	private Long id;
	private String name;
	private String description;
	private String picture;
	private Boolean sold;
	@JsonIgnore
	private Set<Auction> auctions = new HashSet<>();
	
	
	public ItemDTO(){}
	
	
	
	public ItemDTO(Long id, String name, String description, String picture, Boolean sold, Set<Auction> auctions) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.sold = sold;
		this.auctions = auctions;
	}



	public ItemDTO(Item item){
		this(item.getId(), item.getName(), item.getDescription(), item.getPicture()
				,item.getSold(), item.getAuctions());
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public Boolean getSold() {
		return sold;
	}


	public void setSold(Boolean sold) {
		this.sold = sold;
	}


	public Set<Auction> getAuctions() {
		return auctions;
	}


	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}


	
	
}
