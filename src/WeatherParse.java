import java.util.Scanner;
import org.json.JSONObject;

public class WeatherParse {
    RequestUser user = new RequestUser();
    Scanner sc = new Scanner(System.in);
    GeoLocator geo = new GeoLocator();
    //handles locations input from user
    public void handleSpecificLocation(){
        String Location = user.getLocationFromUser();
        if(!Location.isEmpty()){
            // makes a string named city equal to that location ^
            String city = Location;
            // passes that string to the callWeatherApi method
            WeatherFetcher fetcher = new WeatherFetcher();
            String json = fetcher.callWeatherApi(city);
            //Parses the data to make it readable
            parseUserLocationData(json);

        }

    }
    //takes user current location based on ip adress
    // and passes it to the callWeatherApi method , then parses the data
    public void handleCurrentLocation(){
        String address = geo.GetLocationFromIP();
        if(!address.isEmpty()){
            String city = address;
            WeatherFetcher fetcher = new WeatherFetcher();
            String json = fetcher.callWeatherApi(city);
            parseUserLocationData(json);
        }
    }
    //this method chooses what information we get from Json that we want to display and it's format
    public void parseUserLocationData(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            String cityName = jsonObject.getString("name");
            JSONObject main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double fahrenheit = (temperature*1.8)+32;
            int humidity = main.getInt("humidity");
            JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");
            System.out.println("Weather For " + cityName+" :");
            System.out.println("Description: " + description);
            System.out.println("Temperature: " + temperature +"°C/ "+fahrenheit+"°F");
            System.out.println("Humidity: " + humidity + "%");
        }catch(Exception e){
            System.out.println("Error parsing data " + e.getMessage());
        }

    }

}
