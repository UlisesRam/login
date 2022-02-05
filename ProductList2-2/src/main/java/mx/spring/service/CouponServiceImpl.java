package mx.spring.service;

import java.util.*;
import mx.spring.dao.*;
import mx.spring.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service	
public class CouponServiceImpl implements ICouponService{

	@Autowired
	private ICouponDAO couponDAO;
		
	@Override
	@Transactional(readOnly = true)
	public List<Coupon> listCoupon() {
		return (List<Coupon>) couponDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Coupon coupon) {
		couponDAO.save(coupon);
	}

	@Override
	@Transactional
	public void delete(Coupon coupon) {
		couponDAO.delete(coupon);
	}

	@Override
	@Transactional(readOnly = true)
	public Coupon findCoupon(Coupon coupon) {
		return couponDAO.findById(coupon.getCOUPONS_ID()).orElse(null);
	}
	
}
