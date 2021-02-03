package kata.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kata.model.Caterory;
import kata.model.Roll;

public class ScoreServiceImplTest {
	
	private ScoreService scoreService;

    @Before
    public void setUp() throws Exception {
    	scoreService = new ScoreServiceImpl();
    }


	@Test
	public void testScoreChance() {
		int expected = 15;
		int actual = scoreService.score(new Roll(2, 3, 4, 5, 1), Caterory.CHANCE);
		assertEquals(expected, actual);
		assertEquals(16, scoreService.score(new Roll(3, 3, 4, 5, 1), Caterory.CHANCE));
	}

	@Test
	public void testScoreYatzy() {
		int expected = 50;
		int actual = scoreService.score(new Roll(4, 4, 4, 4, 4), Caterory.YATZY);
		assertEquals(expected, actual);
		assertEquals(50, scoreService.score(new Roll(6, 6, 6, 6, 6), Caterory.YATZY));
		assertEquals(0, scoreService.score(new Roll(6, 6, 6, 6, 3), Caterory.YATZY));
	}

	@Test
	public void tetsScoreOnes() {
		assertTrue(scoreService.score(new Roll(1, 2, 3, 4, 5), Caterory.ONES) == 1);
		assertEquals(2, scoreService.score(new Roll(1, 2, 1, 4, 5), Caterory.ONES));
		assertEquals(0, scoreService.score(new Roll(6, 2, 2, 4, 5), Caterory.ONES));
		assertEquals(4, scoreService.score(new Roll(1, 2, 1, 1, 1), Caterory.ONES));
	}

	@Test
	public void tetsScoreTwos() {
		assertEquals(4, scoreService.score(new Roll(1, 2, 3, 2, 6), Caterory.TWOS));
		assertEquals(10, scoreService.score(new Roll(2, 2, 2, 2, 2), Caterory.TWOS));
	}

	@Test
	public void tetsScoreThrees() {
		assertEquals(6, scoreService.score(new Roll(1, 2, 3, 2, 3), Caterory.THREES));
		assertEquals(12, scoreService.score(new Roll(2, 3, 3, 3, 3), Caterory.THREES));
	}

	@Test
	public void tetsScoreFours() {
		assertEquals(12, scoreService.score(new Roll(4, 4, 4, 5, 5), Caterory.FOURS));
		assertEquals(8, scoreService.score(new Roll(4, 4, 5, 5, 5), Caterory.FOURS));
		assertEquals(4, scoreService.score(new Roll(4, 5, 5, 5, 5), Caterory.FOURS));
	}

	@Test
	public void tetsScoreFives() {
		assertEquals(10, scoreService.score(new Roll(4, 4, 4, 5, 5), Caterory.FIVES));
		assertEquals(15, scoreService.score(new Roll(4, 4, 5, 5, 5), Caterory.FIVES));
		assertEquals(20, scoreService.score(new Roll(4, 5, 5, 5, 5), Caterory.FIVES));
	}

	@Test
	public void tetsScoreSixes() {
		assertEquals(0, scoreService.score(new Roll(4, 4, 4, 5, 5), Caterory.SIXES));
		assertEquals(6, scoreService.score(new Roll(4, 4, 6, 5, 5), Caterory.SIXES));
		assertEquals(18, scoreService.score(new Roll(6, 5, 6, 6, 5), Caterory.SIXES));
	}

	@Test
	public void tetsScorePair() {
		assertEquals(6, scoreService.score(new Roll(3, 4, 3, 5, 6), Caterory.PAIR));
		assertEquals(10, scoreService.score(new Roll(5, 3, 3, 3, 5), Caterory.PAIR));
		assertEquals(12, scoreService.score(new Roll(5, 3, 6, 6, 5), Caterory.PAIR));
		assertEquals(0, scoreService.score(new Roll(1, 3, 2, 6, 5), Caterory.PAIR));
	}

	@Test
	public void tetsScoreTwoPair() {
		assertEquals(16, scoreService.score(new Roll(3, 3, 5, 4, 5), Caterory.TWO_PAIRS));
		assertEquals(16, scoreService.score(new Roll(3, 3, 5, 5, 5), Caterory.TWO_PAIRS));
		assertEquals(0, scoreService.score(new Roll(3, 3, 5, 2, 1), Caterory.TWO_PAIRS));
		assertEquals(0, scoreService.score(new Roll(3, 1, 2, 4, 5), Caterory.TWO_PAIRS));
	}

	@Test
	public void tetsScoreThreeOfKind() {
		assertEquals(9, scoreService.score(new Roll(3, 3, 3, 4, 5), Caterory.THREE_OF_KIND));
		assertEquals(15, scoreService.score(new Roll(5, 3, 5, 4, 5), Caterory.THREE_OF_KIND));
		assertEquals(9, scoreService.score(new Roll(3, 3, 3, 3, 5), Caterory.THREE_OF_KIND));
		assertEquals(9, scoreService.score(new Roll(3, 3, 3, 3, 3), Caterory.THREE_OF_KIND));
		assertEquals(0, scoreService.score(new Roll(3, 3, 2, 2, 1), Caterory.THREE_OF_KIND));
	}

	@Test
	public void tetsScoreFourOfKind() {
		assertEquals(12, scoreService.score(new Roll(3, 3, 3, 3, 5), Caterory.FOUR_OF_KIND));
		assertEquals(20, scoreService.score(new Roll(5, 5, 5, 4, 5), Caterory.FOUR_OF_KIND));
		assertEquals(0, scoreService.score(new Roll(5, 5, 5, 4, 4), Caterory.FOUR_OF_KIND));
	}

	@Test
	public void tetsScoreSmallStraight() {
		assertEquals(15, scoreService.score(new Roll(1, 2, 3, 4, 5), Caterory.SMALL_STRAIGHT));
		assertEquals(15, scoreService.score(new Roll(2, 3, 4, 5, 1), Caterory.SMALL_STRAIGHT));
		assertEquals(0, scoreService.score(new Roll(1, 2, 2, 4, 5), Caterory.SMALL_STRAIGHT));
	}

	@Test
	public void tetsScoreLargeStraight() {
		assertEquals(20, scoreService.score(new Roll(6, 2, 3, 4, 5), Caterory.LARGE_STRAIGHT));
		assertEquals(20, scoreService.score(new Roll(2, 3, 4, 5, 6), Caterory.LARGE_STRAIGHT));
		assertEquals(0, scoreService.score(new Roll(1, 2, 2, 4, 5), Caterory.LARGE_STRAIGHT));
	}

	@Test
	public void tetsScoreFullHouse() {
		assertEquals(18, scoreService.score(new Roll(6, 2, 2, 2, 6), Caterory.FULL_HOUSE));
		assertEquals(0, scoreService.score(new Roll(2, 3, 4, 5, 6), Caterory.FULL_HOUSE));
	}


}
