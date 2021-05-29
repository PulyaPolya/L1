import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Prop}
import scala.language.implicitConversions

object L2 {
  trait Order[A] {
    def lteq(a: A, b: A): Boolean
  }

  object Order {
    implicit val booleanOrder = new Order[Boolean] {
      override def lteq(a: Boolean, b: Boolean): Boolean = if (b==true) b else a

    }
  }

  class OrderSyntax[A](a: A)(implicit H: Order[A]) {
    def <=(b: A): Boolean = H.lteq(a, b)

  }

  implicit def hs[A:  Order](a: A) = new OrderSyntax(a)

  def main(args: Array[String]): Unit = {
    val reflexive = forAll{
      (a:Boolean, b:Boolean)=> (a<=a) == true
    }
    val transitive= forAll{
      (a:Boolean,b:Boolean,c:Boolean)=>if((a<=b)&&(b<=c)) a<=c else true
    }
    val antisymmetric = forAll{
      (a:Boolean, b:Boolean)=>if ((a<=b)&&(b<=a)) b==a else true
    }
    val strong = forAll{
      (a:Boolean, b:Boolean)=>(a<=b)||(b<=a)
    }
    reflexive.check()
    antisymmetric.check()
    transitive.check()
    strong.check()

  }


}
