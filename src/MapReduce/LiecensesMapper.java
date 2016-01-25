package MapReduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class LiecensesMapper extends Mapper<Text, Text, Text, Text> {
	private Text key_out = new Text();
	private Text value_out = new Text();
	@Override
	public void map(Text key, Text value, Context context)
			throws IOException, InterruptedException {

		value_out.set(value);

		key_out.set(key);
		context.write(key_out, value_out);

	}

}
