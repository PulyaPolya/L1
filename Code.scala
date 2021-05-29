import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Prop}
import scala.language.implicitConversions

object L2 {
  trait Order[A] {
    def lteq(a: A, b: A): Boolean
    def imply(a: A, b: A): A
    def join(a: A, b: A): A}

  object Order {
    implicit val booleanOrder = new Order[Boolean] {
    override def lteq(a: Boolean, b: Boolean): Boolean = if (b==true) b else a 
    override def join(a: Boolean, b: Boolean): Boolean = if ((a==true)||(b== true)) true else false
       override def imply(a: Boolean, b: Boolean): Boolean = if(a== false ) true else if ((a==true)&&(b==true))true
      else false
    }
  }

   class OrderSyntax[A](a: A)(implicit H: Order[A]) {
    def <=(b: A): Boolean = H.lteq(a, b)
    def -->(b: A): A = H.imply(a, b)
     def ||(b: A): A = H.join(a, b)
  }

  implicit def hs[A:  Order](a: A) = new OrderSyntax(a)

    def main(args: Array[String]): Unit = {
      val reflexive = forAll{
        (a:Boolean, b:Boolean)=> (a<=a) == (true)
      }
      val transitive= forAll{
        (a:Boolean,b:Boolean,c:Boolean)=>((a<=b)&&(b<=c))--> (a<=c)
      }
      val antisymmetric = forAll{
        (a:Boolean, b:Boolean)=>((a<=b)&&(b<=a))--> (b=a)
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
