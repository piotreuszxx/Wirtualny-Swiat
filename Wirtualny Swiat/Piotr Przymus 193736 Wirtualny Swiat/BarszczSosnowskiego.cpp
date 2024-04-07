#include "BarszczSosnowskiego.h"

BarszczSosnowskiego::BarszczSosnowskiego(int polX, int polY, Swiat& swiat)
	: Roslina(polX, polY, swiat)
{
	wiek = 0;
	inicjatywa = 0;
	sila = 10;
	znak_gatunku = BARSZCZ_SOSNOWSKIEGO;
	nazwa = "BarszczSosnowskiego";
}

BarszczSosnowskiego::BarszczSosnowskiego(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak, int wiek, string nazwa, Swiat& swiat)
	: Roslina(polozenieX, polozenieY, sila, inicjatywa, znak, wiek, nazwa, swiat)
{
}

BarszczSosnowskiego::~BarszczSosnowskiego()
{
}

void BarszczSosnowskiego::akcja()
{
	bool rozmnozy = true;
	if (CzyOtoczona())
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie ma gdzie sie rozsiac");
		rozmnozy = false;
	}
	if(rozmnozy)
		Rozmnazanie(nullptr);
	bool zabil = false;
	if (polozeniex > 0 && swiat.GetOrganizm(polozeniex - 1, polozeniey) != nullptr)
	{
		swiat.gotoxy(70, 30);
		cout << "siedzi 1";
		if (swiat.GetOrganizm(polozeniex - 1, polozeniey)->GetInicjatywa() > 0)
		{
			swiat.gotoxy(70, 31);
			cout << "siedzi 1v2";
			Organizm* o = swiat.GetOrganizm(polozeniex - 1, polozeniey);
			string px = to_string(polozeniex);
			string py = to_string(polozeniey);
			string p2x = to_string(o->GetPolozenieX());
			string p2y = to_string(o->GetPolozenieY());
			swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o->GetNazwa() + "(" + p2x + "," + p2y + ")");
			swiat.UsunOrganizm(polozeniex - 1, polozeniey);
			zabil = true;
		}
	}
	if (polozeniex < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(polozeniex + 1, polozeniey) != nullptr)
	{
		swiat.gotoxy(70, 32);
		cout << "siedzi 2";
		if (swiat.GetOrganizm(polozeniex + 1, polozeniey)->GetInicjatywa() > 0)
		{
			swiat.gotoxy(70, 33);
			cout << "siedzi 2v2";
			Organizm* o = swiat.GetOrganizm(polozeniex + 1, polozeniey);
			string px = to_string(polozeniex);
			string py = to_string(polozeniey);
			string p2x = to_string(o->GetPolozenieX());
			string p2y = to_string(o->GetPolozenieY());
			swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o->GetNazwa() + "(" + p2x + "," + p2y + ")");
			swiat.UsunOrganizm(polozeniex + 1, polozeniey);
			zabil = true;
		}
	}
	if (polozeniey > 0 && swiat.GetOrganizm(polozeniex, polozeniey - 1) != nullptr)
	{
		swiat.gotoxy(70, 34);
		cout << "siedzi 3";
		if (swiat.GetOrganizm(polozeniex, polozeniey - 1)->GetInicjatywa() > 0)
		{
			swiat.gotoxy(70, 35);
			cout << "siedzi 3v2";
			Organizm* o = swiat.GetOrganizm(polozeniex, polozeniey - 1);
			string px = to_string(polozeniex);
			string py = to_string(polozeniey);
			string p2x = to_string(o->GetPolozenieX());
			string p2y = to_string(o->GetPolozenieY());
			swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o->GetNazwa() + "(" + p2x + "," + p2y + ")");
			swiat.UsunOrganizm(polozeniex, polozeniey - 1);
			zabil = true;
		}
	}
	if (polozeniey < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(polozeniex, polozeniey + 1) != nullptr)
	{
		swiat.gotoxy(70, 36);
		cout << "siedzi 4";
		if (swiat.GetOrganizm(polozeniex, polozeniey + 1)->GetInicjatywa() > 0)
		{
			swiat.gotoxy(70, 37);
			cout << "siedzi 4v2";
			Organizm* o = swiat.GetOrganizm(polozeniex, polozeniey + 1);
			string px = to_string(polozeniex);
			string py = to_string(polozeniey);
			string p2x = to_string(o->GetPolozenieX());
			string p2y = to_string(o->GetPolozenieY());
			swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o->GetNazwa() + "(" + p2x + "," + p2y + ")");
			swiat.UsunOrganizm(polozeniex, polozeniey + 1);
			zabil = true;
		}
	}
	if(!zabil)
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie zabil zadnego organizmu");
}

void BarszczSosnowskiego::kolizja(Organizm* o)
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
		swiat.WypiszZdarzenie(o->GetNazwa() + "(" + px2 + "," + py2 + ") nadgryzl " + nazwa + "(" + px + "," + py + ") i od niego ginie");
		swiat.ZabijOrganizm(o->GetPolozenieX(), o->GetPolozenieY());
	}
}

void BarszczSosnowskiego::PostawDziecko(int x, int y)
{
	int szansa = rand() % RNG_BARSZCZ_SOSNOWSKIEGO;
	if (szansa == 0)
	{
		swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") rozsiewa sie na (" + to_string(x) + "," + to_string(y) + ")");
		swiat.DodajOrganizm(new BarszczSosnowskiego(x, y, swiat));
		swiat.gotoxy(40, 8);
		return;
	}
	swiat.WypiszZdarzenie(nazwa + "(" + to_string(polozeniex) + "," + to_string(polozeniey) + ") nie rozsiewa sie");
	swiat.gotoxy(40, 8);
}