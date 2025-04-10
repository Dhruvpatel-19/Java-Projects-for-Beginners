import java.util.*;

public class ConsoleBanking {

    static class User {
        String username;
        String password;
        int balance;

        User(String u, String p, int b) {
            this.username = u;
            this.password = p;
            this.balance = b;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> map = new HashMap<>();

        // dummy accounts
        map.put("AAA", new User("AAA", "1111", 1000));
        map.put("BBB", new User("BBB", "2222", 1200));
        map.put("CCC", new User("CCC", "3333", 2000));

        while (true) {
            System.out.println("\nChoose from below options:");
            System.out.println("1. Log into an account");
            System.out.println("2. Create an account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (map.containsKey(username) && map.get(username).password.equals(password)) {
                    User currentUser = map.get(username);
                    System.out.println("Login successful!");

                    while (true) {
                        System.out.println("\nChoose operation:");
                        System.out.println("1. Check balance");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdraw");
                        System.out.println("4. Transfer money");
                        System.out.println("5. Logout");
                        System.out.print("Enter your choice: ");
                        int operation = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (operation == 1) {
                            System.out.println("Current balance: $" + currentUser.balance);
                        } else if (operation == 2) {
                            System.out.print("Enter amount to deposit: ");
                            int amount = scanner.nextInt();
                            currentUser.balance += amount;
                            System.out.println("Deposited successfully!");
                        } else if (operation == 3) {
                            System.out.print("Enter amount to withdraw: ");
                            int amount = scanner.nextInt();
                            if (amount <= currentUser.balance) {
                                currentUser.balance -= amount;
                                System.out.println("Withdrawn successfully!");
                            } else {
                                System.out.println("Insufficient balance.");
                            }
                        } else if (operation == 4) {
                            System.out.print("Enter receiver's username: ");
                            String receiver = scanner.nextLine();
                            if (map.containsKey(receiver)) {
                                System.out.print("Enter amount to transfer: ");
                                int amount = scanner.nextInt();
                                if (amount <= currentUser.balance) {
                                    currentUser.balance -= amount;
                                    map.get(receiver).balance += amount;
                                    System.out.println("Transferred successfully!");
                                } else {
                                    System.out.println("Insufficient balance.");
                                }
                            } else {
                                System.out.println("Receiver does not exist.");
                            }
                        } else if (operation == 5) {
                            System.out.println("Logged out.");
                            break;
                        } else {
                            System.out.println("Invalid option. Try again.");
                        }
                    }

                } else {
                    System.out.println("Invalid username or password.");
                }

            } else if (choice == 2) {
                System.out.println("Creating a new account...");

                String username;
                while (true) {
                    System.out.print("Enter preferred username: ");
                    username = scanner.nextLine();
                    if (!map.containsKey(username)) break;
                    System.out.println("Username already exists. Try a different one.");
                }

                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter initial deposit: ");
                int initialAmount = scanner.nextInt();
                scanner.nextLine(); // consume newline

                map.put(username, new User(username, password, initialAmount));
                System.out.println("Account created successfully!");

            } else if (choice == 3) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
