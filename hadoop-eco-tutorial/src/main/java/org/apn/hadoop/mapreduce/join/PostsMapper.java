package org.apn.hadoop.mapreduce.join;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apn.hadoop.mapreduce.commons.StringHandler;

public class PostsMapper extends Mapper<LongWritable, Text, Text, Text> {
	private static final String FILE_TAG = "PD~";

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		List<String> values = StringHandler.fromCommaSeparatedString(value.toString());
		String userId = StringUtils.trimToEmpty(values.get(0));
		String tagPost = MessageFormat.format("{0}{1}", FILE_TAG, StringUtils.trimToEmpty(values.get(1)));
		context.write(new Text(userId), new Text(tagPost));
	}
}
