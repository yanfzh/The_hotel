package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.Food;
import cn.itcast.utils.PageBean;

public interface IFoodService {

	/**
	 * 主键查询
	 */
	Food findById(int id);

	/**
	 * 分页查询
	 */
	void getAll(PageBean<Food> pb);
	List<Food> findAllFood();
	void delete(int id);
	void save(Food food);
	void update(Food food);
	List<Food> search(String keyword);
}
