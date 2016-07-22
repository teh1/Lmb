package Menu;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import Common.Driver;

import java.net.URISyntaxException;

public class ShortCutBar extends  Driver{

     private Region BarRG;
     private Pattern BarPT,ZalogButtonPT, PerezalogButtonPT;

    public ShortCutBar() throws FindFailed, URISyntaxException {
        //System.out.println("lol1111111111111");
        BarPT = new Pattern(path("ShortCutBar\\ShortCutBar"));
        BarRG = getDriver().wait(BarPT);
    }

    public ShortCutBar ChoiceZalog() throws FindFailed, URISyntaxException {
        ZalogButtonPT = new Pattern(path("ShortCutBar\\ZalogButton"));
        BarRG.find(ZalogButtonPT).click();
        return this;
    }

    public void ChoicePerezalog() throws FindFailed, URISyntaxException {
        PerezalogButtonPT = new Pattern(path("ShortCutBar\\PerezalogButton"));
        BarRG.find(PerezalogButtonPT).click();

    }
}
