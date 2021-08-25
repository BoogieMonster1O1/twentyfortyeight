package io.github.boogiemonster1o1.j2048;

import static io.github.boogiemonster1o1.j2048.App.getFirstDigit;
import org.junit.jupiter.api.Test;

class AppTest {
	@Test
	public void testFrame() throws InterruptedException {
		App app = new App(4);
		app.getFrame()[1][1] = 1024;
		app.getFrame()[1][2] = 64;
		app.getFrame()[2][1] = 128;
		app.getFrame()[3][0] = 2048;
		app.printFrame();
		Thread.sleep(3000L);
		app.getFrame()[2][0] = 256;
		app.printFrame();
		Thread.sleep(3000L);
		app.getFrame()[1][0] = 256;
		app.printFrame();
		Thread.sleep(2000L);
	}

	@Test
	public void testRandom() {
		int fours = 0;
		int cases = 500;
		for (int i = 0; i < cases; i++) {
			int dig = getFirstDigit();
			if (dig == 4) {
				fours++;
			}
			System.out.print(dig);
			System.out.print(" ");
		}
		System.out.println("\nFours: " + fours);
		System.out.println("Chance of four: " + ((double) fours / cases * 100.0) + "%");
	}
}
