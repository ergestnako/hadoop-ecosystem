package com.apn.spark;

import static spark.Spark.get;

public class Main {
	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello World");
	}
}
