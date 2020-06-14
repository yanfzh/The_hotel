package cn.itcast.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.IDinnerTableDao;
import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.FoodType;
import cn.itcast.entity.TableStatus;
import cn.itcast.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{

	@Override
	public DinnerTable findById(int id) {
		String sql = "select * from dinnerTable where id=?";
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findByStatus(TableStatus ts) {
		String sql = "select * from dinnerTable where tableStatus=?";	
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),ts.ordinal());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findAll() {
		String sql = "select * from dinnerTable";
	
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void save(DinnerTable dinnerTable) {
		String sql = "INSERT INTO dinnertable(tableName,tableStatus) VALUES(?,0);";
		try {
			JdbcUtils.getQuerrRunner().update(sql,dinnerTable.getTableName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void update(int id) {
		String sql = "update dinnertable set tableStatus=0,orderDate=null where id=?";
		try {
			JdbcUtils.getQuerrRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void delete(int id) {
		String sql = "delete from dinnertable where id=?";
		try {
			JdbcUtils.getQuerrRunner().update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
