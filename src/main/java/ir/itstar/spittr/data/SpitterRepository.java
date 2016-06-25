package ir.itstar.spittr.data;

public interface SpitterRepository {
	public Spitter save(Spitter unsaved);

	public Spitter findeByUsername(String username);
}