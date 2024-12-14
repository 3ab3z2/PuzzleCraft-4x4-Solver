package com.example.make_a_square_gui;
public class SolverThread extends Thread {
    
    private Grid grid;
    private Piece[] pieces;
    private int index;
    private boolean solutionFound;
    private String message;

    public SolverThread(Grid grid, Piece[] pieces, int index) {
        this.grid = grid;
        this.pieces = pieces;
        this.index = index;
        this.solutionFound = false;
        this.message = "";
    }

    @Override
    public void run() {
        // Comment the following lines if you want to view the solution steps for this case.
        int totalSquares = calculateTotalSquares(pieces);
        if (totalSquares != 16) {
            message = "\nSolution not possible:\nTotal squares do not match grid size.";
            return;
        } // End of the case
        solutionFound = solve(grid, pieces, index);
        if (solutionFound) {
            message = "Solution found!";
        } else {
            message = "No solution.";
        }
    }

    private int calculateTotalSquares(Piece[] pieces) {
        int total = 0;
        for (Piece piece : pieces) {
            total += piece.getNumberOfSquares();
        }
        return total;
    }

    public boolean solve(Grid grid, Piece[] pieces, int index) {
        if (index == pieces.length) {
            return grid.isFull();
        }

        Piece currentPiece = pieces[index];
        for (int rotation = 0; rotation < 4; rotation++) {
            currentPiece = (rotation == 0) ? currentPiece : currentPiece.rotate();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid.canPlacePiece(currentPiece, i, j)) {
                        grid.placePiece(currentPiece, i, j, index + 1);

                        // Print the grid and pause for 3 seconds
                        grid.printGrid();
                        System.out.println();
                        try {
                            Thread.sleep(3000); // Pause for 3 seconds
                        } catch (InterruptedException e) {
                            System.err.println("Thread interrupted during sleep: " + e.getMessage());
                        }

                        if (solve(grid, pieces, index + 1)) {
                            return true;
                        }

                        grid.removePiece(currentPiece, i, j);

                    }
                }
            }
        }

        return false;
    }


    public boolean isSolutionFound() {
        return solutionFound;
    }

    public String getMessage() {
        return message;
    }
}