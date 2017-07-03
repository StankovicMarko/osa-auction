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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.dto.ItemDTO;
import projekat.osa.osa532015.dto.UserDTO;
import projekat.osa.osa532015.entity.Item;
import projekat.osa.osa532015.entity.User;
import projekat.osa.osa532015.security.JwtTokenUtil;
import projekat.osa.osa532015.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	
	@Autowired
    private UserServiceInterface userService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	
	@GetMapping(value="/details")
	public ResponseEntity<UserDTO> getDetails(@RequestHeader(value="Authorization") String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUsername(username);
		UserDTO userDTO = new UserDTO(user);
	
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") Integer id){
		//a product must exist
		User user = userService.findOne(id); 
		
		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		
		user.setAddress(userDTO.getAddress());
		user.setPhone(userDTO.getPhone());
		user.setPicture(userDTO.getPicture());
		user.setEmail(userDTO.getEmail());
	
		user = userService.add(user);
		logger.info("changed user"+user.getId()+user.getUsername());
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);	
	}
	
}
