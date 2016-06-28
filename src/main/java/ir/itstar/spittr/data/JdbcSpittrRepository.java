package ir.itstar.spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSpittrRepository implements SpitterRepository {

	private static final String INSERT_SPITTER = null;
	private static final String SELECT_SPITTER_BY_ID = null;
	private static final String SQL_INSERT_SPITTER =
			"insert into spitter (username, password, fullname) " +
			"values (:username, :password, :fullname)";
	private JdbcOperations jdbcOperations;
	
	public JdbcOperations getJdbcOperations() {
		return jdbcOperations;
	}

	@Autowired
	public JdbcSpittrRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	public Spitter save(Spitter unsaved) {
		// TODO Auto-generated method stub
		return null;
	}

	public Spitter findeByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public Spitter findOne(Long id) {
		
		// --1--
		//impelementing new RowMapper for Spitter
		//return jdbcOperations.queryForObject(SELECT_SPITTER_BY_ID,new SpitterRowMapper(),id);
		
		
		//--2--
//		//use java Lambda expression
		//return jdbcOperations.queryForObject(SELECT_SPITTER_BY_ID,
//				(rs, rowNum) -> {
//					return new Spitter(
//						rs.getString("id"),
//						rs.getString("username"),
//						rs.getString("password"),
//						rs.getString("firstName"),
//						rs.getString("lastName")
//							);
//					}
//				,id);
		
		
		// --3--
		//use java 8 method refrence
		return jdbcOperations.queryForObject(SELECT_SPITTER_BY_ID,this::mapSpitter,id);
				
	}
	
	private Spitter mapSpitter(ResultSet rs, int row) throws SQLException {
		return new Spitter(
				rs.getString("id"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getString("firstName"),
				rs.getString("lastName"));
	}
	
	//using JDBCTemplate
	public void addSpitter(Spitter spitter){
		jdbcOperations.update(INSERT_SPITTER,
				spitter.getUsername(),
				spitter.getPassword(),
				spitter.getEmail());
				
	}
	
	//using NamedParameterJdbcTemplate
	public void addSpitterbyName(Spitter spitter) {
	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("username", spitter.getUsername());
		paramMap.put("password", spitter.getPassword());
		paramMap.put("email", spitter.getEmail());
		
		jdbcOperations.update(SQL_INSERT_SPITTER, paramMap);
	}

}
