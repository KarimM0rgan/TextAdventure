/** Text adventure game. */
public class MyAdventure {

	public static void main(String[] args) {
		new MyAdventure().run();
	}

	/** Damage done by the best weapon the player has picked up. */
	private int bestWeaponDamage;

	/** The room where the player currently is. */
	private Room currentRoom;

	/** Total value of all treasures taken by the player. */
	private int score;

	public MyAdventure() {
		// Create rooms
		Room lobby = new Room("lobby",
				"a mansion, and the lobby is a vast room, filled with puzzling pictures");
		Room livingRoom = new Room("livingRoom", "a multi-spaced area with a cute dog");
		Room kitchen = new Room("kitchen", "a niche kitchen with a chef and crew");
		Room garage = new Room("garage", "a room that handles two cars, and that oven doesn't seem so happy");
		Room guestBathroom = new Room("guestBathroom", "an almost normal bathroom, except that the guests' quiet kid is hiding");
		// Create connections
		lobby.addNeighbor("north", livingRoom);
		livingRoom.addNeighbor("south", lobby);
		livingRoom.addNeighbor("west", kitchen);
		kitchen.addNeighbor("east", livingRoom);
		livingRoom.addNeighbor("east", garage);
		garage.addNeighbor("west", livingRoom);
		livingRoom.addNeighbor("north", guestBathroom);
		guestBathroom.addNeighbor("south", livingRoom);
		// Create and install monsters
		livingRoom.setMonster(new Monster("dog", 2, "a cute looking dog, but his bite doesn't look that cute!"));
		garage.setMonster(new Monster("oven", 4, "an oven. You are not imagining, it is a fire-breathing oven!"));
		guestBathroom.setMonster(new Monster("kiddo", 4, "a kid screaming 'Uncle, I can see you'"));
		kitchen.setMonster(new Monster("chef", 6, "the final boss; slay and get the big prize! HINT: you need to get the required weapon for that first"));
		// Create and install treasures
		livingRoom.setTreasure(new Treasure("k", 25,
				"the letter k. Yep, that's what you get, a letter"));
		garage.setTreasure(new Treasure("a", 25,
				"the letter a. You didn't expect much, did ya?"));
		guestBathroom.setTreasure(new Treasure("c", 25, "the letter c, I swear it's worth it"));
		kitchen.setTreasure(new Treasure("e", 25, "the letter e, and you got a cake!! A warrior needs a cake after their war."));
		// Create and install weapons
		livingRoom.setWeapon(new Weapon("remote", 5, "a TV remote. It's not the best but better than your hands, right?"));
		guestBathroom.setWeapon(new Weapon("plunger", 7, "a Plunger; Better than a remote, and can certainly defeat a monster"));
		// The player starts in the lobby, armed with a sword
		currentRoom = lobby;
		bestWeaponDamage = 3;
	}

	/**
	 * Attacks the specified monster and prints the result. If the monster is
	 * present, this either kills it (if the player's weapon is good enough) or
	 * results in the player's death (and the end of the game).
	 */
	public void tickle(String name) {
		Monster monster = currentRoom.getMonster();
		if (monster != null && monster.getName().equals(name)) {
			if (bestWeaponDamage > monster.getArmor()) {
				StdOut.println("Lessggooooo! Off to the next one");
				currentRoom.setMonster(null);
			} else {
				StdOut.println("Your blow bounces off harmlessly.");
				StdOut.println("The " + monster.getName() + " did you bad hehe");
				StdOut.println();
				StdOut.println("GAME OVER");
				System.exit(0);
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

	/** Moves in the specified direction, if possible. */
	public void go(String direction) {
		Room destination = currentRoom.getNeighbor(direction);
		if (destination == null) {
			StdOut.println("You can't go that way from here.");
		} else {
			currentRoom = destination;
		}
	}

	/** Handles a command read from standard input. */
	public void handleCommand(String line) {
		String[] words = line.split(" ");
		if (currentRoom.getMonster() != null
				&& !(words[0].equals("tickle") || words[0].equals("look") || words[0].equals("go"))) {
			StdOut.println("You can't do that with unfriendlies about.");
			listCommands();
		} else if (words[0].equals("tickle")) {
			tickle(words[1]);
		} else if (words[0].equals("go")) {
			go(words[1]);
		} else if (words[0].equals("look")) {
			look();
		} else if (words[0].equals("take")) {
			take(words[1]);
		} else {
			StdOut.println("How about we get more clear?");
			listCommands();
		}
	}

	/** Prints examples of possible commands as a hint to the player. */
	public void listCommands() {
		StdOut.println("Examples of commands:");
		StdOut.println("  tickle kiddo");
		StdOut.println("  go north");
		StdOut.println("  look");
		StdOut.println("  take k");
	}

	/** Prints a description of the current room and its contents. */
	public void look() {
		StdOut.println("You are in " + currentRoom.getDescription() + ".");
		if (currentRoom.getMonster() != null) {
			StdOut.println("There is "
					+ currentRoom.getMonster().getDescription());
		}
		if (currentRoom.getWeapon() != null) {
			StdOut.println("There is "
					+ currentRoom.getWeapon().getDescription());
		}
		if (currentRoom.getTreasure() != null) {
			StdOut.println("There is "
					+ currentRoom.getTreasure().getDescription());
		}
		StdOut.println("Exits: " + currentRoom.exitList());
	}

	/** Runs the game. */
	public void run() {
		listCommands();
		StdOut.println();
		while (true) {
			StdOut.println("You are in the " + currentRoom.getName() + ".");
			StdOut.print("> ");
			handleCommand(StdIn.readLine());
			StdOut.println();
		}
	}

	/** Attempts to pick up the specified treasure or weapon. */
	public void take(String name) {
		Treasure treasure = currentRoom.getTreasure();
		Weapon weapon = currentRoom.getWeapon();
		if (treasure != null && treasure.getName().equals(name)) {
			currentRoom.setTreasure(null);
			score += treasure.getValue();
			StdOut.println("Your score is now " + score + " out of 100.");
			if (score == 100) {
				StdOut.println();
				StdOut.println("YOU WIN!");
				System.exit(0);
			}
		} else if (weapon != null && weapon.getName().equals(name)) {
			currentRoom.setWeapon(null);
			if (weapon.getDamage() > bestWeaponDamage) {
				bestWeaponDamage = weapon.getDamage();
				StdOut.println("You'll be a more effective tickler with this!");
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

}
