package pokerHands;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import checkers.HandCombinaisonChecker;
import checkers.HandCombinaisonCheckerImpl;
import checkers.HandTypeEnum;


@RunWith(MockitoJUnitRunner.class)
public class PokerGameTurnTest {
	
	@InjectMocks
	PokerGameTurn pokerGameTurn;
	
	@Mock
	Hand blackHand;
	
	@Mock
	Hand whiteHand;
	
	@Mock
	HandCombinaisonChecker handChecker;
	
	PokerHandsPrincipal pokerHandsPrincipal = new PokerHandsPrincipal();

	ArrayList<Card> decks;
	
	Map<Integer, String> mapTest = new HashMap<Integer, String>();


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		decks = pokerHandsPrincipal.buildDeck();
		mapTest.put(2, "2");
		mapTest.put(3, "3");
		mapTest.put(4, "4");
		mapTest.put(5, "5");
		mapTest.put(6, "6");
		mapTest.put(7, "7");
		mapTest.put(8, "8");
		mapTest.put(9, "9");
		mapTest.put(10, "10");
		mapTest.put(11, "Jack");
		mapTest.put(12, "Queen");
		mapTest.put(13, "King");
		mapTest.put(14, "Ace");
		
		Mockito.when(whiteHand.getCardValueNameMap()).thenReturn(mapTest);
		Mockito.when(blackHand.getCardValueNameMap()).thenReturn(mapTest);
		
		
	}
	
	@Test
	public void testBlackWinnerStraightFlush() {
		
		final String blackHandDescriptions[] = new String[] { "TH","JH", "QH", "KH", "AH" };
		
		final String whiteHandDescriptions[] = new String[] { "2H","JH", "QH", "KH", "AH" };
		
		final Integer values[] = new Integer[] { 10, 11, 12, 13, 14 };
		final String suits[] = new String[] { "H","H", "H", "H", "H" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();

		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;

		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);

		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.STRAIGHT_FLUSH);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 2, 11, 12, 13, 14 };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.FLUSH);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("Black"));
		assertTrue(pokerGameTurn.getWinner().endsWith("Straight flush"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}
	
	@Test
	public void testWhiteWinnerStraightFlush() {
		
		final String whiteHandDescriptions[] = new String[] { "TH","JH", "QH", "KH", "AH" };
		
		final String blackHandDescriptions[] = new String[] { "2H","JH", "QH", "KH", "AH" };
		
		final Integer values[] = new Integer[] { 2, 11, 12, 13, 14 };
		final String suits[] = new String[] { "H","H", "H", "H", "H" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();
		
		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;
		
		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);
		
		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.FLUSH);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 10, 11, 12, 13, 14 };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.STRAIGHT_FLUSH);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("White"));
		assertTrue(pokerGameTurn.getWinner().endsWith("Straight flush"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}
	
	@Test
	public void testWhiteWinnerHighPairValue() {
		
		final String blackHandDescriptions[] = new String[] { "2C","2S", "AH", "7C", "KD" };
		
		final String whiteHandDescriptions [] = new String[] { "2D","7S", "JC", "9D", "7D" };
		
		final Integer values[] = new Integer[] { 2, 2, 14, 7, 13 };
		final String suits[] = new String[] { "C","S", "H", "C", "D" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();
		
		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;
		
		
		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);
		Mockito.when(blackHand.getCombinaisonMaxValue()).thenReturn(2);
		
		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.PAIR);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 2, 7, 11, 9, 7 };
		final String suits2[] = new String[] { "D","S", "C", "D", "D" };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits2));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		Mockito.when(whiteHand.getCombinaisonMaxValue()).thenReturn(7);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.PAIR);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("White"));
		assertTrue(pokerGameTurn.getWinner().endsWith("High value 7"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}
	
	@Test
	public void testBlackWinnerHighPairValue() {
		
		final String blackHandDescriptions[] = new String[] { "2C","KS", "AH", "7C", "KD" };
		
		final String whiteHandDescriptions [] = new String[] { "2D","7S", "JC", "9D", "7D" };
		
		final Integer values[] = new Integer[] { 2, 13, 14, 7, 13 };
		final String suits[] = new String[] { "C","S", "H", "C", "D" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();
		
		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;
		
		
		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);
		Mockito.when(blackHand.getCombinaisonMaxValue()).thenReturn(13);
		
		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.PAIR);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 2, 7, 11, 9, 7 };
		final String suits2[] = new String[] { "D","S", "C", "D", "D" };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits2));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		Mockito.when(whiteHand.getCombinaisonMaxValue()).thenReturn(7);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.PAIR);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("Black"));
		assertTrue(pokerGameTurn.getWinner().endsWith("High value King"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}
	
	@Test
	public void testWinnerIsFullHouse() {
		
		final String blackHandDescriptions[] = new String[] { "2H","KS", "KH", "3C", "KD" };
		
		final String whiteHandDescriptions [] = new String[] { "2D","2S", "2C", "9D", "9H" };
		
		final Integer values[] = new Integer[] { 2, 13, 13, 2, 13 };
		final String suits[] = new String[] { "H","S", "H", "C", "D" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();
		
		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;
		
		
		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);
		Mockito.when(blackHand.getCombinaisonMaxValue()).thenReturn(13);
		
		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.FULL_HOUSE);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 2, 2, 2, 9, 9};
		final String suits2[] = new String[] { "D","S", "C", "D", "H" };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits2));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		Mockito.when(whiteHand.getCombinaisonMaxValue()).thenReturn(2);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.FULL_HOUSE);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("Black"));
		assertTrue(pokerGameTurn.getWinner().endsWith("High value King"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}
	
	@Test
	public void testWinnerIsFourOfAKind() {
		
		final String blackHandDescriptions[] = new String[] { "5C","KS", "KH", "6C", "KD" };
		
		final String whiteHandDescriptions [] = new String[] { "2D","2S", "2C", "9D", "2H" };
		
		final Integer values[] = new Integer[] { 5, 13, 13, 6, 13 };
		final String suits[] = new String[] { "C","S", "H", "C", "D" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();
		
		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;
		
		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);
		Mockito.when(blackHand.getCombinaisonMaxValue()).thenReturn(13);
		
		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.FULL_HOUSE);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 2, 2, 2, 9, 2};
		final String suits2[] = new String[] { "D","S", "C", "D", "H" };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits2));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		Mockito.when(whiteHand.getCombinaisonMaxValue()).thenReturn(2);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.FOUR_OF_A_KIND);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("White"));
		assertTrue(pokerGameTurn.getWinner().endsWith("Four of a kind"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}
	
	@Test
	public void testTie() {
		
		// TODO tester le match nul
		
	}
	
	@Test
	public void testWinnerMinValueIsTwoPairs() {
		
		final String blackHandDescriptions[] = new String[] { "2C","KS", "KH", "2C", "7D" };
		
		final String whiteHandDescriptions [] = new String[] { "3D","2S", "KC", "KD", "3H" };
		
		final Integer values[] = new Integer[] { 2, 13, 13, 2, 7 };
		final String suits[] = new String[] { "C","S", "H", "C", "D" };
		
		ArrayList<Integer> blackCardValues = new ArrayList<>();
		ArrayList<String> blackCardSuits = new ArrayList<>();
		ArrayList<String> blackHDs = new ArrayList<>();
		ArrayList<String> whiteHDs = new ArrayList<>();
		
		blackHDs.addAll(Arrays.asList(blackHandDescriptions));
		whiteHDs.addAll(Arrays.asList(whiteHandDescriptions));
		
		pokerGameTurn.blackHand = blackHand;
		
		blackCardValues.addAll(Arrays.asList(values));
		Mockito.when(blackHand.getCardValues()).thenReturn(blackCardValues);
		blackCardSuits.addAll(Arrays.asList(suits));
		Mockito.when(blackHand.getCardSuits()).thenReturn(blackCardSuits);
		Mockito.when(blackHand.getDescriptions()).thenReturn(blackHDs);
		Mockito.when(blackHand.getCombinaisonMaxValue()).thenReturn(13);
		Mockito.when(blackHand.getCombinaisonMinValue()).thenReturn(2);
		
		Mockito.when(handChecker.getHandType(blackHand)).thenReturn(HandTypeEnum.TWO_PAIRS);
		
		ArrayList<Integer> whiteCardValues = new ArrayList<>();
		ArrayList<String> whiteCardSuits = new ArrayList<>();
		
		pokerGameTurn.whiteHand = whiteHand;
		
		final Integer values2[] = new Integer[] { 3, 2, 13, 13, 3};
		final String suits2[] = new String[] { "D","S", "C", "D", "H" };
		whiteCardValues.addAll(Arrays.asList(values2));
		Mockito.when(whiteHand.getCardValues()).thenReturn(whiteCardValues);
		whiteCardSuits.addAll(Arrays.asList(suits2));
		Mockito.when(whiteHand.getCardSuits()).thenReturn(whiteCardSuits);
		Mockito.when(whiteHand.getDescriptions()).thenReturn(whiteHDs);
		Mockito.when(whiteHand.getCombinaisonMaxValue()).thenReturn(13);
		Mockito.when(whiteHand.getCombinaisonMinValue()).thenReturn(3);
		
		Mockito.when(handChecker.getHandType(whiteHand)).thenReturn(HandTypeEnum.TWO_PAIRS);
		
		pokerGameTurn.determinedTurnWinner();
		
		assertTrue(pokerGameTurn.getWinner().startsWith("White"));
		assertTrue(pokerGameTurn.getWinner().endsWith("High value 3"));
		
		//Afficher le gagnant dans la sortie console
		System.out.println(pokerGameTurn.getWinner() + "\n");
		
	}

}
