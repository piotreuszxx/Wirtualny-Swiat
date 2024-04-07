package WSJAVA;

abstract public class Organizm {
    protected int polozeniex, polozeniey, sila, inicjatywa, wiek;
    protected char znak_gatunku;
    protected String nazwa;
    protected Swiat swiat;

    public Organizm(int polX, int polY, Swiat swiat)
    {
        this.polozeniex = polX;
        this.polozeniey = polY;
        this.swiat = swiat;
        wiek = 0;
    }

    public Organizm(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat)
    {
        this.polozeniex = polozenieX;
        this.polozeniey = polozenieY;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.znak_gatunku = znak_gatunku;
        this.wiek = wiek;
        this.nazwa = nazwa;
        this.swiat = swiat;
    }

    abstract public void akcja();
    abstract public void kolizja(Organizm o);
    abstract public void Rozmnazanie(Organizm o);
    abstract public void PostawDziecko(int x, int y);
    public void rysowanie()
    {
        swiat.Dopisz(polozeniex, polozeniey, znak_gatunku);
    }

    public void SetSila(int s)
    {
        sila = s;
    }
    public void SetPolozenieX(int x)
    {
        polozeniex = x;
    }
    public void SetPolozenieY(int y)
    {
        polozeniey = y;
    }
    public void SetWiek(int w)
    {
        wiek = w;
    }

    public int GetPolozenieX()
    {
        return polozeniex;
    }
    public int GetPolozenieY()
    {
        return polozeniey;
    }
    public int GetSila()
    {
        return sila;
    }
    public int GetInicjatywa()
    {
        return inicjatywa;
    }
    public int GetWiek()
    {
        return wiek;
    }
    public char GetZnak_Gatunku()
    {
        return znak_gatunku;
    }
    public String GetNazwa()
    {
        return nazwa;
    }
    public void Postarzej()
    {
        wiek++;
    }
}
