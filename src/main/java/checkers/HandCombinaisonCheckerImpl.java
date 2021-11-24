package checkers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pokerHands.Hand;
import pokerHands.HandImpl;

/**
 * Class modélisant {@link HandCombinaisonChecker}
 *
 */
public class HandCombinaisonCheckerImpl implements HandCombinaisonChecker {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStraightFlush(Hand hand) {
		return isStraight(hand) && isFlush(hand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStraight(Hand hand) {

		ArrayList<Integer> cardValues = hand.getCardValues();

		Collections.sort(cardValues);
		hand.setCombinaisonMaxValue(cardValues.get(cardValues.size() - 1));
		boolean isStraight = true;

		for (int i = 1; i < cardValues.size(); i++) {
			final int previousValue = cardValues.get(i - 1);
			final int currentValue = cardValues.get(i);
			if (previousValue + 1 != currentValue) {
				isStraight = false;
			}

		}
		return isStraight;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFlush(Hand hand) {

		final ArrayList<String> cardSuits = hand.getCardSuits();
		Collections.sort(cardSuits);

		final ArrayList<Integer> cardValues = hand.getCardValues();
		Collections.sort(cardValues);

		hand.setCombinaisonMaxValue(cardValues.get(cardValues.size() - 1));
		hand.getRemainingValues().addAll(cardValues);

		final boolean isFlush = cardSuits.get(0).equals(cardSuits.get(4));

		return isFlush;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFourOfAKind(Hand hand) {
		boolean isFourOfAKind = false;

		// Vérification valeur basse
		// On trie, afin de classer les valeurs de la plus petite à la plus grande.
		ArrayList<Integer> cardValues = hand.getCardValues();
		Collections.sort(cardValues);
		// On vérifie que les 4 premières valeur soit les mêmes.
		final boolean lowerFourOfAKind = checkFourOfAKind(cardValues);
		if (lowerFourOfAKind) {
			hand.setCombinaisonMaxValue(cardValues.get(0));
			isFourOfAKind = lowerFourOfAKind;
		}

		// Vérification par valeur haute
		// On inverse le trie (afin d'utiliser qu'une seule methode de vérification)
		Collections.reverse(cardValues);
		// On vérifie que les 4 premières valeur soit les mêmes.
		final boolean highterFourOfAKind = checkFourOfAKind(cardValues);

		if (highterFourOfAKind) {
			hand.setCombinaisonMaxValue(cardValues.get(0));
			isFourOfAKind = highterFourOfAKind;
		}

		return isFourOfAKind;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFullHouse(Hand hand) {

		boolean isFullHouse = false;

		if (isThreeOfAKind(hand)) {
			Set<Integer> countDuplicatesValue = countDuplicatesValue(hand.getCardValues());
			countDuplicatesValue.remove(new Integer(hand.getCombinaisonMaxValue()));
			if (countDuplicatesValue.size() == 1) {
				hand.setCombinaisonMinValue(countDuplicatesValue.iterator().next());
				isFullHouse = true;
			}
		}

		return isFullHouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isThreeOfAKind(Hand hand) {

		boolean isThreeOfAKind = false;

		// Vérification valeur basse
		// On trie, afin de classer les valeurs de la plus petite à la plus grande.
		ArrayList<Integer> cardValues = hand.getCardValues();
		Collections.sort(cardValues);
		// On vérifie que les 4 premières valeur soit les mêmes.
		final boolean lowerThreeOfAKind = checkThreeOfAKind(cardValues);

		// Vérification par valeur haute
		// On inverse le trie (afin d'utiliser qu'une seule methode de vérification)
		Collections.reverse(cardValues);
		// On vérifie que les 4 premières valeur soit les mêmes.
		final boolean highterThreeOfAKind = checkThreeOfAKind(cardValues);

		if (lowerThreeOfAKind && !highterThreeOfAKind) {
			hand.setCombinaisonMaxValue(cardValues.get(0));
			isThreeOfAKind = lowerThreeOfAKind;
		} else if (!lowerThreeOfAKind && highterThreeOfAKind) {
			hand.setCombinaisonMaxValue(cardValues.get(0));
			isThreeOfAKind = highterThreeOfAKind;
		} else if (lowerThreeOfAKind && highterThreeOfAKind) {
			// les deux sont vrai on est dans le cas [x y y y z] x < y < z
			hand.setCombinaisonMaxValue(cardValues.get(1));
			isThreeOfAKind = lowerThreeOfAKind || highterThreeOfAKind;
		}

		return isThreeOfAKind;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTwoPairs(Hand hand) {
		final ArrayList<Integer> cardValues = hand.getCardValues();
		Set<Integer> duplicatesValue = countDuplicatesValue(cardValues);
		ArrayList<Integer> values = new ArrayList<Integer>();
		if (!duplicatesValue.isEmpty()) {
			int max = Collections.max(duplicatesValue);
			int min = Collections.min(duplicatesValue);

			hand.setCombinaisonMaxValue(max);
			hand.setCombinaisonMinValue(min);

			for (Integer cardValue : cardValues) {
				if (!duplicatesValue.contains(cardValue)) {
					hand.getRemainingValues().add(cardValue);
				}

			}
		}
		return duplicatesValue.size() == 2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPair(Hand hand) {
		final ArrayList<Integer> cardValues = hand.getCardValues();
		final Set<Integer> duplicatesValue = countDuplicatesValue(cardValues);
		int max = Collections.max(duplicatesValue);

		hand.setCombinaisonMaxValue(max);
		for (Integer cardValue : cardValues) {
			if (!duplicatesValue.contains(cardValue)) {
				hand.getRemainingValues().add(cardValue);
			}

		}
		return duplicatesValue.size() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandTypeEnum getHandType(Hand pokerHand) {

		if (isStraightFlush(pokerHand)) {
			return HandTypeEnum.STRAIGHT_FLUSH;
		} else if (isFourOfAKind(pokerHand)) {
			return HandTypeEnum.FOUR_OF_A_KIND;
		} else if (isFullHouse(pokerHand)) {
			return HandTypeEnum.FULL_HOUSE;
		} else if (isFlush(pokerHand)) {
			return HandTypeEnum.FLUSH;
		} else if (isStraight(pokerHand)) {
			return HandTypeEnum.STRAIGHT;
		} else if (isThreeOfAKind(pokerHand)) {
			return HandTypeEnum.THREE_OF_A_KIND;
		} else if (isTwoPairs(pokerHand)) {
			return HandTypeEnum.TWO_PAIRS;
		} else if (isPair(pokerHand)) {
			return HandTypeEnum.PAIR;
		} else {
			ArrayList<Integer> cardValues = pokerHand.getCardValues();
			Collections.sort(cardValues);
			pokerHand.setCombinaisonMaxValue(cardValues.get(cardValues.size() - 1));
			pokerHand.getRemainingValues().addAll(cardValues);
			return HandTypeEnum.HIGH_CARD;
		}

	}

	/**
	 * @param allCardValues
	 * @return
	 */
	private boolean checkThreeOfAKind(ArrayList<Integer> cardValues) {
		boolean isThreeOfAKind = false;
		// Pour éviter la liste vide
		if (cardValues.size() == 5) {

			if (cardValues.get(0) != cardValues.get(1)) {
				// [y x x x z] avec "y < x < z" ou "y > x > z"

				isThreeOfAKind = cardValues.get(1) == cardValues.get(3) && cardValues.get(1) != cardValues.get(4);
			} else {
				// [x x x y z]
				// On vérifie que la 1ere(index 0) valeur du tableau est égale à la 3eme
				// valeur(index 2)
				// Et que cette même 1ere valeur est différente de la 4.(index 3)
				// Comme le tableau est trié, il n'est pas nécésssaire de checker la 2eme(index
				// 1) et la dernière valeur.(index 4)
				isThreeOfAKind = cardValues.get(0) == cardValues.get(2) && cardValues.get(0) != cardValues.get(3);
			}

		}
		return isThreeOfAKind;
	}

	/**
	 * @param allCardValues
	 * @return
	 */
	private boolean checkFourOfAKind(final ArrayList<Integer> cardValues) {

		boolean isFourOfAKind = false;
		if (cardValues.size() == 5) {
			// isFourOfAKind = allCardValues.get(0) == allCardValues.get(1) &&
			// allCardValues.get(1) == allCardValues.get(2)
			// && allCardValues.get(2) == allCardValues.get(3);

			// On a une liste trié, il suffit de regarder si la 1ere valeur est la même que
			// la 4eme.
			isFourOfAKind = cardValues.get(0) == cardValues.get(3);
		}
		return isFourOfAKind;
	}

	/**
	 * @param hand
	 * @return
	 */
	private Set<Integer> countDuplicatesValue(final ArrayList<Integer> cardValues) {
		final Set<Integer> cardSet = new HashSet<>();
		return cardValues.stream().filter(c -> !cardSet.add(c)).collect(Collectors.toSet());

	}

}
