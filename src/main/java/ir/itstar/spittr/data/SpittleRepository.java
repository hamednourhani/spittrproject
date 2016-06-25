package ir.itstar.spittr.data;

import java.util.List;


public interface SpittleRepository {
	public List<Spittle> findSpittles(long max,int count);

	public Spittle findOne(int i);
}
