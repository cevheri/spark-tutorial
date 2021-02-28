package org.cevher.spark

import org.apache.spark.sql.{DataFrame, SparkSession}

//noinspection DuplicatedCode
object FirstScala {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder.appName("csv-scala-demo").getOrCreate
    val data: DataFrame = spark.read.option("header", "true").csv("file:///home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv")

    println("Schema Information : ")
    data.printSchema()
    println("Print 10 record")
    data.head(10).foreach(f => println(f))

    println("Data Count : " + data.count())
    println("")


  }
}
