import java.util.*;

import SudokuPackage.SudokuMethods;



/* A program class that uses recursive backtracking to solve Sudoku puzzles: 
 * 
 * List of helper methods and what they do:
 * 1. setUpPuzzleBoard(): Creates the Sudoku puzzle board for the user by using a [9]x[9] 2-D {int} array and converting the
 *                        user-provided String number(s) into {int}s and placing them into the 2-D {int} array,
 *                        {setUpPUzzleBoard()} has a helper method:
 * 1a. getNumber(): Iterartively gets numbers from the user for filling the puzzle board by using iteration, this method calls on
 *                   {printPuzzleBoard()} after every time it adds a number to the puzzle board.  Blank spaces are filled
 *                   by 0s.
 * 
 * 2. printPuzzleBoard(): Prints the beginning, current, and/or future configuration of the Sudoku puzzleBoard
 * 
 * 3. tryNumber(): Checks whether or not a number is valid relative to the numbers that are to the right and/or left of it and the 
 *                 numbers that are above and/or below it, returns {true} or {false} depending on whther or not the number is valid,
 *                 {tryNumber()} has two helper methods:
 *                 
 *                 if (horizontalCheck(i) == false){return false;}
 *                 else if (verticalCheck(i) == false){return false;}
 *                 return true; 
 * 
 * 3a. horizontalCheck(): Determines whether or not the numbers that are to the right and/or left of the number that is currently
 *                        being processed by {tryNumber()} are the same or not, returns {true} or {false} depending on whether or not
 *                        the number is valid
 * 3b. verticalCheck(): Determines whether or not the numbers that are above and/or below the number that is currently being
 *                      processed by {tryNumber()} are the smae or not, returns {true} or {false} depending on whther or not the 
 *                      number is valid
 * 
 * 4. removeNumber(): Removes the most recently added number from the 2-D {int} array only after all numbers that are between and include 1 and 9
 *                    have been found to not be valid numbers, so only after {tryNumber()} has returned {false} for all of the
 *                    possible numbers
*/
public class Sudoku
{
    public static void main(String [] args)
    {
        SudokuMethods s = new SudokuMethods();

        int [][] puzzleBoard = s.setUpPuzzleBoard();

        if (s.setUpPuzzleBoard(puzzleBoard) == true)
        {
            s.printPuzzleBoard(puzzleBoard);
        }

        else
        {
            System.out.printf("Could not find a solution.\n");
        }
    }
}