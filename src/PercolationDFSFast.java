
public class PercolationDFSFast extends PercolationDFS {
	//create constructor that calls super to initialize state in parent class
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	/**
	 * calls dfs once if newly opened cell should be marked as full
	 *  - Cells marked as full are cleared and marked as open instead
	 * @param row specifies row
	 * @param col specifies column
	 */
	protected void updateOnOpen(int row, int col) {
		boolean one = false;
		boolean two = false;
		boolean three = false;
		boolean four = false;
		
		if (inBounds(row - 1, col)) {
			one = isFull(row - 1, col);
		}
		
		if (inBounds(row + 1, col)) {
			two = isFull(row + 1, col);
		}
		
		if (inBounds(row, col - 1)) {
			three = isFull(row, col - 1);
		}
		
		if (inBounds(row, col + 1)) {
			four = isFull(row, col + 1);
		}
		
		if (row == 0 || one || two || three || four) {
			dfs(row, col);
		}
	}

}
