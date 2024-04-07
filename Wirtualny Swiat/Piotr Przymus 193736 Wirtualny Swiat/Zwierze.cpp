#include "Zwierze.h"

Zwierze::Zwierze(int polX, int polY, Swiat& swiat)
	: Organizm(polX, polY, swiat)
{}

Zwierze::Zwierze(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Organizm(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{}

void Zwierze::akcja()
{
	//funkcja losuj¹ca w któr¹ stronê organizm ma siê poruszyæ, jeœli na tym miejscu stoi inny organizm to wywo³uje jego funkcje kolizja
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

void Zwierze::kolizja(Organizm* o) // o wjechal w this
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

void Zwierze::Rozmnazanie(Organizm* o)
{
	if (swiat.CzySaOtoczone(this, o))
	{
		string px = to_string(polozeniex);
		string py = to_string(polozeniey);
		string px2 = to_string(o->GetPolozenieX());
		string py2 = to_string(o->GetPolozenieY());
		swiat.WypiszZdarzenie(o->GetNazwa() + "(" + px2 + "," + py2 + ") nie moze sie rozmnazac z " + nazwa + "(" + px + "," + py + ")");
		return;
	}

	int x1 = polozeniex;
	int y1 = polozeniey;
	int x2 = o->GetPolozenieX();
	int y2 = o->GetPolozenieY();
	int x, y, kierunek;
	while (true)
	{
		kierunek = rand() % 8;
		if (kierunek == 0)
		{
			if (y1 > 0 && swiat.GetOrganizm(x1, y1 - 1) == nullptr)
			{
				y1--;
				x = x1;
				y = y1;
				break;
			}
		}
		else if (kierunek == 1)
		{
			if (y1 < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x1, y1 + 1) == nullptr)
			{
				y1++;
				x = x1;
				y = y1;
				break;
			}
		}
		else if (kierunek == 2)
		{
			if (x1 > 0 && swiat.GetOrganizm(x1 - 1, y1) == nullptr)
			{
				x1--;
				x = x1;
				y = y1;
				break;
			}
		}
		else if (kierunek == 3)
		{
			if (x1 < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x1 + 1, y1) == nullptr)
			{
				x1++;
				x = x1;
				y = y1;
				break;
			}
		}
		else if (kierunek == 4)
		{
			if (y2 > 0 && swiat.GetOrganizm(x2, y2 - 1) == nullptr)
			{
				y2--;
				x = x2;
				y = y2;
				break;
			}
		}
		else if (kierunek == 5)
		{
			if (y2 < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x2, y2 + 1) == nullptr)
			{
				y2++;
				x = x2;
				y = y2;
				break;
			}
		}
		else if (kierunek == 6)
		{
			if (x2 > 0 && swiat.GetOrganizm(x2 - 1, y2) == nullptr)
			{
				x2--;
				x = x2;
				y = y2;
				break;
			}
		}
		else if (kierunek == 7)
		{
			if (x2 < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x2 + 1, y2) == nullptr)
			{
				x2++;
				x = x2;
				y = y2;
				break;
			}
		}
	}
	swiat.WypiszZdarzenie(o->GetNazwa() + "(" + to_string(o->GetPolozenieX()) + "," + to_string(o->GetPolozenieY()) + ") rozmnaza sie z " + nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") na pole: " + to_string(x) + "," + to_string(y));
	o->PostawDziecko(x, y);
}

Zwierze::~Zwierze()
{}