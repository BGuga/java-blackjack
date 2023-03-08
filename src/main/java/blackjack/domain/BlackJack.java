package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJack {

    private final Users users;
    private final Dealer dealer;

    public BlackJack(List<Name> usersNames, Deck deck) {
        this.users = new Users(usersNames, deck);
        this.dealer = new Dealer(getInitialCards(deck));
    }

    private List<Card> getInitialCards(final Deck randomDeck) {
        List<Card> cards = new ArrayList<>();
        cards.add(randomDeck.drawCard());
        cards.add(randomDeck.drawCard());
        return cards;
    }

    public void giveCard(Name user, Deck deck) {
        final User targetUser = users.finUserByName(user);
        targetUser.draw(deck.drawCard());
    }

    public int giveCardToDealerUntilDontNeed(Deck deck) {
        int additionalCardCount = 0;
        while (dealer.canReceive()) {
            dealer.draw(deck.drawCard());
            additionalCardCount += 1;
        }
        return additionalCardCount;
    }

    public boolean isBust(final Name name) {
        return users.checkBustBy(name);
    }

    public List<User> getUserOf(Result result) {
        final List<User> resultUsers = new ArrayList<>();
        for (User user : users.getUsers()) {
            if (Result.of(user.getGamePoint(), dealer.getGamePoint()) == result) {
                resultUsers.add(user);
            }
        }
        return resultUsers;
    }

    public User getUser(Name user) {
        return users.finUserByName(user);
    }

    public Map<Result, Integer> getDealerResult() {
        final Map<Result, Integer> resultMap = new HashMap<>();
        resultMap.put(Result.WIN, getUserOf(Result.LOSE).size());
        resultMap.put(Result.LOSE, getUserOf(Result.WIN).size());
        resultMap.put(Result.DRAW, getUserOf(Result.DRAW).size());
        return resultMap;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Users getUsers() {
        return users;
    }
}
