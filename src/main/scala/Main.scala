import database.{DatabaseSchema, InitialData, Query}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App with DatabaseSchema with InitialData with Support {

  val database = Database.forConfig("conf")

  private val drop = dropSchema
  Await.ready(drop, Duration.Inf)

  private val create = createSchema.flatMap(_ => insertInitialData())
  Await.ready(create, Duration.Inf)

  private val query = new Query(database)
  printResults(query.productsWithCategories)
  printResults(query.orderItemsWithProducts)
  printResults(query.orderItemsWithOrders)
  printResults(query.ordersWithUsers)

  printResults(query.finalAllCategories)
  printResults(query.finalAllProducts)
  printResults(query.finalAllUsers)
  printResults(query.finalAllOrders)
  printResults(query.finalAllOrderItems)

  printResults(query.countCategoriesByProducts)
}