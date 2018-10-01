package cn.atguigu.mapreduce.table;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable>{

	
	@Override
	protected void reduce(Text key, Iterable<TableBean> values,
			Reducer<Text, TableBean, TableBean, NullWritable>.Context context) throws IOException, InterruptedException {
		ArrayList<TableBean> orderBeans = new ArrayList<>();
		
		TableBean pdBean=new TableBean();
		
		for (TableBean bean : values) {
			if("0".equals(bean.getFlag())) {
				
				TableBean orderBean = new TableBean();
				try {
					BeanUtils.copyProperties(orderBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				orderBeans.add(orderBean);
				
			}else {
				try {
					BeanUtils.copyProperties(pdBean, bean);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}	
		}
	
		//表的拼接
		for (TableBean bean : orderBeans) {
			bean.setPname(pdBean.getPname());
			context.write(bean, NullWritable.get());
		}
	}
	
}
