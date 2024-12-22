package blackjack.domain.player

import blackjack.domain.CardDeck

class Dealer(name: String = DEALER_NAME) : Participant(name, initBet = 0f) {
    private fun shouldDrawCard(): Boolean {
        return calculateCard() <= DRAW_THRESHOLD
    }

    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        if (!isBust() && shouldDrawCard()) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(this)
            onTurnStarted?.invoke(this)
        }
    }

    override fun isDealer(): Boolean {
        return true
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DRAW_THRESHOLD = 16
    }
}
