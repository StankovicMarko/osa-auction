package projekat.osa.osa532015.service;

import java.util.List;

import projekat.osa.osa532015.entity.Auction;




public interface AuctionServiceInterface {
	
	Auction findOne(Integer auctionId);
	
	List<Auction> findAll();
	
	Auction save(Auction auction);
	
	void remove(Integer id);

	
}

