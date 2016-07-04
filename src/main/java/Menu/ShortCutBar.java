package Menu;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import Common.Driver;

import java.net.URISyntaxException;

public class ShortCutBar extends  Driver{

     private Region region;
     private Pattern Bar,ZalogButton;

    public void ShortCutBar() throws FindFailed, URISyntaxException {
       System.out.println("lol");



    }

    public ShortCutBar ChoiceZalog() throws FindFailed, URISyntaxException {

        Bar = new Pattern(path("ShortCutBar\\ShortCutBar"));
        ZalogButton = new Pattern(path("ShortCutBar\\ZalogButton"));

        region = getDriver().wait(Bar);


        region.find(ZalogButton).click();


        return this;
    }
}
