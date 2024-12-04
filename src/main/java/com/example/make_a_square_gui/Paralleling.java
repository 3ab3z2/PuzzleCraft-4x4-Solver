package com.example.make_a_square_gui;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Paralleling implements Runnable {
    private final ReentrantLock lock;
    private static volatile boolean foundBoard = false;
    private static int[][] finallyBoard;
    private final Map<Integer, int[][]> allPieces;

    public Paralleling(Map<Integer, int[][]> allPieces) {
        this.lock = new ReentrantLock();
        this.allPieces = allPieces;
    }

    @Override
    public void run() {
        int[][] finalBoard;
        int threadID = Integer.parseInt(Thread.currentThread().getName());

        int from = threadID * 1000; // Assuming constants.sectionSize = 1000
        int to = Math.min(from + 999, 9999); // Assuming constants.numberOfBoards = 10000
        if (threadID == 9) { // Assuming constants.numberOfThreads = 10
            to = 9999;
        }

        for (int mask = from; mask <= to; mask++) {
            Board slaveBoard = new Board(allPieces);
            finalBoard = slaveBoard.decompose(mask);

            if (foundBoard) {
                break;
            }

            if (finalBoard != null) {
                lock.lock();
                try {
                    foundBoard = true;
                    finallyBoard = finalBoard;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}