#include "Antylopa.h"

Antylopa::Antylopa(int polX, int polY, Swiat& swiat)
	: Zwierze(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 4;
	sila = 4;
	znak_gatunku = ANTYLOPA;
	nazwa = "Antylopa";
}

Antylopa::Antylopa(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat)
	: Zwierze(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat)
{
}

void Antylopa::akcja()
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
			if (y > 1)
			{
				y-=2;
				break;
			}
		}
		else if (kierunek == 1)
		{
			if (y < swiat.GetRozmiarY() - 2)
			{
				y+=2;
				break;
			}
		}
		else if (kierunek == 2)
		{
			if (x > 1)
			{
				x-=2;
				break;
			}
		}
		else if (kierunek == 3)
		{
			if (x < swiat.GetRozmiarX() - 2)
			{
				x+=2;
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

void Antylopa::kolizja(Organizm* o) // o wjechal w this
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

		int szansa = rand() % 2;
		if (szansa)
		{
			int kierunek;
			int x = polozeniex;
			int y = polozeniey;
			bool byly[4];
			for (int i = 0; i < 4; i++)
			{
				byly[i] = false;
			}
			while (true)
			{
				kierunek = rand() % 4;
				if (kierunek == 0 && !byly[kierunek])
				{
					byly[kierunek] = true;
					if (y > 0 && swiat.GetOrganizm(x, y - 1) == nullptr)
					{
						y--;
						break;
					}
				}
				else if (kierunek == 1 && !byly[kierunek])
				{
					byly[kierunek] = true;
					if (y < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x, y + 1) == nullptr)
					{
						y++;
						break;
					}
				}
				else if (kierunek == 2 && !byly[kierunek])
				{
					byly[kierunek] = true;
					if (x > 0 && swiat.GetOrganizm(x - 1, y) == nullptr)
					{
						x--;
						break;
					}
				}
				else if (kierunek == 3 && !byly[kierunek])
				{
					byly[kierunek] = true;
					if (x < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x + 1, y) == nullptr)
					{
						x++;
						break;
					}
				}
				if (byly[0] && byly[1] && byly[2] && byly[3])
				{
					swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") zabija " + nazwa + "(" + px + "," + py + ")");
					swiat.ZabijOrganizm(polozeniex, polozeniey);
					break;
				}
			}
			if(wiek != -1)
			{
				swiat.WypiszZdarzenie(o->GetNazwa() + "(" + p2x + "," + p2y + ") probowal zjesc " + nazwa + "(" + px + "," + py + "), ale ta uciekla na (" + to_string(x) + "," + to_string(y) + ")");
				polozeniex = x;
				polozeniey = y;
			}
			else
			{
				swiat.UsunOrganizm(polozeniex, polozeniey);
			}
		}
		else
		{
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
}

Antylopa::~Antylopa()
{}

void Antylopa::PostawDziecko(int x, int y)
{
	swiat.DodajOrganizm(new Antylopa(x, y, swiat));
	swiat.gotoxy(40, 8);
}