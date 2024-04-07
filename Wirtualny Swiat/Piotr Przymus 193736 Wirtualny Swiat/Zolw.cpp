#include "Zolw.h"

Zolw::Zolw(int polX, int polY, Swiat& swiat)
	: Zwierze(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 1;
	sila = 2;
	znak_gatunku = ZOLW;
	nazwa = "Zolw";
}

Zolw::Zolw(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Zwierze(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{
}

void Zolw::akcja()
{
	//funkcja losuj¹ca w któr¹ stronê organizm ma siê poruszyæ, jeœli na tym miejscu stoi inny organizm to wywo³uje jego funkcje kolizja
	int szansa = rand() % 4;
	if (szansa != 0)
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie rusza sie w tej turze");
		return;
	}
	else
	{
		int x = polozeniex;
		int y = polozeniey;
		int kierunek;
		while (true)
		{
			kierunek = rand() % 4;
			if (kierunek == 0)
			{
				if (y > 0)
				{
					y--;
					break;
				}
			}
			else if (kierunek == 1)
			{
				if (y < swiat.GetRozmiarY() - 1)
				{
					y++;
					break;
				}
			}
			else if (kierunek == 2)
			{
				if (x > 0)
				{
					x--;
					break;
				}
			}
			else if (kierunek == 3)
			{
				if (x < swiat.GetRozmiarX() - 1)
				{
					x++;
					break;
				}
			}
		}
		if (swiat.GetOrganizm(x, y) != nullptr)
		{
			swiat.GetOrganizm(x, y)->kolizja(this); //this wjechal w organizm na polu x,y
			if (wiek != -1 && swiat.GetOrganizm(x, y) == nullptr)
			{
				swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") przemiescil sie na (" + to_string(x) + "," + to_string(y) + ")");
				polozeniex = x; // jesli this nie zostal zjedzony ORAZ zjadl to zmien polozenie
				polozeniey = y;
			}
			if (wiek == -1)
			{
				swiat.UsunOrganizm(polozeniex, polozeniey); // jesli this zostal zjedzony to go usun
			}
		}
		else
		{
			swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") przemiescil sie na (" + to_string(x) + "," + to_string(y) + ")");
			polozeniex = x;
			polozeniey = y;
		}
	}
}

void Zolw::kolizja(Organizm* o) // o wjechal w this
{
	if (o->GetNazwa() == nazwa)
	{
		if (wiek > 0)
			Rozmnazanie(o);
		else
		{
			string px = to_string(polozeniex);
			string py = to_string(polozeniey);
			string p2x = to_string(o->GetPolozenieX());
			string p2y = to_string(o->GetPolozenieY());
			swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") probowal rozmnozyc sie z " + nazwa + "(" + px + "," + py + "), ale drugi jest za mlody");
			return;
		}
	}
	else
	{
		string px = to_string(polozeniex);
		string py = to_string(polozeniey);
		string p2x = to_string(o->GetPolozenieX());
		string p2y = to_string(o->GetPolozenieY());
		if (o->GetSila() >= sila)
		{
			if (o->GetSila() < 5)
			{
				swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") zaatakowal " + nazwa + "(" + px + "," + py + "), ale on odpiera jego atak, bo ma sile < 5");
				return;
			}
			swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") zabija " + nazwa + "(" + px + "," + py + ")");
			swiat.ZabijOrganizm(polozeniex, polozeniey); // zabicie this
			swiat.UsunOrganizm(polozeniex, polozeniey); //usuniecie this z planszy
		}
		else
		{
			swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") ginie od " + nazwa + "(" + px + "," + py + ")");
			swiat.ZabijOrganizm(o->GetPolozenieX(), o->GetPolozenieY()); // zabicie atakujacego
		}
	}
}

Zolw::~Zolw()
{
}

void Zolw::PostawDziecko(int x, int y)
{
	swiat.DodajOrganizm(new Zolw(x, y, swiat));
	swiat.gotoxy(40, 8);
}