package MusicGame;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScoreTest {

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
	public void testGreat() {
		Score score=new Score();
		score.great();
		assertEquals(1, score.getGreat());
		assertEquals(0, score.getNice());
		assertEquals(0, score.getBad());
		assertEquals(0, score.getMiss());
		assertEquals(4, score.getScore());
	}
	
	@Test
	public void testNice() {
		Score score=new Score();
		score.nice();
		assertEquals(0, score.getGreat());
		assertEquals(1, score.getNice());
		assertEquals(0, score.getBad());
		assertEquals(0, score.getMiss());
		assertEquals(2, score.getScore());
	}
	
	@Test
	public void testBad() {
		Score score=new Score();
		score.bad();
		assertEquals(0, score.getGreat());
		assertEquals(0, score.getNice());
		assertEquals(1, score.getBad());
		assertEquals(0, score.getMiss());
		assertEquals(0, score.getScore());
	}
	@Test
	public void testMiss() {
		Score score=new Score();
		score.miss();
		assertEquals(0, score.getGreat());
		assertEquals(0, score.getNice());
		assertEquals(0, score.getBad());
		assertEquals(1, score.getMiss());
		assertEquals(-2, score.getScore());
	}
	
	@Test
	public void testReset() {
		Score score=new Score();
		score.setScore(1);
		score.setGreat(1);
		score.setBad(1);
		score.setCombo(1);
		score.setMiss(1);
		score.reset();
		assertEquals(0, score.getGreat());
		assertEquals(0, score.getNice());
		assertEquals(0, score.getBad());
		assertEquals(0, score.getMiss());
		assertEquals(0, score.getScore());
	}

	@Test
	public void testRecord() throws IOException {
		Score score=new Score();	
		assertTrue(score.record());
	}
}
