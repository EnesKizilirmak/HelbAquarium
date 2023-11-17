
public abstract class FixedGameElement {

	private int posX;
	private int posY;

	

	protected FixedGameElement(int posX, int posY) { // CONSTRUCTOR OVERLOADING WITH 2 PARAM ! 

		this.posX = posX;		// FOR EXEMPLE : 'Insect' CAN BE INSTANTIATED WITH -> NEW Insect(posX, posY)
		this.posY = posY;		
	}

	protected FixedGameElement(Board board) { 	// CONSTRUCTOR OVERLOADING WITH 1 PARAM ! 

		this.posX = board.getRandomPosFor('X');	// FOR EXEMPLE : 'Pellet' CAN BE INSTANTIATED WITH -> NEW Pellet(this)
		this.posY = board.getRandomPosFor('Y');
	}

	protected int getPosX() {
		return posX;
	}

	protected int getPosY() {
		return posY;
	}

	protected void setPosX(int newPosition) {
		posX = newPosition;
	}

	protected void setPosY(int newPosition) {
		posY = newPosition;
	}

	protected abstract String getType();

	protected void triggerAction(Board board) {


		// >>>>>>>>>>>>>>|IF POSITION FISH == EATABLE ELEMENTS POSITION|<<<<<<<<<<<<<<<

		for (Fish f : board.myFishList) {
			for (FixedGameElement elem : board.myEatableElementList) {

				if ((f.getPosX() == elem.getPosX()) && (f.getPosY() == elem.getPosY())) {

					elem.setPosX(board.voidPosition);
					
					if (elem.getType() == "spider") {	// IF ELEM IS A SPIDER BONUS 4 SECONDS
						f.defaultSpeed = 5;
					}	 	
					
					if (elem.getType() == "ladybirds") {	// IF ELEM IS A SPIDER BONUS 6 SECONDS
						f.defaultSpeed = 5;
					}
					
					if (elem.getType() == "butterfly") {// IF ELEM IS A BUTTERFLY BONUS 10 SECONDS
						f.defaultSpeed = 5;
					}
//					
					if (elem.getType() == "pellet") {	// IF ELEM IS A PELLET STOP ALL OTHERS FISHES 10 SECONDS !
					f.fishCanMove = false;
					}
					
				}
			}
		}
	}
}


