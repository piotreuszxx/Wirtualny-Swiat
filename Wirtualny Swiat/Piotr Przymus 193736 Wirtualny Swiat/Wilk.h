#pragma once
#include "Zwierze.h"

class Wilk : public Zwierze
{
public:
	//Wilk();
	Wilk(int polX, int polY, Swiat& swiat);
	Wilk(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~Wilk();
	void PostawDziecko(int x, int y) override;
};