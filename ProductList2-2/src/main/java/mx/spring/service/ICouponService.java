package mx.spring.service;

import java.util.List;
import mx.spring.domain.Coupon;

public interface ICouponService {

	public List<Coupon> listCoupon();
	
	public void save(Coupon coupon);
	
	public void delete(Coupon coupon);
		
	public Coupon findCoupon(Coupon coupon);
	
}
