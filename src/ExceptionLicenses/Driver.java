package ExceptionLicenses;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import MapReduce.LiecensesMapper;
import MapReduce.LiecensesReducer;

public class Driver {

	public static void main(String[] args)throws Exception {
		if (args.length != 2) {           
			System.out.println("usage: [input] [output]");           
			System.exit(-1);         
			}                 
		Job job = Job.getInstance(new Configuration());           
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//Key Value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		job.setMapperClass(LiecensesMapper.class);
		job.setReducerClass(LiecensesReducer.class);
		
		
		job.setInputFormatClass(LiecenseInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setJarByClass(Driver.class);          
		job.submit();

	}

}
