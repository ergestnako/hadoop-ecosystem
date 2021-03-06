package org.apn.hadoop.mapreduce.smsstatus;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SmsDriver {

	public static void main(String[] args) throws Exception {

		Job job = Job.getInstance(new Configuration(), "SMS Status");
		job.setJarByClass(SmsDriver.class);

		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, UserFileMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, DeliveryFileMapper.class);

		job.setReducerClass(SmsReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}