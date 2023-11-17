import java.util.Random;

public class Decor extends FixedGameElement {
	
	private final static int finalDecorWidth=60;  		// THE WIDTH OF THE IMAGE IS 60 pixels
	private final static int finalDecorHeight=120;	   //  THE HEIGHT OF THE IMAGE IS 120 pixels
	
	
	protected Decor(int posX, int posY) {		// CONSTRUCTOR OVERLOADING WITH 2 PARAM !

		super(posX, posY);		// CAN BE INSTANTIATED WITH -> NEW Decor(posX, posY)
	}
	
	protected Decor(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !
		
		super(board);		// CAN BE INSTANTIATED WITH -> NEW Decor(this)
	}

	protected static final int getDecorCounter() {		// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : DECOR.getDecorCounter()
		
		final int minimumOneDecorInGame=1;		
		
		final int StartNumber=1;	 // RANDOM IS BETWEEN 1 AND 3
		final int maxDecorInGame=3; // RANDOM IS BETWEEN 1 AND 3
		
		Random random = new Random();
		int numberOfDecorInGame = random.nextInt(maxDecorInGame + minimumOneDecorInGame - StartNumber) + StartNumber;  // A NULBER BETWEEN StartNumber AND maxDecorInGame
				
		return numberOfDecorInGame;   	
	}

	protected static String getPathToImage() {

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\decor.png";
	}

	@Override
	protected String getType() {

		return "decor";
	}

	protected static int getfinalDecorWidth() {

		return finalDecorWidth;    // RETURN FINAL SIZE X OF THE DECOR
	}

	protected static int getfinalDecorHeight() {

		return finalDecorHeight;    // RETURN FINAL SIZE Y OF THE DECOR 
	}

	@Override
	protected void triggerAction(Board board) {
		
		super.triggerAction(board);
	}
}
