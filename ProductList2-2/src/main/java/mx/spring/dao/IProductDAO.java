package mx.spring.dao;

import mx.spring.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDAO extends JpaRepository<Product, Long>{
	
}
