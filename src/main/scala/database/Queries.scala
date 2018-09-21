package database

import domain._
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Queries(database: Database) extends DatabaseSchema {

  def productsWithCategories: Future[Seq[ProductWithCategory]] = {
    val query = for {
      (p, c) <- products join categories on (_.categoryId === _.id)
    } yield (p, c)

    query.result.statements.foreach(println)
    database.run(query.result).map(_.map(ProductWithCategory.tupled))
  }

  def orderItemsWithProducts: Future[Seq[OrderItemsWithProduct]] = {
    val query = for {
      (oi, p) <- orderItems join products on (_.productId === _.id)
    } yield (oi, p)

    query.result.statements.foreach(println)
    database.run(query.result).map(_.map(OrderItemsWithProduct.tupled))
  }

  def orderItemsWithOrders: Future[Seq[OrderItemWithOrder]] = {
    val query = for {
      (oi, o) <- orderItems join orders on (_.orderId === _.id)
    } yield (oi, o)

    query.result.statements.foreach(println)
    database.run(query.result).map(_.map(OrderItemWithOrder.tupled))
  }


  def ordersWithUsers: Future[Seq[OrderWithUser]] = {
    val query = for {
      (o, u) <- orders join users on (_.userId === _.id)
    } yield (o, u)

    query.result.statements.foreach(println)
    database.run(query.result).map(_.map(OrderWithUser.tupled))
  }

  def finalAllCategories: Future[Seq[Category]] = database.run(categories.result)

  def finalAllProducts: Future[Seq[Product]] = database.run(products.result)

  def finalAllUsers: Future[Seq[User]] = database.run(users.result)

  def finalAllOrders: Future[Seq[Order]] = database.run(orders.result)

  def finalAllOrderItems: Future[Seq[OrderItem]] = database.run(orderItems.result)

}
