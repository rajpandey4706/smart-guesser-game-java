package GuesserGame;

import java.util.*;

// Player Class
class Player {
    String name;
    int score = 0;

    Player(String name) {
        this.name = name;
    }
}

// Game Class
class Game {
    int numberToGuess;
    int maxAttempts = 5;

    public void generateNumber() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
    }

    public int playTurn(Player player, Scanner sc) {
        System.out.println("\n" + player.name + "'s Turn");

        for (int i = 1; i <= maxAttempts; i++) {
            System.out.print("Attempt " + i + ": Enter guess: ");
            int guess = sc.nextInt();

            if (guess == numberToGuess) {
                System.out.println("Correct! 🎉");
                return maxAttempts - i + 1;
            } else if (guess > numberToGuess) {
                System.out.println("Too High ⬆️");
            } else {
                System.out.println("Too Low ⬇️");
            }
        }

        System.out.println("No attempts left ❌");
        return 0;
    }
}

// Umpire Class
class Umpire {
    List<Player> players = new ArrayList<>();

    public void addPlayers(Scanner sc) {
        System.out.print("Enter number of players: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter name of Player " + i + ": ");
            String name = sc.nextLine();
            players.add(new Player(name));
        }
    }

    public void declareWinner() {

        int maxScore = -1;

        // Step 1: Find max score
        for (Player p : players) {
            if (p.score > maxScore) {
                maxScore = p.score;
            }
        }

        // Step 2: Count players with max score
        int count = 0;
        for (Player p : players) {
            if (p.score == maxScore) {
                count++;
            }
        }

        // Step 3: Decision
        if (count > 1) {
            System.out.println("\n🤝 It's a Draw!");
            System.out.print("Players: ");
            for (Player p : players) {
                if (p.score == maxScore) {
                    System.out.print(p.name + " ");
                }
            }
            System.out.println();
        } else {
            for (Player p : players) {
                if (p.score == maxScore) {
                    System.out.println("\n🏆 Winner: " + p.name + " with score " + p.score);
                }
            }
        }
    }
}

// Main Class
public class MyGuesserGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n🎮 Welcome to Smart Guesser Game");

            Game game = new Game();
            game.generateNumber();

            Umpire umpire = new Umpire();
            umpire.addPlayers(sc);

            for (Player p : umpire.players) {
                int score = game.playTurn(p, sc);
                p.score = score;
            }

            umpire.declareWinner();

            System.out.print("\nPlay Again? (yes/no): ");
            String choice = sc.next();

            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Thanks for playing! 👋");
    }
}