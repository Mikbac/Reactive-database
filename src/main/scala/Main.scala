import database.{ConferenceDao, DatabaseSchema, InitialData}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App with DatabaseSchema with InitialData with Magic {

  val database = Database.forConfig("conf")

  private val future = createSchemaIfNotExists.flatMap(_ => insertInitialData())
  Await.ready(future, Duration.Inf)

  private val dao = new ConferenceDao(database)
  printResults(dao.productsWithCategories)

}