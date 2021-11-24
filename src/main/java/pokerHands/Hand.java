package pokerHands;

import java.util.ArrayList;
import java.util.Map;

public interface Hand {
	
	/**
	 * Getter
	 * 
	 * @return la liste des valeurs des cartes
	 */
	ArrayList<Integer> getCardValues();
	
	/**
	 * Getter 
	 * 
	 * @return la liste des cartes d'une main
	 */
	ArrayList<Card> getHandCards();
	
	/**
	 * Getter
	 * 
	 * @return la liste de famille des cartes
	 */
	ArrayList<String> getCardSuits();
	
	/**
	 * Getter
	 * 
	 * @return la pair la plus petite pour le check du double pair.
	 */
	int getCombinaisonMinValue();
	
	/**
	 * Getter
	 * 
	 * @return la paire la plus haute  pour le check du double pair
	 */
	int getCombinaisonMaxValue();
	
	/**
	 * Setter
	 * 
	 * @param minValue la valeur min a setter
	 */
	void setCombinaisonMinValue(int minValue);
	
	/**
	 * Setter 
	 * 
	 * @param maxValue la valeur max a setter
	 */
	void setCombinaisonMaxValue(int maxValue);
	
	/**
	 * Getter
	 * 
	 * @return les valeurs restante dans la main d'un joueur.
	 */
	ArrayList<Integer> getRemainingValues();

	/**
	 * Getter
	 * 
	 * @return la description de la main du joueur.
	 */
	ArrayList<String> getDescriptions();

	/**
	 * Getter
	 * 
	 * @return la map associant la valeur d'une carte à son nom.
	 */
	Map<Integer, String> getCardValueNameMap();

}
