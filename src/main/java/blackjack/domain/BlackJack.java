package blackjack.domain;

public class BlackJack {

    private static final int INITIAL_CARD_COUNT = 2;
    private final Users users;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJack(Users users, Dealer dealer, Deck deck) {
        this.users = users;
        this.dealer = dealer;
        this.deck = deck;
    }

    private void giveCardToPlayers() {
        giveCardToUsers();
        giveCardToDealer();
    }

    private void giveCardToUsers() {
        users.giveEachUser(deck, INITIAL_CARD_COUNT);
    }

    private void giveCardToDealer() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.draw(deck.drawCard());
        }
    }
}
