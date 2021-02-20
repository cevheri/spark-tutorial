package org.cevher.spark.scala.groupby

import org.apache.spark.sql.{DataFrame, SparkSession}

//noinspection DuplicatedCode
object GroupByExample {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder.appName("csv-scala-demo").getOrCreate
    val df: DataFrame = spark.read.option("header", "true").csv("file:///home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv")
    import spark.implicits._

    df.select("Year of Birth").rdd.map(x => (x, 1)).groupByKey().map(t => (t._1, t._2.sum)).collect().foreach(println)

  }

}
