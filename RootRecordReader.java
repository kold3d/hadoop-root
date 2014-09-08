package edu.tamu.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class RootRecordReader extends RecordReader<IntWritable,Text> {
    private boolean sentName = false;
    private Text fileName = new Text();
    private IntWritable key = new IntWritable();
    
    @Override
    public void initialize(InputSplit genericSplit, TaskAttemptContext taskAttempContext) throws IOException {
	FileSplit fileSplit = (FileSplit)genericSplit;
	fileName.set(fileSplit.getPath().toUri().toString());
	key.set(1);
    }

    @Override
    public boolean nextKeyValue() throws IOException {
	return !sentName;
    }

    @Override
    public IntWritable getCurrentKey() {
	return key;
    }

    @Override
    public Text getCurrentValue() {
	sentName = true;
	return fileName;
    }

    @Override
    public float getProgress() throws IOException {
	float progress = 0;
	if(sentName) progress = 1;
	return progress;
    }

    @Override
    public void close() throws IOException {
    }
}
