package cn.itcast.entity;

public class OderDetail {
	private int id;
	private int orderId;
	private int food_id;
	private int foodCount;
	private double foodPrice;
	//这个实例囊括所有商品订单信息
	private Oder oder; 
	private Food food;
	
	public double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Oder getOder() {
		return oder;
	}
	public void setOder(Oder oder) {
		this.oder = oder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	
}
