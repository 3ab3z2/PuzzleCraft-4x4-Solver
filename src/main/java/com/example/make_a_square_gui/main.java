package com.example.make_a_square_gui;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        PiecesModel piecesModel = new PiecesModel();
        Piece[] pieces = piecesModel.getAllPieces();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Available pieces:");
        System.out.println("1. L");
        System.out.println("2. Z");
        System.out.println("3. I");
        System.out.println("4. J");
        System.out.println("5. T");
        System.out.println("6. S");
        System.out.println("7. O");

        System.out.print("How many pieces do you want to use: ");
        int numPieces = scanner.nextInt();

        Piece[] selectedPieces = new Piece[numPieces];

        for (int i = 0; i < numPieces; i++) {
            System.out.print("Enter the number for piece " + (i + 1) + ": ");
            int pieceChoice = scanner.nextInt();

            if (pieceChoice >= 1 && pieceChoice <= 7) {
                selectedPieces[i] = pieces[pieceChoice - 1];
            } else {
                System.out.println("Invalid piece number. Please choose a number between 1 and 7.");
                i--;
            }
        }

        System.out.print("Enter the number of threads to use (1-4): ");
        int numThreads = scanner.nextInt();
        numThreads = Math.max(1, Math.min(4, numThreads));

        MultiThreadedSolver solver = new MultiThreadedSolver(grid, selectedPieces, numThreads);
        solver.solve();

        if (solver.isSolutionFound()) {
            System.out.println("\nSolution found!");
            System.out.println("Final grid state:");
            Grid finalGrid = solver.getFinalGrid();
            if (finalGrid != null) {
                finalGrid.printGrid();
            }
        } else {
            System.out.println("\nNo solution found.");
        }

        scanner.close();
    }
}