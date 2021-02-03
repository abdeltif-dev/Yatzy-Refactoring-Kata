package kata;

import kata.model.Caterory;
import kata.model.Roll;
import kata.service.ScoreService;
import kata.service.ScoreServiceImpl;

public class Yatzy {

	protected int[] dice;
	
	private static ScoreService scoreService = new ScoreServiceImpl();

	public Yatzy(int d1, int d2, int d3, int d4, int d5) {
		dice = new int[5];
		dice[0] = d1;
		dice[1] = d2;
		dice[2] = d3;
		dice[3] = d4;
		dice[4] = d5;
	}

	public static int chance(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.CHANCE);
	}

	/**
	 * If all dice have the same number, the player scores 50 points. For example:
     *	1,1,1,1,1 placed on "yatzy" scores 50
     *	1,1,1,2,1 placed on "yatzy" scores 0
	 * 
	 * @param dice
	 * @return
	 */
	public static int yatzy(int... dice) {
		if(dice.length == 5) {
			return scoreService.score(new Roll(dice[0], dice[1], dice[2], dice[3], dice[4]), Caterory.YATZY);
		} else {
			return 0;
		}
	}

	/**
	 * The player scores the sum of the dice that reads one. 
     * 3,3,3,4,5 placed on "ones" scores 0
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int ones(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.ONES);
	}

	/**
	 * The player scores the sum of the dice that reads two. 
     * 2,3,2,5,1 placed on "twos" scores 4 (2+2)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int twos(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.TWOS);
	}

	/**
	 * The player scores the sum of the dice that reads two. 
     * 2,3,2,5,1 placed on "threes" scores 3 (3)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int threes(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.THREES);
	}

	/**
	 * The player scores the sum of the dice that reads two. 
     * 4,3,4,5,4 placed on "fours" scores 12 (4+4+4)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public int fours() {
		return scoreService.score(new Roll(dice[0], dice[1], dice[2], dice[3], dice[4]), Caterory.FOURS);
	}

	/**
	 * The player scores the sum of the dice that reads two. 
     * 2,3,2,5,1 placed on "twos" scores 5 (5)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public int fives() {
		return scoreService.score(new Roll(dice[0], dice[1], dice[2], dice[3], dice[4]), Caterory.FIVES);
	}

	/**
	 * The player scores the sum of the dice that reads two. 
     * 2,3,6,6,6 placed on "sixes" scores 18 (6+6+6)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public int sixes() {
		return scoreService.score(new Roll(dice[0], dice[1], dice[2], dice[3], dice[4]), Caterory.SIXES);
	}

	/**
	 * The player scores the sum of the two highest matching dice. For example, when placed on "pair":
     * 3,3,3,4,4 scores 8 (4+4)
     * 1,1,6,2,6 scores 12 (6+6)
     * 3,3,3,4,1 scores 6 (3+3)
     * 3,3,3,3,1 scores 6 (3+3)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.PAIR);
	}

	/**
	 * If there are two pairs of dice with the same number, the player scores the sum of these dice. For example, when placed on "two pairs":
     * 1,1,2,3,3 scores 8 (1+1+3+3)
     * 1,1,2,3,4 scores 0
     * 1,1,2,2,2 scores 6 (1+1+2+2)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.TWO_PAIRS);
	}

	/**
	 * If there are four dice with the same number, the player scores the sum of these dice. For example, when placed on "four of a kind":
     * 2,2,2,2,5 scores 8 (2+2+2+2)
     * 2,2,2,5,5 scores 0
     * 2,2,2,2,2 scores 8 (2+2+2+2)
	 * @param _1
	 * @param _2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.FOUR_OF_KIND);
	}

	/**
	 * If there are three dice with the same number, the player scores the sum of these dice. For example, when placed on "three of a kind":
     * 3,3,3,4,5 scores 9 (3+3+3)
     * 3,3,4,5,6 scores 0
     * 3,3,3,3,1 scores 9 (3+3+3)
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.THREE_OF_KIND);
	}

	/**
	 * When placed on "small straight", if the dice read
     * 1,2,3,4,5,
	 * the player scores 15 (the sum of all the dice).
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.SMALL_STRAIGHT);
	}

	/**
	 * When placed on "large straight", if the dice read
 	 * 2,3,4,5,6,
     * the player scores 20 (the sum of all the dice).
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.LARGE_STRAIGHT);
	}

	/**
	 * If the dice are two of a kind and three of a kind, the player scores the sum of all the dice. For example, when placed on "full house":
     * 1,1,2,2,2 scores 8 (1+1+2+2+2)
     * 2,2,3,3,4 scores 0
     * 4,4,4,4,4 scores 0
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
		return scoreService.score(new Roll(d1, d2, d3, d4, d5), Caterory.FULL_HOUSE);
	}
}
