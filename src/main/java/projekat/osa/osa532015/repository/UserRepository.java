package projekat.osa.osa532015.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.osa532015.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
    User findByUsername(String username);
    
    List<User> findAll();
}
