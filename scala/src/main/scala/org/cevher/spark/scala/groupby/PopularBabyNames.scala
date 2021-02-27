package org.cevher.spark.scala.groupby

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StringType}
import org.apache.spark.sql.{DataFrame, SparkSession}

//noinspection DuplicatedCode
object PopularBabyNames {

  def main(args: Array[String]): Unit = {

    // reference data : https://data.cityofnewyork.us/Health/Popular-Baby-Names/25th-nujf
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
    val a = df.groupBy("Year of Birth", "Gender").count()
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

    // important !!!!!!!!!!!!!!!!!!!!!!!!!!1
    println("== withColumn() ==")
    val b = df.withColumn("Year of Birth", col("Year of Birth").cast(IntegerType))
      .withColumn("Gender", col("Gender").cast(StringType))
      .withColumn("Count", col("Count").cast(IntegerType))

    println("== printSchema ==")
    b.printSchema
    //    root
    //    |-- Year of Birth: integer (nullable = true)
    //    |-- Gender: string (nullable = true)
    //    |-- Ethnicity: string (nullable = true)
    //    |-- Child's First Name: string (nullable = true)
    //    |-- Count: integer (nullable = true)
    //    |-- Rank: string (nullable = true)

    println("== sql sum org.cevher.spark.scala.groupby ==")
    val c = b.groupBy("Year of Birth", "Gender").sum("Count")
    c.show()
    //+-------------+------+----------+
    //|Year of Birth|Gender|sum(Count)|
    //+-------------+------+----------+
    //|         2016|  MALE|     38380|
    //|         2015|  MALE|     39008|
    //|         2017|  MALE|     36498|
    //|         2012|FEMALE|     88685|
    //|         2012|  MALE|    114707|
    //|         2015|FEMALE|     30592|
    //|         2011|  MALE|    113692|
    //|         2014|  MALE|    106749|
    //|         2017|FEMALE|     28897|
    //|         2014|FEMALE|     85524|
    //|         2013|FEMALE|     84835|
    //|         2016|FEMALE|     30360|
    //|         2011|FEMALE|     88213|
    //|         2013|  MALE|    108507|
    //+-------------+------+----------+


  }

}
