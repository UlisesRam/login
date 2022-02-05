package mx.spring.service;

import java.util.List;
import mx.spring.domain.PromEve;
import mx.spring.dao.IPromEveDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PromEveServiceImpl implements IPromEveService{

	@Autowired
	private IPromEveDAO promEveDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<PromEve> listPromEve(){
		return (List<PromEve>) promEveDAO.findAll();
	}

	@Override
	public void save(PromEve promEve) {
		promEveDAO.save(promEve);
		
	}

	@Override
	public void delete(PromEve promEve) {
		promEveDAO.delete(promEve);
		
	}

	@Override
	public PromEve findPromEve(PromEve promEve) {
		return promEveDAO.findById(promEve.getPROMOTION_EVENT_ID()).orElse(null);
	}
	
	
}
