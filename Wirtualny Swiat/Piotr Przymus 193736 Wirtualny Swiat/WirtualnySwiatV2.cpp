#include "Swiat.h"
#include "Czlowiek.h"

int main()
{
	SetConsoleTitleA("Piotr Przymus 193736 Grupa I");
	int r_x, r_y;
	while (true)
	{
		cout << "Podaj wymiary swiata (x, y): ";
		cin >> r_x >> r_y;
		if (r_x > 0 && r_y > 0 && r_x * r_y >= 25)
			break;
	}
	system("cls");
	Swiat* swiat = new Swiat(r_x, r_y);
	int xc, yc;
	xc = swiat->GetXczlowieka();
	yc = swiat->GetYczlowieka();
	Czlowiek* c = new Czlowiek (xc, yc, *swiat);
	swiat->DodajCzlowieka(c);
	swiat->Draw();
	Sleep(3000);

	while (swiat->isRunning())
	{
		char znak;
		bool uzytoMocy = false;
		string kierunekczlowieka = "zostaje";
		int licz = c->GetLicznik();
		swiat->DrawMenu(licz);

		while (true)
		{
			swiat->gotoxy(50, 11);
			znak = _getch();
			if (znak == ENTER)
			{
				swiat->SetKierunekCzlowieka(kierunekczlowieka);
				break;
			}
			else if (znak == ARROW_LEFT)
			{
				cout << "------- lewo ------";
				kierunekczlowieka = "lewo";
			}
			else if (znak == ARROW_RIGHT)
			{
				cout << "------ prawo ------";
				kierunekczlowieka = "prawo";
			}
			else if (znak == ARROW_UP)
			{
				cout << "------- gora ------";
				kierunekczlowieka = "gora";
			}
			else if (znak == ARROW_DOWN)
			{
				cout << "------- dol -------";
				kierunekczlowieka = "dol";
			}
			else if (znak == '0')
			{
				cout << "----- zostaje -----";
				kierunekczlowieka = "zostaje";
			}
			else if (znak == ESC)
			{
				return 0;
			}
			else if (znak == 's')
			{
				swiat->Save(c->GetLicznik());
				system("cls");
				swiat->DrawMenu(licz);
				swiat->Draw();
			}
			else if (znak == 'l')
			{
				swiat->Load();
				system("cls");
				swiat->DrawMenu(licz);
				swiat->Draw();
			}
			else if (znak == 'm' && licz == 0)
			{
				c->ZwiekszSile();
				cout << "- moc aktywowana -";
			}
		}
		system("cls");
		swiat->Update();
		swiat->Draw();
		//Sleep(2000);
	}

	swiat->Clean();
	delete c;
	delete swiat;
}