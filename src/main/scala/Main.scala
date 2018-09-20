import database.{DatabaseSchema, InitialData, Queries}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App with DatabaseSchema with InitialData with Support {

  val database = Database.forConfig("conf")

  private val future = createSchemaIfNotExists.flatMap(_ => insertInitialData())
  Await.ready(future, Duration.Inf)

  private val query = new Queries(database)
  printResults(query.productsWithCategories)
  printResults(query.orderItemsWithProducts)
  printResults(query.orderItemsWithOrders)
  printResults(query.ordersWithUsers)

}