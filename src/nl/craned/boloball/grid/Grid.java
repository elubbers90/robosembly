package nl.craned.boloball.grid;

public class Grid {

    private SquareData[][] grid;

    public Grid(int width, int height) {
        grid = new SquareData[width][height];
        initializeEmptyGrid();
    }
    
    public Grid(SquareData[][] generatedMap){
		grid = generatedMap;
	}

    private void initializeEmptyGrid() {
        for(SquareData[] row : grid) {
            for(int i = 0; i < row.length; i++) {
                row[i] = new SquareData(SquareData.BG);
            }
        }
    }
    
    public SquareData get(int x, int y){
		return grid[x][y];
	}
    
    public int getWidth(){
    	return grid.length;
    }
    
    public int getHeight(){
    	return grid[0].length;
    }
}