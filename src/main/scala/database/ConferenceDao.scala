package database

import domain._
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ConferenceDao(database: Database) extends DatabaseSchema {

  def productsWithCategories: Future[Seq[(ProductWithCategory)]] = {
  val query = for {
  (t, r) <- products join categories on (_.categoryId === _.id)
} yield (t, r)

  query.result.statements.foreach(println)
  database.run(query.result).map(_.map(ProductWithCategory.tupled))
}

}
