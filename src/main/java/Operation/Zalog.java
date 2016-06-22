package Operation;

import Common.Common;
import Common.DataBase;
import Common.Driver;
import static Common.Common.WorkDate;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import org.sikuli.script.Observer;

import java.net.URISyntaxException;

import java.security.acl.Group;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Zalog extends Driver{
    //private final float SEC = 0.5f;

    private Region ZalogRG;
    private Pattern ZalogPT;
    private int TalmbMaterials_ID;
    private int TalmbAlgorithm_ID;

    public void SetDiscountCardNumber(){
        Region CardNumberRG = null;
        try {
            CardNumberRG = ZalogRG.find(new Pattern(path("Zalog\\E_DiscountCardNumber")));
            CardNumberRG.click();
            Common d = new Common();
            //CardNumberRG.type(d.toEnglish("Андрієць О"));
            CardNumberRG.type(",euftyrj");

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        ZalogRG.highlight(2);
    }
    public void SetDiscountCardNumber(String CardNumber){
        Region CardNumberRG;
        try {
            CardNumberRG = ZalogRG.find(new Pattern(path("Zalog\\E_DiscountCardNumber")));
            CardNumberRG.click();
            CardNumberRG.type(CardNumber);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        ZalogRG.highlight(2);
    }

    public Zalog() throws FindFailed, URISyntaxException {
        //username = new Pattern(path("ShortCutBar"));

        ZalogPT = new Pattern(path("Zalog\\f_Zalog"));
       /* errorMessage = new Pattern(DataProperties.path("loginFailedLoginMessage.png"));
        */
    }

/*
    def changed(event):
    print "something changed in ", event.region
    for ch in event.getChanges():
            ch.highlight() # highlight all changes
                                           wait(1)
    for ch in event.getChanges():
            ch.highlight() # turn off the highlights

    r = selectRegion("select a region to observe")
    # any change in r larger than 50 pixels would trigger the changed function
    r.onChange(50, changed)
            r.observe(background=True)

    wait(30) # another way to observe for 30 seconds
    r.stopObserver()*/

    public void StartZalog() {
        try {


            ZalogRG = getDriver().wait(ZalogPT.similar((float) 0.7),20);
            ZalogRG.highlight(2);

            //changed();

         //   ZalogForm.onChange(changed);
            //ZalogForm.observe;
            //wait(30);
            ZalogRG.stopObserver();

            //System.out.println(ZalogForm.onChange());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectFIO() {
        Region fioRG;
        try {
            fioRG = ZalogRG.find(new Pattern(path("Zalog\\E_FIO")));

            fioRG = fioRG.right(fioRG.getW() * 4);
            fioRG.click();

            DataBase Base = new DataBase();
            Base.Connect();

            List<String[]> rs;// = new ArrayList<>();

            System.out.println("select first 1 ID, \"Fam\"||\" \"||\"Imja\"||\" \"||\"Otc\" from \"PrFizLicList\"(null, 451) order by \"Fam\",\"Imja\",\"Otc\"");
            rs = Base.Query("select first 1 ID, \"Fam\"||\' \'||\"Imja\"||\' \'||\"Otc\" from \"PrFizLicList\"(null, 451) order by \"Fam\",\"Imja\",\"Otc\"");
            Base.Closed();
            //Common с = new Common();
            fioRG.type(Common.toEnglish(rs.get(0)[1]));
            fioRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectFIO(String like) {
        Region fioRG;
        try {
            fioRG = ZalogRG.find(new Pattern(path("Zalog\\E_FIO")));

            fioRG = fioRG.right(fioRG.getW() * 4);
            fioRG.click();

            DataBase Base = new DataBase();
            Base.Connect();

            List<String[]> rs;// = new ArrayList<>();

            rs = Base.Query("select first 1 ID, \"Fam\"||\' \'||\"Imja\"||\' \'||\"Otc\" from \"PrFizLicList\"(null, 451) where \"Fam\" like '"+like+"%' order by \"Fam\",\"Imja\",\"Otc\"");
            Base.Closed();
            fioRG.type(Common.toEnglish(rs.get(0)[1]));
            fioRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectAlgorithmKredit(String name) {
        Region AlgorithmRG;

        try {
            Pattern a = new Pattern(path("Zalog\\E_AlgorithmKredita"));
            AlgorithmRG = ZalogRG.find(a);

            AlgorithmRG = AlgorithmRG.right(AlgorithmRG.getW()*5);
            AlgorithmRG.click();

            boolean on;
            //выкл/вкл NumLock так как коммбинация SHIFT +HOME не работает с включенным NumLock
            on = (Key.isLockOn('\ue03b'));
            if(on)
                AlgorithmRG.type(Key.NUM_LOCK);

            AlgorithmRG.type(Key.HOME, Key.SHIFT);
            AlgorithmRG.type(Key.BACKSPACE);

            if(on)
                AlgorithmRG.type(Key.NUM_LOCK);

            AlgorithmRG.type(Common.toEnglish(name));
            List<String[]> rs;
            DataBase b = new DataBase();
            b.Connect();
            rs = b.Query(" select  \"TalmbAlgorithm\".id, \"TalmbAlgorithm\".\"Name\" from \"TalmbAlgorithm\" " +
                    "      left join \"TalmbAlgorithmParam\" on \"TalmbAlgorithmParam\".\"IDTalmbAlgorithm\" = \"TalmbAlgorithm\".id " +
                    "      where \"TalmbAlgorithmParam\".\"IsUsed\" = 1 " +
                    "      and \"TalmbAlgorithm\".\"Name\" like '" + name + "%'");
            b.Closed();
            TalmbAlgorithm_ID =  rs.get(0)[0];

            System.out.println("sfsfsfsdsd ========"+rs.size());


            AlgorithmRG.type(Key.ENTER);



        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }


    }

    public void SetKolPeriod(int n){
        Region KolPeriod;
        int i;

        try {
            KolPeriod = ZalogRG.find(new Pattern(path("Zalog\\E_KolPeriod")));
            i = KolPeriod.getW()/2+1;

            KolPeriod.setX(KolPeriod.getX() + i);
            KolPeriod.setW(i);
            //переприсваиваем новый регион, без этого клик срабатывает по LastMatch - старые, не изменный регион.
            KolPeriod = new Region(KolPeriod);

            KolPeriod.click(KolPeriod);

            boolean on;
            //выкл/вкл NumLock так как коммбинация SHIFT +HOME не работает с включенным NumLock
            on = (Key.isLockOn('\ue03b'));
            if(on)
                KolPeriod.type(Key.NUM_LOCK);

            KolPeriod.type(Key.HOME, Key.SHIFT);
            KolPeriod.type(Key.BACKSPACE);
            KolPeriod.type(String.valueOf(n));

            if(on)
                KolPeriod.type(Key.NUM_LOCK);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SetDateReturn(String StrDateReturn)  {
        int n;
        if (StrDateReturn == null) StrDateReturn = "currentdate";

        //SetWorkDate();
        // Calendar calendar = new GregorianCalendar(2016,Calendar.FEBRUARY,28);
        Calendar DateReturn = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //, Locale.JAPAN
        try {
            DateReturn.setTime(sdf.parse(StrDateReturn));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        n = (int)((DateReturn.getTimeInMillis() - WorkDate.getTimeInMillis())/(1000*60*60*24));
        System.out.println(n);

        // SetKolPeriod(n);

    }

    public void AddMainoButtonClick() {
        try {
            ZalogRG.find(new Pattern(path("Zalog\\B_Add"))).click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectGroupMaino(String str) {
        Region Groups, GroupName = null;
        try {
            Groups = getDriver().find(new Pattern(path("GroupMaino\\f_GroupMaino")));
            Groups = Groups.below(Groups.getH() * 1);

            if (str.equals("Техника"))
                GroupName = Groups.find(new Pattern(path("GroupMaino\\Technics")));
            if (str.equals("Драг.Металл"))
                GroupName = Groups.find(new Pattern(path("GroupMaino\\DragMetall")));

            GroupName.doubleClick();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectGroupMaino() {
        Region Groups, GroupName;

        try {

            Groups = getDriver().find(new Pattern(path("GroupMaino\\f_GroupMaino")));
            Groups = Groups.below(Groups.getH() * 1);
            GroupName = Groups.find(new Pattern(path("GroupMaino\\DragMetall")));

            GroupName.doubleClick();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectMaino(String NameMaino) {
        ZalogRG.type(Common.toEnglish(NameMaino));
        ZalogRG.type(Key.ENTER);
    }

    public void SelectMaterial(String Name) {
        ZalogRG.type(Common.toEnglish(Name));
        ZalogRG.type(Key.ENTER);
    }

    public void SelectProba(String ProbaName, String Suffix){
        Region SelectProbaRG, ProbaNameRG;
        try {
            SelectProbaRG = getDriver().find(new Pattern(path("Zalog\\F_SelectProba")));
            ProbaNameRG = SelectProbaRG.find(new Pattern(path("Zalog\\E_Proba")));
            ProbaNameRG = ProbaNameRG.right(ProbaNameRG.getW()*2);
            ProbaNameRG.click();
            ProbaNameRG.type(Common.toEnglish(ProbaName));

            ProbaNameRG.type(Key.TAB); //переход на суфикс

            if (Suffix != null) {
                ProbaNameRG.type(Common.toEnglish(Suffix));
                ProbaNameRG.type(Key.ENTER);
            }

            ProbaNameRG.type(Key.ENTER); // кнопка ОК

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SetAmount(int kol){
        Region AmountRG;
        try {
            AmountRG = getDriver().find(new Pattern(path("Zalog\\F_Amount")));
            AmountRG = AmountRG.right(AmountRG.getW());
            AmountRG.click();
            AmountRG.type(String.valueOf(kol));
            AmountRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }
    public void SetWeight(double weight ){
        Region WeightRG, NtWtRG;
        try {
            WeightRG = getDriver().find(new Pattern(path("Zalog\\F_Weight")));
            NtWtRG = WeightRG.find(new Pattern(path("Zalog\\E_NtWt")));
            NtWtRG = NtWtRG.right(NtWtRG.getW());
            NtWtRG.click();
            NtWtRG.type(Common.toEnglish(String.valueOf(weight)));
            NtWtRG.type(Key.ENTER);
            NtWtRG.type(Key.ENTER);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

}
