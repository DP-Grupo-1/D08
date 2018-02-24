
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CommentServiceTest extends AbstractTest {

	//Service under test--------------------------------------------------------------
	@Autowired
	private CommentService	commentService;


	//Tests------------------------------------------------------------------------
	@Test
	public void testDelete() {
		super.authenticate("admin");
		final List<Comment> comments = (List<Comment>) this.commentService.findAll();
		final Comment comment = comments.get(0);
		System.out.println(comments);
		this.commentService.quitarCommentReply(comment, 95, 103);

		this.commentService.delete(comment);

		System.out.println(comments);
		Assert.isTrue(!this.commentService.findAll().contains(comment));
		super.authenticate(null);
	}
	@Test
	public void testFindAll() {
		final List<Comment> comments = (List<Comment>) this.commentService.findAll();
		final Comment comment = comments.get(0);
		System.out.println(comment.getText());

	}
}
