package mx.spring.service;

import java.util.*;
import mx.spring.dao.*;
import mx.spring.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productDAO;
	
	@Override
	@Transactional(readOnly = true) //Only query DB information
	public List<Product> listProduct(){
		return (List<Product>) productDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Product product) { //Must do commit or rollback
		productDAO.save(product);		
	}
	
	@Override
	@Transactional
	public void delete(Product product) { //Must do commit or rollback
		productDAO.delete(product);
	}
	
	@Override
	@Transactional(readOnly = true) //Only query DB information
	public Product findProduct(Product product) {
		return productDAO.findById(product.getProduct_id()).orElse(null);
	}
	
	
	// Other Impl 
	@Override
	@Transactional (readOnly = true)
	public Product editProduct(Long product_id) {
		return productDAO.getById(product_id);
	}

}
