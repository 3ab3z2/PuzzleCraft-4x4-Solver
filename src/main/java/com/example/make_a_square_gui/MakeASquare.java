//package com.example.make_a_square_gui;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class MakeASquare {
//
//    public static int[][] Square(Map<Integer, int[][]> pieces) throws InterruptedException {
//        // Logic to solve the square puzzle using the provided pieces
//        // This method will attempt to fit the pieces into a 4x4 grid
//        // and return the final board state if successful, or null if not.
//
//        // Initialize the board
//        Board board = new Board(pieces);
//        int[][] finalBoard = null;
//
//        // Implement the logic to fill the board with pieces
//        // This may involve recursive backtracking or other algorithms
//
//        for (int i = 0; i < 10000; i++) {
//            finalBoard = board.decompose(i);
//            if (board.isValidBoard(finalBoard)) {
//                return finalBoard;
//            }
//        }
//
//        return null;
//    }
//
//    public static int[][] parallelSquare(Map<Integer, int[][]> pieces) throws InterruptedException {
//        // Initialize the board
//        Board board = new Board(pieces);
//        int[][] finalBoard = null;
//
//        // Create threads for parallel processing
//        int numberOfThreads = 10; // Assuming 10 threads
//        Thread[] threads = new Thread[numberOfThreads];
//        Paralleling[] tasks = new Paralleling[numberOfThreads];
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            tasks[i] = new Paralleling(pieces);
//            threads[i] = new Thread(tasks[i], String.valueOf(i));
//            threads[i].start();
//        }
//
//        // Wait for all threads to complete
//        for (Thread thread : threads) {
//            thread.join();
//        }
//
//        // Check if a solution was found
//        if (Paralleling.foundBoard) {
//            finalBoard = Paralleling.finallyBoard;
//        }
//
//        return finalBoard;
//    }
//
//    private static void printBoard(int[][] board) {
//        for (int[] row : board) {
//            for (int cell : row) {
//                System.out.print(cell + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        // Example pieces map
//        Map<Integer, int[][]> pieces = new HashMap<>();
//        pieces.put(1, new int[][]{{1, 1}, {1, 1}});
//        pieces.put(2, new int[][]{{2, 2, 2}, {0, 2, 0}});
//        pieces.put(3, new int[][]{{3, 3}, {3, 0}});
//        pieces.put(4, new int[][]{{4}, {4}, {4}, {4}});
//
//        // Solve the puzzle using single-threaded approach
//        int[][] result = Square(pieces);
//        if (result != null) {
//            System.out.println("Single-threaded solution found:");
//            printBoard(result);
//        } else {
//            System.out.println("No solution found in single-threaded approach.");
//        }
//
//        // Solve the puzzle using multi-threaded approach
//        int[][] parallelResult = parallelSquare(pieces);
//        if (parallelResult != null) {
//            System.out.println("Multi-threaded solution found:");
//            printBoard(parallelResult);
//        } else {
//            System.out.println("No solution found in multi-threaded approach.");
//        }
//    }
//
//    private static class Paralleling implements Runnable {
//        private Map<Integer, int[][]> pieces;
//        public static boolean foundBoard = false;
//        public static int[][] finallyBoard = null;
//
//        public Paralleling(Map<Integer, int[][]> pieces) {
//            this.pieces = pieces;
//        }
//
//        @Override
//        public void run() {
//            // Implement the logic to solve the puzzle in parallel
//            Board board = new Board(pieces);
//            for (int i = 0; i < 10000; i++) {
//                int[][] candidateBoard = board.decompose(i);
//                if (board.isValidBoard(candidateBoard)) {
//                    synchronized (Paralleling.class) {
//                        if (!foundBoard) {
//                            foundBoard = true;
//                            finallyBoard = candidateBoard;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//    }
//}