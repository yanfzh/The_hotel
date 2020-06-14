package cn.itcast.dao;

import java.util.List;
import cn.itcast.entity.Oder;
import cn.itcast.entity.OderDetail;

public interface IOderDao {
	List<Oder> findAll();
	List<OderDetail> findDetailList();
	void create(Oder orders);
	void create(OderDetail lineItem);
	void modify(Oder orders);
}
