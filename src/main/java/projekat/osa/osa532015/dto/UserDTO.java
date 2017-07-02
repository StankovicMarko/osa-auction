package projekat.osa.osa532015.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Authority;
import projekat.osa.osa532015.entity.Bid;
import projekat.osa.osa532015.entity.User;



public class UserDTO implements Serializable {
	
	private long id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String picture;
	
	private String address;
	
	private String phone;
	
	private List<Authority> authorities;
	
	private Set<Bid> bids = new HashSet<Bid>();
	
	private Set<Auction> auctions = new HashSet<>();
	
	
	/**
	 * 
	 */
	public UserDTO() {
		super();
	}


	public UserDTO(long id, String username, String email, String password, String picture, String address,
			String phone, List<Authority> authorities, Set<Bid> bids, Set<Auction> auctions) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.address = address;
		this.phone = phone;
		this.authorities = authorities;
		this.bids = bids;
		this.auctions = auctions;
	}

	
	public UserDTO(User user){
		this(user.getId(), user.getUsername(),user.getEmail(), user.getPassword(), 
				user.getPicture(), user.getAddress(), user.getPhone(), user.getAuthorities()
				, user.getBids(), user.getAuctions());
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	public List<Authority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}


	public Set<Bid> getBids() {
		return bids;
	}


	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}


	public Set<Auction> getAuctions() {
		return auctions;
	}


	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}
	

	
	
	

}

