package com.example.make_a_square_gui;

import java.util.concurrent.locks.ReentrantLock;

public class SolverThread extends Thread {
    private Grid grid;
    private Piece[] pieces;
    private int index;
    private boolean solutionFound;
    private String message;
    private int threadId;
    private ReentrantLock lock;
    private volatile boolean sharedSolutionFound;

    public SolverThread(Grid grid, Piece[] pieces, int index) {
        this.grid = grid;
        this.pieces = pieces;
        this.index = index;
        this.solutionFound = false;
        this.message = "";
    }

    public void setThreadId(int id) {
        this.threadId = id;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public void setSharedSolutionFlag(boolean flag) {
        this.sharedSolutionFound = flag;
    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public void run() {
        int totalSquares = calculateTotalSquares(pieces);
        if (totalSquares != 16) {
            message = "\nSolution not possible:\nTotal squares do not match grid size.";
            return;
        }

        // Start with different rotations based on thread ID
        int startRotation = threadId % 4;
        solutionFound = solveWithRotation(grid, pieces, index, startRotation);

        if (solutionFound) {
            lock.lock();
            try {
                if (!sharedSolutionFound) {
                    message = "Solution found by thread " + threadId + "!";
                    sharedSolutionFound = true;
                }
            } finally {
                lock.unlock();
            }
        } else {
            message = "No solution found by thread " + threadId;
        }
    }

    private boolean solveWithRotation(Grid grid, Piece[] pieces, int index, int startRotation) {
        if (sharedSolutionFound) {
            return false;
        }

        if (index == pieces.length) {
            return grid.isFull();
        }

        Piece currentPiece = pieces[index];
        for (int rotation = 0; rotation < 4; rotation++) {
            int actualRotation = (rotation + startRotation) % 4;
            currentPiece = (actualRotation == 0) ? currentPiece : currentPiece.rotate();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid.canPlacePiece(currentPiece, i, j)) {
                        grid.placePiece(currentPiece, i, j, index + 1);

                        lock.lock();
                        try {
                            System.out.println("Thread " + threadId + " current state:");
                            grid.printGrid();
                            System.out.println();
                        } finally {
                            lock.unlock();
                        }

                        if (solveWithRotation(grid, pieces, index + 1, startRotation)) {
                            return true;
                        }

                        grid.removePiece(currentPiece, i, j);
                    }
                }
            }
        }

        return false;
    }

    private int calculateTotalSquares(Piece[] pieces) {
        int total = 0;
        for (Piece piece : pieces) {
            total += piece.getNumberOfSquares();
        }
        return total;
    }

    public boolean isSolutionFound() {
        return solutionFound;
    }

    public String getMessage() {
        return message;
    }
}