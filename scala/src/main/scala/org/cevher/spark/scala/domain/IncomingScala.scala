package org.cevher.spark.scala.domain

/**
 * Scala consumer with spark from Kafka Incoming Data detail json
 */
case class IncomingScala(
                          applicationName: String,
                          applicationVersion: String,
                          applicationInstanceName: String,
                          applicationInstanceIp: String,
                          startTime: String,
                          endTime: String,
                          url: String,
                          requestType: String,
                          className: String,
                          methodName: String,
                          userLoginName: String,
                          userSessionId: String,
                          exitCode: String,
                          exceptionType: String,
                          exceptionMessage: String,
                          exceptionStackTrace: String,
                          externalData: String,
                          inParams: List[IncomingScalaParams],
                          outParams: String
                        )
