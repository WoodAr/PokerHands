package pokerHands;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import checkers.HandCombinaisonChecker;
import checkers.HandCombinaisonCheckerImpl;
import checkers.HandTypeEnum;

/**
 * Class permettant de modéliser un tour
 *
 */
public class PokerGameTurn implements IPokerGameTurn {

	/**
	 * Le gagnant du tour.
	 */
	private String winner = "";

	/**
	 * Checker permettant de vérifier les combinaisons.
	 */
	HandCombinaisonChecker handCombinaisonChecker;
	
	/**
	 * Main du joueur Black
	 */
	Hand blackHand;
	/**
	 * Main du joueur White
	 */
	Hand whiteHand;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void play(List<Card> decks) {

		final Hand blackHand = new HandImpl();
		final Hand whiteHand = new HandImpl();

		for (int index = 0; index < 5; index++) {

			final Random random = new Random();

			int randomizeCardIndex = random.nextInt(decks.size());
			populateHand(decks, blackHand, randomizeCardIndex);
			this.blackHand = blackHand;

			// On reprend un nouvel index
			randomizeCardIndex = random.nextInt(decks.size());
			populateHand(decks, whiteHand, randomizeCardIndex);
			this.whiteHand = whiteHand;

		}
		
	}

	/**
	 * Methode permettant de generer la d'un joueur.
	 * 
	 * @param decks le deck du jeu
	 * @param hand la main du joueur
	 * @param randomizeCardIndex donnée aléaoire pour récuperer une carte
	 */
	private void populateHand(List<Card> decks, final Hand hand, final int randomizeCardIndex) {

		final Card card = decks.remove(randomizeCardIndex);
		final String cardStringValue = card.getValue();

		hand.getHandCards().add(card);
		hand.getCardValues().add(card.getIntValue(cardStringValue));
		hand.getCardSuits().add(card.getSuit());
		hand.getDescriptions().add(card.getDescription());
		hand.getCardValueNameMap().put(card.getIntValue(cardStringValue), card.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void determinedTurnWinner() {
		
		handCombinaisonChecker = new HandCombinaisonCheckerImpl();

		HandTypeEnum blackHandType = handCombinaisonChecker.getHandType(blackHand);
		HandTypeEnum whiteHandType = handCombinaisonChecker.getHandType(whiteHand);

		System.out.println("Black: " + blackHand.getDescriptions() + " White: " + whiteHand.getDescriptions());

		if (blackHandType.getCode() > whiteHandType.getCode()) {
			//int combinaisonMaxValue = blackHand.getCombinaisonMaxValue();
			this.winner = "Black wins. - with: " + blackHandType.getText();
		} else if (blackHandType.getCode() == whiteHandType.getCode()) {
			if (blackHand.getCombinaisonMaxValue() > whiteHand.getCombinaisonMaxValue()) {
				
				int combinaisonMaxValue = blackHand.getCombinaisonMaxValue();
				this.winner = "Black wins. - with : High value " + blackHand.getCardValueNameMap().get(combinaisonMaxValue);
				
				
			} else if (blackHand.getCombinaisonMaxValue() == whiteHand.getCombinaisonMaxValue()) {
				if (blackHand.getCombinaisonMinValue() > whiteHand.getCombinaisonMinValue()) {
					int combinaisonMinValue = blackHand.getCombinaisonMinValue();
					this.winner = "Black wins. - with : High value "
							+ blackHand.getCardValueNameMap().get(combinaisonMinValue);
				} else if (blackHand.getCombinaisonMinValue() == whiteHand.getCombinaisonMinValue()) {

					final List<Integer> blackRemainingValues = blackHand.getRemainingValues();
					final List<Integer> whiteRemainingValues = whiteHand.getRemainingValues();

					Collections.sort(blackRemainingValues);
					Collections.sort(whiteRemainingValues);

					Collections.reverse(blackRemainingValues);
					Collections.reverse(whiteRemainingValues);

					if (blackRemainingValues.size() == whiteRemainingValues.size()) {
						for (int i = 0; i < blackRemainingValues.size(); i++) {
							if (blackRemainingValues.get(i) > whiteRemainingValues.get(i)) {
								int remainingValue = blackRemainingValues.get(i);
								this.winner = "Black wins. - with : High value "
										+ blackHand.getCardValueNameMap().get(remainingValue);
							} else if (blackRemainingValues.get(i) == whiteRemainingValues.get(i)) {
								if (i == blackRemainingValues.size()) {
									this.winner = "Tie";
								}
							} else {
								int remainingValue = whiteRemainingValues.get(i);
								this.winner = "White wins. - with : High value "
										+ whiteHand.getCardValueNameMap().get(remainingValue);
							}
						}
					}

				} else {
					int combinaisonMinValue = whiteHand.getCombinaisonMinValue();
					this.winner = "White wins. - with : High value "
							+ whiteHand.getCardValueNameMap().get(combinaisonMinValue);
				}

			} else {
				int combinaisonMaxValue = whiteHand.getCombinaisonMaxValue();
				this.winner = "White wins. - with : High value " + whiteHand.getCardValueNameMap().get(combinaisonMaxValue);
			}
		} else {
			this.winner = "White wins. - with: " + whiteHandType.getText();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getWinner() {
		return this.winner;
	}

}
