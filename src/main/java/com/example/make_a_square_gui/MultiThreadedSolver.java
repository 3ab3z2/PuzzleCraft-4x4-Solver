package com.example.make_a_square_gui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadedSolver {
    private static final int BOARD_SIZE = 4;
    private static volatile boolean solutionFound = false;
    private final ReentrantLock lock = new ReentrantLock();
    private Grid sharedGrid;
    private List<SolverThread> solverThreads;

    public MultiThreadedSolver(Grid grid, Piece[] pieces, int numThreads) {
        this.sharedGrid = grid;
        this.solverThreads = new ArrayList<>();

        // Create different piece combinations for each thread
        for (int i = 0; i < numThreads; i++) {
            Grid threadGrid = new Grid();
            SolverThread solver = new SolverThread(threadGrid, pieces, 0);
            solver.setThreadId(i);
            solver.setLock(lock);
            solver.setSharedSolutionFlag(solutionFound);
            solverThreads.add(solver);
        }
    }

    public void solve() {
        ExecutorService executor = Executors.newFixedThreadPool(solverThreads.size());
        List<Future<Boolean>> futures = new ArrayList<>();

        // Submit all solver threads
        for (SolverThread solver : solverThreads) {
            futures.add(executor.submit(() -> {
                solver.run();
                return solver.isSolutionFound();
            }));
        }

        // Wait for any thread to find a solution
        try {
            for (Future<Boolean> future : futures) {
                if (future.get()) {
                    solutionFound = true;
                    break;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }

    public boolean isSolutionFound() {
        return solutionFound;
    }

    public Grid getFinalGrid() {
        for (SolverThread solver : solverThreads) {
            if (solver.isSolutionFound()) {
                return solver.getGrid();
            }
        }
        return null;
    }
}