import play._;
import play.test._;

import org.scalatest._;
import org.scalatest.junit._;
import org.scalatest.matchers._;

import models._;
import play.db.anorm._;

import java.util.{Date};

class SecureGroupTests extends UnitFlatSpec with ShouldMatchers with BeforeAndAfterEach{

	override def beforeEach(){
	  Fixtures.deleteDatabase();
	}//end method
  
	it should "create and retrieve a secure group" in{
		SecureGroup.create( 
				SecureGroup( NotAssigned , 
				      "admin" ,
				      new Date() ,
				      false ,
				      new Date() ));
		val group = SecureGroup.find("name={name}").on("name"->"admin").first() ;
		group should not be (None)
		group.get.name should be ("admin")
		
	}//end method
	
}//end UserTests