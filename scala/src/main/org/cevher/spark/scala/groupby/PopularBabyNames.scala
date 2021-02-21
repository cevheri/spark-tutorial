package org.cevher.spark.scala.groupby

import org.apache.spark.sql.{DataFrame, SparkSession}

//noinspection DuplicatedCode
object PopularBabyNames {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder.appName("csv-scala-demo").getOrCreate
    val df: DataFrame = spark.read.option("header", "true").csv("file:///home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv")

    println("== printSchema ==")
    df.printSchema
    // root
    // |-- Year of Birth: string (nullable = true)
    // |-- Gender: string (nullable = true)
    // |-- Ethnicity: string (nullable = true)
    // |-- Child's First Name: string (nullable = true)
    // |-- Count: string (nullable = true)
    // |-- Rank: string (nullable = true)

    println("== count() ==")
    val a = df.groupBy("Year of Birth","Gender").count()
    a.show()
    //+-------------+------+-----+
    //|Year of Birth|Gender|count|
    //+-------------+------+-----+
    //|2015         |MALE  |1001 |
    //|2017         |FEMALE|1012 |
    //|2011         |FEMALE|3001 |
    //|2014         |MALE  |2838 |
    //|2013         |MALE  |2855 |
    //|2012         |MALE  |2859 |
    //|2016         |MALE  |1016 |
    //|2012         |FEMALE|3000 |
    //|2015         |FEMALE|1044 |
    //|2016         |FEMALE|1047 |
    //|2013         |FEMALE|2941 |
    //|2017         |MALE  |961  |
    //|2011         |MALE  |2862 |
    //|2014         |FEMALE|3027 |
    //+-------------+------+-----+

  }

}
