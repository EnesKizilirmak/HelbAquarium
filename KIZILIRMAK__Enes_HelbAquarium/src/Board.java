import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	protected Timer timer;
	protected boolean inGame = true;

	// >>>>>>>>>>>>>>|GAME BOARD|<<<<<<<<<<<<<<<

	protected final int B_WIDTH = 1200;
	protected final int B_HEIGHT = 600;
	protected int DOT_SIZE = 30;
	protected int DELAY = 10;

	// >>>>>>>>>>>>>>|BACKGROUND IMAGE|<<<<<<<<<<<<<<< 

	protected int temperatureNumber = 2;			  // 2 == DEFAULT TEMPERATURE

	protected Image coldBackGroundImage; 			// if temperatureNumber==1
	protected Image defaultBackGroundImage; 	   // if temperatureNumber==2
	protected Image hotBackGroundImage; 		  // if temperatureNumber==3

	// >>>>>>>>>>>>>>|BIG VALUE TO MOVE GAME ELEMENTS OUTSIDE OF THE AQUARIUM (VOID)|<<<<<<<<<<<<<<<

	protected final int voidPosition = -999;   // NEGATIVE VALUE, TOP PUT OUTSIDE OF AQUARIUM


	// >>>>>>>>>>>>>>|CREATION OF ARRAYLIST AND HASHMAP|<<<<<<<<<<<<<<<

	protected ArrayList<Fish> myFishList;  					 // ARRAYLIST FOR FISHES
	protected HashMap<String, ImageIcon> myFishHashMap; 		// DATA STRUCTURE THAT CONTAINS A STRING AND AN ICONIMAGE FOR FISHES

	protected ArrayList<Fish> myFishListCopy;  					 // ARRAYLIST FOR FISHES REPRODUCTION


	protected ArrayList<FixedGameElement> myEatableElementList; 	 // ARRAYLIST FOR EATABLE ELEMENTS
	private HashMap<String, ImageIcon> myFixedGameElementHashMap;   // DATA STRUCTURE THAT CONTAINS A STRING AND AN ICONIMAGE FOR EATABLE ELEMENTS

	protected ArrayList<Decor> myDecorList; 				// ARRAYLIST FOR DECOR
	private HashMap<String, ImageIcon> myDecorHashMap; // DATA STRUCTURE THAT CONTAINS A STRING AND AN ICONIMAGE FOR DECOR

	// >>>>>>>>>>>>>>|MY KEYBOARD CLASS|<<<<<<<<<<<<<<<

	private TAdapter myTAdapter;


	protected Board() {

		initInput();
		initBoard();
	}

	private void initBoard() {

		setFocusable(true);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		loadImages();
		initGame();
	}

	private void initInput() {
		myTAdapter = new TAdapter(this);
		addKeyListener(myTAdapter);
	}	

	private void loadImages() {

		// >>>>>>>>>>>>>>|CREATION HASH MAP|<<<<<<<<<<<<<<<

		myFishHashMap = new HashMap<String, ImageIcon>();
		myFixedGameElementHashMap = new HashMap<String, ImageIcon>();
		myDecorHashMap = new HashMap<String, ImageIcon>();

		// >>>>>>>>>>>>>>|AQUARIUM BACKGROUND IMAGES|<<<<<<<<<<<<<<<

		ImageIcon def = new ImageIcon("C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\defaultBackground.jpg");
		defaultBackGroundImage = def.getImage().getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_DEFAULT);

		ImageIcon cold = new ImageIcon("C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\coldBackground.jpg");
		coldBackGroundImage = cold.getImage().getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_DEFAULT);

		ImageIcon hot = new ImageIcon("C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\hotBackground.jpg");
		hotBackGroundImage = hot.getImage().getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_DEFAULT);

		// >>>>>>>>>>>>>>|IMAGE ICON FOR ALL FISHES|<<<<<<<<<<<<<<<

		ImageIcon forange = new ImageIcon(FishOrange.getPathToImage());
		myFishHashMap.put("fishOrange", forange);

		ImageIcon fpurple = new ImageIcon(FishPurple.getPathToImage());
		myFishHashMap.put("fishPurple", fpurple);

		ImageIcon fblue = new ImageIcon(FishBlue.getPathToImage());
		myFishHashMap.put("fishBlue", fblue);

		ImageIcon fred = new ImageIcon(FishRed.getPathToImage());
		myFishHashMap.put("fishRed", fred);

		// >>>>>>>>>>>>>>|IMAGE ICON FOR EATABLE ELEMENTS|<<<<<<<<<<<<<<<

		ImageIcon ldybrd = new ImageIcon(Ladybirds.getPathToImage());
		myFixedGameElementHashMap.put("ladybirds", ldybrd);

		ImageIcon spider = new ImageIcon(Spider.getPathToImage());
		myFixedGameElementHashMap.put("spider", spider);

		ImageIcon Butfly = new ImageIcon(Butterfly.getPathToImage());
		myFixedGameElementHashMap.put("butterfly", Butfly);

		ImageIcon pel = new ImageIcon(Pellet.getPathToImage());
		myFixedGameElementHashMap.put("pellet", pel);

		// >>>>>>>>>>>>>>|IMAGE ICON FOR DECOR|<<<<<<<<<<<<<<<

		ImageIcon dec = new ImageIcon(Decor.getPathToImage());
		myDecorHashMap.put("decor", dec);

	}

	private void initGame() {

		createGameElements();
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void checkIfDecorIsTotallyInAquarium() {

		for (Decor d: myDecorList) {
			if (d.getPosX() >= B_WIDTH - Decor.getfinalDecorWidth() || d.getPosY() >= B_HEIGHT - Decor.getfinalDecorHeight()) {  // IF A PART OF DECOR IS OUT OF AQUARIUM

				d.setPosX(getRandomPosFor('X'));		// SET A RANDOM POSITION FOR DECOR 
				d.setPosY(getRandomPosFor('Y'));				
			}
		}	
	}

	protected void createGameElements() {

		myFishList = new ArrayList<Fish>();
		myEatableElementList = new ArrayList<FixedGameElement>();
		myDecorList = new ArrayList<Decor>();

		createFish();
		createEatbleElements();
		createDecor();
	}

	private void createFish() {

		// >>>>>>>>>>>>>>|ADDING FISHES BY TYPES IN MY ARRAYLIST|<<<<<<<<<<<<<<<

		for (int i = 0; i < FishOrange.getFishCounter(); i++) 
			myFishList.add(new FishOrange(getRandomPosFor('X'), getRandomPosFor('Y'), getRandomPosFor('X'), getRandomPosFor('Y'))); // CALL CONSTRUCTOR WITH (4 PARAM) 

		for (int i = 0; i < FishRed.getFishCounter(); i++) 
			myFishList.add(new FishRed(getRandomPosFor('X'), getRandomPosFor('Y'), this)); // CALL CONSTRUCTOR WITH (3 PARAM)  

		for (int i = 0; i < FishPurple.getFishCounter(); i++) 
			myFishList.add(new FishPurple(this));  // CALL CONSTRUCTOR WITH (1 PARAM) 

		for (int i = 0; i < FishBlue.getFishCounter(); i++) 
			myFishList.add(new FishBlue(this));  // CALL CONSTRUCTOR WITH (1 PARAM)  
	}

	private void createEatbleElements() {

		// >>>>>>>>>>>>>>|ADDING EATABLE ELEMENTS IN MY ARRAYLIST|<<<<<<<<<<<<<<<

		for (int i = 0; i < Ladybirds.getLadybirdsCounter(); i++) 
			myEatableElementList.add(new Ladybirds(getRandomPosFor('X'), getRandomPosFor('Y') )); // CALL CONSTRUCTOR WITH (2 PARAM)  

		for (int i = 0; i < Spider.getSpiderCounter(); i++) 
			myEatableElementList.add(new Spider(getRandomPosFor('X'), getRandomPosFor('Y') )); // CALL CONSTRUCTOR WITH (2 PARAM)  

		for (int i = 0; i < Butterfly.getButterflyCounter(); i++) 
			myEatableElementList.add(new Butterfly(this)); // CALL CONSTRUCTOR WITH (1 PARAM)  

		for (int i = 0; i < Pellet.getPelletCounter(); i++) 
			myEatableElementList.add(new Pellet(this)); // CALL CONSTRUCTOR WITH (1 PARAM)  
	}

	private void createDecor() {

		// >>>>>>>>>>>>>>[ADDING DECORS IN MY ARRAYLIST]<<<<<<<<<<<<<<<

		for (int i = 0; i < Decor.getDecorCounter(); i++) 
			myDecorList.add(new Decor(this));  // CALL CONSTRUCTOR WITH (1 PARAM)  
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	protected void doDrawing(Graphics g) {
		if (inGame) {

			// >>>>>>>>>>>>>>|DRAWING BACKGROUND|<<<<<<<<<<<<<<<

			if (temperatureNumber == 1) {
				g.drawImage(coldBackGroundImage, 0, 0, null);  // DRAWING DEFAULT BACKGROUND
			}
			if (temperatureNumber == 2) {
				g.drawImage(defaultBackGroundImage, 0, 0, null); // DRAWING COLD BACKGROUND
			}
			if (temperatureNumber == 3) {
				g.drawImage(hotBackGroundImage, 0, 0, null); // DRAWING HOT BACKGROUND
			}

			// >>>>>>>>>>>>>>|DRAWING GAME ELEMENTS|<<<<<<<<<<<<<<<

			for (Fish f : myFishList) {
				for (FixedGameElement elem : myEatableElementList) {
					for (Decor d : myDecorList) {

						g.drawImage(myFishHashMap.get(f.getType()).getImage(), f.getPosX(), f.getPosY(), this); 					  // DRAWING FISHES
						g.drawImage(myFixedGameElementHashMap.get(elem.getType()).getImage(), elem.getPosX(), elem.getPosY(), this); // DRAWING EATABLES ELEMENTS
						g.drawImage(myDecorHashMap.get(d.getType()).getImage(), d.getPosX(), d.getPosY(), this); 					// DRAWING DECOR
					}
				}
			}
			Toolkit.getDefaultToolkit().sync();
		}
		else {

			getGameCrashed(g); 
		}
	}

	private void checkGameELementCollision() {

		checkCollisionInSpawn();
		checkIfFishRedEat();
		checkFishReproduction();



		//		// >>>>>>>>>>>>>>|IF POSITION FISH == EATABLE ELEMENTS POSITION|<<<<<<<<<<<<<<<

		for (Fish f : myFishList) {
			for (FixedGameElement elem : myEatableElementList) {

				if ((f.getPosX() == elem.getPosX()) && (f.getPosY() == elem.getPosY())) {

				elem.triggerAction(this);
			}

			}

			// >>>>>>>>>>>>>>|IF TARGET POSITION == DECOR POSITION|<<<<<<<<<<<<<<<

			if (CheckIfPositionEqualToDecor(f.getTargetX(), f.getTargetY())) {

				f.setTargetX(getRandomPosFor('X')); // CHANGE POSITION OF TARGET
				f.setTargetY(getRandomPosFor('Y'));

				// System.out.println("NEW TARGET !, BECAUSE TARGET WAS IN THE DECOR ");
			}
			}
	}


	private void checkIfFishRedEat() {			// RED FISH EAT OTHERS FISHES ! 

		myFishListCopy = new ArrayList<Fish>();		// A COPY OF MY LIST TO VERIFY CONDITIONS 
		myFishListCopy.addAll(myFishList);

		for(Fish f1 : myFishListCopy) {		
			for(Fish f2 : myFishListCopy) {	

				if(f1.getType() == "fishRed" && f2.getType() != "fishRed") {					 				 // IF F1 RED AND F2 NOT RED
					if (f1.getPosX() == f2.getPosX() && f1.getPosY() == f2.getPosY()) {					  		// IF F1 AND F2 SAME POSITION 

						f2.setPosX(voidPosition);	 // OUT OF AQUARIUM 
						myFishList.remove(f2);

						System.out.println(f1.getType() + " EATED "  + f2.getType());
					}
				}
			}
		}
	}

	protected boolean CheckIfPositionIsValid(int testPosX, int testPosY) {

		boolean res = true; // POSITION IS VALID

		for (Fish f : myFishList) {

			if (testPosY >= B_HEIGHT) 			   // POSITION NOT VALID IF THE POS Y >= B_HEIGHT (DOWN AQUARIUM LIMIT)
				res = false;
			
			if (testPosY < 0) 				   // POSITION NOT VALID IF THE POS Y < 0 (UP AQUARIUM LIMIT)
				res = false;
			
			if (testPosX >= B_WIDTH) 	   // POSITION NOT VALID IF THE POS X >= B_WIDTH (RIGHT AQUARIUM LIMIT)
				res = false;

			if (testPosX < 0) 
				res = false;				
			
			if (res == false) {
				f.setTargetX(getRandomPosFor('X'));
				f.setTargetY(getRandomPosFor('Y'));
			}
		}
		return res; // RETURN RESULT
	}

	protected boolean CheckIfPositionEqualToDecor(int paramX, int paramY) {

		for (Decor d : myDecorList) {

			if ((paramX >= d.getPosX()) 										    // IF FISH POS X >= DECOR POS X (random)
					&& paramX <= d.getPosX() + Decor.getfinalDecorWidth()		   // AND IF FISH POS X <= DECOR POS X (random) + FINAL DECOR SIZE X (60)
					&& paramY >= d.getPosY() 								      // AND IF FISH POS Y >= DECOR POS Y (random)
					&& paramY <= d.getPosY() + Decor.getfinalDecorHeight()) {    // AND IF FISH POS Y <= DECOR POS Y (random) + FINAL DECOR SIZE Y (120)

				return true;
			}
		}
		return false;
	}

	private void checkFishReproduction() {

		myFishListCopy = new ArrayList<Fish>();		// A COPY OF MY LIST TO VERIFY CONDITIONS 
		myFishListCopy.addAll(myFishList);

		for (Fish f1: myFishListCopy) {
			for (Fish f2: myFishListCopy) {
				if(f1 != f2) {																										       // IF IT'S NOT THE SAME FISH ! 
					if (f1.getPosX() == f2.getPosX() && f1.getPosY() == f2.getPosY()) {													  // IF THEY HAVE SAME POSITION ! 			
						if (f1.getPosX() >= 0 && f1.getPosY() >= 0 && f1.getPosX() < B_WIDTH && f1.getPosY() < B_HEIGHT) { 		         // ONLY IF FISHES ARE IN AQUARIUM !
							if (f2.getPosX() >= 0 && f2.getPosY() >= 0 && f2.getPosX() < B_WIDTH && f2.getPosY() < B_HEIGHT) { 			// ONLY IF FISHES ARE IN AQUARIUM !


								if(AlgorithmForBornProbability() == true) {	 // IF TRUE 

									// DO REPRODUCTION ACTION 
									f1.setPosX(voidPosition);
									f2.setPosX(voidPosition);

									if(f1.getPosX() == voidPosition && f2.getPosX() == voidPosition) {
										myFishList.remove(f1);		// REMOVE FROM LIST
										myFishList.remove(f2);		// REMOVE FROM LIST
									}

									addThreeFishInGame(f1, f2);

								}	
								// ELSE NOTHING HAPPENING ... 
							}
						}
					}
				}
			}
		}
	}

	private void addThreeFishInGame(Fish f1, Fish f2) {

		final int NumberOfFishToAdd = 3;

		if (f1.getType() == "fishOrange" && f2.getType() == "fishOrange") {		//IF IT'S A ORANGE FISH  
			for (int i=0; i< NumberOfFishToAdd ; i++) 
				myFishList.add(new FishOrange(this)); // ADD 3 FISHES ORANGE!  
		}
		if (f1.getType() == "fishRed" && f2.getType() == "fishRed") {		//IF IT'S A ORANGE FISH  
			for (int i=0; i< NumberOfFishToAdd ; i++) 
				myFishList.add(new FishRed(this)); // ADD 3 FISHES RED !  
		}
		if (f1.getType() == "fishPurple" && f2.getType() == "fishPurple") {		//IF IT'S A ORANGE FISH  
			for (int i=0; i< NumberOfFishToAdd ; i++) 
				myFishList.add(new FishPurple(this)); // ADD 3 FISHES PURPLE !  
		}
		if (f1.getType() == "fishBlue" && f2.getType() == "fishBlue") {		//IF IT'S A ORANGE FISH  
			for (int i=0; i< NumberOfFishToAdd ; i++) 
				myFishList.add(new FishBlue(this)); // ADD 3 FISHES BLUE!  
		}
	}

	private boolean AlgorithmForBornProbability() {

		boolean res = false;
		Random rand = new Random();

		int ListSize = myFishList.size()*10;	// If THERE IS SO MANY FISH THE PROBABILITY IS BIGGER !

		boolean val = rand.nextInt(ListSize)==0;   // MORE FISH (listSize bigger) PROBABILTY TON RETURN TRUE IS LOW ! 

		if (val == true )
			res = true; // REPRODUCTION IS OK ! RETURN TRUE;

		return res;		// FALSE IF VAL NOT FIND, TRUE IF FIND !
	}



	private void checkCollisionInSpawn() { // THIS METHOD IS IN checkGameELementCollision() METHOD

		for (FixedGameElement elem: myEatableElementList) {

			// >>>>>>>>>>>>>>|IF EATABLE ELEMNTS SPAWNS IN DECOR POSITION|<<<<<<<<<<<<<<<

			if (CheckIfPositionEqualToDecor(elem.getPosX(), elem.getPosY())) {

				elem.setPosX(getRandomPosFor('X')); 	// CHANGE POSITION OF EATABLES ELEMENTS
				elem.setPosY(getRandomPosFor('Y'));		// CHANGE POSITION OF EATABLES ELEMENTS

				// System.out.println("NEW POSITION FOR " + elem.getType() + " BECAUSE IT SPAWNED AT THE SAME POSITION OF DECOR ! ");
			}

			// >>>>>>>>>>>>>>|IF FISH SPAWNS IN DECOR POSITION|<<<<<<<<<<<<<<<

			for (Fish f: myFishList) {

				if (CheckIfPositionEqualToDecor(f.getPosX(), f.getPosY())) {

					f.setTargetX(getRandomPosFor('X'));
					f.setTargetY(getRandomPosFor('Y'));

					// System.out.println("NEW POISTION FOR " + f.getType() + " BECAUSE I SPAWNED AT THE SAME POSITION OF DECOR !");
				}
			}

			// >>>>>>>>>>>>>>|IF TWO DECORS HAS SAME POSITION|<<<<<<<<<<<<<<<

			for (Decor d1: myDecorList) {
				for (Decor d2: myDecorList) {

					if (d1 != d2 ) {
						if (d1.getPosX() == d2.getPosX() && d1.getPosY() == d2.getPosY()) {

							d2.setPosX(getRandomPosFor('X'));
							d2.setPosY(getRandomPosFor('Y'));
							// System.out.println("NEW POISTION FOR " + d2.getType() + " BECAUSE IT SPAWNED AT THE SAME POSITION OF DECOR !");
						}
					}
				}
			}
		}
	}

	protected int getRandomPosFor(char POS) {

		int randomPos=0;

		if (POS == 'X' || POS == 'x') {
			int randomPositionForX = (int) (Math.floor(Math.random() * B_WIDTH / DOT_SIZE) * DOT_SIZE); // VALUE BETWEEN 0 AND B_WIDTH AND ONLY NUMBERS DIVISIBLE BY (DOT_SIZE)
			randomPos = randomPositionForX;
		}
		if (POS == 'Y' || POS == 'y') {
			int randomPositionForY = (int) (Math.floor(Math.random() * B_HEIGHT / DOT_SIZE) * DOT_SIZE); // VALUE BETWEEN 0 AND B_HEIGHT AND ONLY NUMBERS DIVISIBLE BY (DOT_SIZE)
			randomPos = randomPositionForY;
		}
		return randomPos;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (inGame) {

			checkIfDecorIsTotallyInAquarium();
			checkGameELementCollision();

			for (Fish f1 : myFishList) {
				f1.move(this);
			}
			for (FixedGameElement elem : myEatableElementList) {
				elem.triggerAction(this);
			}
			if( myFishList.size() + myDecorList.size() + myEatableElementList.size() > 100) {	// IF THERE IS SO MANY ELEMENTS IN GAME, STOP THE GAME (CRASH FOR PERFORMANCE) 
				inGame=false;
			}
		}
		repaint();
	}

	private void getGameCrashed(Graphics g) {

		setBackground(Color.black);  

		String msg = "THE GAME CRASHED BECAUSE YOU CREATED SO MANY ELEMENTS ! ";
		Font small = new Font("LucidaSans", Font.BOLD, 26);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.red);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}
}
