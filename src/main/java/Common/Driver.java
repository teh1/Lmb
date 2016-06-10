package Common;

import Menu.ShortCutBar;
import org.sikuli.script.Screen;

import java.io.IOException;
import java.net.URISyntaxException;

public class Driver {
    private static Screen driver;

    public synchronized static Screen getInstance() {
        if (driver == null){
            driver = new Screen();
        }
        return driver;
    }


    public Process RunApp() {
        try {
            return Runtime.getRuntime().exec("app.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  String path(String img) throws URISyntaxException {
        String p;
        p="gfghfghfghfh";
        //p = this.getClass().getResource(img+".png").toURI().toString().substring(6);
         p = this.getClass().getClassLoader().getResource(img+".png").toURI().toString().substring(6);
       //p = return ClassLoader.getSystemResource(file).getPath().toString().substring(1);

  //      System.out.println(img);
        return p;
    }

    protected  Screen getDriver() {
        return Driver.getInstance();
    }
}
