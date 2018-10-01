package cn.atguigu.HdfsClient;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.junit.Test;

public class HdfsClient {

	
	Logger logger = Logger.getLogger(getClass());
	@Test
	public void upload() {
		FileSystem fs;
		try {
			Path path = new Path("d:/hello.txt");
			logger.info(path.getName());
		// 1 获取文件系统
		Configuration configuration = new Configuration();
		// 配置在集群上运行
		// configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
		// FileSystem fs = FileSystem.get(configuration);
		fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration,
		"atguigu");
		// 2 上传文件
		// FileSystem fs对象就是集群文件操作
		fs.copyFromLocalFile(path, new Path("/hello.txt"));
	
		// 3 关闭资源
		fs.close();	
		logger.info("over");
		} catch (IllegalArgumentException e) {	
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
	}
	
	
}
