
public class Pellet extends FixedGameElement {

	protected Pellet(int posX, int posY) {	// CONSTRUCTOR OVERLOADING WITH 2 PARAM !

		super(posX, posY);		// CAN BE INSTANTIATED WITH -> NEW Pellet(posX, posY)
	}

	protected Pellet(Board board) {		// CONSTRUCTOR OVERLOADING WITH 1 PARAM !
		
		super(board);		// CAN BE INSTANTIATED WITH -> NEW Pellet(this)
	}
	
	protected static int getPelletCounter() {	// METHOD STATIC (EXPLICATION PRESENTATION + RAPPORT)
		
		return 0;
	}	
	
	protected static String getPathToImage() {

			return "C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\PelletGrey.png";
		
		}

	@Override
	protected String getType() {

		return "pellet";
	}

	@Override
	protected void triggerAction(Board board) {

		super.triggerAction(board);
	}
}
