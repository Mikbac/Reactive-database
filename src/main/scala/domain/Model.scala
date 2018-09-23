package domain

import java.time.LocalDateTime

case class Category(id: Int, name: String)

case class Product(id: Int, name: String, price: Float, quantity: Int, categoryId: Int)

case class User(id: Int, name: String, address: String, email: String, phone: String)

case class Order(id: Int, date: LocalDateTime = LocalDateTime.now(), userId: Int, status: Boolean)

case class OrderItem(id: Option[Int] = None, productId: Int, quantity: Int, orderId: Int)

case class ProductWithCategory(product: Product, category: Category)

case class OrderItemsWithProduct(orderItems: OrderItem, product: Product)

case class OrderItemWithOrder(orderItems: OrderItem, order: Order)

case class OrderWithUser(order: Order, user: User)