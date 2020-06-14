package cn.itcast.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import cn.itcast.dao.IDinnerTableDao;
import cn.itcast.dao.impl.DinnerTableDao;
import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.FoodType;
import cn.itcast.entity.TableStatus;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;
import cn.itcast.utils.JdbcUtils;

public class DinnerTableService implements IDinnerTableService {

	// ���õ�Dao, ͨ����������ʵ��
	private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance(
			"dinnerTableDao", IDinnerTableDao.class);

	@Override
	public DinnerTable findById(int id) {
		try {
			return dinnerTableDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findNoUseTable() {
		try {
			// ����dao�ĸ���״̬��ѯ�ķ�������ѯû��Ԥ���Ĳ���
			return dinnerTableDao.findByStatus(TableStatus.Free);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findAllTable() {
		try {
			// ����dao�ĸ���״̬��ѯ�ķ�������ѯ���еĲ���״̬
			return dinnerTableDao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void save(DinnerTable dinnerTable) {
		try {
			dinnerTableDao.save(dinnerTable);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(int id) {
		try {
			dinnerTableDao.update(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void delete(int id) {
		try {
			dinnerTableDao.delete(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
    public void setStatus(int tableId) {
		Date date=new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
	    String sql = "update dinnertable set tableStatus=1,orderDate=? where id=?";
		try {
			JdbcUtils.getQuerrRunner().update(sql,timestamp,tableId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
    }
}
