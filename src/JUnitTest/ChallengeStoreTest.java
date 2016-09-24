package JUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MusicGame.ConnectSQL;

public class ChallengeStoreTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetTable() throws SQLException {
		assertFalse(new ConnectSQL().setTable());
	}
	@Test
	public void testGetChallenge() throws SQLException {
		assertNotNull(new ConnectSQL().getChallenge("weiweima"));
	}
	@Test
	public void testAdd() throws SQLException {
		assertEquals(1, new ConnectSQL().add("1", "2", "3"));
	}

}
