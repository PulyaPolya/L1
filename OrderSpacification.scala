import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object OrderSpecification extends Properties("Order") {

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
  }

}