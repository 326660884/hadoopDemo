package cn.atguigu.mapreduce.flowsum;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class FlowBean implements Writable  {
	/**
	 * 上级流量
	 */
	private long upFlow;
	
	/**
	 * 下行流量
	 */
	private long downFlow;
	
	/**
	 * 总流量
	 */
	private  long sumFlow;
	
	
	public FlowBean() {
		super();	
	}
	
	//反序列化
	@Override
	public void readFields(DataInput in) throws IOException {
		this.upFlow = in.readLong();
		this.downFlow=in.readLong();
		this.sumFlow=in.readLong();
		
	}
	
	//序列化
	@Override
	public void write(DataOutput output) throws IOException {
		output.writeLong(upFlow);
		output.writeLong(downFlow);
		output.writeLong(sumFlow);
	}

	public long getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}

	public long getDownFlow() {
		return downFlow;
	}

	public void setDownFlow(long downFlow) {
		this.downFlow = downFlow;
	}

	public long getSumFlow() {
		return sumFlow;
	}

	public void setSumFlow(long sumFlow) {
		this.sumFlow = sumFlow;
	}

	@Override
	public String toString() {
		return  upFlow + "\t" + downFlow + "\t" + sumFlow;
	}

	public FlowBean(long upFlow, long downFlow) {
		this.upFlow = upFlow;
		this.downFlow = downFlow;
	}

	 
	
	
	
}
