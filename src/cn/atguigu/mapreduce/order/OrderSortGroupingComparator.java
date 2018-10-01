package cn.atguigu.mapreduce.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderSortGroupingComparator extends WritableComparator{
	
	/**
	 * 必须这样
	 */
	protected OrderSortGroupingComparator() {
		super(OrderBean.class,true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		OrderBean aBean=(OrderBean) a;	
		OrderBean bBean=(OrderBean) b;
		
		int result;
		if (aBean.getOrder_id() > bBean.getOrder_id()) {
			result = 1;
		} else if (aBean.getOrder_id() < bBean.getOrder_id()) {
			result = -1;
		} else {
			result = 0;
		}
//		
		return result;
	}

//	@Override
//	public int compare(Object a, Object b) {
//		OrderBean aBean=(OrderBean) a;	
//		OrderBean bBean=(OrderBean) b;
//		
//		int result;
//		if (aBean.getOrder_id() > bBean.getOrder_id()) {
//			result = 1;
//		} else if (aBean.getOrder_id() < bBean.getOrder_id()) {
//			result = -1;
//		} else {
//			result = 0;
//		}
//		
//		return result;
//	}

	
	
}
