package MapReduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class LiecensesReducer extends Reducer<Text, Text, Text, Text>  {
	private Text outText= new Text();
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)throws IOException, InterruptedException{
		Iterator<Text> it=values.iterator();
	    int i=0;
		while (it.hasNext())
		{
			it.next();
			i++;
		}
		outText.set(String.valueOf(i));
		context.write(key, outText);
		

		
	}
	
	
}
