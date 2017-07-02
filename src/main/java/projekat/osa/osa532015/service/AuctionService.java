package projekat.osa.osa532015.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.repository.AuctionRepository;



@Service
public class AuctionService implements AuctionServiceInterface{

	@Autowired
	AuctionRepository auctionRepository;
	
	
	@Override
	public Auction findOne(Integer auctionId){
		return auctionRepository.findOne(auctionId);
	}

	@Override
	public List<Auction> findAll() {
		return auctionRepository.findAll();
	}

	@Override
	public Auction save(Auction auction) {
		return auctionRepository.save(auction);
	}

	@Override
	public void remove(Integer id) {
		auctionRepository.delete(id);
	}
}
