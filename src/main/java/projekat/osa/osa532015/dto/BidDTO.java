package projekat.osa.osa532015.dto;

import java.io.Serializable;
import java.util.Date;

import projekat.osa.osa532015.entity.Bid;


public class BidDTO implements Serializable{
	
	private Long id;
	private Float price;
	private Date date;
	private long user_id;
	private long auction_id;
	
	
	public BidDTO(){}

	public BidDTO(Long id, Float price, Date date, long bid_user, long auction) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.user_id = bid_user;
		this.auction_id = auction;
	}
	
	
	public BidDTO(Bid bid){
		this(bid.getId(), bid.getPrice(), bid.getDate(),
				bid.getBid_user().getId(), bid.getAuction().getId());
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getAuction_id() {
		return auction_id;
	}

	public void setAuction_id(long auction_id) {
		this.auction_id = auction_id;
	}


	

	
	
}
