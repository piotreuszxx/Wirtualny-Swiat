#pragma once
#include "Zwierze.h"

class Lis : public Zwierze
{
public:
	Lis(int polX, int polY, Swiat& swiat);
	Lis(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	void akcja() override;
	~Lis();
	void PostawDziecko(int x, int y) override;
};