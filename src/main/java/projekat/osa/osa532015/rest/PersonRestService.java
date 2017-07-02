package projekat.osa.osa532015.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.osa.osa532015.entity.User;
import projekat.osa.osa532015.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonRestService {
	
	   @Autowired
	    UserRepository userRepository;
	   
	   private final Log logger = LogFactory.getLog(this.getClass());
	   
    private static final List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("Hello", "World"));
        persons.add(new Person("Foo", "Bar"));
    }
    
 

    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public static List<Person> getPersons() {
        return persons;
    }

    @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
    public static Person getPerson(@PathVariable("name") String name) {
        return persons.stream()
                .filter(person -> name.equalsIgnoreCase(person.getName()))
                .findAny().orElse(null);
    }
    
    
    @RequestMapping(path = "/persons/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String getPP() {
    	String resp = "";
    	ArrayList<User> users= (ArrayList<User>) userRepository.findAll();
    	for (User user : users) {
    		resp = resp + user.getUsername() + user.getEmail()+"\n";
		}
        return resp;
    }
    
}
