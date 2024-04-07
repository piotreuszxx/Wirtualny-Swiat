package WSJAVA;
import java.util.Random;

abstract public class Roslina extends Organizm{
    public Roslina(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
    }

    public Roslina(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    protected boolean CzyOtoczona()
    {
        if (polozeniex > 0 && swiat.GetOrganizm(polozeniex - 1, polozeniey) == null)
        {
            return false;
        }
        if (polozeniex < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(polozeniex + 1, polozeniey) == null)
        {
            return false;
        }
        if (polozeniey > 0 && swiat.GetOrganizm(polozeniex, polozeniey - 1) == null)
        {
            return false;
        }
        if (polozeniey < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(polozeniex, polozeniey + 1) == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public void akcja()
    {
        if (CzyOtoczona())
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") nie ma gdzie sie rozsiac");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie ma gdzie sie rozsiac");
            return;
        }
        Rozmnazanie(null);
    }

    //abstract public void PostawDziecko(int x, int y);

    @Override
    public void Rozmnazanie(Organizm o)
    {
        int x = polozeniex;
        int y = polozeniey;
        Random rand = new Random();
        int kierunek;
        while (true)
        {
            kierunek = rand.nextInt(4);
            if (kierunek == 0)
            {
                if (y > 0 && swiat.GetOrganizm(x, y - 1) == null)
                {
                    y--;
                    break;
                }
            }
            else if (kierunek == 1 && swiat.GetOrganizm(x, y + 1) == null)
            {
                if (y < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x, y + 1) == null)
                {
                    y++;
                    break;
                }
            }
            else if (kierunek == 2 && swiat.GetOrganizm(x - 1, y) == null)
            {
                if (x > 0)
                {
                    x--;
                    break;
                }
            }
            else if (kierunek == 3)
            {
                if (x < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x + 1, y) == null)
                {
                    x++;
                    break;
                }
            }
        }
        this.PostawDziecko(x, y);
    }

    @Override
    public void kolizja(Organizm o)
    {
        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());
        //System.out.println(o.GetNazwa() + "(" + p2x + "," + p2y + ") zdeptal " + nazwa + "(" + px + "," + py + ")");
        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zdeptal " + nazwa + "(" + px + "," + py + ")");

        swiat.UsunOrganizm(polozeniex, polozeniey);
    }
}
