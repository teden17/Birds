package birds;

//Class that represents a babybird
//Extends THread
public class Baby extends Thread{
	boolean hungry = false;
	int ID;
	int wormsEaten;
	//Constructor that gives an instance of a Baby an ID and initilizes wormseaten to 0 
	public Baby(int ID) {
		this.ID = ID;
		this.wormsEaten = 0;
	}

	@Override
	public void run() {
		while(true){
			sleeps();
			hungry = true;
			while(hungry){
				//get lock to check if there are any worms available
				BirdMain.lock.acquireUninterruptibly();
				if(BirdMain.dish==0){//if 0 signal mami and wait to hear from her again then eat
					System.out.println("babybird "+ID+" want more worms");
					BirdMain.signalMami.release();
					BirdMain.signalMami.acquireUninterruptibly();
					System.out.println("babybird "+ID+" is happy again");
					BirdMain.lock.release();
					eat();
				}
				else{
					BirdMain.lock.release();
					eat();
				}	
			}
		}	
	}

	//method that represents when a baby eats
	private void eat() {
		//get lock to be able to eat
		BirdMain.lock.acquireUninterruptibly();
		BirdMain.dish--;
		wormsEaten++;
		System.out.println("babybird "+ID+" ate its "+wormsEaten+" worm "+" now "+
				BirdMain.dish+ " worms left");
		BirdMain.lock.release();
		hungry = false;
		double eatingTime = Math.random()*2000;
		try {
			Thread.sleep((long) eatingTime);
		} catch (InterruptedException e) {
			System.out.println("could not eat");
		}
		
	}

	//Method that represents when a baby sleeps
	private void sleeps() {
		double naptime = Math.random() * 10000;
		System.out.println("babybird "+ ID+" gonna sleep for a while..");
		try {
			Thread.sleep((long) naptime);
		} catch (InterruptedException e) {
			System.out.println("not tired");
		}	
	}
}
