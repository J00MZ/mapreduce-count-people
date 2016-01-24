package ExceptionLicenses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class LiecenseInputFormat extends FileInputFormat<Text,Text> {

	@Override
	public RecordReader<Text, Text> createRecordReader(InputSplit arg0,
			TaskAttemptContext arg1) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return new LiecenseRecordReader() ;
	}
private class LiecenseRecordReader extends RecordReader<Text, Text>{
		
		Text _currentKey; 
		Text _currentValue;
		Configuration _configuration; 
		FSDataInputStream _in;
		long _start,_end,_pos; 
		BufferedReader _reader; 
		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			_in.close(); 
		}

		@Override
		public Text getCurrentKey() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return _currentKey;
		}

		@Override
		public Text getCurrentValue() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return _currentValue;
		}

		@Override
		public float getProgress() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			if (_start == _end){
				return 1.0f;
			}
			return (_pos-_start)/(float)(_end-_start);
		}

		@Override
		public void initialize(InputSplit inputSplit, TaskAttemptContext context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			
			FileSplit fileSplit = (FileSplit)inputSplit;
			Path  file  =  fileSplit.getPath(); 
			_configuration = context.getConfiguration();
			FileSystem fs = file.getFileSystem(_configuration);
			_in = fs.open(file);
			_currentKey = new Text();
			_currentValue = new Text();
			_start = fileSplit.getStart(); 
			_end = _start + fileSplit.getLength();
			_pos = _start;
			_reader = new BufferedReader(new InputStreamReader(_in));
			
			
			
			
		}

		@Override
		public boolean nextKeyValue() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			boolean retVal = false;
			String line = _reader.readLine();
			if (line != null){
				retVal = true; 
				String []kv= line.split(",");
				_currentKey.set(kv[0]);
				_currentValue.set(kv[1]);
				_pos += _in.getPos();
				
			}
			return retVal; 
		}
		
	}

}
