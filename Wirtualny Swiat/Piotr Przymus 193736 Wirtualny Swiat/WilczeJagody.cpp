#include "WilczeJagody.h"

WilczeJagody::WilczeJagody(int polX, int polY, Swiat& swiat)
	: Roslina(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 0;
	sila = 99;
	znak_gatunku = WILCZE_JAGODY;
	nazwa = "WilczeJagody";
}

WilczeJagody::WilczeJagody(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat)
	: Roslina(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat)
{
}

WilczeJagody::~WilczeJagody()
{
}

void WilczeJagody::kolizja(Organizm* o)
{
	string px = to_string(polozeniex);
	string py = to_string(polozeniey);
	string px2 = to_string(o->GetPolozenieX());
	string py2 = to_string(o->GetPolozenieY());
	if (o->GetSila() >= sila)
	{
		swiat.WypiszZdarzenie(o->GetNazwa() + "(" + px2 + "," + py2 + ") zjadl " + nazwa + "(" + px + "," + py + ") i razem z nim ginie");
		swiat.ZabijOrganizm(o->GetPolozenieX(), o->GetPolozenieY());
		swiat.UsunOrganizm(polozeniex, polozeniey);

	}
	else
	{
		swiat.WypiszZdarzenie(o->GetNazwa() + "(" + px2 + "," + py2 + ") nadgryzl " + nazwa + "(" + px + "," + py + ") i od nich ginie");
		swiat.ZabijOrganizm(o->GetPolozenieX(), o->GetPolozenieY());
	}
}

void WilczeJagody::PostawDziecko(int x, int y)
{
	int szansa = rand() % RNG_WILCZE_JAGODY;
	if (szansa == 0)
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") rozsiewaja sie na (" + to_string(x) + "," + to_string(y) + ")");
		swiat.DodajOrganizm(new WilczeJagody(x, y, swiat));
		swiat.gotoxy(40, 8);
		return;
	}
	swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie rozsiewaja sie");
	swiat.gotoxy(40, 8);
}