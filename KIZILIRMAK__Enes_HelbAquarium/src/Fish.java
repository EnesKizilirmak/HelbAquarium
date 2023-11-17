import java.util.ArrayList;
import java.util.Collections;


public abstract class Fish{



	protected boolean fishCanMove = true; // FOR EACH FISH TYPE WE CAN DO IT FOR EXEMPLE : f.fishCanMove = false; (this fish will stop)

	protected int defaultSpeed=3;		 // DEFAULT SPEED
	protected int speed;	// FISHES SPEED IN GAME !

	protected final int MaxSpeed = defaultSpeed*2; 	   // MAXIMAL FISH SPEED

		
//	protected int speed = defaultSpeed;				  // SPEED VARIABLE

	protected int posX;				 // FISH posX IN BOARD
	protected int posY;				// FISH posY IN BOARD
	
	private int targetX;		  // FISH TARGET X 
	private int targetY;	     // FISH TARGET Y 

	protected Fish(int posX, int posY, int targetX, int targetY) {  // CONSTRUCTOR OVERLOADING WITH 4 PARAM !

		this.posX = posX;		 // FOR EXEMPLE : 'FishBlue' CAN BE INSTANTIATED WITH -> NEW FishBlue(posX, posY, TARGET X, TARGET Y)
		this.posY = posY;
	}

	protected Fish(int posX, int posY, Board board) { 	// CONSTRUCTOR OVERLOADING WITH 3 PARAM !

		this.posX = posX;		// FOR EXEMPLE : 'FishRed' CAN BE INSTANTIATED WITH -> NEW FishRed(posX, posY, this)
		this.posY = posY;
				
		this.targetX = board.getRandomPosFor('X');
		this.targetY = board.getRandomPosFor('Y');
	}
	
	protected Fish(Board board)	{		// CONSTRUCTOR OVERLOADING WITH 1 PARAM ! 
	
		this.posX = board.getRandomPosFor('X');		// FOR EXEMPLE : 'FishPurple' CAN BE INSTANTIATED WITH -> NEW FishPurple(this)
		this.posY = board.getRandomPosFor('Y');
		
		this.targetX = board.getRandomPosFor('X');
		this.targetY = board.getRandomPosFor('Y');
	}
	
	protected int getPosX() {

		return posX;
	}
//
	protected int getPosY() {

		return posY;
	}

	protected void setPosX(int newPosition) {

		posX = newPosition;
	}

	protected void setPosY(int newPosition) {

		posY = newPosition;
	}

	protected int getTargetX() {

		return targetX;
	}

	protected void setTargetX(int targetX) {

		this.targetX = targetX;
	}

	protected int getTargetY() {

		return targetY;
	}

	protected void setTargetY(int targetY) {

		this.targetY = targetY;
	}

	
	protected int getSpeed() {
		
		return speed;  // IS BY DEFAULT SPEED !
	}

	protected void setSpeed(int speed) {

		this.speed = speed;
	}

		
	
	protected void move(Board board) {

		if (fishCanMove == true) {		

		
			// >>>>>>>>>>>>>>|CREATION OF ARRAYLIST|<<<<<<<<<<<<<<<

			ArrayList<Integer> myListPosX = new ArrayList<Integer>();
			ArrayList<Integer> myListPosY = new ArrayList<Integer>();
			ArrayList<Double> myDistanceList = new ArrayList<Double>();


	//		for(Fish f: board.myFishList) {
	//			
	//		}
			
			// >>>>>>>>>>>>>>|NESTED FOR, TO GET EVERY POSSIBILITIES|<<<<<<<<<<<<<<<

			for(int i=-1; i<= 1; i++) {					// 		| (X-1 & Y-1)  (X-1 & Y)  (X-1 & Y+1) |	 &&	 | (X & Y-1)  (X & Y)  (X & Y+1) | &&  | (X+1 & Y-1)  (X+1 & Y)  (X+1 & Y+1) |	 --> 9 POSSIBILITIES
				for (int j=-1; j <= 1; j++) {

					int testPosX = getPosX() + i* speed;
					int testPosY = getPosY() + j* speed;
					
					
					if (board.CheckIfPositionIsValid(testPosX, testPosY)) {
						
						myListPosX.add(testPosX);			// 		| (X-1 & Y-1)  (X-1 & Y)  (X-1 & Y+1) |	 &&	 | (X & Y-1)  (X & Y)  (X & Y+1) | &&  | (X+1 & Y-1)  (X+1 & Y)  (X+1 & Y+1) |	 --> 9 POSSIBILITIES
						myListPosY.add(testPosY);
					}
				}
			}

			for (int i=0; i<myListPosX.size(); i++) {
				
				Double distanceValue = getDistance(getTargetX(), getTargetY(), myListPosX.get(i), myListPosY.get(i));
				myDistanceList.add(distanceValue);
			}
	
			getMinimumIndexForDistance(myListPosX, myListPosY, myDistanceList);
			
			// >>>>>>>>>>>>>>| IF TARGET IS REACHED, GIVE NEW TARGET|<<<<<<<<<<<<<<<   

			if (getPosX() == getTargetX() && getPosY() == getTargetY()) {   // IF CURRENT POSITION == TARGET

				setTargetX(board.getRandomPosFor('X'));		// GIVE ANOTHER TARGET 
				setTargetY(board.getRandomPosFor('Y'));		// GIVE ANOTHER TARGET
				
	//				System.out.println("TARGET REACHED BY" + getType() + " !!! ");
			}
		}
	}
	private void getMinimumIndexForDistance(ArrayList <Integer> myListPosX, ArrayList <Integer> myListPosY, ArrayList <Double> myDistanceList) {
		
		double min = Collections.min(myDistanceList);
		int IndexMinimum = myDistanceList.indexOf(min);

		setPosX(myListPosX.get(IndexMinimum)); 
		setPosY(myListPosY.get(IndexMinimum)); 
	}

	protected Double getDistance(int posX0, int posY0, int posX1, int posY1) {

		int distanceX = posX1-posX0;
		int distanceY = posY1-posY0;
				
		
		final int exposant2 = 2;

		return Math.sqrt(Math.pow(distanceX, exposant2) + Math.pow(distanceY, exposant2));
	}

	protected abstract String getType();	

}
