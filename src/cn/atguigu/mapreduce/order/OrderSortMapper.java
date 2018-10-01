package cn.atguigu.mapreduce.order;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderSortMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable>{
	/**
	 * key
	 */
	protected OrderBean  k = new  OrderBean();
	
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		
		String[] fieds = line.split("\t");
	
		k.setOrder_id(Integer.parseInt(fieds[0]));
		k.setPrice(Double.parseDouble(fieds[2]));
		
		context.write(k, NullWritable.get());
	}
	
	
}
