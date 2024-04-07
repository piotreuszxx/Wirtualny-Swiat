#pragma once
#include "Zwierze.h"

class Owca : public Zwierze
{
public:
	Owca(int polX, int polY, Swiat& swiat);
	Owca(int polozenieX, int polozenieY, int sila, int inicjatywa, char znak_gatunku, int wiek, string nazwa, Swiat& swiat);
	~Owca();
	void PostawDziecko(int x, int y) override;
};