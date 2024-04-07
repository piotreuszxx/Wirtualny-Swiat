#include "Owca.h"

Owca::Owca(int polX, int polY, Swiat& swiat)
	: Zwierze(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 4;
	sila = 4;
	znak_gatunku = OWCA;
	nazwa = "Owca";
}

Owca::Owca(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Zwierze(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{
}

Owca::~Owca()
{
}

void Owca::PostawDziecko(int x, int y)
{
	swiat.DodajOrganizm(new Owca(x, y, swiat));
	swiat.gotoxy(40, 8);
}