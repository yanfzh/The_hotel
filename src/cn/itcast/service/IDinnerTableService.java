package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.DinnerTable;

public interface IDinnerTableService {

	/**
	 * ��ѯ����δԤ���Ĳ���
	 */
	List<DinnerTable> findNoUseTable();
	
	/**
	 * ��ѯ����δԤ���Ĳ���
	 */
	List<DinnerTable> findAllTable();
	
	/**
	 * ����������ѯ
	 */
	DinnerTable findById(int id);
	/**
	 * ���
	 */
	void save(DinnerTable dinnerTable);
	/**
	 * ����
	 */
	void update(int id);
	/**
	 * ɾ��
	 */
	void delete(int id);
	void setStatus(int tableId);
}
