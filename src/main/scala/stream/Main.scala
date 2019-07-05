package stream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.{Done, NotUsed}

import scala.concurrent.Future

object Main extends App {

  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, NotUsed] = Source(1 to 100)
//  source.runForeach(i => println(i))(materializer)
  val done: Future[Done] = source.runForeach(i => println(i))(materializer)

  implicit val ec = system.dispatcher
  done.onComplete(_ => system.terminate())

}
