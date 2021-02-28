package org.cevher.spark

import com.typesafe.config.ConfigFactory

object StreamingSettings {

  private val config = ConfigFactory.load()

  object SparkStreaming {
    private val spark = config.getConfig("streamer.spark")

    lazy val master = spark.getString("master")
    lazy val name = spark.getString("app_name")
  }

  object KafkaConsumer {
    private val producer = config.getConfig("streamer.kafka")

    lazy val interval = producer.getInt("batch_interval")
    lazy val kafkaTopic = producer.getString("topic")
    lazy val offset = producer.getString("offset")

    lazy val bootstrapServers = producer.getString("bootstrap_servers")
    lazy val keySerializer = producer.getString("key_serializer")
    lazy val valueSerializer = producer.getString("value_serializer")
    lazy val groupName = producer.getString("group_name")
  }

  object Cassandra {

    private val cassandra = config.getConfig("streamer.cassandra")

    lazy val format = cassandra.getString("format")
    lazy val host = cassandra.getString("host")
    lazy val port = cassandra.getString("port")

    lazy val user = cassandra.getString("user")
    lazy val pass = cassandra.getString("pass")
    lazy val keyspace = cassandra.getString("keyspace")
    lazy val table = cassandra.getString("table")

  }

}
