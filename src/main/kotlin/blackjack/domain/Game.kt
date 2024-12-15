package blackjack.domain

import blackjack.domain.player.Participant
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class Game(
    val players: List<Player>,
    val drawer: Deck,
    val dealer: Dealer,
) {
    fun startGame(
        onPrintResultCallback: ((List<Participant>) -> Unit),
        onTurnStarted: (Participant) -> String,
    ) {
        drawer.fillDeck(Card.cards)
        initTurn(onPrintResultCallback)
        players.forEach { player ->
            player.startTurn(onTurnStarted, onPrintResultCallback)
        }
        dealer.startTurn(onTurnStarted, onPrintResultCallback)
    }

    private fun initTurn(onPrintResultCallback: ((List<Participant>) -> Unit)) {
        (players + dealer).forEach { player ->
            repeat(2) { player.drawCard(CardDeck.drawCard()) }
        }
        onPrintResultCallback(players + dealer)
    }

    companion object {
        fun createGame(
            players: List<Player>,
            cardDeck: Deck = CardDeck,
        ): Game {
            return Game(players, cardDeck, Dealer())
        }
    }
}
