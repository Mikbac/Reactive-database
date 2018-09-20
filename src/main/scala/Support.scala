import database.DatabaseSchema
import slick.jdbc.H2Profile.api._
import slick.jdbc.meta.MTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Success

trait Support {
  self: DatabaseSchema =>

  def database: Database

  def printResults[T](f: Future[Iterable[T]]): Unit = {
    Await.result(f, Duration.Inf).foreach(println)
    println()
  }


  def createSchemaIfNotExists: Future[Unit] = {
    database.run(MTable.getTables).flatMap(tables =>
      if (tables.isEmpty) {
        database.run(allSchemas.create).andThen {
          case Success(_) => println("Schema created\n")
        }
      } else {
        println("Schema already exists\n")
        Future.successful()
      }
    )
  }
}
