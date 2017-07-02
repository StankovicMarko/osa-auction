package projekat.osa.osa532015.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.dto.AuctionDTO;
import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.service.AuctionServiceInterface;


@RestController
@RequestMapping(value="api/auctions")
public class AuctionController {
	
	@Autowired
	private AuctionServiceInterface auctionService;
	private final Log logger = LogFactory.getLog(this.getClass());

	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<AuctionDTO>> getAuctions() {
		List<Auction> auctions = auctionService.findAll();
		//convert categories to DTOs
		List<AuctionDTO> auctionsDTO = new ArrayList<AuctionDTO>();
		for (Auction a : auctions) {
			auctionsDTO.add(new AuctionDTO(a));
		}
		return new ResponseEntity<List<AuctionDTO>>(auctionsDTO, HttpStatus.OK);
	}
	


}
