import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object Order extends Properties("Order") {

  property("reflexive") = forAll {
    (a: Boolean, b: Boolean) => (a <= a) == true
  }

  property("transitive") = forAll {
    (a: Boolean, b: Boolean, c: Boolean) => if ((a <= b) && (b <= c)) a <= c else true
  }
  property("antisymmetri") = forAll {
    (a: Boolean, b: Boolean) => if ((a <= b) && (b <= a)) b == a else true
  }
  property("strong") = forAll {
    (a: Boolean, b: Boolean) => (a <= b) || (b <= a)
  }
}