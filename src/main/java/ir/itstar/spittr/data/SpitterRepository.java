package ir.itstar.spittr.data;

import java.util.List;

public interface SpitterRepository {
	
	public Spitter save(Spitter unsaved);

	public Spitter findeByUsername(String username);
	
	public void addSpitter(Spitter spitter);
	public void addSpitterbyName(Spitter spitter);
	
	public Spitter findOne(Long id);
	public long count();
	public List<Spitter> findAll();
	
}
