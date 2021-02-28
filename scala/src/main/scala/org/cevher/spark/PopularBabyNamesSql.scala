package org.cevher.spark

import org.apache.spark.sql.{DataFrame, SparkSession}

object PopularBabyNamesSql {

  def main(args: Array[String]): Unit = {

    // reference data : https://data.cityofnewyork.us/Health/Popular-Baby-Names/25th-nujf
    val spark: SparkSession = SparkSession.builder.appName("Popular_Baby_Names").getOrCreate
    //val df: DataFrame = spark.read.option("header", "true").csv("file:///home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv")
    val df: DataFrame = spark.read.option("header", "true").csv("file:///home/cevher/Downloads/Fire_Incident_Dispatch_Data.csv")
    val df1 = spark.read.textFile("/home/cevher/Downloads/Fire_Incident_Dispatch_Data.csv")

    // df register
    println("== df register == ")
    df.createTempView("df")

    println("== printSchema ==")
    df.printSchema
    // root
    // |-- Year of Birth: string (nullable = true)
    // |-- Gender: string (nullable = true)
    // |-- Ethnicity: string (nullable = true)
    // |-- Child's First Name: string (nullable = true)
    // |-- Count: string (nullable = true)
    // |-- Rank: string (nullable = true)

    println("== head(3) ==")
    df.head(3)
    // res10: Array[org.apache.spark.sql.Row] = Array(
    // [2011,FEMALE,HISPANIC,GERALDINE,13,75],
    // [2011,FEMALE,HISPANIC,GIA,      21,67],
    // [2011,FEMALE,HISPANIC,GIANNA,   49,42])

    println("== sql explain == Physical Plan ===========")
    spark.sql("select `Year of Birth`,  Gender, count(1) as num from df group by `Year of Birth`, Gender order by Gender").explain
    // == Physical Plan ==
    // *(3) Sort [Gender#17 ASC NULLS FIRST], true, 0
    // +- Exchange rangepartitioning(Gender#17 ASC NULLS FIRST, 200), true, [id=#184]
    //    +- *(2) HashAggregate(keys=[Year of Birth#16, Gender#17], functions=[count(1)])
    //       +- Exchange hashpartitioning(Year of Birth#16, Gender#17, 200), true, [id=#180]
    //          +- *(1) HashAggregate(keys=[Year of Birth#16, Gender#17], functions=[partial_count(1)])
    //             +- FileScan csv [Year of Birth#16,Gender#17] Batched: false, DataFilters: [], Format: CSV, Location: InMemoryFileIndex[file:/home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv], PartitionFilters: [], PushedFilters: [], ReadSchema: struct<Year of Birth:string,Gender:string>

    println("== Sql Select Count() ==")
    val b = spark.sql("select `Year of Birth`,  Gender, count(1) as num from df group by `Year of Birth`, Gender order by Gender")
    b.show()
    // +-------------+------+----+
    //|Year of Birth|Gender| num|
    //+-------------+------+----+
    //|         2013|FEMALE|2941|
    //|         2017|FEMALE|1012|
    //|         2012|FEMALE|3000|
    //|         2015|FEMALE|1044|
    //|         2016|FEMALE|1047|
    //|         2011|FEMALE|3001|
    //|         2014|FEMALE|3027|
    //|         2015|  MALE|1001|
    //|         2014|  MALE|2838|
    //|         2013|  MALE|2855|
    //|         2012|  MALE|2859|
    //|         2017|  MALE| 961|
    //|         2011|  MALE|2862|
    //|         2016|  MALE|1016|
    //+-------------+------+----+

    println("== Sql Select Sum() == Physical Plan == ")
    spark.sql("select `Year of Birth`,  Gender, sum(Count) as num from df group by `Year of Birth`, Gender order by `Year of Birth`, Gender").explain
    // == Physical Plan ==
    //*(3) Sort [Year of Birth#16 ASC NULLS FIRST, Gender#17 ASC NULLS FIRST], true, 0
    //+- Exchange rangepartitioning(Year of Birth#16 ASC NULLS FIRST, Gender#17 ASC NULLS FIRST, 200), true, [id=#268]
    //   +- *(2) HashAggregate(keys=[Year of Birth#16, Gender#17], functions=[sum(cast(Count#20 as double))])
    //      +- Exchange hashpartitioning(Year of Birth#16, Gender#17, 200), true, [id=#264]
    //         +- *(1) HashAggregate(keys=[Year of Birth#16, Gender#17], functions=[partial_sum(cast(Count#20 as double))])
    //            +- FileScan csv [Year of Birth#16,Gender#17,Count#20] Batched: false, DataFilters: [], Format: CSV, Location: InMemoryFileIndex[file:/home/cevher/projects/spark-tutorial/files/Popular_Baby_Names.csv], PartitionFilters: [], PushedFilters: [], ReadSchema: struct<Year of Birth:string,Gender:string,Count:string>

    println("== Sql Select Sum() ==")
    val c = spark.sql("select `Year of Birth`,  Gender, sum(Count) as num from df group by `Year of Birth`, Gender order by `Year of Birth`, Gender")
    c.show()
    // +-------------+------+--------+
    //|Year of Birth|Gender|     num|
    //+-------------+------+--------+
    //|         2011|FEMALE| 88213.0|
    //|         2011|  MALE|113692.0|
    //|         2012|FEMALE| 88685.0|
    //|         2012|  MALE|114707.0|
    //|         2013|FEMALE| 84835.0|
    //|         2013|  MALE|108507.0|
    //|         2014|FEMALE| 85524.0|
    //|         2014|  MALE|106749.0|
    //|         2015|FEMALE| 30592.0|
    //|         2015|  MALE| 39008.0|
    //|         2016|FEMALE| 30360.0|
    //|         2016|  MALE| 38380.0|
    //|         2017|FEMALE| 28897.0|
    //|         2017|  MALE| 36498.0|
    //+-------------+------+--------+

  }

}
