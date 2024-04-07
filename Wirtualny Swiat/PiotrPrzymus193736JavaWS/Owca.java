package WSJAVA;

public class Owca extends Zwierze{
    public Owca(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 4;
        this.inicjatywa = 4;
        this.znak_gatunku = Stale.OWCA;
        this.wiek = 0;
        this.nazwa = "Owca";
    }
    public Owca(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void PostawDziecko(int x, int y)
    {
        swiat.DodajOrganizm(new Owca(x, y, swiat));
        //swiat.gotoxy(40, 8);
    }
}
