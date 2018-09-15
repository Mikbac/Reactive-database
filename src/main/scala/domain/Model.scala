package domain

import java.time.LocalDateTime

case class Category(id: Int, name:String)

case class Product(id: Int, name: String, price: Float, quantity: Int, categoryId: Int)

case class User(id: Int, name: String, address: String, email: String, phone: String)

case class Order(id: Int, date: LocalDateTime = LocalDateTime.now(), userId: Int)

case class OrderItem(id: Int, productId: Int, quantity: Int, orderId: Int)

case class ProductWithCategory(id: Int, product: Product, category: Category)

case class OrderItemsWithProduct(id: Int, orderItems: OrderItem, product: Product)

case class OrderItemWithOrder(id: Int, orderItems: OrderItem, order: Order)

case class OrderWithUser(id: Int, order: Order, items: OrderItem)