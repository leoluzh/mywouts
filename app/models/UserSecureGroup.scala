package models;

import play.db.anorm._;
import play.db.anorm.SqlParser._;
//import Magic
import play.db.anorm.defaults._;
import java.util.{Date};

case class UserSecureGroup ( 
    id: Pk[Long] ,
    userId : Long , 
    secureGroupId : Long );

object UserSecureGroup extends Magic[UserSecureGroup]{
  
	def listUsersBySecureGroup( secureGroupId : Long ) = {
		SQL(
		"""
			SELECT usr.* FROM 
		    User usr 
		    INNER JOIN UserSecureGroup ugs ON ugs.userId = usr.id 
			WHERE ugs.secureGroupId = {secureGroupId}
		""").on("secureGroupId"->secureGroupId)
		.as(User*);
	}//end method
	
	def listSecureGroupsByUser( userId : Long ) = {
		SQL(
		"""
		    SELECT sg.* FROM 
		    SecureGroup sg 
		    INNER JOIN UserSecureGroup usg ON usg.secureGroupId = sg.id 
		    WHERE usg.userId = {userId}
		""").on("userId"->userId)
		.as(SecureGroup*)
	}//end method
  
}//end object