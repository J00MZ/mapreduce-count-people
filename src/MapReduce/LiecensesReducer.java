package MapReduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class LiecensesReducer extends Reducer<Text, Text, Text, Text>  {
	private Text outText= new Text();
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)throws IOException, InterruptedException{
		Iterator<Text> it=values.iterator();
		Iterator<Text> it2=values.iterator();
		String res = isExistMoreThanOne(it,it2);
		if(res.compareTo("")!=0 ){
			outText.set(res);
			context.write(key, outText);
		}
	}
	private String isExistMoreThanOne(	Iterator<Text>  it1,	Iterator<Text> it2){
		 int i=0;
		int j=0;
		while (it1.hasNext())
		{
		  i++;
		  String val =  it1.next().toString();
		  while (it2.hasNext())
			{
			  j++;
			  String val2 =  it2.next().toString();
			  if(j != i && val.equals(val2) ){
				  return val;
			  }
			}
		}
		return "";
	}
}
