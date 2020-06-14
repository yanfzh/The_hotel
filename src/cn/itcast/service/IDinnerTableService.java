package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.DinnerTable;

public interface IDinnerTableService {

	/**
	 * 查询所有未预定的餐桌
	 */
	List<DinnerTable> findNoUseTable();
	
	/**
	 * 查询所有未预定的餐桌
	 */
	List<DinnerTable> findAllTable();
	
	/**
	 * 根据主键查询
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
	void setStatus(int tableId);
}
