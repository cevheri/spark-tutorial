package org.cevher.spark.scala

import org.apache.spark.sql.{DataFrame, SparkSession}

object FirstScala {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder.appName("csv-scala-demo").getOrCreate
    val data: DataFrame = spark.read.csv("file:///home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv")

    data.printSchema()
    // option("header", "true").

    data.head(10).foreach(f => println(f))

    println("")
    println("cevheri:" + data.count())
    println("")

//    data
//      .groupBy("INCIDENT_DATETIME")
//      .count()
    //.withColumnRenamed("count", "n")
    //.filter("n >= 2")

  }
}
