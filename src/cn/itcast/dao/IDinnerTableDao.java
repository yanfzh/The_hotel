package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;

public interface IDinnerTableDao {

	/**
	 * 根据预定状态查询
	 */
	List<DinnerTable> findByStatus(TableStatus ts);

	/**
	 * 查询所有
	 */
	List<DinnerTable> findAll();
	
	/**
	 * 主键查询
	 */
	DinnerTable findById(int id);
	/**
	 * 添加
	 */
	void save(DinnerTable dinnerTable);
	/**
	 * 退桌
	 */
	void update(int id);
	/**
	 * 删除
	 */
	void delete(int id);

}
