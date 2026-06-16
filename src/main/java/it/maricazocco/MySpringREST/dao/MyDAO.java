package it.maricazocco.MySpringREST.dao;

import java.util.List;
import java.util.Map;


public interface MyDAO<T> {	
	public void save(T t);
	public void update(T t);
	public void delete(int id);	
	public List<Map<String, Object>> findAll();
	//...
}