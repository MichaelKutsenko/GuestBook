package softGroup.testTask.util;

import com.alibaba.fastjson.JSON;
import softGroup.testTask.objects.JSONList;
import softGroup.testTask.objects.Country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LocationGetter {

    public static List<Country> countryList() {

        String request = "https://api.vk.com/api.php?oauth=1&method=database.getCountries&need_all=1&count=1000&lang=en";

        try {
            String result = performRequest(request);
            JSONList countires = JSON.parseObject(result, JSONList.class);

            return  countires.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String performRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))){

            char[] buf = new char[100000];

            int r = 0;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
        } finally {
            http.disconnect();
        }

        return sb.toString();
    }
}
