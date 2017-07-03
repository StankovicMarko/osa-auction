package projekat.osa.osa532015.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.dto.AuctionDTO;
import projekat.osa.osa532015.dto.BidDTO;
import projekat.osa.osa532015.dto.ItemDTO;
import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Bid;
import projekat.osa.osa532015.entity.Item;
import projekat.osa.osa532015.entity.User;
import projekat.osa.osa532015.security.JwtTokenUtil;
import projekat.osa.osa532015.service.AuctionServiceInterface;
import projekat.osa.osa532015.service.BidServiceInteraface;
import projekat.osa.osa532015.service.ItemServiceInterface;
import projekat.osa.osa532015.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/bids")
public class BidController {

	@Autowired
    private UserServiceInterface userService;
	
	@Autowired
    private BidServiceInteraface bidService;
	
	@Autowired
    private AuctionServiceInterface auctionService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	
	
	@GetMapping
	public ResponseEntity<List<BidDTO>> getBids() {
		List<Bid> bids = bidService.findAll();
		List<BidDTO> bidsDTO = new ArrayList<BidDTO>();
		for (Bid b : bids) {
			bidsDTO.add(new BidDTO(b));
		}
		return new ResponseEntity<List<BidDTO>>(bidsDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<BidDTO> saveItem(@RequestBody BidDTO bidDTO, 
			@RequestHeader(value="Authorization") String token){
		Bid bid= new Bid();
		logger.info(bidDTO.getPrice());
		
		bid.setPrice(bidDTO.getPrice());
		bid.setAuction(auctionService.findOne(bidDTO.getAuction_id()));
		bid.setDate(new Date());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUsername(username);
		bid.setUser(user);
	
		bid = bidService.save(bid);
		
		logger.info("new bid"+bid.getId()+bid.getUser().getUsername());
		return new ResponseEntity<BidDTO>(new BidDTO(bid), HttpStatus.CREATED);	
	}
	
	
	
	
	
}
