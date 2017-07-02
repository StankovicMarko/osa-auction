package projekat.osa.osa532015.service;

import projekat.osa.osa532015.entity.User;

public interface UserServiceInterface {

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	void add(User user);
}