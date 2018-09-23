import database.Query
import domain.OrderItem

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

class AddOrderItems(query: Query) {
  def add: Future[Seq[OrderItem]] = {
    val newOrderItems = Seq(
      OrderItem(productId = 1, quantity = 12, orderId = 1),
      OrderItem(productId = 2, quantity = 32, orderId = 2),
      OrderItem(productId = 3, quantity = 5, orderId = 3),
      OrderItem(productId = 1, quantity = 6, orderId = 3),
      OrderItem(productId = 2, quantity = 6, orderId = 3),
      OrderItem(productId = 3, quantity = 6, orderId = 3),
      OrderItem(productId = 1, quantity = 6, orderId = 3),
      OrderItem(productId = 1, quantity = 12, orderId = 1),
      OrderItem(productId = 2, quantity = 32, orderId = 2),
      OrderItem(productId = 3, quantity = 5, orderId = 3),
      OrderItem(productId = 1, quantity = 6, orderId = 3),
      OrderItem(productId = 2, quantity = 6, orderId = 3),
      OrderItem(productId = 3, quantity = 6, orderId = 3),
      OrderItem(productId = 1, quantity = 6, orderId = 3)
    )
    val futures: Seq[Future[OrderItem]] = newOrderItems.map(query.addOrderItem)
    val sequence: Future[Seq[OrderItem]] = Future.sequence(futures)
    sequence.andThen {
      case Success(_) => println("Adding complete")
    }
  }
}
