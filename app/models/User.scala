package models;

import play.db.anorm._;
import play.db.anorm.SqlParser._;
import play.db.anorm.defaults._;
import java.util.{Date};

//User
//Case classe implements many methods automatic, such as getters/setters, tostring, equals, hashcode

case class User (
	id : Pk[Long] , 
	email : String , 
	fullname : String ,
	password : String ,
	salt : String ,
	createdAt : Date ,
	deactivated : Boolean ,
	deactivatedAt : Date	
){
  
  def secureGroups() = {
    User.listSecureGroups( this.id() )
  }//end method
  
}//end klazz

//Object Companion - Thinking in java this element represents static class and methods.
//In Scala concept this is a singleton class/object/pattern!!!

object User extends Magic[User] {
  
	def connect( email: String , password: String ) = {
	  User.find("email = {email} and password={password}")
	  .on("email" -> email , "password" -> password )
	  .first()
	}//end method
	
	
	def listSecureGroups( userId : Long ) = {
		UserSecureGroup.listSecureGroupsByUser(userId)
	}//end method
	
  
}//end object user
		