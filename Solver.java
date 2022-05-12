
public class Solver {
	
	private static final int GRID_SIZE = 9; 
	
	public static void main(String[] args) {
		
		int [][] board = {
			{0,3,0,5,0,0,1,0,0},
			{6,0,0,7,0,2,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{5,0,0,0,0,0,0,2,7},
			{0,1,0,8,0,0,0,0,0},
			{0,0,0,0,9,0,0,0,0},
			{2,0,7,0,0,0,0,0,0},
			{0,0,0,0,3,0,8,0,0},
			{0,0,4,0,0,0,0,0,0}
			};
		
		System.out.println(solve(board));
		
		printBoard(board);

	}
	
	private static boolean isNumberInRow (int[][] board, int number, int row) {
		for(int i = 0; i < GRID_SIZE; i++) {
			if(board[row][i] == number) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isNumberInColumn (int[][] board, int number, int column) {
		for(int i = 0; i < GRID_SIZE; i++) {
			if(board[i][column] == number) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isNumberInSquare(int[][] board, int number, int row, int column) {
		int leftTopColumn = column - column % 3;
		int leftTopRow = row - row % 3;
		for(int j = leftTopRow; j < leftTopRow + 3; j++) {
			for(int i = leftTopColumn; i < leftTopColumn + 3; i++) {
				if(board[j][i] == number) {
					return true;
				}
			}
		}
		return false;		
	}
	
	private static boolean isValidPlace (int[][] board, int number, int row, int column) {
		return !isNumberInColumn(board, number, column) && 
				!isNumberInRow(board, number, row) && 
				!isNumberInSquare(board, number, row, column);
	}
	
	private static boolean solve(int[][] board) {
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int column = 0; column < GRID_SIZE; column++) {
				if(board[row][column] == 0) {
					for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if(isValidPlace(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;
							
							if(solve(board)) {
							return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	
	private static void printBoard(int[][] board) {
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int column = 0; column < GRID_SIZE; column++) {
				System.out.print(board[row][column] + " ");
			}
		System.out.println();
		}
	}
}
