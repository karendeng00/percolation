import java.util.Queue;
import java.util.LinkedList;
public class PercolationBFS extends PercolationDFSFast {
	//constructor that calls super to initialize state in parent class
	public PercolationBFS(int n) {
		super(n);
	}
	
	@Override
	/**
	 * Uses queue to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 * */
	protected void dfs(int row, int col) {
		
		
		int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};
        
        if (!inBounds(row, col)) {
        	return;
        }
        
        if ((isFull(row, col) || !isOpen(row, col))) {
        	return;
        }
        
        myGrid[row][col] = FULL;
		Queue<Integer> qp = new LinkedList<>();
		qp.add((row * (myGrid.length)) + col);
		
		
		while(qp.size() != 0) {
			Integer i = qp.remove();
            for(int k=0; k < rowDelta.length ; k++){
                int r = (i / myGrid.length) + rowDelta[k];	 
                int c = (i % myGrid.length) + colDelta[k];
                if (inBounds(r,c) && !isFull(r, c) && isOpen(r, c)) {
                	myGrid[r][c] = FULL;
                	qp.add((r * (myGrid.length)) + c);
                }
            }
		}
		
		/*
		 * protected void dfs(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;
		
		myGrid[row][col] = FULL;
		dfs(row - 1, col);
		dfs(row, col - 1);
		dfs(row, col + 1);
		dfs(row + 1, col);
	}
		 */
		
	}
	
}
