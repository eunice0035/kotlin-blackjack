package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class WinningCalculatorTest {
    @Test
    fun calculateWinningStatus() {
        val player1 = Player("Player 1")
        val player2 = Player("Player 2")
        val mockCard =
            listOf(
                Card.createCard("9", "클로버"),
                Card.createCard("8", "하트"),
            )

        mockCard.forEach { card ->
            player1.drawCard(card)
            player2.drawCard(card)
        }

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("A", "클로버"))
        dealer.drawCard(Card.createCard("10", "하트"))

        WinningCalculator.calculatorGameResult(listOf(player1, player2), dealer)

        dealer.winCount shouldBe 2
        dealer.loseCount shouldBe 0

        player1.loseCount shouldBe 1
        player2.loseCount shouldBe 1
    }

    @Test
    fun `calculateWinningStatus - dealer 1 win 1 lose`() {
        val player1 = Player("Player 1")
        player1.drawCard(Card.createCard("A", "하트"))
        player1.drawCard(Card.createCard("3", "하트"))

        val player2 = Player("Player 2")
        player2.drawCard(Card.createCard("2", "클로버"))
        player2.drawCard(Card.createCard("8", "클로버"))
        player2.drawCard(Card.createCard("A", "클로버"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("3", "클로버"))
        dealer.drawCard(Card.createCard("9", "클로버"))
        dealer.drawCard(Card.createCard("8", "다이아몬드"))

        WinningCalculator.calculatorGameResult(listOf(player1, player2), dealer)

        dealer.winCount shouldBe 1
        dealer.loseCount shouldBe 1

        player1.winCount shouldBe 0
        player1.loseCount shouldBe 1

        player2.winCount shouldBe 1
        player2.loseCount shouldBe 0
    }
}