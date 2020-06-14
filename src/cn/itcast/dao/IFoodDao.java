package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Food;
import cn.itcast.utils.PageBean;

/**
 * ��Ʒ����
 * @author Jie.Yuan
 *
 */
public interface IFoodDao {

	/**
	 * ��ҳ�Ұ�������ѯ���еĲ�Ʒ
	 */
	void getAll(PageBean<Food> pb);
	
	/**
	 * ������ͳ�Ʋ�Ʒ���ܼ�¼��
	 */
	int getTotalCount(PageBean<Food> pb);
	
	/**
	 * ����id��ѯ
	 */
	Food findById(int id);
	
	 void delete(int id);
	 List<Food> findAllFood();
	 void save(Food food);
	 void update(Food food);
	 List<Food> search(String keyword);
}
