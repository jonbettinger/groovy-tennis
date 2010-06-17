package tennis

import junit.framework.TestCase
import static Score.*;

class TennisGameTest extends TestCase {
	def player1
	def player2
	Game game
	
	void setUp() {
		game = new Game()
		player1 = game.player1
		player2 = game.player2
	}
	
	void testCanGreateAGame() {
		assertNotNull new Game()
	}
	
	void testGetCurrentScoreYieldsZeroAtStart() {
		assert [Love, Love] == game.score
	}
	
	void testGetPlayer1CurrentScoreYieldsFifteenAfterPlayerScoresOnce() {
		game.scorePoint(player1)
		assert [Fifteen, Love] == game.score
	}
	
	void testGetPlayer2CurrentScoreYieldsFifteenAfterPlayerScoresOnce() {
		game.scorePoint(player2)
		assert [Love, Fifteen] == game.score
		
	}
	
	void testGetCurrentScoreYieldsThirtyAfterPlayerScoresTwice() {		
		2.times {  game.scorePoint(player1) }
		assert [Thirty, Love] == game.score
		
	}
	
	void testGetCurrentScoreYieldsFifteenAllAfterEachPlayerScoresOnce() {
		game.scorePoint(player1)
		game.scorePoint(player2)
		assert [Fifteen, Fifteen] == game.score
	}
	
	void testGetCurrentScoreYieldsFortyLoveAfterPlayerScoresThrice() {		
		3.times {  game.scorePoint(player1) }
		assert [Forty, Love] == game.score
	}
	
	void testGetCurrentScoreYieldsWinnerAfterPlayerWith40PointsScores() {		
		3.times {  game.scorePoint player1  }
		assert [Forty, Love] == game.score
		game.scorePoint player1
		assert [Winner, Loser] == game.score
	}
	
	void testGetCurrentScoreYieldsDeuceWhenBothPlayersScoreThreeTimes() {		
		3.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		assert [Deuce, Deuce] == game.score
	}
	
	void testGetCurrentScoreYieldsAdvantageForDeuceGameWhenAPlayerScores() {		
		3.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		assert [Deuce, Deuce] == game.score
		game.scorePoint player1  
		assert [Advantage, Down] == game.score
	}
	
	
	void testGetCurrentScoreYieldsDeuceForAdvantageGameWhenDownPlayerScores() {		
		4.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		assert [Deuce, Deuce] == game.score
	}
	
	void testPlayerWithAdvantageWinsAfterScoring() {		
		3.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		game.scorePoint player1
		assert [Advantage,Down] == game.score
		game.scorePoint player1  
		assert [Winner, Loser] == game.score
	}
}
