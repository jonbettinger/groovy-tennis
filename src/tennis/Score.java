package tennis;

import java.util.Arrays;
import java.util.List;

public enum Score {
	Winner {
		List<Score> nextScore(Score other) {
			return score(Winner, Loser);
		}
	},
	Loser {
		List<Score> nextScore(Score other) {
			return score(Loser, Winner);
		}
	},
	Advantage {
		List<Score> nextScore(Score other) {
			return score(Winner, Loser);
		}
	},
	Down {
		List<Score> nextScore(Score other) {
			return score(Deuce, Deuce);
		}
	},
	Deuce {
		List<Score> nextScore(Score other) {
			return score(Advantage, Down);
		}
	},
	Forty {
		List<Score> nextScore(Score other) {
			return score(Winner, Loser);
		}
	},
	Thirty {
		List<Score> nextScore(Score other) {
			if (other == Forty) {
				return score(Deuce, Deuce);
			} else {
				return score(Forty, other);
			}
		}
	},
	Fifteen {
		List<Score> nextScore(Score other) {
			return score(Thirty, other);
		}
	},
	Love {
		List<Score> nextScore(Score other) {
			return score(Fifteen, other);
		}
	};

	protected List<Score> score(Score one, Score two) {
		return Arrays.asList(new Score[] { one, two });
	}

	abstract List<Score> nextScore(Score other);

}
