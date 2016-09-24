package JUnitTest;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MusicGame.FileIn;
import MusicGame.FileOut;
import MusicGame.Score;

public class HistoryTest {

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
	public void testRecord() throws IOException {
		Score score=new Score();	
		assertTrue(score.record());
	}
	
	@Test
	public void testScoreFilein() throws IOException {	
		assertNotNull(new FileIn("test.saved.chart").readHistroy());
	}
	
	
	@Test
	public void testScoreFileOut() throws IOException {
		Score score =new Score();
		assertTrue(new FileOut("test.saved.chart").scoreFileout(score));
	}



}
