package org.cevher.spark

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

import com.mongodb.spark._

object KafkaFlameConsumer {

  private val EXTRA_CLASS_PATH = "spark.executor.extraClassPath"
  private val CURRENT_DIRECTORY = "./"
  private val SERVER_ADDRESS = "localhost"
  private val SERVER_PORT = "9042"

  def main(args: Array[String]): Unit = {

    val sparkConfig = new SparkConf()
      .setAppName("my_test")
      .set("spark.cassandra.connection.host", SERVER_ADDRESS)
      .set("spark.cassandra.connection.port", SERVER_PORT)
      .set("spark.mongodb.input.uri", "mongodb://127.0.0.1/test.flowinformation")
      .set("spark.mongodb.output.uri", "mongodb://127.0.0.1/test.flowinformation")
      .set(EXTRA_CLASS_PATH, CURRENT_DIRECTORY)

    val spark = SparkSession.builder
      .config(sparkConfig)
      .getOrCreate()

    val streamingContext = new StreamingContext(spark.sparkContext, Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "spark_test_group3"
    )

    val topics = Array("hbys-flame")
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    //stream.map(record => record.value()).print(1)

    //    val table1: DataFrame = spark.read.format("org.apache.spark.sql.cassandra")
    //      .options(Map("table" -> "flowinformation", "keyspace" -> "hbys_log"))
    //      .load()

    stream.foreachRDD { rdd: RDD[ConsumerRecord[String, String]] =>

      val incomingRDD: RDD[IncomingJava] = rdd.map(z => JsonParserJava.parseJson(z.value()))
      val ds: DataFrame = spark.createDataFrame(incomingRDD, classOf[IncomingJava])
      val df: DataFrame = ds.select(
        "startTime"
        , "applicationInstanceIp"
        , "applicationVersion"
        , "applicationInstanceName"
        , "endTime"
        , "url"
        , "requestType"
        , "className"
        , "methodName"
        , "userLoginName"
        , "userSessionId"
        , "exitCode"
        , "exceptionType"
        , "exceptionMessage"
        , "exceptionStackTrace"
        , "externalData"
      )
      val dt: DataFrame = df
        .withColumnRenamed("startTime", "starttime")
        .withColumnRenamed("applicationInstanceIp", "applicationinstanceip")
        .withColumnRenamed("applicationVersion", "applicationversion")
        .withColumnRenamed("applicationInstanceName", "applicationinstancename")
        .withColumnRenamed("endTime", "endtime")
        .withColumnRenamed("url", "url")
        .withColumnRenamed("requestType", "requesttype")
        .withColumnRenamed("className", "classname")
        .withColumnRenamed("methodName", "methodname")
        .withColumnRenamed("userLoginName", "userloginname")
        .withColumnRenamed("userSessionId", "usersessionid")
        .withColumnRenamed("exitCode", "exitcode")
        .withColumnRenamed("exceptionType", "exceptiontype")
        .withColumnRenamed("exceptionMessage", "exceptionmessage")
        .withColumnRenamed("exceptionStackTrace", "exceptionstacktrace")
        .withColumnRenamed("externalData", "externaldata")

      //      val df1 = spark.sqlContext
      //        .read
      //        .format("org.apache.spark.sql.cassandra")
      //        .options(Map("table" -> "flowinformation", "keyspace" -> "hbys_log")).load.cache()

      dt.write
        .format("org.apache.spark.sql.cassandra")
        .options(Map("table" -> "flowinformation", "keyspace" -> "hbys_log"))
        .mode("append")
        .save()


      df.write
        .format("mongo")
        .mode("append")
        .option("database", "test")
        .option("collection", "flowinformation")
        .save()

    }
    streamingContext.start()
    streamingContext.awaitTermination()

  }

}
