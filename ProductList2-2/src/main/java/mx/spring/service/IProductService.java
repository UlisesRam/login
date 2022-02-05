package mx.spring.service;

import java.util.List;
import mx.spring.domain.Product;

public interface IProductService {

	public List<Product> listProduct();

	public void save(Product product);

	public void delete(Product product);

	public Product findProduct(Product product);
	
	
	// Other Serv
	public Product editProduct(Long product_id);

}
