package checkers;

import pokerHands.Hand;
import pokerHands.HandImpl;

/**
 * Interface representant un checker sur les differentes combinaison
 *
 */
public interface HandCombinaisonChecker {

	/**
	 * Vérifie que la combinaison est straight flush.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est straight flush (5 valeurs consécutives de la même couleur), false sinon.
	 */
	boolean isStraightFlush(final Hand hand);
	
	/**
	 * Vérifie que la combinaison est straight.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est straight(5 Valeurs consécutives), false sinon.
	 */
	boolean isStraight(final Hand hand);

	/**
	 * Vérifie que la combinaison est flush.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est flush (5 cartes de la même couleur), false sinon.
	 */
	boolean isFlush(final Hand hand);

	/**
	 * Vérifie que la combinaison est Four of a kind
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est four of a kind(4 cartes de même valeurs, avec une paire), false sinon.
	 */
	boolean isFourOfAKind(final Hand hand);
	
	/**
	 * Vérifie que la combinaison est full house.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est full house(3 cartes de même valeurs, avec une paire), false sinon.
	 */
	boolean isFullHouse(final Hand hand);
	
	/**
	 * Vérifie que la combinaison est three of a kind.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est three of a kind(3 cartes de même valeurs), false sinon.
	 */
	boolean isThreeOfAKind(final Hand hand);
	
	/**
	 * Vérifie que la combinaison est two pairs.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est two pairs(double paire), false sinon.
	 */
	boolean isTwoPairs(final Hand hand);
	
	/**
	 * Vérifie que la combinaison est pair.
	 * 
	 * @param hand main du joueur.
	 * @return true si la main est three of a kind(2 cartes de même valeurs), false sinon.
	 */
	boolean isPair(final Hand hand);
	
	/**
	 * Get hand type.
	 * 
	 * @param hand main du joueur.
	 * @return le type de main du joueur.
	 */
	HandTypeEnum getHandType(Hand hand);

}
