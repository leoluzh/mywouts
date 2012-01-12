package models;

import play.db.anorm._;
import play.db.anorm.SqlParser._;
//import Magic
import play.db.anorm.defaults._;
import java.util.{Date};

//Group

case class SecureGroup(
    id: Pk[Long] ,
    name : String ,
    createdAt : Date ,
    deactivated : Boolean ,
    deactivatedAt : Date
){
  
  def users() = {
    SecureGroup.listUsers( this.id() )
  }//end method
  
}//end klazz


object SecureGroup extends Magic[SecureGroup]{
  
	def findByName( name : String ) = {
		SecureGroup.find("name={name}").on("name"->name).first();
	}//end method
  
	def listUsers( secureGroupId : Long ) = {
		UserSecureGroup.listUsersBySecureGroup(secureGroupId); 
	}//end method
	
}//end object