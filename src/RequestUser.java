import java.util.Scanner;
public class RequestUser {

    Scanner sc = new Scanner(System.in);
    //displays menu to user
    public void displayMenu(){

        System.out.println("please pick from the following options to continue:");
        System.out.println(" 1. get weather from specific location");
        System.out.println(" 2. get weather from my current location");
        System.out.println(" 3. Exit");
    }
    //method to take user input
    public int chooseOption() {

        while (true) {
            try {


                int choice;
                choice = sc.nextInt();

                return choice;


                //catches non number inputs
            } catch (Exception e) {
                System.out.println("Invalid input please enter a number");
                sc.nextLine();
            }

        }

    }
    //prompts the user to enter location
    public String getLocationFromUser() {
        System.out.println("Please enter the location ");
        return sc.nextLine().trim();

    }
}

