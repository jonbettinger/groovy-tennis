package tennis
import static Score.*;

class Game {
	def score = [Love, Love]
	final def player1 = 0
	final def player2 = 1
	
	void scorePoint(def playerNumber) {
		def newScores = score[playerNumber].nextScore(score[otherPlayer(playerNumber)]);
		score[playerNumber] = newScores[0]
		score[otherPlayer(playerNumber)] = newScores[1]
	}
	
	private otherPlayer(def playerNumber) {
		playerNumber == 1 ? 0 : 1
	}
	
	String toString() {
		score
	}
}
