package projekat.osa.osa532015.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.dto.AuctionDTO;
import projekat.osa.osa532015.dto.ItemDTO;
import projekat.osa.osa532015.entity.Auction;
import projekat.osa.osa532015.entity.Item;
import projekat.osa.osa532015.service.AuctionServiceInterface;
import projekat.osa.osa532015.service.ItemServiceInterface;



@RestController
@RequestMapping(value="api/items")
public class ItemController {
	
	@Autowired
	private ItemServiceInterface itemService;
	private final Log logger = LogFactory.getLog(this.getClass());

	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getItems() {
		List<Item> items = itemService.findAll();
		//convert categories to DTOs
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item i : items) {
			itemsDTO.add(new ItemDTO(i));
		}
		return new ResponseEntity<List<ItemDTO>>(itemsDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO){
		Item item= new Item();
		item.setPicture(itemDTO.getPicture());
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
	
		item = itemService.save(item);
		return new ResponseEntity<ItemDTO>(new ItemDTO(item), HttpStatus.CREATED);	
	}

	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
		Item item= itemService.findOne(id);
		if (item != null){
			itemService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
