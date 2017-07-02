package projekat.osa.osa532015.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.osa.osa532015.entity.Auction;




public interface AuctionRepository extends JpaRepository<Auction, Integer>{

	
//	@Query() ako ti treba upit, ako ni to ne resava to je dobar indikator da ti
	//treba servis jer zahteva dodatnu obradu podataka
	
	
}





