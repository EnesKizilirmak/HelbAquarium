
public class FishBlue extends Fish {

	protected static int FishCounter = 1; // STATIC VARIABLE IS USED IN STATIC METHOD !

	protected FishBlue(int posX, int posY, int targetX, int targetY) { 	  // CONSTRUCTOR OVERLOADING WITH 4 PARAM ! 

		super(posX, posY, targetX, targetY);	 // CAN BE INSTANTIATED WITH -> NEW FishBlue(posX, posY, TARGET X, TARGET Y)
	}

	protected FishBlue(int posX, int posY, Board board) {			// CONSTRUCTOR OVERLOADING WITH 3 PARAM ! 

		super(posX,posY,board); 		// CAN BE INSTANTIATED WITH -> NEW FishBlue(posX, posY, this)
	}

	protected FishBlue(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !  

		super(board);		// CAN BE INSTANTIATED WITH -> NEW FishBlue(this)
	}

	protected static final int getFishCounter() { 	//² STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : FishBlue.getFishBlueCounter()

		return FishCounter;
	}	

	protected static String getPathToImage() {

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\FishBlue.png";
	}

	@Override
	protected String getType() {

		return "fishBlue";
	}

	@Override
	protected void move(Board board) {

		super.move(board);
		
		
		// >>>>>>>>>>>>>>[FISH BLUE SPEED  ]<<<<<<<<<<<<<<<

			final int fishBlueDefaultSpeedBonus=0;					 // SET SPEED !
			setSpeed(defaultSpeed + fishBlueDefaultSpeedBonus); 	// FISH BLUE'S DEFAULT SPEED IS FASTER THAN OTHERS ! 
		
		
			
		// >>>>>>>>>>>>>>[FISH BLUE MOVE BEHAVIOR, (SOCIABLE FISH)  ]<<<<<<<<<<<<<<<

		for(Fish f1: board.myFishList) {
			for(Fish f2: board.myFishList) {
				
				if(f1.getType() == "fishBlue" && f2.getType() == "fishBlue" || f1.getType() == "fishBlue" && f2.getType() == "fishPurple" ) {		// If THEY ARE BLUE FISHES OR PURPLE !
					if (f2.getPosX() >= 0 && f2.getPosY() >= 0 && f2.getPosX() < board.B_WIDTH && f2.getPosY() < board.B_HEIGHT) { 			// ONLY IF THIS INSECT IS IN BOARD !

					f1.setTargetX(f2.getTargetX());								 								  								   // F1 HAS THE SAME TARGET X THAN F2 ! 
					f1.setTargetY(f2.getTargetY());						 	      																  // F1 HAS THE SAME TARGET X THAN F2 ! 
					
				
					
//					Le plus proche TODO !
					}
				}	
			}
		}
		
	}
}

