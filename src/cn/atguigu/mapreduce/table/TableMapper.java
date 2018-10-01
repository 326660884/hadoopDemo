package cn.atguigu.mapreduce.table;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean>{

	private TableBean bean= new TableBean();
	private Text k = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, TableBean>.Context context)
			throws IOException, InterruptedException {
		FileSplit split = (FileSplit) context.getInputSplit();
		String name = split.getPath().getName();
		
		String line = value.toString();
		
		if(name.startsWith("order")) {
			String[] fields = line.split("\t");
			bean.setOrder_id(fields[0]);
			bean.setP_id(fields[1]);
			bean.setAmount(Integer.parseInt(fields[2]));
			bean.setPname("");
			bean.setFlag("0");
			k.set(fields[1]);
		}else {
			String[] fields = line.split("\t");
			bean.setP_id(fields[0]);
			bean.setPname(fields[1]);
			bean.setFlag("1");
			bean.setAmount(0);
			bean.setOrder_id("");
			k.set(fields[0]);
		}
		
		context.write(k, bean);
	}
	
	
}
