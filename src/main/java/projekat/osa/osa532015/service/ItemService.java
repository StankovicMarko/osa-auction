package projekat.osa.osa532015.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.osa.osa532015.entity.Item;
import projekat.osa.osa532015.repository.ItemRepository;

@Service
public class ItemService implements ItemServiceInterface {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public Item findOne(Integer itemId){
		return itemRepository.findOne(itemId);
	}
	
	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void remove(Integer id) {
		itemRepository.delete(id);
	}
}
