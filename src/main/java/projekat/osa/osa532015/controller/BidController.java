package projekat.osa.osa532015.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.security.JwtTokenUtil;
import projekat.osa.osa532015.service.ItemServiceInterface;
import projekat.osa.osa532015.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/bids")
public class BidController {

	@Autowired
    private UserServiceInterface userService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private ItemServiceInterface itemService;
	private final Log logger = LogFactory.getLog(this.getClass());
}
