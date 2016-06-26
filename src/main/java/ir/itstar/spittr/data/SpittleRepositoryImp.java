package ir.itstar.spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SpittleRepositoryImp implements SpittleRepository {

	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		List<Spittle> spittlesList = new ArrayList<Spittle>();
		for(int i=0; i<count ; i++){
			spittlesList.add(new Spittle("Spittle No."+i, new Date()));
		}
		return spittlesList;
	}

	public Spittle findOne(int i) {
		// TODO Auto-generated method stub
		
		return new Spittle("Spittle No."+i, new Date());
	}

}
