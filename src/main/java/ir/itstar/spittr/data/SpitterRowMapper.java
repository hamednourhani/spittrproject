package ir.itstar.spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SpitterRowMapper implements RowMapper<Spitter> {

	public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		// TODO 
		return new Spitter(
				rs.getString("id"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getString("firstName"),
				rs.getString("lastName"));
				
				
	}

}
