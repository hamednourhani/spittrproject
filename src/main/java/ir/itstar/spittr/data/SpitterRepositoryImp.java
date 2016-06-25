package ir.itstar.spittr.data;

import org.springframework.stereotype.Repository;



@Repository
public class SpitterRepositoryImp implements SpitterRepository {

	
	public Spitter save(Spitter unsaved) {
		unsaved.setId(2L);
		return unsaved;
	}

	
	public Spitter findeByUsername(String username) {
		return new Spitter(2L,username,"password","firstName","LastName","info@example.com");
	}

}
