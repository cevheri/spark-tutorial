package org.cevher.spark

/**
 * Scala consumer with spark from Kafka Incoming Data detail json in or out param
 */
case class IncomingScalaParams(
                                name: String,
                                paramType: String,
                                paramValue: String,
                                inOut: String
                              )
