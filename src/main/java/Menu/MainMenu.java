package Menu;

import Common.Driver;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.net.URISyntaxException;

public class MainMenu extends Driver {

    private Pattern Menu = null;
    private Region Menu_region = null, mSessiaRG = null, subSessiaRG = null;

    public MainMenu() {
        Menu = new Pattern(path("Menu\\Menu"));
    }

    private Region mMenu() {
        if (Menu_region != null) return Menu_region;
        try {
            Menu_region = getDriver().wait(Menu);
            return Menu_region;
            //Menu_region.highlight(1);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return null;
    }

    public Region mSessia() {
        if (mSessiaRG != null) return mSessiaRG;
        try {
            mSessiaRG = mMenu().find(new Pattern(path("Menu\\mSessia")));
            return mSessiaRG;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return null;
    }


    public Region subSessia(int num) {
        Region tmpsubSessiaRG;
        try {
            if (subSessiaRG == null)
                subSessiaRG = getDriver().wait(new Pattern(path("Menu\\subSessia")));
            tmpsubSessiaRG = subSessiaRG;
            tmpsubSessiaRG.setRaster(2, 1);
            return tmpsubSessiaRG.getCell(num, 0);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return null;
    }

    //mSessia.highlight(1).click();

        /*subSessia = getDriver().wait(new Pattern(path("Menu\\subSessia")));
    subSessia.setRaster(2, 1);

    //mSessia.find(new Pattern(path("Menu\\subSessia"))).setRaster(2, 1);

    //Menu_region.setRaster(1, 10);
    subSessia.getCell(0, 0).click();

    getDriver().wait(new Pattern(path("Common\\b_OK"))).click();
    mSessia.highlight(1).click();

    System.out.printf("fsdfdsfdsf %d", subSessia.getCell(1, 0).click());
    // subSessia.getCell(1, 0).click();*/

}
