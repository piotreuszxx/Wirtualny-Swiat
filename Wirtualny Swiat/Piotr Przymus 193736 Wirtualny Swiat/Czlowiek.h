#pragma once
#include "Zwierze.h"

class Czlowiek : public Zwierze
{
private:
	int licznik;
public:
	Czlowiek(int polX, int polY, Swiat& swiat);
	Czlowiek(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, int licznik, Swiat& swiat);
	~Czlowiek();
	void akcja() override;
	void PostawDziecko(int x, int y) override;
	void ZwiekszSile();
	int GetLicznik();
};