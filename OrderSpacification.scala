import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object OrderSpecification extends Properties("Order") {

   property ("reflexive") = forAll{
        (a:Boolean, b:Boolean)=> (a<=a) == (true)
      }
      property ("transitive")= forAll{
        (a:Boolean,b:Boolean,c:Boolean)=>((a<=b)&&(b<=c))--> (a<=c)
      }
     property ("antisymmetric") = forAll{
        (a:Boolean, b:Boolean)=>((a<=b)&&(b<=a))--> (b=a)
      }
      property ("strong") = forAll{
        (a:Boolean, b:Boolean)=>(a<=b)||(b<=a)
      }
  }

}