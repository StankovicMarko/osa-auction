package projekat.osa.osa532015.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Bid;


public class AuctionDTO implements Serializable{

	
	private long auction_id;
	private Float startPrice;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="CET")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="CET")
	private Date endDate;
	private Integer item_id;
	private String item_pic;
	private String owner;
	@JsonIgnore
	private Set<Bid> bids= new HashSet<Bid>();
	
	
	public AuctionDTO(){}

	public AuctionDTO(long auction_id, Float startPrice, Date startDate, Date endDate, Integer item_id, String item_pic,
			String owner, Set<Bid> bids) {
		super();
		this.auction_id = auction_id;
		this.startPrice = startPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.item_id = item_id;
		this.item_pic = item_pic;
		this.owner = owner;
		this.bids = bids;
	}


	public AuctionDTO(Auction a){
		this(a.getAuction_id(), a.getStartPrice(), a.getStartDate(), a.getEndDate(), a.getAuctionItem().getId(),
				a.getAuctionItem().getPicture(), a.getUser().getUsername(), a.getBids());
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

	public String getItem_pic() {
		return item_pic;
	}

	public void setItem_pic(String item_pic) {
		this.item_pic = item_pic;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Set<Bid> getBids() {
		return bids;
	}


	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}



	
	
}
