import scala.language.implicitConversions

object L2 {
  trait Order[A] {
    def lteq(a: A, b: A): Boolean
  }

  object Order {
    implicit val booleanOrder = new Order[Boolean] {
      override def lteq(a: Boolean, b: Boolean): Boolean = if (b == true) b else a

    }
  }

  class OrderSyntax[A](a: A)(implicit H: Order[A]) {
    def <=(b: A): Boolean = H.lteq(a, b)

  }

  implicit def hs[A: Order](a: A) = new OrderSyntax(a)


}