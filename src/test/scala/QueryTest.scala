
import _root_.database.{DatabaseSchema, InitialData, Query}
import org.junit.jupiter.api._
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

@Test
class QueryTest() extends DatabaseSchema with InitialData with Support {


  var database: Database = _

  @AfterEach
  def createDatabase: Unit = {
    val drop = dropSchema
    Await.ready(drop, Duration.Inf)
  }

  @BeforeEach
  def dropDatabase: Unit = {
    database = Database.forConfig("conf")
    val drop = dropSchema
    Await.ready(drop, Duration.Inf)

    val create = createSchema.flatMap(_ => insertInitialData())
    Await.ready(create, Duration.Inf)
  }

  @Test
  def relationsTest() = {
    val query = new Query(database)
    printResults(query.productsWithCategories)
    printResults(query.orderItemsWithProducts)
    printResults(query.orderItemsWithOrders)
    printResults(query.ordersWithUsers)
  }

  @Test
  def finalAllTest() = {
    val query = new Query(database)
    printResults(query.finalAllCategories)
    printResults(query.finalAllProducts)
    printResults(query.finalAllUsers)
    printResults(query.finalAllOrders)
    printResults(query.finalAllOrderItems)
  }

  @Test
  def countTest() = {
    val query = new Query(database)
    printResults(query.countCategoriesByProducts)
  }

}
