package projekat.osa.osa532015.service;

import java.util.List;

import projekat.osa.osa532015.entity.Item;




public interface ItemServiceInterface {

	
	Item findOne(Integer productId);
	
	List<Item> findAll();
	
	Item save(Item auction);
	
	void remove(Integer id);
;
	
}
