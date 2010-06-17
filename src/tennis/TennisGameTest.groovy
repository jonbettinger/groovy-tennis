package tennis

import org.junit.Before 
import org.junit.Test 
import static Score.*;

class TennisGameTest {
	def player1
	def player2
	Game game
	
	@Before
	void setUp() {
		game = new Game()
		player1 = game.player1
		player2 = game.player2
	}
	
	@Test
	void canGreateAGame() {
		assert null != new Game()
	}
	
	@Test
	void initialScoreIsLoveLove() {
		assert [Love, Love] == game.score
	}
	
	@Test
	void gameScoreYieldsFifteenLoveAfterPlayer1ScoresOnce() {
		game.scorePoint(player1)
		assert [Fifteen, Love] == game.score
	}
	
	@Test
	void gameScoreYieldsLoveFifteenAfterPlayer2ScoresOnce() {
		game.scorePoint(player2)
		assert [Love, Fifteen] == game.score
		
	}
	
	@Test
	void gameScoreYieldsThirtyLoveAfterPlayer1ScoresTwice() {		
		2.times {  game.scorePoint(player1) }
		assert [Thirty, Love] == game.score
		
	}
	
	@Test
	void gameScoreYieldsFifteenAllAfterEachPlayerScoresOnce() {
		game.scorePoint(player1)
		game.scorePoint(player2)
		assert [Fifteen, Fifteen] == game.score
	}
	
	@Test
	void gameScoreYieldsFortyLoveAfterPlayer1ScoresThrice() {		
		3.times {  game.scorePoint(player1) }
		assert [Forty, Love] == game.score
	}
	
	@Test
	void gameScoreYieldsWinnerAfterPlayer1ScoresWithForty() {		
		3.times {  game.scorePoint player1  }
		assert [Forty, Love] == game.score
		game.scorePoint player1
		assert [Winner, Loser] == game.score
	}
	
	@Test
	void gameScoreYieldsDeuceAfterBothPlayerScoreThrice() {		
		3.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		assert [Deuce, Deuce] == game.score
	}
	
	@Test
	void gameScoreYieldsAdvangeAfterPlayer1ScoresWithDeuceScore() {		
		3.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		assert [Deuce, Deuce] == game.score
		game.scorePoint player1  
		assert [Advantage, Down] == game.score
	}
	
	
	@Test
	void gameScoreYieldsDeuceWhenDownPlayerScores() {		
		4.times { 
			game.scorePoint player1  
			game.scorePoint player2
		}
		assert [Deuce, Deuce] == game.score
	}
	
	@Test
	void playerWithAdvantageWinsAfterScoring() {		
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
