package pokerHands;

import java.util.List;

/**
 * Interface definissant un tour
 *
 */
public interface IPokerGameTurn {

	/**
	 * Jouer une partie.
	 * 
	 * @param decks deck du jeu
	 */
	void play(List<Card> decks);

	/**
	 * Determine le gagnant du tour.
	 */
	void determinedTurnWinner();

	/**
	 * Getter 
	 * @return le gagnant
	 */
	String getWinner();

}
