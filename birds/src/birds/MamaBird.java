package birds;

public class MamaBird extends Thread{

	@Override
	public void run() {
		resting();
		
	}

	private void collectDish() {
		BirdMain.dish = BirdMain.W;
		System.out.println("Mommy just got some more worms ");
		System.out.println();
		System.out.println();
		BirdMain.signalMami.release();
		resting();
		
	}

	private void resting() {
		BirdMain.signalMami.acquireUninterruptibly();
		System.out.println("Mommy will feed you guys!");
		collectDish();
	}

}
