package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;

public interface IDinnerTableDao {

	/**
	 * ����Ԥ��״̬��ѯ
	 */
	List<DinnerTable> findByStatus(TableStatus ts);

	/**
	 * ��ѯ����
	 */
	List<DinnerTable> findAll();
	
	/**
	 * ������ѯ
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

}
