package projekat.osa.osa532015.entity;


import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.Where;


@Entity
@Table(name="bids")
public class Bid implements Serializable {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="bid_id", unique=true, nullable=false)
	private Integer id;
	
	
	@Column(name="price", unique=false, nullable=false)
	private Float price;
	
	@Temporal(TIMESTAMP)
	@Column(name="bid_date", unique=true, nullable=false)
	private Date date;
	
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
	private User bid_user;
	
	
	@ManyToOne
	@JoinColumn(name="auction_id", referencedColumnName="auction_id", nullable=false)
	private Auction auction;
	
	
	///napravi add and remove metode
	
	
	public Bid(){}
	
	public Bid(Integer id, Float price, Date date, User bid_user, Auction auction) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.bid_user = bid_user;
		this.auction = auction;
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

	public User getUser() {
		return bid_user;
	}

	public void setUser(User bid_user) {
		this.bid_user = bid_user;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	

	public User getBid_user() {
		return bid_user;
	}

	public void setBid_user(User bid_user) {
		this.bid_user = bid_user;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", price=" + price + ", date=" + date + ", user=" + "]";
	}
	
	
	
	
	
}
