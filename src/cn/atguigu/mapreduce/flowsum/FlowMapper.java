package cn.atguigu.mapreduce.flowsum;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowMapper extends Mapper<LongWritable,Text,Text,FlowBean> {
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context)
			throws IOException, InterruptedException {
		
		// 1 获得一行
		String line = value.toString();
		// 2 切割
		String[] fields = line.split("\t");
		//	3 封装对象
		String phoneNum=fields[1];
		long upFlow = Long.parseLong(fields[3]);
		long downFlow = Long.parseLong(fields[4]);
		
		FlowBean v = new FlowBean(upFlow,downFlow);
		Text k = new Text();
		k.set(phoneNum);
		// 4写出数据
		context.write(k, v);
		
	}
	
}
