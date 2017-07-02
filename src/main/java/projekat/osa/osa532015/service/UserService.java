package projekat.osa.osa532015.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.osa532015.entity.User;
import projekat.osa.osa532015.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsernameAndPassword(String username, String password){
		User user = userRepository.findByUsername(username);
		if(user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	@Override
	public User findByUsername(String username){
		User user = userRepository.findByUsername(username);
			return user;
	}
	
	@Override
	public void add(User user){
//		user = userRepository.findOne(user.getId());
//		user.getOrders().add(order);
		userRepository.save(user);
	}

}