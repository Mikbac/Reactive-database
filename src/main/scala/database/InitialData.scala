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

      categories ++= Seq(
        Category(1, "Vegetables"),
        Category(2, "Fruits"),
        Category(3, "Electronics"),
        Category(4, "Books")
      ),

      products ++= Seq(
        Product(1, "Tomatoes", 1.99f, 430, 1),
        Product(2, "Apples", 0.99f, 330, 2),
        Product(3, "TV", 2221.77f, 30, 3),
        Product(4, "Smartband", 799.75f, 50, 3),
        Product(5, "Potatoes", 799.75f, 50, 1),
        Product(6, "Onions", 799.75f, 50, 1),
        Product(7, "Cucumbers", 799.75f, 50, 1),
        Product(8, "Bananas", 799.75f, 50, 2),
        Product(9, "Oranges", 799.75f, 50, 2),
        Product(10, "Carrots", 799.75f, 50, 1)
      ),

      users ++= Seq(
        User(1, "Pawel", "San Francisco", "pawel@mw.com", "123444567"),
        User(2, "Tom", "Edynburg", "tom@mw.com", "999837460"),
        User(3, "Daniel", "Glasgow", "daniel@mw.com", "152649604")
      ),

      orders ++= Seq(
        Order(id = 1, userId = 1),
        Order(id = 2, userId = 2),
        Order(id = 3, userId = 3),
        Order(id = 4, userId = 1),
        Order(id = 5, userId = 3)
      ),

      orderItems ++= Seq(
        OrderItem(1, 1, 12, 1),
        OrderItem(2, 2, 32, 2),
        OrderItem(3, 3, 5, 3),
        OrderItem(4, 1, 6, 3),
        OrderItem(5, 2, 6, 3),
        OrderItem(6, 3, 6, 3),
        OrderItem(7, 1, 6, 3)
      )

    )
    database.run(queries)
  }
}
