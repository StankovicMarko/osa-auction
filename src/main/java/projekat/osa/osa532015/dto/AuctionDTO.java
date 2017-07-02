package projekat.osa.osa532015.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Transient;


import com.fasterxml.jackson.annotation.JsonIgnore;

import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Bid;


public class AuctionDTO implements Serializable{

	
	private long auction_id;
	private Float startPrice;
	private Date startDate;
	private Date endDate;
	private long item_id;
	private long user_id;
	private Set<Bid> bids= new HashSet<Bid>();
	
	
	public AuctionDTO(){}



	
	public AuctionDTO(long auction_id, Float startPrice, Date startDate, Date endDate, long item_id, long user_id,
			Set<Bid> bids) {
		super();
		this.auction_id = auction_id;
		this.startPrice = startPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.item_id = item_id;
		this.user_id = user_id;
		this.bids = bids;
	}


	public AuctionDTO(Auction a){
		this(a.getAuction_id(), a.getStartPrice(), a.getStartDate(), a.getEndDate(),
				a.getAuctionItem().getId(), a.getUser().getId(), a.getBids());
	}


	public long getAuction_id() {
		return auction_id;
	}


	public void setAuction_id(long auction_id) {
		this.auction_id = auction_id;
	}


	public Float getStartPrice() {
		return startPrice;
	}


	public void setStartPrice(Float startPrice) {
		this.startPrice = startPrice;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public long getItem_id() {
		return item_id;
	}


	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public Set<Bid> getBids() {
		return bids;
	}


	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}



	
	
}
