#pragma once
#include "Stale.h"

class Organizm;

class Swiat
{
protected:
	int rozmiarx;
	int rozmiary;
	char** plansza;
	Organizm** tab_wszystkich;

	bool running;
	int linijka;
	string kierunekczlowieka;
	int xczlowieka;
	int yczlowieka;

	void Sort();

public:
	Swiat(int x, int y);

	int GetRozmiarX();
	int GetRozmiarY();
	bool isRunning();
	void Linijka();
	void Dopisz(int x, int y, char zn);
	void DodajOrganizm(Organizm* o);
	Organizm* GetOrganizm(int x, int y);
	void ZabijOrganizm(int x, int y);
	void UsunOrganizm(int x, int y);
	bool CzySaOtoczone(Organizm* o1, Organizm* o2);

	void GetInput();
	void SetKierunekCzlowieka(string kierunek);
	string GetKierunekCzlowieka();
	int GetXczlowieka();
	int GetYczlowieka();
	void Update();
	void Draw();
	void Clean();
	void gotoxy(int x, int y);
	void WypiszZdarzenie(string tekst);

	void DodajCzlowieka(Organizm* o);

	void DrawMenu(int l);
	void Save(int licz);
	void Load();


	~Swiat();
};