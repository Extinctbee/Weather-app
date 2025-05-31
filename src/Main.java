import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Weather Check");
        //loop keeps running unless case 3 is chosen in which the program ends
       while(true) {
           // creates the instances of the classes with the objects
           RequestUser user = new RequestUser();
           WeatherParse parse = new WeatherParse();
           GeoLocator locator = new GeoLocator();
           // calls method to display the menu

           user.displayMenu();
           //calls method to take the user input and put it into choice
           int choice = user.chooseOption();

           //case is run based on the choice from user
           switch(choice) {
               case 1:
                   //displays weather data of a specific city the user inputs
                   parse.handleSpecificLocation();
                   break;
                   case 2:
                       //displays weather data of the users current location based on ip address
                       parse.handleCurrentLocation();
                       break;
                   case 3:
                       //allows user to exit the program
                       System.out.println("Thank you for using our service. Goodbye!");
                       sc.close();
                       return;



           }

       }
    }
}