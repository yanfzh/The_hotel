package cn.itcast.service;

import java.util.List;
import java.util.Map;

import cn.itcast.entity.Oder;
import cn.itcast.entity.OderDetail;

public interface IOderService {
	List<Oder> findAllList();
	List<OderDetail> findDetailList();
	double submitOrders(List<Map<String,Object>> car,int tableId);
	void payFor(int tableId);
}
