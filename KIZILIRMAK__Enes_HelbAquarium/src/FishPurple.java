
public class FishPurple extends Fish {

	protected static int FishCounter =1; // STATIC VARIABLE IS USED IN STATIC METHOD !

	protected FishPurple(int posX, int posY, int targetX, int targetY) { 	 // CONSTRUCTOR OVERLOADING WITH 4 PARAM !  

		super(posX, posY, targetX, targetY);	 // CAN BE INSTANTIATED WITH -> NEW FishPurple(posX, posY, TARGET X, TARGET Y)
	}

	protected FishPurple(int posX, int posY, Board board) {		// CONSTRUCTOR OVERLOADING WITH 3 PARAM ! 

		super(posX,posY, board); 		// CAN BE INSTANTIATED WITH -> NEW FishPurple(posX, posY, this)
	}

	protected FishPurple(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM ! 

		super(board);	// CAN BE INSTANTIATED WITH -> NEW FishPurple(this)
	}

	protected static final int getFishCounter() {		// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : FishPurple.getFishPurpleCounter()

		return FishCounter;
	}

	protected static String getPathToImage() {			// STATIC (EXPLICATION PRESENTATION)

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\FishPurple.png";
	}

	@Override
	protected String getType() {

		return "fishPurple";
	}

	@Override
	protected void move(Board board) {

		super.move(board);

		// >>>>>>>>>>>>>>[FISH RED SPEED ]<<<<<<<<<<<<<<<

		//			int speedByDecorNumber = Decor.getDecorCounter();
		//			int fishPurpleSpeed = defaultSpeed + speedByDecorNumber;	// J'ai décidé de commenté ma condition car, elle ne fonctionne pas parfaitement !
		//			setSpeed(fishPurpleSpeed);

		setSpeed(defaultSpeed);


		// >>>>>>>>>>>>>>[FISH PURPLE MOVE BEHAVIOR, (CAREFUL FISH) ]<<<<<<<<<<<<<<<

		for(Fish f1: board.myFishList) {
			for(Fish f2: board.myFishList) {

				if (f1.getType() == "fishRed" && f2.getType() == "fishPurple") {

//					if (f1.getPosX() == f2.getPosX()) {				// HORIZONTAL DIRECTION
//						
//						f2.setTargetX(board.getRandomPosFor('X'));
//					}
//					
//					if (f1.getPosY() == f2.getPosY()) {
//						
//						f2.setTargetY(board.getRandomPosFor('Y'));	// VERTICAL DIRECTION 
//					}
				}
			}
		}
	}
}
