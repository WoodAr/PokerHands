package pokerHands;

import java.util.HashMap;
import java.util.Map;

/**
 * Class modélisant une carte.
 *
 */
public class Card {
	
	/**
	 * "couleur / famille" de la carte.
	 */
	private final String suit;
	
	/**
	 * Valeur de la carte.
	 */
	private final String value;
	
	/**
	 * réel couleur de la carte.
	 */
	private String color;
	
	/**
	 * Nom de la carte
	 */
	private String name;
	
	/**
	 * Map permettant de retrouver le nom d'une carte par sa valeur.
	 */
	private final Map<Integer, String> ValueNameCardMap = new HashMap<Integer, String>();
	
	/**
	 * 
	 * Constructeur
	 * 
	 * @param suit "couleur/famille" de la carte
	 * @param value valeur de la carte
	 * @param name nome de la carte
	 */
	public Card(final String suit, final String value, final String name) {
		this.suit = suit;
		this.value = value;
		this.name = name;
	}
	
	/**
	 * Récupère la famille de la carte.
	 * 
	 * @return la famille de la carte
	 */
	public String getSuit() {
		return this.suit;
	}

	/**
	 * Récupère la valeur de la carte.
	 * 
	 * @return la valeur de la carte.
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Récupère la couleur de la carte.
	 * 
	 * @return la couleur de la carte.
	 */
	public String getColor() {
		return this.color;
	}
	
	/**
	 * Récupère le nom de la carte.
	 * 
	 * @return le nom de la carte.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set une couleur à une carte
	 * 
	 * @param color couleur a setter
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Récupère le nom d'une carte.
	 * 
	 * @param cardValue valeur de la carte.
	 * @return le nom de la carte.
	 */
	public String getCardName(final int cardValue) {
		return ValueNameCardMap.get(cardValue);
	}
	
	/**
	 * Récupère la description associé à une carte.
	 * 
	 * @return la description de la carte.
	 */
	public String getDescription() {
		return this.value + this.suit;
	}

	/**
	 * Récupère la valeur en int de la carte.
	 * 
	 * @param value valeur de la carte 
	 * @return valeur en int de la carte
	 */
	public int getIntValue(final String value) {
		switch (value) {
		case "T":
			return 10;
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		case "A":
			return 14;
		default:
			return Integer.valueOf(value);
		}
	}
	
	/**
	 * Retourne une map contenant les valeurs des cartes associé aux nom.
	 * 
	 * @return une map de clé valeur, valeurCarte et NomCarte
	 */
	public Map<Integer, String> getHighValueMap() {
		return ValueNameCardMap;
	}

}
