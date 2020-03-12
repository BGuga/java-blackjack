package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.deck.Card;
import domain.deck.DeckFactory;
import domain.deck.Symbol;
import domain.deck.Type;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        String names = "pobi, jason";
        players = Players.of(names);
    }

    @Test
    @DisplayName("각 플레이어 draw")
    void draw() {
        List<Integer> expected = players.getPlayers()
                .stream()
                .map(player -> player.cards.size() + 1)
                .collect(Collectors.toList());

        players.draw(DeckFactory.getDeck());

        List<Integer> result = players.getPlayers()
                .stream()
                .map(player -> player.cards.size())
                .collect(Collectors.toList());

        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("모든 플레이어 이름")
    @ValueSource(strings = {"pobi", "jason"})
    void getAllNames(String name) {
        String playerNames = players.getAllNames();

        assertThat(playerNames).contains(name);
    }

    @Test
    @DisplayName("모든 플레이어 첫 카드 분배 결과")
    void getAllFirstDrawResult() {
        players.getPlayers()
                .forEach(player -> player.draw(new Card(Symbol.SPADE, Type.ACE)));
        String expected = "pobi카드: A스페이드\njason카드: A스페이드";

        assertThat(players.getAllFirstDrawResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("모든 플레이어 최종 결과")
    void getAllTotalDrawResult() {
        players.getPlayers()
                .get(0)
                .draw(new Card(Symbol.SPADE, Type.ACE));
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.SPADE, Type.THREE));
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.HEART, Type.FIVE));
        String expected = "pobi카드: A스페이드 - 결과: 11\njason카드: 3스페이드, 5하트 - 결과: 8";

        assertThat(players.getAllTotalDrawResult()).isEqualTo(expected);
    }
}