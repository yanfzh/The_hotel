package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.IFoodDao;
import cn.itcast.dao.impl.FoodDao;
import cn.itcast.entity.Food;
import cn.itcast.entity.FoodType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodService;
import cn.itcast.utils.JdbcUtils;
import cn.itcast.utils.PageBean;

public class FoodService implements IFoodService {

	// ´´½¨dao
	private IFoodDao foodDao = BeanFactory.getInstance("foodDao", IFoodDao.class);
	
	@Override
	public Food findById(int id) {
		try {
			return foodDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAll(PageBean<Food> pb) {
		try {
			foodDao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Food> findAllFood() {
		try {
			return foodDao.findAllFood();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void delete(int id) {

		try {
			foodDao.delete(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	@Override
	public void save(Food food) {
		try {
			foodDao.save(food);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Food food) {
		try {
			foodDao.update(food);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Food> search(String keyword) {
		try {
			return foodDao.search(keyword);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
