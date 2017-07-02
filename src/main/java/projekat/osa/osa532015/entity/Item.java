package projekat.osa.osa532015.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


@Entity
@Table(name="items")
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="item_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="item_name", unique=false, nullable=false)
	private String name;
	
	@Column(name="item_desc", unique=false, nullable=false)
	private String description;
	
	@Column(name="item_pic", unique=false, nullable=false)
	private String picture;
	 
	@Column(name="item_sold", unique=false, nullable=true)
	private Boolean sold;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="auctionItem")
	private Set<Auction> auctions = new HashSet<>();
	
	///TODO
	///treba dodati add remove metode
	
	public Item(){}
	
	public Item(Long id, String name, String description, String picture, Boolean sold, 
			Set<Auction> auctions) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.sold = sold;
		this.auctions = auctions;
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
	public Set<Auction> getAuction() {
		return auctions;
	}
	public void setAuction(Set<Auction> auctions) {
		this.auctions = auctions;
	}

	
	
	public Set<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ",  sold=" + sold + "]";
	}
	
	
	
}
