import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import io.github.cdimascio.dotenv.Dotenv;
public class WeatherFetcher {

    private final String WeatherApiKey;




    public WeatherFetcher() {
        // Load .env and get the API key
        Dotenv dotenv = Dotenv.configure()
                .directory("./")       // ensure it looks in the project root
                .ignoreIfMissing()     // avoids crash if missing
                .load();

        this.WeatherApiKey = dotenv.get("WEATHER_API_KEY");

        if (this.WeatherApiKey == null) {
            System.out.println("⚠️ WEATHER_API_KEY not found in .env file!");
        }
    }
    // method to call the OpenWeather api
    public String callWeatherApi(String city)  {
        //replaces any whitespace characters with plus symbol because whitespace is not allowed in links
        city = city.replaceAll(" " , "+");
        //Stores api url from OpenWeather in urlString
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid="+WeatherApiKey+"&units=metric";

        try {
            // Create an Url object from the string version of the API link
            URL url = new URL(urlString);

            //Open an HTTP connection to the URL so we can send a request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Sets the type of HTTP request we want to send - in this case , "GET" to retrieve data
            connection.setRequestMethod("GET");

            // create a reader to read the incoming data from the API response
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            // Storing the whole response line by line in this String builder
            StringBuilder response = new StringBuilder();
            //Read each line from the response until there is nothing left
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            //close the reader to free up resources
            reader.close();
            //returns complete response as one string
            return response.toString();
            //prints out this error if we can't fetch the data
        } catch (Exception e) {
            System.out.println("Error fetching weather data" + e.getMessage());
            return null;
        }

        }
    }

