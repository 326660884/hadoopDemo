package cn.atguigu.mapreduce.flowsum;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean>{

	@Override
	protected void reduce(Text key, Iterable<FlowBean> values, Context context)
			throws IOException, InterruptedException {
		// 1 求和
		long sum_upFlow= 0;
		
		long sum_dowmFlow=0;
		
		for (FlowBean bean : values) {
			sum_upFlow += bean.getUpFlow();
			sum_dowmFlow += bean.getDownFlow();		
		}
		FlowBean flowBean = new FlowBean(sum_upFlow,sum_dowmFlow);
		flowBean.setSumFlow(sum_upFlow+sum_dowmFlow);
		
		// 2 输出
		context.write(key, flowBean);
		//
	}
	
}
