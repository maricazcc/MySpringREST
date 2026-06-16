package it.maricazocco.MySpringREST.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.maricazocco.MySpringREST.dao.MyClassDAO;
import it.maricazocco.MySpringREST.models.MyClass;

@Service
public class MyService {
	@Autowired
	private MyClassDAO myClassDAO;
	
	/*public void save(MyClass myclass) {
    	myClassDAO.save(myclass);       	
    } */
	
	public String save(MyClass myClass) {
		String result;
		
		if (myClass.getMyInt() <= 999 && myClass.getMyString().equals("Apple")) {
			myClassDAO.save(myClass);
			result = "OK, prodotto inserito nel DB";
		} else {
			result = "NO, non vendiamo prodotti Non Apple e con prezzo > 999";
		}
		
		return result;
	}
	
	public void update(MyClass myclass) {
    	myClassDAO.update(myclass);       	
    }
	
	public void delete(int id) {
    	myClassDAO.delete(id);       	
    }
	
	public List<Map<String, Object>> findAll() {
    	return myClassDAO.findAll();       	
    }
	

}
