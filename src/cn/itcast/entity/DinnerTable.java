package cn.itcast.entity;

import java.util.Date;

public class DinnerTable {

	private int id;// PRIMARY KEY AUTO_INCREMENT, -- ��������
	private String tableName;// VARCHAR(20), -- ������
	private int tableStatus;// INT DEFAULT 0, -- ����״̬��0�����У� 1��Ԥ��
	private Date orderDate;// DATETIME
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
