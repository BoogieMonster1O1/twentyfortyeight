package io.github.boogiemonster1o1.j2048;

import java.util.Scanner;

public class App {
	// Modes
	private static final int CLASSIC = 1;
	private static final int ENDLESS = 2;
	private static final int POINTS = 3;
	private static final int NUMBER = 4;
	private static final int ANTI = 5;

	private final int[][] frame;
	private final int size;
	private final int mode;
	private int extraData;

	public App(int size, int mode, int extraData) {
		this.frame = new int[size][size];
		this.size = size;
		this.mode = mode;
		this.extraData = extraData;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("Welcome to J2048");
		System.out.println("An open-source all-rights-reserved reimplementation of the popular game");
		System.out.println("***********************************************************");
		System.out.println("\tAuthor: Shrish Deshpande");
		System.out.println("\tGitHub: https://github.com/BoogieMonster1O1/J2048");
		System.out.println("***********************************************************");
		System.out.println();
		System.out.println("The concept of this are as follows:");
		System.out.println("1. Adjacent numbers will get added up");
		System.out.println("2. Numbers will occupy the space of zeroes (if available)");
		System.out.println("4. You will start with two 2s, two 4s or one 2 and one 4. Each move adds another 2 or 4 to the matrix. The position is decided randomly.");
		System.out.println("5. If there are no zeroes remaining and no adjacent numbers that can be added, the game is lost");
		System.out.println();
		System.out.println("Enter the size of the matrix (Must be between 4 and 7) (Recommended: 4)");
		int size = sc.nextInt();
		if (size < 4) {
			System.out.println("Size is too small. Must be at least 4");
			return;
		} else if (size > 7) {
			System.out.println("Size is too large. Must be at most 7");
			return;
		}

		System.out.println("Enter your choice");
		System.out.println("1 - Classic 2048 - Reach the 2048 number to win");
		System.out.println("2 - Endless 2048 - Keep matching numbers endlessly until you lose (endless version of classic)");
		System.out.println("3 - Points 2048 - Reach a certain number of points to win");
		System.out.println("4 - Number 2048 - Reach a certain number to win");
		System.out.println("5 - Anti 2048 - Lose before you reach a certain number of points to win");

		int choice = sc.nextInt();
		int extra;

		switch (choice) {
			case '1':
			case '2':
				extra = 0;
				break;
			case '3':
			case '5':
				System.out.println("Enter number of points");
				int points = sc.nextInt();
				if (points < 0) {
					System.out.println("Negative values not allowed");
					return;
				}
				extra = points;
				break;
			case '4':
				System.out.println("Enter the number to reach");
				int num = sc.nextInt();
				if (num < 0) {
					System.out.println("Negative values not allowed");
					return;
				}
				extra = num;
				break;
			default:
				System.out.println("Invalid input entered for choice");
				return;
		}

		App app = new App(size, choice, extra);
	}

	public void init() {
	}

	public void printFrame() {
		System.out.println("\f");
		int places = getPlaces(getGreatest(this.getFrame()));
		for (int i = 0; i < this.size; i++) {
			System.out.print("| ");
			for (int j = 0; j < this.size; j++) {
				int c = this.frame[i][j];
				System.out.print(c);
				for (int d = places - getPlaces(c) + 1; d > 0; d--) { // + 1 for extra space
					System.out.print(" ");
				}
				System.out.print("| ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public int[][] getFrame() {
		return frame;
	}


	// Math functions
	public static int getPlaces(int num) {
		int places = 0;
		while (num >= 10) {
			num /= 10;
			places++;
		}
		return places;
	}

	public static int getGreatest(int[][] arr) {
		int g = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] > g) {
					g = arr[i][j];
				}
			}
		}
		return g;
	}


	// Random number functions
	/*
	 * max - upper bound (exclusive)
	 * min - lower bound (inclusive)
	 */
	public static int random(int min, int max) {
		return (int) (min + (Math.random() * (max - min)));
	}

	public static int random(int max) {
		return random(0, max);
	}

	public static int getFirstDigit() {
		int random = random(5); // Returns a random number greater than or equal to 0 and less than 5
		return random > 2 ? 4 : 2; // Chance of 4 is 2/5
	}
}
