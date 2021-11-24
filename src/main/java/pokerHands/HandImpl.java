package pokerHands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HandImpl implements Hand {
	
	/**
	 * Liste des valeurs des cartes
	 */
	private final ArrayList<Integer> cardValues =  new ArrayList<Integer>();
	
	/**
	 * Liste des cartes
	 */
	private ArrayList<Card> cards = new ArrayList<>();
	
	/**
	 * Liste des valeurs de la famille des cartes
	 */
	private ArrayList<String> suits = new ArrayList<>();
	
	/**
	 * Description des cartes
	 */
	private ArrayList<String> descriptions = new ArrayList<>();
	
	/**
	 * Valeur minimal du double pair
	 */
	private int minValue;
	
	/**
	 * Valeur max du double pair
	 */
	private int maxValue;
	
	/**
	 * Liste contenant les valeurs restant a tester en cas d'égalité
	 */
	private ArrayList<Integer> remainingValues = new ArrayList<>();
	
	/**
	 * Map associant la valeur d'une carte à son nom.
	 */
	private final Map<Integer, String> ValueNameCardMap = new HashMap<Integer, String>();


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Integer> getCardValues() {
		return this.cardValues;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Card> getHandCards() {
		return this.cards;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<String> getCardSuits() {
		return this.suits;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<String> getDescriptions() {
		return this.descriptions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCombinaisonMaxValue() {
		return this.maxValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCombinaisonMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCombinaisonMinValue() {
		return this.minValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCombinaisonMinValue(int minValue) {
		this.minValue = minValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<Integer> getRemainingValues() {
		return this.remainingValues;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Integer, String> getCardValueNameMap() {
		return ValueNameCardMap;
	}


}
