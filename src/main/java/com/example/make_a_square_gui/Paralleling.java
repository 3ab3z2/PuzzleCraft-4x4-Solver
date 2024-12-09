package com.example.make_a_square_gui;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Paralleling implements Runnable {
    private final ReentrantLock lock;
    public static volatile boolean foundBoard = false;
    public static int[][] finallyBoard; // Shared final solution
    private final Map<Integer, int[][]> allPieces;
    private final int threadID;
    private final int sectionSize; // Number of masks each thread processes

    public Paralleling(Map<Integer, int[][]> allPieces, int threadID, int sectionSize) {
        this.lock = new ReentrantLock();
        this.allPieces = allPieces;
        this.threadID = threadID;
        this.sectionSize = sectionSize;
    }

    @Override
    public void run() {
        int from = threadID * sectionSize;
        int to = from + sectionSize - 1;

        for (int mask = from; mask <= to; mask++) {
            if (foundBoard) {
                break;
            }

            Board slaveBoard = new Board(allPieces);
            int[][] possibleSolution = slaveBoard.decompose(mask);

            if (possibleSolution != null) {
                lock.lock();
                try {
                    if (!foundBoard) { // Double-check condition after locking
                        foundBoard = true;
                        finallyBoard = possibleSolution;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
