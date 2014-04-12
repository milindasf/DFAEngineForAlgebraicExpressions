import java.util.Vector;

public class DFAEngine {

	private int numOfStates;
	private int numOfSymbols;
	private int indexOfStartingState;
	private Vector<Integer> indexesOfAcceptingStates;
	private int[][] transitionMatrix;
	private char[] alphabet;

	private DFAEngine(int numOfStates, int numOfSymbols,
			int indexOfStartingState, int[] indexesOfAcceptingStates,
			char[] alphabet) {

		this.numOfStates = numOfStates;
		this.numOfSymbols = numOfSymbols;
		this.indexOfStartingState = indexOfStartingState;

		this.indexesOfAcceptingStates = new Vector<Integer>();

		for (int i = 0; i < indexesOfAcceptingStates.length; i++) {
			this.indexesOfAcceptingStates.add(indexesOfAcceptingStates[i]);
		}

		transitionMatrix = new int[this.numOfStates][this.numOfSymbols];

		for (int i = 0; i < this.numOfStates; i++) {
			for (int j = 0; j < this.numOfSymbols; j++) {
				transitionMatrix[i][j] = -1; // Initialize all the transitions
												// to -1 at the beginning.
			}
		}

		this.alphabet = alphabet;

	}

	private boolean AddTransition(int startStateIndex, char symbol,
			int endStateIndex) {

		boolean state = false;

		if (startStateIndex >= this.numOfStates) {
			state = false;
			return state;
		}

		if (endStateIndex >= this.numOfSymbols) {
			state = false;
			return state;

		}

		int symbolIndex = this.GetSymbolIndex(symbol);

		if (symbolIndex == -1) {
			state = false;
			return state;

		}

		this.transitionMatrix[startStateIndex][symbolIndex] = endStateIndex;

		return state;

	}

	private int GetSymbolIndex(char symbol) {

		int symbolIndex = -1;

		for (int i = 0; i < this.alphabet.length; i++) {

			if (this.alphabet[i] == symbol) {
				symbolIndex = i;
				break;
			}

		}

		return symbolIndex;

	}
	/**
	 * get an expression and decide whether it is a valid arithmetic expression
	 * @param expression
	 * @return true - if accepted, false - otherwise
	 */
	public boolean CanRecognize(String expression) {

		boolean state = false;
		char c;
		int symbolIndex;
		int currentStateIndex = this.indexOfStartingState;
		for (int i = 0; i < expression.length(); i++) {

			c = expression.charAt(i);
			symbolIndex = this.GetSymbolIndex(c);
			if (symbolIndex == -1) {
				state = false;
				return state;
			} else {
				currentStateIndex = this.transitionMatrix[currentStateIndex][symbolIndex];
				if (currentStateIndex == -1) {
					state = false;
					return false;
				}
			}

		}

		for (int i = 0; i < this.indexesOfAcceptingStates.size(); i++) {

			if (currentStateIndex == this.indexesOfAcceptingStates.get(i)) {
				state = true;
				return true;
			}

		}

		return state;

	}
	
	/**
	 * Initialize necessary states and transitions for the DFA and return an instance of DFAEngine
	 * @return
	 */
	public static DFAEngine initializeDFA() {
		int[] acceptingState = { 1, 3 };
		char[] alphabet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'+', '-', '*', '/', '.' };
		DFAEngine basicAlgebericExpressoion = new DFAEngine(4, 15, 0,
				acceptingState, alphabet);

		// State 00
		basicAlgebericExpressoion.AddTransition(0, '+', 0);
		basicAlgebericExpressoion.AddTransition(0, '-', 0);
		for (int i = 0; i < 10; i++) {
			String str = "" + i;
			basicAlgebericExpressoion.AddTransition(0, str.charAt(0), 1);
		}
		basicAlgebericExpressoion.AddTransition(0, '.', 2);
		// State 01
		for (int i = 0; i < 10; i++) {
			String str = "" + i;
			basicAlgebericExpressoion.AddTransition(1, str.charAt(0), 1);
		}
		basicAlgebericExpressoion.AddTransition(1, '.', 2);
		basicAlgebericExpressoion.AddTransition(1, '+', 0);
		basicAlgebericExpressoion.AddTransition(1, '-', 0);
		basicAlgebericExpressoion.AddTransition(1, '/', 0);
		basicAlgebericExpressoion.AddTransition(1, '*', 0);
		// State 02
		for (int i = 0; i < 10; i++) {
			String str = "" + i;
			basicAlgebericExpressoion.AddTransition(2, str.charAt(0), 3);
		}
		// state 03
		for (int i = 0; i < 10; i++) {
			String str = "" + i;
			basicAlgebericExpressoion.AddTransition(3, str.charAt(0), 3);
		}
		basicAlgebericExpressoion.AddTransition(3, '+', 0);
		basicAlgebericExpressoion.AddTransition(3, '-', 0);
		basicAlgebericExpressoion.AddTransition(3, '/', 0);
		basicAlgebericExpressoion.AddTransition(3, '*', 0);

		//boolean state = basicAlgebericExpressoion.CanRecognize(".318*67.90+9");
		//System.out.println(state);
		return basicAlgebericExpressoion;

	}

}
