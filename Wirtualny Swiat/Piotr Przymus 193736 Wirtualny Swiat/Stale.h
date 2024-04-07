#pragma once
#include <iostream>
#include <windows.h>
#include <stdio.h>
#include <conio.h>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <fstream>
#include <ctime>
using namespace std;


// przyciski
#define ESC 0x1b			
#define ARROW_UP 0x48 // 72		
#define ARROW_DOWN 0x50 // 80
#define ARROW_RIGHT 0x4d // 77
#define ARROW_LEFT 0x4b // 75
#define ENTER 0x0d

// symbole
#define ZIEMIA '.'
#define WILK 'W' // '\xF0\x9F\x90\xBA' //🐺
#define OWCA 'O' // '\xF0\x9F\x90\x91' //🐑
#define LIS 'L' // '\xF0\x9F\xA6\x8A' //🦊
#define ZOLW 'Z' // '\xF0\x9F\x90\xA2' //🐢
#define ANTYLOPA 'A' // '\xF0\x9F\xA6\x8C' //🦌
#define CZLOWIEK 'C' // '\xF0\x9F\x9A\xB6' //🚶

#define TRAWA 'T' // '\xF0\x9F\x8C\xBF' //🌿
#define MLECZ 'M' // '\xF0\x9F\x8C\xBB' //🌻
#define GUARANA 'G' // '\xF0\x9F\x8D\x87' //🍇
#define WILCZE_JAGODY 'J' // \xF0\x9F\x8D\x92' //🍒
#define BARSZCZ_SOSNOWSKIEGO 'B' // '\xF0\x9F\xA5\xA6' //🥦

// stale
#define RNG_TRAWA 2
#define	RNG_MLECZ 4
#define RNG_GUARANA 4
#define RNG_WILCZE_JAGODY 10
#define RNG_BARSZCZ_SOSNOWSKIEGO 10
