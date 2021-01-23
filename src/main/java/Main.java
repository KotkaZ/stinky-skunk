import com.google.gson.Gson;
import memory.memHandler;
import modules.glowESP;
import offsets.Offsets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        System.out.println("Reading in offsets...");

        Offsets offsets = null;
        try{
            offsets = readOffsets("https://raw.githubusercontent.com/frk1/hazedumper/master/csgo.json");
        }
        catch (IOException ioException) {
            System.out.println("Offsets reading failed.");
            ioException.printStackTrace();
            System.exit(1);
        }

        memHandler memHandler = new memHandler();
        glowESP glowESP = new glowESP(memHandler, offsets.signatures, offsets.netvars);

    }

    private static Offsets readOffsets(String _url) throws IOException {
        URL url = new URL(_url);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        return new Gson().fromJson(reader,Offsets.class);
    }
}
