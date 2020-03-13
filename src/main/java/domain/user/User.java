package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.card.Card;
import domain.card.Cards;

public abstract class User {
	protected final Cards cards;

	public User() {
		this.cards = new Cards(new ArrayList<>());
	}

	public void addCards(Card... cards) {
		this.cards.addAll(Arrays.asList(cards));
	}

	public boolean isBlackjack() {
		return cards.isBlackjack();
	}

	public boolean isBust() {
		return this.cards.isBust();
	}

	public int calculateScore() {
		return cards.calculateScore();
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public abstract List<Card> getInitialCard();

	public abstract String getName();
}
