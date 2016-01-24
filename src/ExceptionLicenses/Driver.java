package ExceptionLicenses;

import FaceBookFriendsMapper;


import MapReduce.LiecensesMapper;
import MapReduce.LiecensesReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {

	public static void main(String[] args) {
		if (args.length != 2) {           
			System.out.println("usage: [input] [output]");           
			System.exit(-1);         
			}                 
		Job job = Job.getInstance(new Configuration());         
	
		
		//Key Value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		job.setMapperClass(LiecensesMapper.class);
		job.setReducerClass(LiecensesReducer.class);
		
		
		job.setInputFormatClass(LiecenseInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

	}

}
