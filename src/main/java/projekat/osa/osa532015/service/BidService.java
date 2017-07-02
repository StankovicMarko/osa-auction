package projekat.osa.osa532015.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.osa532015.entity.Bid;
import projekat.osa.osa532015.repository.BidRepository;

@Service
public class BidService implements BidServiceInteraface {

	@Autowired
	BidRepository bitRepository;
	
	
	@Override
	public Bid findOne(Integer id){
		return bitRepository.findOne(id);
	}

	@Override
	public List<Bid> findAll() {
		return bitRepository.findAll();
	}

	@Override
	public Bid save(Bid bid) {
		return bitRepository.save(bid);
	}

	@Override
	public void remove(Integer id) {
		bitRepository.delete(id);
	}
}
