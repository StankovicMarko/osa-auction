package projekat.osa.osa532015.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.Where;


@Entity
@Table(name="auctions")
public class Auction implements Serializable {

	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="auction_id", unique=true, nullable=false) 
	private Long auction_id;
	
	@Column(name="start_price", unique=false, nullable=false)
	private Float startPrice;
	
	@Temporal(TIMESTAMP)
	@Column(name="start_date", unique=false, nullable=false)
	private Date startDate;
	
	@Temporal(TIMESTAMP)
	@Column(name="end_date", unique=false, nullable=true)
	private Date endDate;
	
	
	@ManyToOne
	@JoinColumn(name="item_id", referencedColumnName="item_id", nullable=false)
	private Item auctionItem;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
	private User user;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="auction")
	private Set<Bid> bids= new HashSet<Bid>();
	
	
	/////TODO
	///add, remove metode
	 public void add(Bid b) {
		    b.setAuction(this);
		    getBids().add(b);
		  }
	 
	 public void remove(Bid b) {
		    getBids().remove(b);
		  }
	
	///mozda konstruktor  koji prima dto
	public Auction(){}


	public Auction(Long auction_id, Float startPrice, Date startDate, Date endDate, String role, Item auctionItem, User user,
			Set<Bid> bids) {
		
		super();
		this.auction_id = auction_id;
		this.startPrice = startPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.auctionItem = auctionItem;
		this.user = user;
		this.bids = bids;
	}


	public Long getId() {
		return auction_id;
	}


	public void setId(Long auction_id) {
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


	public Item getAuctionItem() {
		return auctionItem;
	}


	public void setAuctionItem(Item auctionItem) {
		this.auctionItem = auctionItem;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<Bid> getBids() {
		return bids;
	}


	public void setBids(HashSet<Bid> bids) {
		this.bids = bids;
	}


	@Override
	public String toString() {
		return "Auction [id=" + auction_id + ", startPrice=" + startPrice +  ", auctionItem=" + auctionItem+"]";
	}


	public Long getAuction_id() {
		return auction_id;
	}


	public void setAuction_id(Long auction_id) {
		this.auction_id = auction_id;
	}


	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
	
	
	
	
	
}
