
public class FishRed extends Fish{

	protected static int FishCounter = 1; // STATIC VARIABLE IS USED IN STATIC METHOD !

	protected FishRed(int posX, int posY, int targetX, int targetY) { 	  // CONSTRUCTOR OVERLOADING WITH 4 PARAM ! 

		super(posX, posY, targetX, targetY);	 // CAN BE INSTANTIATED WITH -> NEW FishRed(posX, posY, TARGET X, TARGET Y)
	}

	protected FishRed(int posX, int posY, Board board) {			// CONSTRUCTOR OVERLOADING WITH 3 PARAM ! 

		super(posX,posY,board); 		// CAN BE INSTANTIATED WITH -> NEW FishRed(posX, posY, this)
	}

	protected FishRed(Board board) {	// CONSTRUCTOR OVERLOADING WITH 1 PARAM ! 

		super(board);		// CAN BE INSTANTIATED WITH -> NEW FishRed(this)
	}

	protected static int getFishCounter() {		// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : FishRed.getFishRedCounter()

		return FishCounter;
	}	

	protected static String getPathToImage() {		

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\FishRed.png";
	}

	@Override
	protected String getType() {

		return "fishRed";
	}

	@Override
	protected void move(Board board) {

		super.move(board);

		final int malusSpeed=2;
		final int BonusSpeed=2;


		// >>>>>>>>>>>>>>[FISH RED SPEED ]<<<<<<<<<<<<<<<

		if(board.temperatureNumber == 1) // 1 == COLD TEMPERATURE
			setSpeed(defaultSpeed-malusSpeed);
		if(board.temperatureNumber == 2) // 2 == DEFAULT TEMPERATURE 
			setSpeed(defaultSpeed);
		if(board.temperatureNumber == 3) // 3 == HOT TEMPERATURE
			setSpeed(defaultSpeed+BonusSpeed);

		// >>>>>>>>>>>>>>[FISH RED MOVE BEHAVIOR, (PREDATOR FISH) ]<<<<<<<<<<<<<<<
		
		for(Fish f1: board.myFishList) {
			for(Fish f2: board.myFishList) {

				if(f1.getType() == "fishRed" && f2.getType() != "fishRed") {
					if (f2.getPosX() >= 0 && f2.getPosY() >= 0 && f2.getPosX() < board.B_WIDTH && f2.getPosY() < board.B_HEIGHT) { 			// ONLY IF FISH IS IN BOARD !

						board.CheckIfPositionIsValid(f1.getTargetX(), f1.getTargetY());
						
						f1.setTargetX(f2.getPosX());
						f1.setTargetY(f2.getPosY());	
						
					}
				}
			}
		}
	}
}

