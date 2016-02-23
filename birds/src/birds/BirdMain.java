package birds;

import java.util.concurrent.Semaphore;

public class BirdMain {
	//W is number of worms that mami returns with
	public static int W = 10;
	//dish is the variable that represents nr of worms available to eat
	public static int dish = W;
	//semaphore to signal mami, used as flag
	public static Semaphore signalMami = new Semaphore(0, true);
	//used as lock for shared variables
	public static Semaphore lock = new Semaphore(1,true);

	public static void main(String[] args) {
		MamaBird mama = new MamaBird();
		mama.start();
		int numberofBabies = 100;
		for(int i = 1; i <= numberofBabies; i++){
			Baby baby = new Baby(i);
			baby.start();
		}
		

	}

}
