package cn.atguigu.mapreduce.flowsum;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FlowDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		//1 获得job对象
		
		Job job = Job.getInstance(new Configuration());
		//2设置jar包路径
		job.setJarByClass(FlowDriver.class);
		//3管理mapper和reducer类
		job.setMapperClass(FlowMapper.class);
		job.setReducerClass(FlowReducer.class);
		//4设置mapper输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		//8设置合并 combiner
		job.setCombinerClass(FlowReducer.class);
		
		//5设置K,V类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		//6设置输入输出类型
		FileInputFormat.setInputPaths(job, new Path(args[0]) );
		FileOutputFormat.setOutputPath(job, new Path(args[1]) );
		//7提交
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}
	
}
