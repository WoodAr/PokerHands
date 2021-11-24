package checkers;

/**
 * Enum representatnt les différentes combinaison.
 * 
 * Un code a été ajouté pour classer les combinaisons.
 *
 */
public enum HandTypeEnum {

	/**
	 * 5 cartes de même couleurs(suits) avec des valeurs consécutives
	 */
	STRAIGHT_FLUSH(80, "Straight flush"), 
	
	/**
	 * 4 cartes de même valeur.
	 */
	FOUR_OF_A_KIND(70, "Four of a kind"), 
	
	/**
	 * 3 cartes de même valeurs, avec les 2 restants qui forment une paire.
	 */
	FULL_HOUSE(60, "Full house"), 
	
	/**
	 * 5 cartes de mêmes couleur (suits)
	 */
	FLUSH(50, "Flush"), 
	
	/**
	 * 5 cartes qui se suivent
	 */
	STRAIGHT(40, "Straight"), 
	
	/**
	 * 3 cartes de même valeurs.
	 */
	THREE_OF_A_KIND(30, "Three of a kind"), 
	
	/**
	 * 2 pairs différentes
	 */
	TWO_PAIRS(20, "Two pairs"), 
	
	/**
	 * 2 cartes de même valeur.
	 */
	PAIR(10, "Pair"), 
	
	/**
	 * Une main normal
	 */
	HIGH_CARD(0, "High card");
	
	/**
	 * Code de la combinaison.
	 */
	private final int code;
	/**
	 * Description de la combinaison.
	 */
	private final String text;

	/**
	 * Constructor
	 * 
	 * @param code un code permettant de classer les combinaisons.
	 * @param text description de la combinaison.
	 */
	private HandTypeEnum(final int code, final String text) {
		this.code = code;
		this.text = text;
	}
	
	/**
	 * Getter.
	 * 
	 * @return le code de la combinaison.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Getter
	 * 
	 * @return la description de la combinaison
	 */
	public String getText() {
		return text;
	}

}
