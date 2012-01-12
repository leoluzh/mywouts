import play._;
import play.test._;

import org.scalatest._;
import org.scalatest.junit._;
import org.scalatest.matchers._;

import models._;
import play.db.anorm._;

import java.util.{Date};

class UserSecureGroupTests extends UnitFlatSpec with ShouldMatchers with BeforeAndAfterEach{

	override def beforeEach(){
	  Fixtures.deleteDatabase();
	}//end method
  
	it should "create and retrieve a user secure group" in{
	  
		User.create( 
		User( NotAssigned , 
			  "foobar@gmail.com" ,
			  "foobar" ,
			  "1234",
			  "1234",
			  new Date() ,
			  false ,
			  new Date())
		) ;
	  
		var user = User.connect("foobar@gmail.com","1234");
		
		user should not be (None)
		user.get.fullname should be ("foobar")
		
		SecureGroup.create( 
		SecureGroup( NotAssigned , 
				     "foobar_group" ,
				     new Date() ,
				     false ,
				     new Date() )
		);
		
		var secureGroup = SecureGroup.findByName("foobar_group");
		secureGroup.get should not be (None)
		secureGroup.get.name should be ("foobar_group")
		
		var userSecureGroup = UserSecureGroup.create(
		UserSecureGroup( NotAssigned , user.get.id() , secureGroup.get.id() )
		);
		
		UserSecureGroup.count().single() should be (1)
		
		var users = UserSecureGroup.listUsersBySecureGroup( secureGroup.get.id() );
		
		//users.length should not be (None)
		users.length should be (1)
		user = users.headOption
		user should not be (None)
		user.get.fullname should be ("foobar")
		
		var groups = UserSecureGroup.listSecureGroupsByUser( user.get.id() );
		
		//groups.length should not be (None)
		groups.length should be (1)
		var group = groups.headOption
		
		group should not be (None)
		group.get.name should be ("foobar_group")
		
		
	}//end method
	
}//end UserTests