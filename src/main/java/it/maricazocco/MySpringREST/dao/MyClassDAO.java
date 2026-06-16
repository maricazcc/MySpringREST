package it.maricazocco.MySpringREST.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import it.maricazocco.MySpringREST.models.MyClass;

@Component
public class MyClassDAO implements MyDAO<MyClass> {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public void save(MyClass myClass) {
		jdbcTemplate.update("INSERT INTO myclass (mystring, myint) VALUES (?, ?)", myClass.getMyString(), myClass.getMyInt());
	}	

	@Override
	public void update(MyClass myClass) {
		jdbcTemplate.update("UPDATE myclass SET mystring = ?, myint = ? WHERE id = ?", myClass.getMyString(), myClass.getMyInt(), myClass.getId());
	}
	
	@Override
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM myclass WHERE id = ?", id);
	}

	@Override
	public List<Map<String, Object>> findAll() {
		return jdbcTemplate.queryForList("SELECT * FROM myclass");
	}

	

}
