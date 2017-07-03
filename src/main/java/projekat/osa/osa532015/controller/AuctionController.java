package projekat.osa.osa532015.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.dto.AuctionDTO;
import projekat.osa.osa532015.dto.ItemDTO;
import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Item;
import projekat.osa.osa532015.entity.User;
import projekat.osa.osa532015.security.JwtTokenUtil;
import projekat.osa.osa532015.service.AuctionServiceInterface;
import projekat.osa.osa532015.service.ItemServiceInterface;
import projekat.osa.osa532015.service.UserServiceInterface;


@RestController
@RequestMapping(value="api/auctions")
public class AuctionController {
	
	@Autowired
    private UserServiceInterface userService;
	
	@Autowired
    private ItemServiceInterface itemService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuctionServiceInterface auctionService;
	private final Log logger = LogFactory.getLog(this.getClass());

	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<AuctionDTO>> getAuctions() {
		List<Auction> auctions = auctionService.findAll();
		List<AuctionDTO> auctionsDTO = new ArrayList<AuctionDTO>();
		for (Auction a : auctions) {
			auctionsDTO.add(new AuctionDTO(a));
		}
		return new ResponseEntity<List<AuctionDTO>>(auctionsDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/my")
	public ResponseEntity<List<AuctionDTO>> getMyAuctions(@RequestHeader(value="Authorization") String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUsername(username);
		Integer user_id = user.getId();
		
		List<Auction> auctions = auctionService.findAll();
		List<AuctionDTO> auctionsDTO = new ArrayList<AuctionDTO>();
		
		for (Auction a : auctions) {
			if(a.getUser().getId() != user_id){
				continue;
			}
			if(a.getStartDate().after(new Date())){
			auctionsDTO.add(new AuctionDTO(a));}
		}
		return new ResponseEntity<List<AuctionDTO>>(auctionsDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<AuctionDTO> saveItem(@RequestBody AuctionDTO auctionDTO, @RequestHeader(value="Authorization") String token){
		Auction a= new Auction();
		a.setStartDate(auctionDTO.getStartDate());
		a.setEndDate(auctionDTO.getEndDate());
		a.setStartPrice(auctionDTO.getStartPrice());
		a.setAuctionItem(itemService.findOne(auctionDTO.getItem_id()));
//		logger.info(a.getStartDate() + " " + a.getEndDate());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUsername(username);
		a.setUser(user);
		a = auctionService.save(a);
		logger.info("new auction"+a.getId()+a.getUser().getUsername());;

		return new ResponseEntity<AuctionDTO>(new AuctionDTO(a), HttpStatus.CREATED);	
	}
	
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<AuctionDTO> updateItem(@RequestBody AuctionDTO auctionDTO, @PathVariable("id") Integer id){
		//a product must exist
		Auction auction = auctionService.findOne(id); 
		
		if (auction == null) {
			return new ResponseEntity<AuctionDTO>(HttpStatus.BAD_REQUEST);
		}
		
		auction.setStartDate(auctionDTO.getStartDate());
		auction.setEndDate(auctionDTO.getEndDate());
		auction.setStartPrice(auctionDTO.getStartPrice());
	
		auction = auctionService.save(auction);
		
		logger.info("changed auction"+auction.getId()+auction.getUser().getUsername());
		
		return new ResponseEntity<AuctionDTO>(new AuctionDTO(auction), HttpStatus.OK);	
	}
	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
		Auction auction = auctionService.findOne(id);
		if (auction != null){
			auctionService.remove(id);
			logger.info("deleted auction"+auction.getId()+auction.getUser().getUsername());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
