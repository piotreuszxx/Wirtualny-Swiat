#include "Mlecz.h"

Mlecz::Mlecz(int polX, int polY, Swiat& swiat)
	: Roslina(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 0;
	sila = 0;
	znak_gatunku = MLECZ;
	nazwa = "Mlecz";
}

Mlecz::Mlecz(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Roslina(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{
}

Mlecz::~Mlecz()
{
}

void Mlecz::akcja()
{
	for (int i = 0; i < 3; i++)
		Roslina::akcja();
}

void Mlecz::PostawDziecko(int x, int y)
{
	int szansa = rand() % RNG_MLECZ;
	if (szansa == 0)
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") rozsiewa sie na (" + to_string(x) + "," + to_string(y) + ")");
		swiat.DodajOrganizm(new Mlecz(x, y, swiat));
		swiat.gotoxy(40, 8);
		return;
	}
	swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie rozsiewa sie");
	swiat.gotoxy(40, 8);
}