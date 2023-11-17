
public class Butterfly extends FixedGameElement {

	public Butterfly(int posX, int posY) {		// CONSTRUCTOR OVERLOADING WITH 2 PARAM !
		
		super(posX, posY);		// CAN BE INSTANTIATED WITH -> NEW Butterfly(posX, posY)
	}

	public Butterfly(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !
		
		super(board);		// CAN BE INSTANTIATED WITH -> NEW Butterfly(this)
	}

	protected static int getButterflyCounter() {	// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : Butterfly.getButterflyCounter()
		
		return 3;
	}	
	
	protected static String getPathToImage() {	

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\Butterfly.png";
	}
	
	@Override
	protected String getType() {
		
		return "butterfly";
	}

	@Override
	protected void triggerAction(Board board) {		// 10s SPEED BONUS 

		super.triggerAction(board);
	}
}


