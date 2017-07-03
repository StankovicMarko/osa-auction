package projekat.osa.osa532015.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import projekat.osa.osa532015.entity.Bid;


public class BidDTO implements Serializable{
	
	private Integer id;
	private Float price;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="CET")
	private Date date;
	private Integer user_id;
	private Integer auction_id;
	
	
	public BidDTO(){}

	public BidDTO(Integer id, Float price, Date date, Integer bid_user, Integer auction) {
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
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getAuction_id() {
		return auction_id;
	}

	public void setAuction_id(Integer auction_id) {
		this.auction_id = auction_id;
	}


	

	
	
}
