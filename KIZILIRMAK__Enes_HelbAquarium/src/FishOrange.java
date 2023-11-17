
public class FishOrange extends Fish {

	protected static int FishCounter = 1; // STATIC VARIABLE IS USED IN STATIC METHOD !
	
	protected FishOrange(int posX, int posY, int targetX, int targetY) { 	 // CONSTRUCTOR OVERLOADING WITH 4 PARAM ! 

		super(posX, posY, targetX, targetY);	 // CAN BE INSTANTIATED WITH -> NEW FishOrange(posX, posY, TARGET X, TARGET Y)
	}

	protected FishOrange(int posX, int posY, Board board) {		// CONSTRUCTOR OVERLOADING WITH 3 PARAM ! 
		
		super(posX,posY,board);   // CAN BE INSTANTIATED WITH -> NEW FishOrange(posX, posY, this)
	}

	protected FishOrange(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !  
		
		super(board);	// CAN BE INSTANTIATED WITH -> NEW FishOrange(this)
	}

	
	protected static int getFishCounter() {		// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : FishOrange.getFishOrangeCounter()

		return FishCounter;
	}	

	protected final static String getPathToImage() {

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\FishOrange.png";
	}

	@Override
	protected final String getType() {

		return "fishOrange";
	}
	
	@Override
	protected void move(Board board) {
		
		super.move(board);
		
		
		setSpeed(defaultSpeed);		   // SET SPEED !
		
	}
}	

