package blackjack.ui

import blackjack.domain.player.Participant
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class ResultView {
    fun printStartMessage(players: List<Player>) {
        val playersName = StringBuilder()
        players.forEachIndexed { index, player ->
            playersName.append(player.name)
            if (index != players.lastIndex) {
                playersName.append(", ")
            }
        }
        println("딜러와 ${playersName}에게 2장씩 나누었습니다.")
    }

    fun printGameResult(players: List<Player>, dealer: Dealer) {
        (players + dealer).forEach { player ->
            player.showCards()
            println(" - 결과: ${player.calculateCard()}")
        }

        println("\n## 최종 승패")
        (players + dealer).forEach { player ->
            showWinningResult(player)
        }
    }

    fun showWinningResult(player: Participant) {
        print("${player.name}: ")

        if (player.name == Dealer.DEALER_NAME) {
            println("${player.winCount}승 ${player.loseCount} 패")
            return
        }

        if (player.winCount > 0) {
            println("승")
        } else {
            println("패")
        }
    }

    fun printDealerDrawExtra(participant: Participant) {
        if (participant.name != Dealer.DEALER_NAME) return
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
