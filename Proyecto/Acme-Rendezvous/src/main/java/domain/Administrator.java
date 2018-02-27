
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
//@Table(uniqueConstraints = {
//	@UniqueConstraint(columnNames = {
//		"userAccount_id"
//	})
//})
public class Administrator extends Actor {

}
