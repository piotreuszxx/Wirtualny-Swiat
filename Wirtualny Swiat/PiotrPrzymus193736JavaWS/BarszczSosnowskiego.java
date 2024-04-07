package WSJAVA;
import java.util.Random;

public class BarszczSosnowskiego extends Roslina{
    public BarszczSosnowskiego(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 10;
        this.inicjatywa = 0;
        this.znak_gatunku = Stale.BARSZCZ_SOSNOWSKIEGO;
        this.wiek = 0;
        this.nazwa = "BarszczSosnowskiego";
    }
    public BarszczSosnowskiego(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void akcja()
    {
        boolean rozmnozy = true;
        if (CzyOtoczona())
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie ma gdzie sie rozsiac");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie ma gdzie sie rozsiac");
            rozmnozy = false;
        }
        if(rozmnozy)
            Rozmnazanie(null);
        boolean zabil = false;
        if (polozeniex > 0 && swiat.GetOrganizm(polozeniex - 1, polozeniey) != null)
        {
            if (swiat.GetOrganizm(polozeniex - 1, polozeniey).GetInicjatywa() > 0)
            {
                //swiat.gotoxy(70, 31);
                //cout << "siedzi 1v2";
                Organizm o = swiat.GetOrganizm(polozeniex - 1, polozeniey);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                //System.out.println(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");

                swiat.UsunOrganizm(polozeniex - 1, polozeniey);
                zabil = true;
            }
        }
        if (polozeniex < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(polozeniex + 1, polozeniey) != null)
        {
            if (swiat.GetOrganizm(polozeniex + 1, polozeniey).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex + 1, polozeniey);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                //System.out.println(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");

                swiat.UsunOrganizm(polozeniex + 1, polozeniey);
                zabil = true;
            }
        }
        if (polozeniey > 0 && swiat.GetOrganizm(polozeniex, polozeniey - 1) != null)
        {
            if (swiat.GetOrganizm(polozeniex, polozeniey - 1).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex, polozeniey - 1);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                //System.out.println(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");

                swiat.UsunOrganizm(polozeniex, polozeniey - 1);
                zabil = true;
            }
        }
        if (polozeniey < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(polozeniex, polozeniey + 1) != null)
        {
            if (swiat.GetOrganizm(polozeniex, polozeniey + 1).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex, polozeniey + 1);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                //System.out.println(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");

                swiat.UsunOrganizm(polozeniex, polozeniey + 1);
                zabil = true;
            }
        }
        if(!zabil)
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie zabil zadnego organizmu");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie zabil zadnego organizmu");
        }
    }

    @Override
    public void kolizja(Organizm o)
    {
        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());

        if (o.GetSila() >= sila)
        {
            //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") zabil " + nazwa + "(" + px + "," + py + ")");
            swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + ") i razem z nim ginie");
            swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY());
            swiat.UsunOrganizm(polozeniex, polozeniey);
        }
        else
        {
            //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") nadgryzl " + nazwa + "(" + px + "," + py + ") i od niego ginie");
            swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") nadgryzl " + nazwa + "(" + px + "," + py + ") i od niego ginie");
            swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY());
        }
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        Random rand = new Random();
        int szansa = rand.nextInt(Stale.RNG_BARSZCZ_SOSNOWSKIEGO);
        if (szansa == 0)
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new BarszczSosnowskiego(x, y, swiat));
            //swiat.gotoxy(40, 8);
            return;
        }
        //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
        //swiat.gotoxy(40, 8);
    }
}
