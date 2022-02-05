package mx.spring.service;

import java.util.List;
import mx.spring.domain.PromEve;

public interface IPromEveService {

	public List<PromEve> listPromEve();
	
	public void save(PromEve promEve);
	
	public void delete(PromEve promEve);
	
	public PromEve findPromEve(PromEve promEve); 
	
}
