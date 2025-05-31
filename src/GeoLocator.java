import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoLocator {
    public String GetLocationFromIP() {
    String url = "https://ipinfo.io/json?token=a8062759b9538c";
    try{
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            jsonBuilder.append(line);
        }
        reader.close();

        JSONObject jsonOBJ = new JSONObject(jsonBuilder.toString());
        String city = jsonOBJ.getString("city");

        return city;

    } catch (Exception e) {
        System.out.println("Error fetching geo location" + e.getMessage());
        e.printStackTrace();
        return null;
    }
    }

}
