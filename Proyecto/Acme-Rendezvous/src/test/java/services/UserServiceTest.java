
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class UserServiceTest {

	//Service under test--------------------------------------------------
	@Autowired
	private UserService	userService;


	//Tests----------------------------------------------------------

	@Test
	public void testCreate() {
		final User user = this.userService.create();
		Assert.isNull(user.getName());
		Assert.isNull(user.getSurname());
		Assert.isNull(user.getPostalAddress());
		Assert.isNull(user.getPhoneNumber());
		Assert.isNull(user.getEmail());
		Assert.notNull(user.getUserAccount());

	}
}
