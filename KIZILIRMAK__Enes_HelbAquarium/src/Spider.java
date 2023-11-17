
public class Spider extends FixedGameElement {

	public Spider(int posX, int posY) {		// CONSTRUCTOR OVERLOADING WITH 2 PARAM !
		
		super(posX, posY);		// CAN BE INSTANTIATED WITH -> NEW Spider(posX, posY)
	}

	public Spider(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !
		
		super(board);		// CAN BE INSTANTIATED WITH -> NEW Spider(this)
	}	

	protected static int getSpiderCounter() {	// STATIC WE CAN DIRRECTLY INSTANTIATE WITH CLASS NAME EX : Spider.getSpiderCounter()
		
		return 3;
	}	
	
	protected static String getPathToImage() {	

		return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\Spider.png";
	}
	
	@Override
	protected String getType() {

		return "spider";
	}

	@Override
	protected void triggerAction(Board board) {		// 4s SPEED BONUS 
		
		super.triggerAction(board);

	}

}
