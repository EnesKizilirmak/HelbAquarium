
public class Ladybirds extends FixedGameElement {

	
	protected Ladybirds(int posX, int posY) {	// CONSTRUCTOR OVERLOADING WITH 2 PARAM !

		super(posX, posY);		// CAN BE INSTANTIATED WITH -> NEW Ladybirds(posX, posY)
	}
	
	protected Ladybirds(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !
		
		super(board);		// CAN BE INSTANTIATED WITH -> NEW Ladybirds(this)
	}
	
	protected static int getLadybirdsCounter() {	// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : Ladybirds.getLadybirdsCounter()
		
		return 3;
	}	
	
	protected static String getPathToImage() {	

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\Ladybirds.png";
	}

	@Override
	protected String getType() {

		return "ladybirds";
	}

	@Override
	protected void triggerAction(Board board) {	// 6s SPEED BONUS 
		
		super.triggerAction(board);
		
	
	}
}
