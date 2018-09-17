package database

import domain._
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

trait InitialData {

  self: DatabaseSchema =>

  def database: Database

  def insertInitialData(): Future[Unit] = {
    val queries = DBIO.seq(
      categories.delete, products.delete, users.delete, orders.delete, orderItems.delete,
      categories += Category(1, "Vegetables"),
      categories += Category(2, "Fruits"),
      categories += Category(3, "Electronics"),

      products ++= Seq(
        Product(1, "Tomatoes", 1.99f, 430, 1),
        Product(2, "Apples", 0.99f, 330, 2),
        Product(3, "TV", 2221.77f, 30, 3)
      ),

      users ++= Seq(
        User(1, "Pawel", "San Francisco", "pawel@mw.com", "123444567"),
        User(1, "Tom", "Edynburg", "tom@mw.com", "999837460"),
        User(1, "Daniel", "Glasgow", "daniel@mw.com", "152649604")
      ),

      orders ++= Seq(
        Order(id = 1, userId = 1),
        Order(id = 2, userId = 2),
        Order(id = 3, userId = 3)
      ),

      orderItems ++= Seq(
        OrderItem(1, 1, 12, 1),
        OrderItem(2, 2, 32, 2),
        OrderItem(3, 3, 5, 3),
        OrderItem(4, 2, 6, 3)
      ),

    )
    database.run(queries)
  }
}
