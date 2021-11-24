package pokerHands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import checkers.HandCombinaisonCheckerImpl;

/**
 * Class de test sur le checker de combinaison.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class HandCheckCombinaisonTest {

	PokerHandsPrincipal pokerHandsPrincipal = new PokerHandsPrincipal();

	final HandCombinaisonCheckerImpl combinaisonChecker = new HandCombinaisonCheckerImpl();

	ArrayList<Card> decks;
	ArrayList<String> expectedDecks;
	ArrayList<Integer> cardValues;
	ArrayList<String> suits;

	/**
	 * Tableau des types de cartes
	 */
	private final static String EXPECTED_DECKS[] = new String[] { "2C", "2D", "2H", "2S", "3C", "3D", "3H", "3S", "4C",
			"4D", "4H", "4S", "5C", "5D", "5H", "5S", "6C", "6D", "6H", "6S", "7C", "7D", "7H", "7S", "8C", "8D", "8H",
			"8S", "9C", "9D", "9H", "9S", "TC", "TD", "TH", "TS", "JC", "JD", "JH", "JS", "QC", "QD", "QH", "QS", "KC",
			"KD", "KH", "KS", "AC", "AD", "AH", "AS", };

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		decks = pokerHandsPrincipal.buildDeck();
		expectedDecks = new ArrayList<>();
		cardValues = new ArrayList<>();
		suits = new ArrayList<>();

		expectedDecks.addAll(Arrays.asList(EXPECTED_DECKS));

	}

	@Test
	public void testBuildDeck() {
		List<Card> decks = pokerHandsPrincipal.buildDeck();
		List<String> expectedDecks = Arrays.asList(EXPECTED_DECKS);

		assertEquals(expectedDecks.size(), decks.size());
	}

	@Test
	public void testStraightFlushOk() {

		final Integer values[] = new Integer[] { 9, 11, 12, 13, 10 };
		final String suits[] = new String[] { "H", "H", "H", "H", "H" };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		final boolean isStraight = combinaisonChecker.isStraight(hand);
		assertTrue(isStraight);
		final boolean isFlush = combinaisonChecker.isFlush(hand);
		assertTrue(isFlush);

		boolean isStraightFlush = combinaisonChecker.isStraightFlush(hand);
		assertTrue(isStraightFlush);
	}

	@Test
	public void testStraightFlushNOk() {

		final Integer values[] = new Integer[] { 9, 11, 12, 13, 10 };
		final String suits[] = new String[] { "H", "S", "H", "H", "H" };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		final boolean isStraight = combinaisonChecker.isStraight(hand);
		assertTrue(isStraight);
		final boolean isFlush = combinaisonChecker.isFlush(hand);
		assertFalse(isFlush);

		final boolean isStraightFlush = combinaisonChecker.isStraightFlush(hand);
		assertFalse(isStraightFlush);
	}

	@Test
	public void testStraightFlushNOk2() {

		final Integer values[] = new Integer[] { 9, 3, 12, 13, 10 };
		final String suits[] = new String[] { "H", "H", "H", "H", "H" };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		final boolean isStraight = combinaisonChecker.isStraight(hand);
		assertFalse(isStraight);
		final boolean isFlush = combinaisonChecker.isFlush(hand);
		assertTrue(isFlush);

		final boolean isStraightFlush = combinaisonChecker.isStraightFlush(hand);
		assertFalse(isStraightFlush);
	}

	@Test
	public void testStraightFlushNOk3() {

		final Integer values[] = new Integer[] { 9, 3, 4, 13, 10 };
		final String suits[] = new String[] { "H", "H", "S", "H", "H" };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		final boolean isStraight = combinaisonChecker.isStraight(hand);
		assertFalse(isStraight);
		final boolean isFlush = combinaisonChecker.isFlush(hand);
		assertFalse(isFlush);

		final boolean isStraightFlush = combinaisonChecker.isStraightFlush(hand);
		assertFalse(isStraightFlush);
	}

	@Test
	public void testStraightOk() {
		final Integer values[] = new Integer[] { 9, 11, 12, 13, 10 };

		assertEquals(expectedDecks.size(), decks.size());

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isStraight = combinaisonChecker.isStraight(hand);
		assertTrue(isStraight);

	}

	@Test
	public void testStraightNOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 9, 2, 12, 13, 10 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isStraight = combinaisonChecker.isStraight(hand);
		assertFalse(isStraight);

	}

	@Test
	public void testFlushOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final String suits[] = new String[] { "H", "H", "H", "H", "H" };

		final Integer values[] = new Integer[] { 9, 2, 12, 13, 10 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		boolean isFlush = combinaisonChecker.isFlush(hand);
		assertTrue(isFlush);

	}

	@Test
	public void testFlushNOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final String suits[] = new String[] { "H", "S", "H", "H", "H" };
		final Integer values[] = new Integer[] { 9, 2, 12, 13, 10 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		final boolean isFlush = combinaisonChecker.isFlush(hand);
		assertFalse(isFlush);

	}

	@Test
	public void testFlushNOk2() {

		assertEquals(expectedDecks.size(), decks.size());

		final String suits[] = new String[] { "H", "H", "H", "D", "H" };

		final Integer values[] = new Integer[] { 9, 2, 12, 13, 10 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		this.suits.addAll(Arrays.asList(suits));
		Mockito.when(hand.getCardSuits()).thenReturn(this.suits);

		boolean isFlush = combinaisonChecker.isFlush(hand);
		assertFalse(isFlush);

	}

	@Test
	public void testLowerFourOfAKindOk() {
		List<Card> decks = pokerHandsPrincipal.buildDeck();
		List<String> expectedDecks = Arrays.asList(EXPECTED_DECKS);

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 9, 2, 2, 2, 2 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isFourOfAKind = combinaisonChecker.isFourOfAKind(hand);
		assertTrue(isFourOfAKind);

	}

	@Test
	public void testLowerFourOfAKindNOk() {
		List<Card> decks = pokerHandsPrincipal.buildDeck();
		List<String> expectedDecks = Arrays.asList(EXPECTED_DECKS);

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 2, 2, 3, 2 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isFourOfAKind = combinaisonChecker.isFourOfAKind(hand);
		assertFalse(isFourOfAKind);

	}

	@Test
	public void testHighterFourOfAKindOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 11, 11, 11, 3, 11 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isFourOfAKind = combinaisonChecker.isFourOfAKind(hand);
		assertTrue(isFourOfAKind);

	}

	@Test
	public void testFullHouseOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 11, 3, 11, 3, 11 };

		Hand hand = Mockito.mock(Hand.class);
		cardValues.addAll(Arrays.asList(values));

		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		Mockito.when(hand.getCombinaisonMaxValue()).thenReturn(11);

		boolean isFullHouse = combinaisonChecker.isFullHouse(hand);
		assertTrue(isFullHouse);

	}

	@Test
	public void testFullHouseNOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 11, 3, 11, 4, 11 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);
		Mockito.when(hand.getCombinaisonMaxValue()).thenReturn(11);

		final boolean isThreeOfAKind = combinaisonChecker.isThreeOfAKind(hand);
		assertTrue(isThreeOfAKind);

		final boolean isFullHouse = combinaisonChecker.isFullHouse(hand);
		assertFalse(isFullHouse);

	}

	@Test
	public void testThreeOfAKindOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 11, 12, 11, 11, 10 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isThreeOfAKind = combinaisonChecker.isThreeOfAKind(hand);
		assertTrue(isThreeOfAKind);

	}

	@Test
	public void testThreeOfAKindOk2() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 11, 3, 3, 2 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isThreeOfAKind = combinaisonChecker.isThreeOfAKind(hand);
		assertTrue(isThreeOfAKind);

	}

	@Test
	public void testThreeOfAKindNOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 11, 11, 3, 2 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isThreeOfAKind = combinaisonChecker.isThreeOfAKind(hand);
		assertFalse(isThreeOfAKind);

	}

	@Test
	public void testThreeOfAKindNOk2() {

		List<Card> decks = pokerHandsPrincipal.buildDeck();
		List<String> expectedDecks = Arrays.asList(EXPECTED_DECKS);

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 3, 3, 3, 2 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isThreeOfAKind = combinaisonChecker.isThreeOfAKind(hand);
		assertFalse(isThreeOfAKind);

	}

	@Test
	public void testTwoPairsOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 3, 4, 5, 5 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isTwoPairs = combinaisonChecker.isTwoPairs(hand);
		assertTrue(isTwoPairs);

	}

	@Test
	public void testTwoPairsNOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 7, 4, 5, 5 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isTwoPairs = combinaisonChecker.isTwoPairs(hand);
		assertFalse(isTwoPairs);

	}

	@Test
	public void testPairsOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 9, 4, 5, 5 };

		Hand hand = Mockito.mock(Hand.class);

		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isPair = combinaisonChecker.isPair(hand);
		assertTrue(isPair);
	}

	@Test
	public void testPairsNOk() {

		assertEquals(expectedDecks.size(), decks.size());

		final Integer values[] = new Integer[] { 3, 4, 4, 5, 5 };

		Hand hand = Mockito.mock(Hand.class);
		cardValues.addAll(Arrays.asList(values));
		Mockito.when(hand.getCardValues()).thenReturn(cardValues);

		boolean isPair = combinaisonChecker.isPair(hand);
		assertFalse(isPair);
	}
}
