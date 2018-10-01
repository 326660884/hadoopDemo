package cn.atguigu.mapreduce.order;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * 二次排序
 * @author Administrator
 *
 */
public class OrderBean implements WritableComparable<OrderBean>{
	
	private int order_id;
	private double price;
	
	public OrderBean() {
		super();
	}
	public OrderBean(int id,double price) {
		super();
		this.order_id=id;
		this.price=price;
	}
	
	
	
	@Override
	public void readFields(DataInput input) throws IOException {
		order_id=input.readInt();
		price=input.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(order_id);
		out.writeDouble(price);
	}
	
	/**
	 *  排序
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(OrderBean o) {
//		int result=order_id - o.getOrder_id();
//		if(result!=0) {
//			return result;
//		}
//		result = price > o.getPrice() ? -1 : 1;
		int result;
		if (order_id > o.getOrder_id()) {
			result = 1;
		} else if (order_id < o.getOrder_id()) {
			result = -1;
		} else {
			// 价格倒序排序
			result = price > o.getPrice() ? -1 : 1;
		}
		
		return result;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return order_id+"\t"+price;
	}
	
	
}
