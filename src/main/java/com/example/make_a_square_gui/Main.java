import java.util.Scanner;

public class Main {
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

        SolverThread solverThread = new SolverThread(grid, selectedPieces, 0);
        solverThread.start();

        try {
            solverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(solverThread.getMessage());

        if (solverThread.isSolutionFound()) {
            System.out.println("Final grid state:");
            grid.printGrid();
        }
        scanner.close();    
    }
}