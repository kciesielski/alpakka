/*
 * Copyright (C) 2016-2017 Lightbend Inc. <http://www.lightbend.com>
 */
package akka.stream.alpakka.sqs.javadsl

import akka.Done
import akka.stream.alpakka.sqs.{MessageActionPair, SqsAckSinkSettings, SqsAckSinkStage}
import akka.stream.javadsl.Sink
import com.amazonaws.services.sqs.AmazonSQSAsync

import scala.concurrent.Future

object SqsAckSink {

  /**
   * Java API: creates a [[SqsAckSinkStage]] for a SQS queue using an [[com.amazonaws.services.sqs.AmazonSQSAsync]]
   */
  def create(queueUrl: String,
             settings: SqsAckSinkSettings,
             sqsClient: AmazonSQSAsync): Sink[MessageActionPair, Future[Done]] =
    Sink.fromGraph(new SqsAckSinkStage(queueUrl, settings, sqsClient))

  /**
   * Java API: creates a [[SqsAckSinkStage]] for a SQS queue using an [[com.amazonaws.services.sqs.AmazonSQSAsync]] with default settings.
   */
  def create(queueUrl: String, sqsClient: AmazonSQSAsync): Sink[MessageActionPair, Future[Done]] =
    Sink.fromGraph(new SqsAckSinkStage(queueUrl, SqsAckSinkSettings.Defaults, sqsClient))
}
