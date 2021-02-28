package org.cevher.spark

import org.apache.spark.sql.{DataFrame, SparkSession}

//noinspection DuplicatedCode
object Example01 {

  def main(args: Array[String]): Unit = {

    // reference data : https://data.cityofnewyork.us/Health/Popular-Baby-Names/25th-nujf
    val spark: SparkSession = SparkSession.builder.appName("csv-scala-demo").getOrCreate
    val df: DataFrame = spark.read.option("header", "true").csv("file:///home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv")
    df.select("Year of Birth").rdd.map(x => (x, 1)).reduceByKey(_ + _).collect().foreach(println)

  }
}
