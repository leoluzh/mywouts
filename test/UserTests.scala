import play._;
import play.test._;

import org.scalatest._;
import org.scalatest.junit._;
import org.scalatest.matchers._;

import models._;
import play.db.anorm._;

import java.util.{Date};

class UserTests extends UnitFlatSpec with ShouldMatchers with BeforeAndAfterEach{

	override def beforeEach(){
	  Fixtures.deleteDatabase();
	}//end method
  
	it should "create and retrieve a user" in{
		User.create( 
				User( NotAssigned , 
				      "foo@gmail.com" ,
				      "foo" ,
				      "1234",
				      "1234",
				      new Date() ,
				      false ,
				      new Date())) ;
		val user = User.find("email={email}").on("email"->"foo@gmail.com").first() ;
		user should not be (None)
		user.get.fullname should be ("foo")
		
	}//end method
	
	it should "connect a user" in {
	  
	  User.create(
		User( NotAssigned , 
		      "bar@gmail.com", 
		      "bar",
		      "1234",
		      "1234",
		      new Date() , 
		      false , 
		      new Date() ));	  
	  
	  User.connect("bar@gmail.com","1234") should not be (None)
	  User.connect("bar@gmail.com","2345") should be (None)
	  User.connect("foobar@gmail.com","1234") should be (None)
	  
	}//end method

}//end UserTests