#pragma once
#include "Roslina.h"

class Trawa : public Roslina
{
public:
	Trawa(int polX, int polY, Swiat& swiat);
	Trawa(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~Trawa();
	void PostawDziecko(int x, int y) override;
};