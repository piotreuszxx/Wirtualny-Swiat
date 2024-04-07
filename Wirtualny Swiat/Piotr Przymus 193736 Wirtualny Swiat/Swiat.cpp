#define _CRT_SECURE_NO_WARNINGS
#include "Swiat.h"
#include "Wilk.h"
#include "Owca.h"
#include "Lis.h"
#include "Zolw.h"
#include "Antylopa.h"
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "WilczeJagody.h"
#include "BarszczSosnowskiego.h"
#include "Czlowiek.h"

Swiat::Swiat(int x, int y)
{
	srand(time(NULL));
	rozmiarx = x;
	rozmiary = y;
	running = true;
	linijka = rozmiary + 1;
	kierunekczlowieka = "zostaje";
	tab_wszystkich = new Organizm * [rozmiarx * rozmiary];

	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		tab_wszystkich[i] = nullptr;
	}

	plansza = new char* [rozmiary];
	for (int i = 0; i < rozmiary; i++)
	{
		plansza[i] = new char[rozmiarx];
	}

	for (int i = 0; i < rozmiary; i++)
	{
		for (int j = 0; j < rozmiarx; j++)
		{
			plansza[i][j] = ZIEMIA;
		}
	}
	/*
	tab_wszystkich[0] = new Wilk(3, 4, *this);
	tab_wszystkich[1] = new Wilk(4, 4, *this);
	tab_wszystkich[2] = new Owca(2, 2, *this);
	tab_wszystkich[3] = new Owca(3, 2, *this);
	tab_wszystkich[4] = new Lis(0, 0, *this);
	tab_wszystkich[5] = new Lis(1, 0, *this);
	tab_wszystkich[6] = new Zolw(3,0, *this);
	tab_wszystkich[7] = new Zolw(4, 0, *this);
	tab_wszystkich[8] = new Antylopa(0, 3, *this);
	tab_wszystkich[9] = new Antylopa(1, 3, *this);
	tab_wszystkich[10] = new Trawa(0, 1, *this);
	tab_wszystkich[11] = new Trawa(1, 1, *this);
	tab_wszystkich[12] = new Mlecz(2, 1, *this);
	tab_wszystkich[13] = new Mlecz(3, 1, *this);
	tab_wszystkich[14] = new Guarana(4, 1, *this);
	tab_wszystkich[15] = new Guarana(0, 2, *this);
	tab_wszystkich[16] = new WilczeJagody(1, 2, *this);
	tab_wszystkich[17] = new WilczeJagody(2, 3, *this);
	tab_wszystkich[18] = new BarszczSosnowskiego(0, 4, *this);
	tab_wszystkich[19] = new BarszczSosnowskiego(1, 4, *this);
	*/
	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 2; j++)
		{
			int x, y;
			while (true)
			{
				x = rand() % rozmiarx;
				y = rand() % rozmiary;
				if (GetOrganizm(x, y) == nullptr)
				{
					break;
				}
			}
			if (i == 0)
			{
				if(j == 0)
					tab_wszystkich[i] = new Wilk(x, y, *this);
				else
					tab_wszystkich[i+10] = new Wilk(x, y, *this);
			}
			else if (i == 1)
			{
				if (j == 0)
					tab_wszystkich[i] = new Owca(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Owca(x, y, *this);
			}
			else if (i == 2)
			{
				if (j == 0)
					tab_wszystkich[i] = new Lis(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Lis(x, y, *this);
			}
			else if (i == 3)
			{
				if (j == 0)
					tab_wszystkich[i] = new Zolw(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Zolw(x, y, *this);
			}
			else if (i == 4)
			{
				if (j == 0)
					tab_wszystkich[i] = new Antylopa(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Antylopa(x, y, *this);
			}
			else if (i == 5)
			{
				if (j == 0)
					tab_wszystkich[i] = new Trawa(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Trawa(x, y, *this);
			}
			else if (i == 6)
			{
				if (j == 0)
					tab_wszystkich[i] = new Mlecz(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Mlecz(x, y, *this);
			}
			else if (i == 7)
			{
				if (j == 0)
					tab_wszystkich[i] = new Guarana(x, y, *this);
				else
					tab_wszystkich[i + 10] = new Guarana(x, y, *this);
			}
			else if (i == 8)
			{
				if (j == 0)
					tab_wszystkich[i] = new WilczeJagody(x, y, *this);
				else
					tab_wszystkich[i + 10] = new WilczeJagody(x, y, *this);
			}
		}
	}
	int xc, yc;
	while(true)
	{
		xc = rand() % rozmiarx;
		yc = rand() % rozmiary;
		if (GetOrganizm(xc, yc) == nullptr)
		{
			break;
		}
	}
	xczlowieka = xc;
	yczlowieka = yc;
}

void Swiat::DodajCzlowieka(Organizm* o)
{
	tab_wszystkich[20] = o;
}

void Swiat::Sort()
{
	int n = rozmiarx*rozmiary;
	bool swapped = true;
	while (swapped)
	{
		swapped = false;
		for (int i = 0; i < n - 1; i++)
		{
			if (tab_wszystkich[i] == nullptr)
			{
				tab_wszystkich[i] = tab_wszystkich[i + 1];
				tab_wszystkich[i + 1] = nullptr;
				swapped = true;
			}
			else if (tab_wszystkich[i + 1] == nullptr)
			{
				continue;
			}
			else if (tab_wszystkich[i]->GetInicjatywa() < tab_wszystkich[i + 1]->GetInicjatywa())
			{
				Organizm* temp = tab_wszystkich[i];
				tab_wszystkich[i] = tab_wszystkich[i + 1];
				tab_wszystkich[i + 1] = temp;
				swapped = true;
			}
		}
		n--;
	}
}

void Swiat::Linijka()
{
	linijka = rozmiary + 2;
}

int Swiat::GetRozmiarX()
{
	return rozmiarx;
}

int Swiat::GetRozmiarY()
{
	return rozmiary;
}

bool Swiat::isRunning()
{
	return running;
}

void Swiat::Dopisz(int x, int y, char zn)
{
	plansza[y][x] = zn;
}

void Swiat::DodajOrganizm(Organizm* o)
{
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] == nullptr)
		{
			tab_wszystkich[i] = o;
			gotoxy(20, 3);
			break;
		}
	}
}

Organizm* Swiat::GetOrganizm(int x, int y)
{
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] && tab_wszystkich[i]->GetPolozenieX() == x && tab_wszystkich[i]->GetPolozenieY() == y)
			return tab_wszystkich[i];
	}
	return nullptr;
}

void Swiat::ZabijOrganizm(int x, int y)
{
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] && tab_wszystkich[i]->GetPolozenieX() == x && tab_wszystkich[i]->GetPolozenieY() == y)
		{
			tab_wszystkich[i]->SetWiek(-1);
		}
	}
}

void Swiat::UsunOrganizm(int x, int y)
{
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] && tab_wszystkich[i]->GetPolozenieX() == x && tab_wszystkich[i]->GetPolozenieY() == y)
		{
			delete tab_wszystkich[i];
			tab_wszystkich[i] = nullptr;
		}
	}
}

void Swiat::Update()
{
	Linijka();
	Sort();
	/*
	gotoxy(46, 12);
	cout << "Gatunek: ";
	gotoxy(46, 13);
	cout << "Inicjatywa: ";
	gotoxy(46, 14);
	cout << "Wiek: ";
	gotoxy(46, 15);
	cout << "Sila: ";
	gotoxy(46, 16);
	cout << "Polozenie X: ";
	gotoxy(46, 17);
	cout << "Polozenie Y: ";

	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		gotoxy(60+i, 12);
		if (tab_wszystkich[i] != nullptr)
		{
			cout << tab_wszystkich[i]->GetZnak_Gatunku()<< " ";
			gotoxy(60 + i, 13);
			cout <<	tab_wszystkich[i]->GetInicjatywa() << " ";
			gotoxy(60 + i, 14);
			cout << tab_wszystkich[i]->GetWiek() << " ";
			gotoxy(60 + i, 15);
			cout << tab_wszystkich[i]->GetSila() << " ";
			gotoxy(60 + i, 16);
			cout << tab_wszystkich[i]->GetPolozenieX() << " ";
			gotoxy(60 + i, 17);
			cout << tab_wszystkich[i]->GetPolozenieY() << " ";
		}
		else cout << "- ";
	}
	*/
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] != nullptr)
			tab_wszystkich[i]->Postarzej();
	}
	for (int i = 0; i < rozmiarx*rozmiary; i++)
	{
		if (tab_wszystkich[i] != nullptr)
		{
			if (tab_wszystkich[i]->GetWiek())
			{
				tab_wszystkich[i]->akcja();
				//Sleep(3000);
			}
		}
	}
}

void Swiat::Draw()
{
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] != nullptr)
		{
			tab_wszystkich[i]->rysowanie();
		}
	}

	gotoxy(0, 0);
	cout << " ";
	for (int i = 0; i < rozmiary + 1; i++)
	{
		if (i == 0)
		{
			for (int j = 1; j < rozmiarx+1; j++)
			{
				gotoxy(j, i);
				cout << j-1;
			}
		}
		else
		{
			gotoxy(0, i);
			cout << i - 1;
		}
	}

	for (int i = 0; i < rozmiary; i++)
	{
		for (int j = 0; j < rozmiarx; j++)
		{
			gotoxy(j+1, i+1);
			if (GetOrganizm(j, i) != nullptr)
				cout << plansza[i][j];
			else
				cout << ZIEMIA;
		}
	}
}

string Swiat::GetKierunekCzlowieka()
{
	return kierunekczlowieka;
}

void Swiat::SetKierunekCzlowieka(string kierunek)
{
	kierunekczlowieka = kierunek;
}

int Swiat::GetXczlowieka()
{
	return xczlowieka;
}

int Swiat::GetYczlowieka()
{
	return yczlowieka;
}

bool Swiat::CzySaOtoczone(Organizm* o1, Organizm* o2)
{
	int x1 = o1->GetPolozenieX();
	int y1 = o1->GetPolozenieY();
	int x2 = o2->GetPolozenieX();
	int y2 = o2->GetPolozenieY();
	if ((x1 - 1 < 0 || ( x1-1 >= 0 && GetOrganizm(x1 - 1, y1) != nullptr)) && (x1 + 1 >= rozmiarx || (x1 + 1 < rozmiarx && GetOrganizm(x1 + 1, y1) != nullptr)) && (y1 - 1 < 0 || (y1 - 1 >= 0 && GetOrganizm(x1, y1 - 1) != nullptr)) && (y1 + 1 >= rozmiary || (y1 + 1 < rozmiary && GetOrganizm(x1, y1 + 1) != nullptr)))
	{
		if ((x2 - 1 < 0 || (x2 - 1 >= 0 && GetOrganizm(x2 - 1, y2) != nullptr)) && (x2 + 1 >= rozmiarx || (x2 + 1 < rozmiarx && GetOrganizm(x2 + 1, y2) != nullptr)) && (y2 - 1 < 0 || (y2 - 1 >= 0 && GetOrganizm(x2, y2 - 1) != nullptr)) && (y2 + 1 >= rozmiary || (y2 + 1 < rozmiary && GetOrganizm(x2, y2 + 1) != nullptr)))
		{
			return true;
		}
	}
	return false;
}

void Swiat::Clean()
{
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] != nullptr)
			delete tab_wszystkich[i];
	}
	delete[] tab_wszystkich;

	for (int i = 0; i < rozmiary; i++)
	{
		if (plansza[i] != nullptr)
			delete[] plansza[i];
	}
	delete[] plansza;
}

void Swiat::gotoxy(int x, int y)
{
	COORD coord = { x,y };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void Swiat::WypiszZdarzenie(string tekst)
{
	gotoxy(0, linijka);
	cout << tekst;
	linijka++;
}

void Swiat::DrawMenu(int l)
{
	gotoxy(50, 2);
	cout << "Kliknij klawisz i zatwierdz enterem.";
	gotoxy(50, 3);
	cout << "ENTER - zatwierdzenie wyboru (sam enter = zostanie na miejscu";
	gotoxy(50, 4);
	cout << "Strzalki - kierunek";
	gotoxy(50, 5);
	cout << "0 - zostanie na miejscu";
	gotoxy(50, 6);
	cout << "ESC - wyjscie z gry";
	gotoxy(50, 7);
	cout << "s - zapis gry";
	gotoxy(50, 8);
	cout << "l - wczytanie gry";
	gotoxy(50, 9);
	cout << "m - uzyj mocy";
	gotoxy(50, 10);
	if (l < 0 || l > 10)
	{
		cout << "Czlowiek nie zyje";
	}
	else if (l == 0)
		cout << "Umiejetnosc gotowa do uzycia";
	else if (l > 5)
		cout << "Umiejetnosc aktywna, pozostalo " << l - 5 << " tur";
	else if (l == 5)
		cout << "Koniec umiejetnosci, do kolejnego uzycia pozostalo Ci 5 tur";
	else if (l < 5 && l != 0)
		cout << "Nie mozesz uzyc umiejetnosci, pozostalo " << l << " tur";
}

void Swiat::Save(int licz)
{
	Sort();
	system("cls");
	FILE* plik2;
	char nazwapliku[100];
	cout << "Podaj nazwe pliku:" << endl;
	cin >> nazwapliku;
	plik2 = fopen(nazwapliku, "w");
	fclose(plik2);
	
	fstream plik;
	plik.open(nazwapliku, ios::out);
	plik << rozmiarx << " " << rozmiary << endl;
	for (int i = 0; i < rozmiarx * rozmiary; i++)
	{
		if (tab_wszystkich[i] == nullptr)
			break;
		else
		{
			if (tab_wszystkich[i]->GetNazwa() == "Czlowiek")
				plik << tab_wszystkich[i]->GetNazwa() << " " << tab_wszystkich[i]->GetPolozenieX() << " " << tab_wszystkich[i]->GetPolozenieY() << " " << tab_wszystkich[i]->GetSila() << " " << tab_wszystkich[i]->GetInicjatywa() << " " << tab_wszystkich[i]->GetZnak_Gatunku() << " " << tab_wszystkich[i]->GetWiek() << " " << licz << endl;
			else
				plik << tab_wszystkich[i]->GetNazwa() << " " << tab_wszystkich[i]->GetPolozenieX() << " " << tab_wszystkich[i]->GetPolozenieY() << " " << tab_wszystkich[i]->GetSila() << " " << tab_wszystkich[i]->GetInicjatywa() << " " << tab_wszystkich[i]->GetZnak_Gatunku() << " " << tab_wszystkich[i]->GetWiek() << endl;
		}
	}
	plik.close();
	cout<< "Zapisano gre do pliku " << nazwapliku << endl;
	Sleep(1000);
}

void Swiat::Load()
{
	system("cls");
	char nazwapliku[100];
	cout << "Podaj nazwe pliku:" << endl;
	cin >> nazwapliku;
	fstream plik;
	plik.open(nazwapliku, ios::in);
	if (plik.good() == false)
	{
		cout << "Nie ma takiego pliku" << endl;
		Sleep(1000);
		return;
	}
	else
	{
		for (int i = 0; i < rozmiarx * rozmiary; i++)
		{
			if (tab_wszystkich[i] != nullptr)
				delete tab_wszystkich[i];
		}
		delete[] tab_wszystkich;

		for (int i = 0; i < rozmiary; i++)
		{
			if (plansza[i] != nullptr)
				delete[] plansza[i];
		}
		delete[] plansza;


		plik >> rozmiarx >> rozmiary;

		tab_wszystkich = new Organizm * [rozmiarx * rozmiary];

		for (int i = 0; i < rozmiarx * rozmiary; i++)
		{
			tab_wszystkich[i] = nullptr;
		}

		for (int i = 0; i < rozmiarx * rozmiary; i++)
		{
			int x, y, sila, inicjatywa, wiek;
			int licz;
			char znak;
			string nazwa;
			plik >> nazwa;
			if (nazwa == "Czlowiek")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek >> licz;
				tab_wszystkich[i] = new Czlowiek(x, y, sila, inicjatywa, znak, wiek, nazwa, licz, *this);
			}
			else if(nazwa == "Wilk")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Wilk(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Owca")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Owca(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Lis")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Lis(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Zolw")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Zolw(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Antylopa")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Antylopa(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Trawa")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Trawa(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Mlecz")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Mlecz(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
			else if (nazwa == "Guarana")
			{
				plik >> x >> y >> sila >> inicjatywa >> znak >> wiek;
				tab_wszystkich[i] = new Guarana(x, y, sila, inicjatywa, znak, wiek, nazwa, *this);
			}
		}

		plansza = new char* [rozmiary];
		for (int i = 0; i < rozmiary; i++)
		{
			plansza[i] = new char[rozmiarx];
		}

		for (int i = 0; i < rozmiary; i++)
		{
			for (int j = 0; j < rozmiarx; j++)
			{
				plansza[i][j] = ZIEMIA;
			}
		}
		plik.close();
		cout << "Wczytano gre z pliku " << nazwapliku << endl;
		Sleep(1000);
	}

}

Swiat::~Swiat()
{
}