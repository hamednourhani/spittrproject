package ir.itstar.spittr.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSpitterRepository implements SpitterRepository {

	private SessionFactory sessionFactory;
	
	@Autowired
	public HibernateSpitterRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public long count(){
		return findAll().size();
	}
	
	public List<Spitter> findAll() {
		return currentSession()
				.createCriteria(Spitter.class).list();
	}

	@Override
	public Spitter save(Spitter spitter) {
		Serializable id = currentSession().save(spitter);
		return new Spitter((Long) id,
		spitter.getUsername(),
		spitter.getPassword(),
		spitter.getFirstName(),
		spitter.getLastName(),
		spitter.getEmail());
	}

	@Override
	public Spitter findeByUsername(String username) {
		return (Spitter) currentSession()
				.createCriteria(Spitter.class)
				.add(Restrictions.eq("username", username))
				.list().get(0);
	}

	@Override
	public void addSpitter(Spitter spitter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSpitterbyName(Spitter spitter) {
		// TODO Auto-generated method stub

	}

	@Override
	public Spitter findOne(Long id) {
		return (Spitter) currentSession().get(Spitter.class, id);
	}

}
