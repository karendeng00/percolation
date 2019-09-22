
public class PercolationUF implements IPercolate {
	boolean[][] myGrid;
	int myOpenCount;
	IUnionFind myFinder = new QuickUWPC();
	private final int VTOP;
	private final int VBOTTOM;
	
	//creates PercolationUF constructor
	public PercolationUF(int size, IUnionFind finder) {
		//assigns values to final variables
		VTOP = size * size;
		VBOTTOM = size * size + 1;
		//creates new grid
		myGrid = new boolean[size][size];
		
		//storing object in instance variable
			myFinder = finder;
		
		//initializes IUnionFind object of size N*N + 2
		finder.initialize(size * size + 2);
		
		//no open cells 
		myOpenCount = 0;
	}
	 
	
	/**
	 * Checks if row and column are in bounds of grid dimensions
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if (row,col) on grid, false otherwise
	 */
	
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	@Override
	/**
	 * Checks if grid on cell is open or true 
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if cell at certain (row,col) in 2D array is open (true), false otherwise
	 */
	
	public boolean isOpen(int row, int col) {
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	

	@Override
	/**
	 * Checks if grid on cell is full 
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if cell at certain (row,col) in 2D array is full, false otherwise
	 */
	
	public boolean isFull(int row, int col) {
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return myFinder.connected(row * myGrid.length + col, VTOP);
	}		
	
	
	@Override
	/**
	 * Checks if grid on cell percolates
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if cell is connected to VTOP and VBOTTOM, false otherwise
	 */
	
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	@Override
	/**
	 * Counts the number of cells in grid that are open 
	 * @return number of cells that are open
	 */
	
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	

	@Override
	/**
	 * Opens cell if cell is not already open, connects open cell to open neighbors. 
	 * @param row specifies row
	 * @param col specifies column
	 */
	
	
	public void open(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		if( ! isOpen(row,col)) {
			myGrid[row][col] = true;
			myOpenCount++;
			
			if(inBounds(row + 1, col) && isOpen(row + 1, col)) {
				//myFinder.connected((row + 1) * myGrid.length + col, row * myGrid.length + col);
				myFinder.union((row + 1) * myGrid.length + col, row * myGrid.length + col);
			}
			
			if( inBounds(row - 1, col) && isOpen(row - 1, col)) {
				//myFinder.connected((row - 1) * myGrid.length + col, row * myGrid.length + col);
				myFinder.union((row - 1) * myGrid.length + col, row * myGrid.length + col);
			}
			
			if( inBounds(row, col - 1) && isOpen(row, col - 1)) {
				//myFinder.connected((row) * myGrid.length + col - 1, row * myGrid.length + col);
				myFinder.union((row) * myGrid.length + col - 1, row * myGrid.length + col);
			}
			
			if( inBounds(row, col + 1) && isOpen(row, col + 1)) {
				//myFinder.connected((row) * myGrid.length + col + 1, row * myGrid.length + col);
				myFinder.union((row) * myGrid.length + col + 1, row * myGrid.length + col);
			}
			
			if (row == 0) {
				myFinder.union(row * myGrid.length + col, VTOP);
			}
			if (row == myGrid.length - 1) {
				myFinder.union(row * myGrid.length + col, VBOTTOM);
			}	
		}
			
			
		
			
			
		
	}
	
	
}
