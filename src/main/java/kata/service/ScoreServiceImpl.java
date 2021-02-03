package kata.service;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kata.model.Caterory;
import kata.model.Roll;

public class ScoreServiceImpl implements ScoreService {

	private static final Map<Caterory, ToIntFunction<Roll>> CATEGORIES_FUNCTIONS_MAP = new HashMap<>();

	public ScoreServiceImpl() {
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.CHANCE, roll -> scoreChance(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.YATZY, roll -> scoreYatzy(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.ONES, roll -> scoreKind(roll, 1));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.TWOS, roll -> scoreKind(roll, 2));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.THREES, roll -> scoreKind(roll, 3));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.FOURS, roll -> scoreKind(roll, 4));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.FIVES, roll -> scoreKind(roll, 5));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.SIXES, roll -> scoreKind(roll, 6));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.PAIR, roll -> scorePair(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.TWO_PAIRS, roll -> scoreTwoPair(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.THREE_OF_KIND, roll -> scoreThreeOfKind(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.FOUR_OF_KIND, roll -> scoreFourOfKind(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.SMALL_STRAIGHT, roll -> scoreSmallStraight(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.LARGE_STRAIGHT, roll -> scoreLargeStraight(roll));
		CATEGORIES_FUNCTIONS_MAP.put(Caterory.FULL_HOUSE, roll -> scoreFullHouse(roll));
	}

	/**
	 * Score a GIVEN roll in a GIVEN category
	 * 
	 * @param roll
	 * @param category
	 * @return
	 */
	public int score(Roll roll, Caterory caterory) {
		if (roll != null) {
			return CATEGORIES_FUNCTIONS_MAP.get(caterory).applyAsInt(roll);
		} else {
			return 0;
		}
	}

	private IntStream getIntStream(Roll roll) {
		return IntStream.of(roll.getDice1(), roll.getDice2(), roll.getDice3(), roll.getDice4(), roll.getDice5());
	}

	/**
	 * The player scores the sum of all dice, no matter what they read. For example:
	 * 1,1,3,3,6 placed on "chance" scores 14 (1+1+3+3+6) 4,5,5,6,1 placed on
	 * "chance" scores 21 (4+5+5+6+1)
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreChance(Roll roll) {
		return getIntStream(roll).sum();
	}

	/**
	 * If all dice have the same number, the player scores 50 points. For example:
	 * 1,1,1,1,1 placed on "yatzy" scores 50 1,1,1,2,1 placed on "yatzy" scores 0
	 * 
	 * @param dice
	 * @return
	 */
	private int scoreYatzy(Roll roll) {
		long count = getIntStream(roll).distinct().count();
		if (count == 1) {
			return 50;
		} else {
			return 0;
		}
	}

	/**
	 * The player scores the sum of the dice that reads one, two, three, four, five
	 * or six, respectively.
	 * 
	 * 1,1,2,4,4 placed on "fours" scores 8 (4+4) 2,3,2,5,1 placed on "twos" scores
	 * 4 (2+2) 3,3,3,4,5 placed on "ones" scores 0
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreKind(Roll roll, int kind) {
		return getIntStream(roll).filter(side -> side == kind).sum();
	}

	/**
	 * The player scores the sum of the two highest matching dice. For example, when
	 * placed on "pair": 3,3,3,4,4 scores 8 (4+4) 1,1,6,2,6 scores 12 (6+6)
	 * 3,3,3,4,1 scores 6 (3+3) 3,3,3,3,1 scores 6 (3+3)
	 * 
	 * @param roll
	 * @return
	 */
	private int scorePair(Roll roll) {

		Map<Integer, Long> map = getIntStream(roll).boxed()
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		OptionalInt optionalInt = map.entrySet().stream().filter(e -> e.getValue() >= 2).mapToInt(Map.Entry::getKey)
				.max();

		if (optionalInt.isPresent()) {
			return optionalInt.getAsInt() * 2;
		} else {
			return 0;
		}
	}

	/**
	 * If there are two pairs of dice with the same number, the player scores the
	 * sum of these dice. For example, when placed on "two pairs": 1,1,2,3,3 scores
	 * 8 (1+1+3+3) 1,1,2,3,4 scores 0 1,1,2,2,2 scores 6 (1+1+2+2)
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreTwoPair(Roll roll) {
		Map<Integer, Long> map = getIntStream(roll).boxed()
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		long count = map.entrySet().stream().filter(e -> e.getValue() >= 2).count();

		if (count == 2) {
			return map.entrySet().stream().filter(e -> e.getValue() >= 2).mapToInt(Map.Entry::getKey).sum() * 2;
		} else {
			return 0;
		}
	}

	/**
	 * If there are four dice with the same number, the player scores the sum of
	 * these dice. For example, when placed on "four of a kind": 2,2,2,2,5 scores 8
	 * (2+2+2+2) 2,2,2,5,5 scores 0 2,2,2,2,2 scores 8 (2+2+2+2)
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreFourOfKind(Roll roll) {
		return scoreOfKind(roll, 4);
	}

	/**
	 * If there are three dice with the same number, the player scores the sum of
	 * these dice. For example, when placed on "three of a kind": 3,3,3,4,5 scores 9
	 * (3+3+3) 3,3,4,5,6 scores 0 3,3,3,3,1 scores 9 (3+3+3)
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreThreeOfKind(Roll roll) {
		return scoreOfKind(roll, 3);
	}

	private int scoreOfKind(Roll roll, int occurence) {
		Map<Integer, Long> map = getIntStream(roll).boxed()
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		OptionalInt optionalInt = map.entrySet().stream().filter(e -> e.getValue() >= occurence)
				.mapToInt(Map.Entry::getKey).findAny();

		if (optionalInt.isPresent()) {
			return optionalInt.getAsInt() * occurence;
		} else {
			return 0;
		}
	}

	/**
	 * When placed on "small straight", if the dice read 1,2,3,4,5, the player
	 * scores 15 (the sum of all the dice).
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreSmallStraight(Roll roll) {
		long count = getIntStream(roll).filter(d -> d < 6).distinct().count();
		if (count == 5) {
			return 15;
		} else {
			return 0;
		}
	}

	/**
	 * When placed on "large straight", if the dice read 2,3,4,5,6, the player
	 * scores 20 (the sum of all the dice).
	 * 
	 * @param roll
	 * @return
	 */
	private int scoreLargeStraight(Roll roll) {
		long count = getIntStream(roll).filter(d -> d > 1).distinct().count();
		if (count == 5) {
			return 20;
		} else {
			return 0;
		}
	}

	/**
	 * If the dice are two of a kind and three of a kind, the player scores the sum
	 * of all the dice. For example, when placed on "full house": 1,1,2,2,2 scores 8
	 * (1+1+2+2+2) 2,2,3,3,4 scores 0 4,4,4,4,4 scores 0
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @return
	 */
	private int scoreFullHouse(Roll roll) {
		Map<Integer, Long> map = getIntStream(roll).boxed()
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		boolean b1 = map.entrySet().stream().anyMatch(e -> e.getValue() == 2);
		boolean b2 = map.entrySet().stream().anyMatch(e -> e.getValue() == 3);

		if (b1 && b2) {
			return getIntStream(roll).sum();
		} else {
			return 0;
		}
	}

}
