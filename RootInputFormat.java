package edu.tamu.hadoop;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import edu.tamu.hadoop.RootRecordReader;

public class RootInputFormat extends FileInputFormat<IntWritable,Text> {
    @Override
    public RecordReader<IntWritable,Text> createRecordReader(InputSplit inputSplit,
							     TaskAttemptContext taskAttemptContext) 
    throws IOException, InterruptedException {
	return new RootRecordReader();
    }
    
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
	return false;
    }
}
