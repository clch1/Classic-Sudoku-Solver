package SudokuPackage;


import java.util.*;




// A template class that provides custom-made methods for solving a classic Sudoku puzzle:
public class SudokuMethods
{
    // Instance field:
    public String name;

    // Constructor method:
    public SudokuMethods()
    {
        name = "method";
    }


    // Creates the Sudoku puzzle board for the user by using a [9]x[9] 2-D {int}
    // array and converting the user-provided String number(s) into {int}s and placing them
    // into the 2-D {int} array, prints and returns the solution or prints an error message:
    public int [][] setUpPuzzleBoard()
    {
        // Initializes the puzzle board:
        int [][] puzzleBoard = new int [9][9];

        // Iteratively gets the numbers for stting up the puzzle board from the user:
        for (int i = 0; i < puzzleBoard.length; i++)
        {
            for (int j = 0; j < puzzleBoard[0].length; j++)
            {
                puzzleBoard[i][j] = this.getNumber(i, j);

                System.out.printf("\n");
            }
        }
        
        return puzzleBoard;
    }


    // Over-loaded version of {setUpPuzzleBoard()} that adds 
    // numbers to the intially constructed puzzle board that now 
    // needs to be completed; {indexOne} and {indexTwo} help to make the program run faster:
    public boolean setUpPuzzleBoard(int [][] puzzleBoard)
    {
        // The boolean value that will terminate the program:
        boolean validity = false;

        /* NEEDS TO ADD NUMBERS TO {puzzleBoard} ONLY WHERE THERE ARE 
         * ZEROES
         */
        for (int i = 0; i < puzzleBoard.length; i++)
        {
            for (int j = 0; j < puzzleBoard[0].length; j++)
            {
                if (puzzleBoard[i][j] == 0)
                {
                    for (int k = 1; k < 10; k++)
                    {
                        if (this.tryNumber(k, puzzleBoard, i, j) == true)
                        {
                            puzzleBoard[i][j] = k;

                            validity = this.setUpPuzzleBoard(puzzleBoard);
                        }

                        else if (this.tryNumber(k, puzzleBoard, i, j) == false && k < 9)
                        {
                            continue;
                        }

                        // Restes the value of puzzleBoard[i][j] once we determined that {k}
                        // was not the correct value:
                        if (validity == false)
                        {
                            puzzleBoard[i][j] = 0;
                        }

                        else if (validity == true)
                        {
                            return validity;
                        }

                        if (k == 9) //&& this.tryNumber(j + 1, puzzleBoard, i, j) == false
                        {
                            return false;
                        }

                        // At this point, an earlier installemnt of 9 was rejected, so we want to restart the loop:
                        else if (j == puzzleBoard[0].length - 1 && k == 9)
                        {
                            return false;
                        }

                        else if (puzzleBoard[i][j] == 0 && k < 9) // puzzleBoard[i][j] != 0 && k < 9 // && this.tryNumber(k, puzzleBoard, i, j) == false
                        {
                            continue;
                        }

                        // At this point, an earlier installemnt of 9 was rejected, so we want to restart the loop:
                        else if (k == 9)
                        {
                            return false;
                        }
                    }
                }

                /* WE NEED TO RETURN {true} ONLY AFTER WE HAVE FILLED EVERY SPACE IN THE PUZZLE BOARD*/
                if (i == puzzleBoard.length - 1 && j == puzzleBoard[0].length - 1 && puzzleBoard[i][j] != 0)
                {
                    return true;
                }
            }
        }

        // Base cases:

        if (validity == true)
        {
            return true;
        }

        else if (validity == false)
        {
            return false;
        }

        return setUpPuzzleBoard(puzzleBoard);
    }


    // Gets a number from the user to fill the puzzle board with:
    public int getNumber(int i, int j)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.printf("Please enter a number for filling the puzzle board with at location [%d][%d]: ", i, j);

        return keyboard.nextInt();
    }


    // Prints the puzzle board:
    public void printPuzzleBoard(int [][] puzzleBoard)
    {
        // Creates the upper part of boxes:
        for (int x = 0; x < puzzleBoard[1].length + 6; x++)
        {
            System.out.printf("-");
        }

        // // Creates space between the upper border and the numbers:
        // Iteratively gets the numbers for stting up the puzzle board from the user:
        for (int i = 0; i < puzzleBoard.length; i++)
        {
            // Create separation between vertical boxes
            System.out.printf("\n");

            for (int j = 0; j < puzzleBoard[0].length; j++)
            {
                if (j == 0)
                {
                    System.out.printf("|%d", puzzleBoard[i][j]);
                }

                // Ensures that only multiples of 2 get a " | " to the right of them on the puzzle board:
                else if (j == 1 || j == 3 || j == 4 || j == 6 || j == 7)
                {
                    System.out.printf("%d", puzzleBoard[i][j]);
                }

                // Creates separation between horizontal boxes:
                else if (j == 2 || j == 5)
                {
                    System.out.printf("%d||", puzzleBoard[i][j]);
                }

                else if (j == 8)
                {
                    System.out.printf("%d|", puzzleBoard[i][j]);
                }
            }

            // Creates space between the upper border and the numbers:
            System.out.printf("\n");

            // Creates the lower part of boxes:
            for (int y = 0; y < puzzleBoard[0].length + 6; y++)
            {
                System.out.printf("-");
            }

            // Creates more distinction between different sections of rows of the
            // {puzzleBoard}:
            if (i == 2 || i == 5 || i == 8)
            {
                System.out.printf("\n");

                for (int z = 0; z < puzzleBoard[0].length + 6; z++)
                {
                    System.out.printf("-");
                }
            }
        }

        System.out.printf("\n");
    }


    /* Checks whether or not a number is valid relative to the numbers that are to the right and/or left
     * of it and the numbers that are above and/or below it in {puzzleBoard}, 
     * returns a {true} if the number is valid and {false} otherwise:
     */
    public boolean tryNumber(int number, int [][] puzzleBoard, int indexOne, int indexTwo)
    {
        if (horizontalCheck(number, puzzleBoard, indexOne) == false)
        {
            return false;
        }

        else if (verticalCheck(number, puzzleBoard, indexTwo) == false)
        {
            return false;
        }

        else if (withinSmallSquareCheck(number, puzzleBoard, indexOne, indexTwo) == false)
        {
            return false;
        }

        return true;
    }


    /* Determines whether or not the numbers that are to the right and/or left of {number} are 
     * the same in {puzzleBoard}, returns {true} if they are not and false otherwise:
     */
    public boolean horizontalCheck(int number, int [][] puzzleBoard, int indexOne)
    {
        for (int i = 0; i < puzzleBoard[0].length; i++)
        {
            if (number == puzzleBoard[indexOne][i])
            {
                return false;
            }
        }

        return true;
    }


    /* Determines whether or not the numbers that are to the above and/or below 
     * {number} are the same in {puzzleBoard}, return {true} if they are not and {false }
     * otherwise:
     */
    public boolean verticalCheck(int number, int [][] puzzleBoard, int indexTwo)
    {
        for (int i = 0; i < puzzleBoard.length; i++)
        {
            if (number == puzzleBoard[i][indexTwo])
            {
                return false;
            }
        }

        return true;
    }


   /* Determines whether or not a number already exists within its own small square,
    * returns {true} if not and {false} otherwise:
    */
    public boolean withinSmallSquareCheck(int number, int [][] puzzleBoard, int indexOne, int indexTwo)
    {
        //System.out.printf("We have entered {withinSmallSquareCheck}.\n{indexOne} == %d.\n{indexTwo} == %d.\n{number} == %d.\n", indexOne, indexTwo, number);
        // Works for only the top row of small squares:
        if (indexOne < 3)
        {
            for (int a = 0; a < 3; a++)
            {
                //System.out.printf("{a} == %d.\n", a);
                // Works for only the top, left small square:
                if (indexTwo <= 2)
                {
                    for (int b = 0; b < 3; b++)
                    {
                        //System.out.printf("{a} == %d, and {b} == %d.\n{number} == %d.\n{indexOne} == %d.\n{indexTwo} == %d.\n", a, b, number, indexOne, indexTwo);
                        if (number == puzzleBoard[a][b])
                        {
                            return false;
                        }
                    }
                }

                // Works for only the top, middle small square:
                else if (3 <= indexTwo && indexTwo < 6)
                {
                    for (int c = 3; c < 6; c++)
                    {
                        //System.out.printf("{a} == %d, and {c} == %d.\n{number} == %d.\n{indexOne} == %d.\n{indexTwo} == %d.\n", a, c, number, indexOne, indexTwo);
                        if (number == puzzleBoard[a][c])
                        {
                            //System.out.printf("We have entered {withinSmallSquareCheck} and are about to return {false}. {indexOne} == %d, {indexTwo} == %d, a == %d, c == %d, {number} == %d, {puzzleBoard[%d][%d]} == %d.\n", indexOne, indexTwo, a, c, number, a, c, puzzleBoard[a][c]);
                            return false;
                        }
                    }
                }

                // Works for only the top, right small square:
                else if (6 <= indexTwo && indexTwo < 9)
                {
                    for (int d = 6; d < 9; d++)
                    {
                        if (number == puzzleBoard[a][d])
                        {
                            return false;
                        }
                    }
                }
            }
        }


        // Works for only the middle row of small squares:
        else if (3 <=indexOne && indexOne < 6)
        {
            for (int e = 3; e < 6; e++)
            {
                // Works for only the middle, left small square:
                if (indexTwo <= 2)
                {
                    for (int f = 0; f < 3; f++)
                    {
                        if (number == puzzleBoard[e][f])
                        {
                            return false;
                        }
                    }
                }

                // Works for only the perfectly middle small square:
                if (3 <= indexTwo && indexTwo < 6)
                {
                    for (int g = 3; g < 6; g++)
                    {
                        if (number == puzzleBoard[e][g])
                        {
                            return false;
                        }
                    }
                }

                // Works for only the middle, right small square:
                if (6 <= indexTwo && indexTwo < 9)
                {
                    for (int h = 6; h < 9; h++)
                    {
                        if (number == puzzleBoard[e][h])
                        {
                            return false;
                        }
                    }
                }
            }
        }


        // Works for only the bottom row of small squares:
        else if (6 <= indexOne && indexOne < 9)
        {
            for (int i = 6; i < 9; i++)
            {
                // Works for only the bottom, left small square:
                if (indexTwo < 3)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        if (number == puzzleBoard[i][j])
                        {
                            return false;
                        }
                    }
                }

                // Works for only the bottom, middle small sqaure:
                else if (3 <= indexTwo && indexTwo < 6)
                {
                    for (int k = 3; k < 6; k++)
                    {
                        if (number == puzzleBoard[i][k])
                        {
                            return false;
                        }
                    }
                }

                // Works for only the bottom, right small square:
                else if (6 <= indexTwo && indexTwo < 9)
                {
                    for (int l = 6; l < 9; l++)
                    {
                        if (number == puzzleBoard[i][l])
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}