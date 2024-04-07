package WSJAVA;

public class Czlowiek extends Zwierze{
    private int licznik;

    public Czlowiek(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 5;
        this.inicjatywa = 4;
        this.znak_gatunku = Stale.CZLOWIEK;
        this.wiek = 0;
        this.nazwa = "Czlowiek";
        this.licznik = 0;
    }
    public Czlowiek(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, int licznik, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
        this.licznik = licznik;
    }

    @Override
    public void akcja()
    {
        int x = polozeniex;
        int y = polozeniey;
        String k = swiat.GetKierunekCzlowieka();
        if (k.equals("zostaje"))
        {
            // nic
        }
        else if (k.equals("lewo") && x > 0)
        {
            x--;
        }
        else if (k.equals("prawo") && x < swiat.GetRozmiarX() - 1)
        {
            x++;
        }
        else if (k.equals("gora") && y > 0)
        {
            y--;
        }
        else if (k.equals("dol") && y < swiat.GetRozmiarY() - 1)
        {
            y++;
        }
        if (polozeniex == x && polozeniey == y)
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") zostaje na miejscu");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") zostaje na miejscu");
        }
        if (swiat.GetOrganizm(x, y) != null)
        {
            swiat.GetOrganizm(x, y).kolizja(this); //this wjechal w organizm na polu x,y
            if (wiek != -1 && swiat.GetOrganizm(x, y) == null)
            {
                polozeniex = x; // jesli this nie zostal zjedzony ORAZ zjadl to zmien polozenie
                polozeniey = y;
            }
            if (licznik > 5)
                sila--;
            if (licznik > 0)
                licznik--;
            if (wiek == -1)
            {
                swiat.UsunOrganizm(polozeniex, polozeniey); // jesli this zostal zjedzony to go usun
            }

        }
        else
        {
            //System.out.println(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
            polozeniex = x;
            polozeniey = y;
            if (licznik > 5)
                sila--;
            if (licznik > 0)
                licznik--;
        }
    }

    public void ZwiekszSile()
    {
        if(licznik == 0)
        {
            licznik = 10;
            sila += 5;
        }
    }

    @Override
    public void PostawDziecko(int x, int y)
    {}

    public int GetLicznik()
    {
        return licznik;
    }
}
