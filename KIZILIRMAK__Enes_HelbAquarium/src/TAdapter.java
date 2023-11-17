import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TAdapter implements KeyListener {

	private Board board;

	public TAdapter(Board board) {
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// System.out.println("Key pressed: " + e.getKeyCode()); // TO KNOW THE ASCII CODE

		// >>>>>>>>>>>>>>|PAUSE GAME !|<<<<<<<<<<<<<<<
		if ((e.getKeyCode() == 80)) { // KEY PRESSED --> (P)
			System.out.println("----->>>>>>>>>>>>>>|GAME PAUSED, PRESS SPACE TO RESUME !|<<<<<<<<<<----- ");
			board.timer.stop();
		}
		// >>>>>>>>>>>>>>|CONTINUE GAME ! <<<<<<<<<<<<<<<
		if ((e.getKeyCode() == 32)) { // KEY PRESSED --> (SPACE)
			board.timer.restart(); // RESUME GAME					
		}
		// >>>>>>>>>>>>>>|LEAVE GAME !|<<<<<<<<<<<<<<<
		if (e.getKeyCode() == 27) { // KEY PRESSED (ESC)
			System.out.println("----->>>>>>>>>>>>>>|GAME LEFT !|<<<<<<<<<<-----");
			System.exit(0); // CLOSE THE WINDOW WITH ESC
		}
		// >>>>>>>>>>>>>>|RESTART GAME !|<<<<<<<<<<<<<<<
		if ((e.getKeyCode() == 96 || e.getKeyCode() == 48)) { // KEY PRESSED --> (0)
			board.temperatureNumber = 2; // RESET TEMPERATURE BACKGROUND IMAGE
			board.createGameElements();	
		}

		for (Fish f : board.myFishList) {

			// >>>>>>>>>>>>>>|AQUARIUM TEMPERATURE IS COLD ! <<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 97 || e.getKeyCode() == 49)) { // KEY PRESSED --> (1)
				board.temperatureNumber = 1; // SET BACKGROUND IMAGE TO COLD
				if(f.getType()=="fishRed") {
				}
			}
			// >>>>>>>>>>>>>>|AQUARIUM TEMPERATURE IS BY DEFAULT !|<<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 98 || e.getKeyCode() == 50)) { // KEY PRESSED --> (2)
				board.temperatureNumber = 2; // SET BACKGROUND IMAGE TO DEFAULT
				if(f.getType()=="fishRed") {
				}
			}
			// >>>>>>>>>>>>>>|AQUARIUM TEMPERATURE IS HOT !|<<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 99 || e.getKeyCode() == 51)) { // KEY PRESSED --> (3)
				board.temperatureNumber = 3; // SET BACKGROUND IMAGE TO HOT
				if(f.getType()=="fishRed") {
				}
			}
		}

		// >>>>>>>>>>>>>>|ADD AN INSECT RANDOMLY !|<<<<<<<<<<<<<<<
		if ((e.getKeyCode() == 100 || e.getKeyCode() == 52)) { // KEY PRESSED --> (4)
			System.out.println("----->>>>>>>>>>>>>>|INSECT ADDED !|<<<<<<<<<<----- ");

			int randomStartNumber = 1; // RANDOM IS BETWEEN 1 AND 3
			int numberOfInsectType = 3; // RANDOM IS BETWEEN 1 AND 3

			int randInsect = (int) (Math.random() * numberOfInsectType) + randomStartNumber; // ADD A RANDOM FISH IN GAME BETWEEN 4 TYPES OF FISH

			if (randInsect == 1)	// ADD LADYBIRDS
				board.myEatableElementList.add(new Ladybirds(board.getRandomPosFor('X'), board.getRandomPosFor('Y')));
			if (randInsect == 2)	// ADD SPIDER
				board.myEatableElementList.add(new Spider(board.getRandomPosFor('X'), board.getRandomPosFor('Y')));
			if (randInsect == 3)	// ADD BUTTERFLY
				board.myEatableElementList.add(new Butterfly(board.getRandomPosFor('X'), board.getRandomPosFor('Y')));
			
		}

		// >>>>>>>>>>>>>>|ADD A PELLET RANDOMLY !|<<<<<<<<<<<<<<<
		if ((e.getKeyCode() == 101 || e.getKeyCode() == 53)) { // KEY PRESSED --> (5)
			System.out.println("----->>>>>>>>>>>>>>|PELLET ADDED !|<<<<<<<<<<----- ");
			board.myEatableElementList.add(new Pellet(board.getRandomPosFor('X'), board.getRandomPosFor('Y') ));
		}	
		// >>>>>>>>>>>>>>|ADD A FISH RANDOMLY !|<<<<<<<<<<<<<<<
		if ((e.getKeyCode() == 105 || e.getKeyCode() == 57)) { // KEY PRESSED --> (9)
			System.out.println("----->>>>>>>>>>>>>>|FISH ADDED !|<<<<<<<<<<----- ");

			int randomStartNumber = 1; // RANDOM IS BETWEEN 1 AND 4
			int numberOfFishType = 4; // RANDOM IS BETWEEN 1 AND 4

			int randFish = (int) (Math.random() * numberOfFishType) + randomStartNumber; // ADD A RANDOM FISH IN GAME BETWEEN 4 TYPES OF FISH

			if (randFish == 1)	// ADD ORANGE FISH
				board.myFishList.add(new FishOrange(board.getRandomPosFor('X'), board.getRandomPosFor('Y'), board.getRandomPosFor('X'), board.getRandomPosFor('Y')));		
			if (randFish == 2)  // ADD RED FISH 
				board.myFishList.add(new FishRed(board.getRandomPosFor('X'), board.getRandomPosFor('Y'), board.getRandomPosFor('X'), board.getRandomPosFor('Y')));
			if (randFish == 3)	// ADD PURPLE FISH 
				board.myFishList.add(new FishPurple(board.getRandomPosFor('X'), board.getRandomPosFor('Y'), board.getRandomPosFor('X'), board.getRandomPosFor('Y')));
			if (randFish == 4)	// ADD BLUE FISH 
				board.myFishList.add(new FishBlue(board.getRandomPosFor('X'), board.getRandomPosFor('Y'), board.getRandomPosFor('X'), board.getRandomPosFor('Y')));
		}

		for (Fish f : board.myFishList) {
			for (FixedGameElement elem : board.myEatableElementList) {

				// >>>>>>>>>>>>>>|INSECTIVORE MODE !|<<<<<<<<<<<<<<<
				if ((e.getKeyCode() == 102 || e.getKeyCode() == 54)) {  // HOLD THE KEY --> (6), (IL FAUT MAINTENIR LA TOUCHE --> (6) ) !
					System.out.println("----->>>>>>>>>>>>>>|INSECTIVORE MODE IS ON !!!|<<<<<<<<<<----- ");

					if (elem.getType() == "ladybirds" || elem.getType() == "spider" || elem.getType() == "butterfly"  ) { // IF ELEM IS AN INSECT
						if (elem.getPosX() >= 0 && elem.getPosY() >= 0 && elem.getPosX() < board.B_WIDTH && elem.getPosY() < board.B_HEIGHT) { 			// ONLY IF THIS INSECT IS IN BOARD !

							f.setTargetX(elem.getPosX());  // SET FISH TARGET TO INSECT POSITION
							f.setTargetY(elem.getPosY()); // SET FISH TARGET TO INSECT POSITION
						}
					}
				}
				// >>>>>>>>>>>>>>|PELLET MODE !|<<<<<<<<<<<<<<<
				if ((e.getKeyCode() == 103 || e.getKeyCode() == 55)) { // HOLD THE KEY --> (7), (IL FAUT MAINTENIR LA TOUCHE --> (7) ) !
					System.out.println("----->>>>>>>>>>>>>>|PELLET MODE IS ON !!!|<<<<<<<<<<----- ");

					if (elem.getType() == "pellet") { 						 // IF ELEM IS AN PELLET
						if (elem.getPosX() >= 0 && elem.getPosY() >= 0) { 	// ONLY IF THIS PELLET IS IN BOARD !
							
							f.setTargetX(elem.getPosX());  // SET FISH TARGET TO PELLET POSITION
							f.setTargetY(elem.getPosY()); // SET FISH TARGET TO PELLET POSITION
						}
					}
				}
			}
			for (Fish f2 : board.myFishList) {

				// >>>>>>>>>>>>>>|REPRODUCTION MODE !|<<<<<<<<<<<<<<<
				if ((e.getKeyCode() == 104 || e.getKeyCode() == 56)) { // HOLD THE KEY --> (8), (IL FAUT MAINTENIR LA TOUCHE --> (8) ) !
					System.out.println("----->>>>>>>>>>>>>>|REPRODUCTION MODE IS ON !!!|<<<<<<<<<<----- ");

					if (f != f2) { 								 // IF NOT SAME FISH IN LIST
						if (f.getType().equals(f2.getType())) { // AND IF THEY ARE SAME FISH TYPE (COLOR)

							f.setTargetX(f2.getTargetX());  // THE TARGET OF FISH 1 BECOMES THE TARGET OF FISH 2
							f.setTargetY(f2.getTargetY()); // THE TARGET OF FISH 1 BECOMES THE TARGET OF FISH 2
						}
					}
				}



				// >>>>>>>>>>>>>>|FISH RED MAGNETIC MODE !|<<<<<<<<<<<<<<<
				if ((e.getKeyCode() == 88 )) { 	// HOLD THE KEY --> (X), (IL FAUT MAINTENIR LA TOUCHE --> (X) ) !
					System.out.println("----->>>>>>>>>>>>>>|FISH RED MAGNETIC MODE IS ON !!!|<<<<<<<<<<----- ");

					if (f.getType() == "fishRed" && f2.getType() != "fishRed") {			// NOT RED FISH 
							
						f2.setTargetX(f.getPosX());
						f2.setTargetY(f.getPosY());
						
//						f.setSpeed(0);	
					}
				}
			}


			// >>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT RED !|<<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 82)) { // KEY PRESSED --> (R)
				System.out.println("----->>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT RED !!!|<<<<<<<<<<-----");
				if (f.getType() != "fishRed")
					f.speed=0;
				else 
					f.speed = f.defaultSpeed;
			}
			// >>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT BLUE !|<<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 66)) { // KEY PRESSED --> (B)
				System.out.println("----->>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT BLUE !!!|<<<<<<<<<<-----");
				if (f.getType() != "fishBlue") 
					f.speed=0;
				else 
					f.speed = f.defaultSpeed;
			}
			// >>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT PURPLE !|<<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 77)) { // KEY PRESSED --> (M)
				System.out.println("----->>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT PURPLE !!!|<<<<<<<<<<-----");
				if (f.getType() != "fishPurple") 
					f.speed=0;
				else 
					f.speed = f.defaultSpeed;
			}
			// >>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT ORANGE !|<<<<<<<<<<<<<<<
			if ((e.getKeyCode() == 79)) { // KEY PRESSED --> (O)
				System.out.println("----->>>>>>>>>>>>>>|STOP ALL FISHES, EXCEPT ORANGE !!!|<<<<<<<<<<-----");
				if (f.getType() != "fishOrange") 
					f.speed=0;
				else 
					f.speed = f.defaultSpeed;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		// NOT USED BUT I HAVE TO WRITTE HERE BECAUSE I IMPLEMENTED KEYLISTENER INTERFACE !
		// WITH INTERFACES WE HAVE TO IMPLEMENTS ALL ABSTRACT METHODS EVEN IF THESE ARE NOT USED 
	}

	@Override
	public void keyTyped(KeyEvent e) {			// NOT USED BUT I HAVE TO WRITTE HERE BECAUSE I IMPLEMENTED KEYLISTENER INTERFACE !
		// WITH INTERFACES WE HAVE TO IMPLEMENTS ALL ABSTRACT METHODS EVEN IF THESE ARE NOT USED 
	}
}


