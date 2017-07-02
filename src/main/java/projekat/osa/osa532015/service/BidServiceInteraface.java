package projekat.osa.osa532015.service;

import java.util.List;

import projekat.osa.osa532015.entity.Bid;



public interface BidServiceInteraface {

	
	Bid findOne(Integer id);
	
	List<Bid> findAll();
	
	Bid save(Bid bid);
	
	void remove(Integer id);
}
