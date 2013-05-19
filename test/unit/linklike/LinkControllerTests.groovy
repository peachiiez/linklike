package linklike



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LinkController)
@Mock([Link])
class LinkControllerTests {

	
    void testAddVote(){
    	mockDomain(Link,
				   [ [title: 'link1', url: 'link1.com'],
				     [title: 'link2', url: 'link2.com'],
				     [title: 'link3', url: 'link3.com'] ])
		
		def page = controller.index()
		assert page.links.size() == 3

		assert Link.get(1).votecnt == 0
		assert Link.get(2).votecnt == 0
		assert Link.get(3).votecnt == 0

		controller.addvote('link1')
		assert Link.get(1).votecnt == 1
		assert Link.get(2).votecnt == 0
		assert Link.get(3).votecnt == 0

		controller.addvote('link1')
		controller.addvote('link2')
		assert Link.get(1).votecnt == 2
		assert Link.get(2).votecnt == 1
		assert Link.get(3).votecnt == 0

		controller.addvote('link1')
		controller.addvote('link2')
		controller.addvote('link3')
		assert Link.get(1).votecnt == 3
		assert Link.get(2).votecnt == 2
		assert Link.get(3).votecnt == 1
    }

    void testUnVote(){
    	mockDomain(Link,
				   [ [title: 'link1', url: 'link1.com'],
				     [title: 'link2', url: 'link2.com'],
				     [title: 'link3', url: 'link3.com'] ])
		
		def page = controller.index()
		assert page.links.size() == 3

		controller.addvote('link1')
		controller.addvote('link1')
		controller.addvote('link2')
		controller.addvote('link2')
		controller.addvote('link3')
		controller.addvote('link3')
		controller.addvote('link3')

		controller.unvote('link1')
		assert Link.get(1).votecnt == 1

		controller.unvote('link2')
		assert Link.get(2).votecnt == 1

		controller.unvote('link2')
		assert Link.get(2).votecnt == 0

		controller.unvote('link3')
		assert Link.get(3).votecnt == 2
    }
}
