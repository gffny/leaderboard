/**
 * 
 */
package test.gffny.leaderboard.portal.controller;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.portal.controller.impl.CompetitionController;

/**
 * @author John Gaffney (john@gffny.com) Apr 23, 2013
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext*.xml" })
public class CompetitionControllerTest {

	@Autowired
	CompetitionController competitionController;

	MockHttpServletRequest requestMock;
	MockHttpServletResponse responseMock;
	AnnotationMethodHandlerAdapter handlerAdapter;
	ObjectMapper mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		requestMock = new MockHttpServletRequest();
		requestMock.setContentType(MediaType.APPLICATION_JSON_VALUE);
		requestMock.addHeader("Accept", MediaType.APPLICATION_JSON_VALUE);

		responseMock = new MockHttpServletResponse();

		handlerAdapter = new AnnotationMethodHandlerAdapter();
		HttpMessageConverter[] messageConverters = { new MappingJacksonHttpMessageConverter() };
		handlerAdapter.setMessageConverters(messageConverters);

		mapper = new ObjectMapper();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.portal.controller.impl.CompetitionController#competitionDefault()}
	 * .
	 */
	@Test
	public final void testCompetitionDefault() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.portal.controller.impl.CompetitionController#competitionDashboard()}
	 * .
	 */
	@Test
	public final void testCompetitionDashboard() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.portal.controller.impl.CompetitionController#createCompetitionDialog()}
	 * .
	 */
	@Test
	public final void testCreateCompetitionDialog() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.portal.controller.impl.CompetitionController#createCompetition(java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testCreateCompetition() throws Exception {
		requestMock.setMethod("POST");
		requestMock.setRequestURI("/competition/a_create");

		requestMock.setAttribute("competition", new Competition("blah", null,
				"private"));

		handlerAdapter.handle(requestMock, responseMock, competitionController);

		Competition actualCompetition = mapper.readValue(
				responseMock.getContentAsString(), Competition.class);
		System.out.println(actualCompetition.getName());
		Assert.assertEquals(actualCompetition.getName(), "blah");

	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.portal.controller.impl.CompetitionController#createCompetitionRound(java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)}
	 * .
	 */
	@Test
	public final void testCreateCompetitionRound() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.portal.controller.impl.CompetitionController#getCompetitionOptions(java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)}
	 * .
	 */
	@Test
	public final void testGetCompetitionOptions() {
		fail("Not yet implemented"); // TODO
	}

}
