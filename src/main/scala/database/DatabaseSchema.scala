package database

import java.sql.Timestamp
import java.time._

import domain._
import slick.jdbc.H2Profile.api._

trait DatabaseSchema {

  class Categories(tag: Tag) extends Table[Category](tag, "category") {
    def id = column[Int]("id", O.PrimaryKey)

    def name = column[String]("name")

    def * = (id, name) <> (Category.tupled, Category.unapply)
  }

  val categories = TableQuery[Categories]

  class Products(tag: Tag) extends Table[Product](tag, "product") {
    def id = column[Int]("id", O.PrimaryKey)

    def name = column[String]("name")

    def price = column[Float]("price")

    def quantity = column[Int]("quantity")

    def categoryId = column[Int]("category_id")

    def category = foreignKey("fk_category", categoryId, categories)(_.id)

    def * = (id, name, price, quantity, categoryId) <> (Product.tupled, Product.unapply)
  }

  val products = TableQuery[Products]

  class Users(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Int]("id", O.PrimaryKey)

    def name = column[String]("name")

    def address = column[String]("address")

    def email = column[String]("email")

    def phone = column[String]("phone")

    def * = (id, name, address, email, phone) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[Users]

  implicit val localDateTimeMapping = MappedColumnType.base[LocalDateTime, Timestamp](
    localDateTime => Timestamp.from(localDateTime.toInstant(ZoneOffset.UTC)), _.toLocalDateTime)

  class Orders(tag: Tag) extends Table[Order](tag, "order") {
    def id = column[Int]("id", O.PrimaryKey)

    def date = column[LocalDateTime]("date")

    def userId = column[Int]("user_id")

    def user = foreignKey("fk_user", userId, users)(_.id)

    def * = (id, date, userId) <> (Order.tupled, Order.unapply)
  }

  val orders = TableQuery[Orders]

  class OrderItems(tag: Tag) extends Table[OrderItem](tag, "order_item") {
    def id = column[Int]("id", O.PrimaryKey)

    def productId = column[Int] {
      "product_id"
    }

    def quantity = column[Int]("quantity")

    def orderId = column[Int] {
      "order_id"
    }

    def product = foreignKey("fk_product", productId, products)(_.id)

    def order = foreignKey("fk_order", orderId, orders)(_.id)

    def * = (id, productId, quantity, orderId) <> (OrderItem.tupled, OrderItem.unapply)
  }

  val orderItems = TableQuery[OrderItems]

  val allSchemas = categories.schema ++ products.schema ++ users.schema ++ orders.schema ++ orderItems.schema
}