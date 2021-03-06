package projekat.osa.osa532015.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value="api/items")
public class ItemController {
	
	@Autowired
    private UserServiceInterface userService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private ItemServiceInterface itemService;
	private final Log logger = LogFactory.getLog(this.getClass());

	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getItems() {
		List<Item> items = itemService.findAll();
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item i : items) {
			itemsDTO.add(new ItemDTO(i));
		}
		return new ResponseEntity<List<ItemDTO>>(itemsDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/my")
	public ResponseEntity<List<ItemDTO>> getMyAuctions(@RequestHeader(value="Authorization") String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUsername(username);
		Integer user_id = user.getId();
		
		List<Item> items = itemService.findAll();
		List<ItemDTO> itemDTO = new ArrayList<ItemDTO>();
		
		for (Item i : items) {
			if(i.getUser().getId() != user_id){
				continue;
			}
			itemDTO.add(new ItemDTO(i));
		}
		return new ResponseEntity<List<ItemDTO>>(itemDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO, @RequestHeader(value="Authorization") String token){
		Item item= new Item();
		item.setPicture(itemDTO.getPicture());
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUsername(username);
		item.setUser(user);
	
		item = itemService.save(item);
		
		logger.info("new item"+item.getId()+item.getName());
		return new ResponseEntity<ItemDTO>(new ItemDTO(item), HttpStatus.CREATED);	
	}

	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("id") Integer id){
		//a product must exist
		Item item = itemService.findOne(id); 
		
		if (item == null) {
			return new ResponseEntity<ItemDTO>(HttpStatus.BAD_REQUEST);
		}
		
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
		item.setPicture(itemDTO.getPicture());
	
		item = itemService.save(item);
		logger.info("changed item"+item.getId()+item.getName());
		return new ResponseEntity<ItemDTO>(new ItemDTO(item), HttpStatus.OK);	
	}
	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
		Item item= itemService.findOne(id);
		if (item != null){
			itemService.remove(id);
			logger.info("deleted item"+item.getId()+item.getName());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
