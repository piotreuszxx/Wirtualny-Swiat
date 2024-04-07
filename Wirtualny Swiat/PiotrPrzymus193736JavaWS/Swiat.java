package WSJAVA;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Swiat implements ActionListener, KeyListener {
    Czlowiek c;
    Random rand = new Random();
    JFrame frame = new JFrame("Piotr Przymus 193736 Wirtualny Swiat");
    JPanel title_panel = new JPanel();
    JPanel container = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel Zdarzenia_panel = new JPanel();
    JTextArea text_zdarzenia = new JTextArea();
    JPanel menu_buttons_panel = new JPanel();
    JButton[][] buttons;
    JButton[] menu_buttons;

    private int rozmiarx, rozmiary;
    private char[][] plansza;
    private Organizm[] tab_wszystkich;
    private boolean running;
    private int linijka;
    private String kierunekczlowieka;
    private int xczlowieka;
    private int yczlowieka;

    private void Sort()
    {
        int n = rozmiarx * rozmiary;
        boolean swapped = true;
        while(swapped)
        {
            swapped = false;
            for (int i = 0; i < n - 1; i++)
            {
                if (tab_wszystkich[i] == null)
                {
                    tab_wszystkich[i] = tab_wszystkich[i + 1];
                    tab_wszystkich[i + 1] = null;
                    swapped = true;
                }
                else if (tab_wszystkich[i + 1] == null)
                {
                    continue;
                }
                else if (tab_wszystkich[i].GetInicjatywa() < tab_wszystkich[i + 1].GetInicjatywa())
                {
                    Organizm temp = tab_wszystkich[i];
                    tab_wszystkich[i] = tab_wszystkich[i + 1];
                    tab_wszystkich[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }

    public Swiat(int r_x, int r_y)
    {
        c = null;
        rozmiarx = r_x;
        rozmiary = r_y;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(rozmiarx * 150, rozmiary * 130);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.addKeyListener(this);

        text_zdarzenia.setBackground(new Color(25, 25, 25));
        text_zdarzenia.setForeground(new Color(255, 255, 255, 255));
        text_zdarzenia.setFont(new Font(Font.SANS_SERIF, Font.BOLD,frame.getWidth()/rozmiary/15));
        text_zdarzenia.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 2000, 2000);

        container.setLayout(new GridLayout(1,2));
        container.setBounds(0, 0, 2000, 2000);

        button_panel.setLayout(new GridLayout(rozmiary, rozmiarx));
        button_panel.setBackground(new Color(150, 150, 150));

        menu_buttons_panel.setLayout(new GridLayout(1, 4));
        menu_buttons_panel.setBackground(new Color(150, 150, 150));


        Zdarzenia_panel.setLayout(new BorderLayout());
        Zdarzenia_panel.setBackground(new Color(25, 25, 25));

        buttons = new JButton[rozmiary][];
        for(int i=0;i<rozmiary; i++)
        {
            buttons[i] = new JButton[rozmiarx];
            for(int j=0; j<rozmiarx; j++)
            {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth()/rozmiarx/5));
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBackground(new Color(25, 25, 25));
                buttons[i][j].addActionListener(this);
                buttons[i][j].setText(Stale.ZIEMIA + "");
            }
        }

        menu_buttons = new JButton[4];
        for(int i=0;i<4; i++)
        {
            menu_buttons[i] = new JButton();
            menu_buttons_panel.add(menu_buttons[i]);
            menu_buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth()/rozmiarx/5));
            menu_buttons[i].setFocusable(false);
            menu_buttons[i].setBackground(new Color(25, 25, 25));
            menu_buttons[i].addActionListener(this);
        }
        menu_buttons_panel.setBounds(0, 0, 2000, 2000);
        menu_buttons[0].setText("Moc");
        menu_buttons[1].setText("Zapisz");
        menu_buttons[2].setText("Wczytaj");
        menu_buttons[3].setText("Kolejna");

        Zdarzenia_panel.add(text_zdarzenia);
        //frame.add(title_panel, BorderLayout.NORTH);
        container.add(button_panel);
        container.add(Zdarzenia_panel);
        frame.add(container, BorderLayout.CENTER);
        frame.add(menu_buttons_panel, BorderLayout.SOUTH);
        //frame.add(button_panel);
        //frame.add(Zdarzenia_panel, BorderLayout.SOUTH);

        running = true;
        linijka = 0;
        kierunekczlowieka = "zostaje";

        tab_wszystkich = new Organizm [rozmiarx * rozmiary];
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            tab_wszystkich[i] = null;
        }

        plansza = new char[rozmiary][];
        for (int i = 0; i < rozmiary; i++)
        {
            plansza[i] = new char[rozmiarx];
        }
        for (int i = 0; i < rozmiary; i++)
        {
            for (int j = 0; j < rozmiarx; j++)
            {
                plansza[i][j] = Stale.ZIEMIA;
            }
        }

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < rozmiarx*rozmiary/10; j++)
            {
                int x, y;
                while (true)
                {
                    x = rand.nextInt(rozmiarx);
                    y = rand.nextInt(rozmiary);
                    if (GetOrganizm(x, y) == null)
                    {
                        break;
                    }
                }
                if (i == 0)
                {
                    tab_wszystkich[i + 10 * j] = new Wilk(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 1)
                {
                    tab_wszystkich[i + 10 * j] = new Owca(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 2)
                {
                    tab_wszystkich[i + 10 * j] = new Lis(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 3)
                {
                    tab_wszystkich[i + 10 * j] = new Zolw(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 4)
                {
                    tab_wszystkich[i + 10 * j] = new Antylopa(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 5)
                {
                    tab_wszystkich[i + 10 * j] = new Trawa(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 6)
                {
                    tab_wszystkich[i + 10 * j] = new Mlecz(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 7)
                {
                    tab_wszystkich[i + 10 * j] = new Guarana(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else if (i == 8)
                {
                    tab_wszystkich[i + 10 * j] = new WilczeJagody(x, y, this);
                    buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                }
                else
                {
                    if(j != rozmiarx*rozmiary/10 - 1 || rozmiarx*rozmiary%10 != 0)
                    {
                        tab_wszystkich[i + 10 * j] = new BarszczSosnowskiego(x, y, this);
                        buttons[y][x].setText(tab_wszystkich[i + 10 * j].GetZnak_Gatunku() + "");
                    }
                }
            }
        }
        int xc, yc;
        while(true)
        {
            xc = rand.nextInt(rozmiarx);
            yc = rand.nextInt(rozmiary);
            if (GetOrganizm(xc, yc) == null)
            {
                break;
            }
        }
        xczlowieka = xc;
        yczlowieka = yc;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==menu_buttons[0])
        {
            if (c.GetLicznik() == 0)
            {
                int s = c.GetSila();
                c.ZwiekszSile();
                if(c.GetSila() != s)
                    text_zdarzenia.append("- moc aktywowana -");
            }
            return;
        }
        if(e.getSource()==menu_buttons[1])
        {
            if(c != null)
                Save(c.GetLicznik());
            else
                Save(0);
            return;
        }
        if(e.getSource()==menu_buttons[2])
        {
            Load();
            return;
        }
        if(e.getSource()==menu_buttons[3])
        {
            Update();
        }
        for(int i=0; i<rozmiary; i++)
        {
            for(int j=0;j<rozmiarx;j++)
            {
                if(e.getSource()==buttons[i][j])
                {
                    if (GetOrganizm(j, i) == null)
                    {
                        String[] opcje = {"Wilk", "Owca", "Lis", "Zolw", "Antylopa", "Trawa", "Mlecz", "Guarana", "WilczeJagody", "BarszczSosnowskiego"};
                        int opcja = JOptionPane.showOptionDialog(null, "Wybierz organizm", "Dodawanie organizmu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcje, opcje[0]);
                        int h = 0;
                        while (tab_wszystkich[h] != null)
                        {
                            h++;
                        }
                        switch(opcja)
                        {
                            case 0:
                                tab_wszystkich[h] = new Wilk(j, i, this);
                                break;
                            case 1:
                                tab_wszystkich[h] = new Owca(j, i, this);
                                break;
                            case 2:
                                tab_wszystkich[h] = new Lis(j, i, this);
                                break;
                            case 3:
                                tab_wszystkich[h] = new Zolw(j, i, this);
                                break;
                            case 4:
                                tab_wszystkich[h] = new Antylopa(j, i, this);
                                break;
                            case 5:
                                tab_wszystkich[h] = new Trawa(j, i, this);
                                break;
                            case 6:
                                tab_wszystkich[h] = new Mlecz(j, i, this);
                                break;
                            case 7:
                                tab_wszystkich[h] = new Guarana(j, i, this);
                                break;
                            case 8:
                                tab_wszystkich[h] = new WilczeJagody(j, i, this);
                                break;
                            case 9:
                                tab_wszystkich[h] = new BarszczSosnowskiego(j, i, this);
                                break;
                        }
                        //System.out.println("Dodano " + opcje[opcja] + " na pozycji (" + j + ", " + i + ")");
                        if(opcje[opcja] != null)
                            WypiszZdarzenie("Dodano " + opcje[opcja] + " na pozycji (" + j + ", " + i + ")");
                        Draw();
                    }
                }
            }
        }
    }

    public int GetRozmiarX()
    {
        return rozmiarx;
    }

    public int GetRozmiarY()
    {
        return rozmiary;
    }

    public boolean isRunning()
    {
        return running;
    }

    public void Dopisz(int x, int y, char zn)
    {
        //buttons[y][x].setText(zn + "");
        plansza[y][x] = zn;
    }

    public void DodajOrganizm(Organizm o)
    {
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] == null)
            {
                tab_wszystkich[i] = o;
                buttons[o.GetPolozenieY()][o.GetPolozenieX()].setText(o.GetZnak_Gatunku() + "");
                break;
            }
        }
    }

    public Organizm GetOrganizm(int x, int y)
    {
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] != null && tab_wszystkich[i].GetPolozenieX() == x && tab_wszystkich[i].GetPolozenieY() == y)
            {
                return tab_wszystkich[i];
            }
        }
        return null;
    }

    public void ZabijOrganizm(int x, int y)
    {
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] != null && tab_wszystkich[i].GetPolozenieX() == x && tab_wszystkich[i].GetPolozenieY() == y)
            {
                tab_wszystkich[i].SetWiek(-1);
            }
        }
    }

    public void UsunOrganizm(int x, int y)
    {
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] != null && tab_wszystkich[i].GetPolozenieX() == x && tab_wszystkich[i].GetPolozenieY() == y)
            {
                if(tab_wszystkich[i].GetNazwa().equals("Czlowiek"))
                    c = null;
                tab_wszystkich[i] = null;
                buttons[y][x].setText(Stale.ZIEMIA + "");
            }
        }
    }

    public boolean CzySaOtoczone(Organizm o1, Organizm o2)
    {
        int x1 = o1.GetPolozenieX();
        int y1 = o1.GetPolozenieY();
        int x2 = o2.GetPolozenieX();
        int y2 = o2.GetPolozenieY();
        if ((x1 - 1 < 0 || ( x1-1 >= 0 && GetOrganizm(x1 - 1, y1) != null)) && (x1 + 1 >= rozmiarx || (x1 + 1 < rozmiarx && GetOrganizm(x1 + 1, y1) != null)) && (y1 - 1 < 0 || (y1 - 1 >= 0 && GetOrganizm(x1, y1 - 1) != null)) && (y1 + 1 >= rozmiary || (y1 + 1 < rozmiary && GetOrganizm(x1, y1 + 1) != null)))
        {
            if ((x2 - 1 < 0 || (x2 - 1 >= 0 && GetOrganizm(x2 - 1, y2) != null)) && (x2 + 1 >= rozmiarx || (x2 + 1 < rozmiarx && GetOrganizm(x2 + 1, y2) != null)) && (y2 - 1 < 0 || (y2 - 1 >= 0 && GetOrganizm(x2, y2 - 1) != null)) && (y2 + 1 >= rozmiary || (y2 + 1 < rozmiary && GetOrganizm(x2, y2 + 1) != null)))
            {
                return true;
            }
        }
        return false;
    }

    public void SetKierunekCzlowieka(String kierunek)
    {
        kierunekczlowieka = kierunek;
    }

    public String GetKierunekCzlowieka()
    {
        return kierunekczlowieka;
    }

    public int GetXczlowieka()
    {
        return xczlowieka;
    }

    public int GetYczlowieka()
    {
        return yczlowieka;
    }

    public void WypiszZdarzenie(String zdarzenie)
    {
        linijka++;
        if(linijka > 30)
            return;
        text_zdarzenia.append("\n" + zdarzenie);
        //frame.setSize(rozmiarx * 80, rozmiary * 60 + linijka * 20);
    }

    public void Update()
    {
        text_zdarzenia.setText("");
        text_zdarzenia.append("Zdarzenia:");
        linijka = 0;
        Sort();
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] != null)
                tab_wszystkich[i].Postarzej();
        }
        for (int i = 0; i < rozmiarx*rozmiary; i++)
        {
            if (tab_wszystkich[i] != null)
            {
                if (tab_wszystkich[i].GetWiek() > 0)
                {
                    tab_wszystkich[i].akcja();
                }
            }
        }
        Draw();
    }

    public void Draw()
    {
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] != null)
            {
                tab_wszystkich[i].rysowanie();
            }
        }
        for(int i=0; i<rozmiary; i++)
        {
            for(int j=0; j<rozmiarx; j++)
            {
                if(GetOrganizm(j, i) == null)
                {
                    buttons[i][j].setText(Stale.ZIEMIA + "");
                }
                else
                {
                    buttons[i][j].setText(plansza[i][j] + "");
                }
            }
        }

        //gotoxy(0, 0);
        /*
        System.out.println(" ");
        for (int i = 0; i < rozmiary + 1; i++)
        {
            if (i == 0)
            {
                for (int j = 1; j < rozmiarx+1; j++)
                {
                    //gotoxy(j, i);
                    System.out.println(j-1);
                }
            }
            else
            {
                //gotoxy(0, i);
                System.out.println(i-1);
            }
        }

        for (int i = 0; i < rozmiary; i++)
        {
            for (int j = 0; j < rozmiarx; j++)
            {
                //gotoxy(j+1, i+1);
                if (GetOrganizm(j, i) != null)
                    System.out.println(plansza[i][j]);
                else
                    System.out.println(Stale.ZIEMIA);
            }
        }
        */
    }

    public void Clean()
    {
        for (int i = 0; i < rozmiarx * rozmiary; i++)
        {
            if (tab_wszystkich[i] != null)
                tab_wszystkich[i] = null;
        }
        tab_wszystkich = null;

        for (int i = 0; i < rozmiary; i++)
        {
            if (plansza[i] != null)
                plansza[i] = null;
        }
        plansza = null;
    }

    public void DodajCzlowieka(Organizm o)
    {
        tab_wszystkich[20] = o;
        buttons[tab_wszystkich[20].GetPolozenieY()][tab_wszystkich[20].GetPolozenieX()].setText(tab_wszystkich[20].GetZnak_Gatunku() + "");
    }

    public void DrawMenu(int l)
    {
        text_zdarzenia.append("\n----------------------------------------\n");
        text_zdarzenia.append("Kliknij klawisz i zatwierdz enterem.\n");
        text_zdarzenia.append("Wcisnij ESC aby wyjsc z gry.\n");
        text_zdarzenia.append("Wcisnij s aby zapisac gre.\n");
        text_zdarzenia.append("Wcisnij l aby wczytac gre.\n");
        text_zdarzenia.append("Wcisnij m aby uzyc mocy.\n");
        text_zdarzenia.append("Wcisnij strzalke aby wybrac kierunek.\n");
        text_zdarzenia.append("Wcisnij spacje aby zostac na miejscu.\n");
        if (l < 0 || l > 10)
        {
            text_zdarzenia.append("Czlowiek nie zyje\n");
        }
        else if (l == 0)
            text_zdarzenia.append("Umiejetnosc gotowa do uzycia\n");
        else if (l > 5)
            text_zdarzenia.append("Umiejetnosc aktywna, pozostalo " + (l-5) + " tur\n");
        else if (l == 5)
            text_zdarzenia.append("Koniec umiejetnosci, do kolejnego uzycia pozostalo Ci 5 tur\n");
        else if (l < 5 && l != 0)
            text_zdarzenia.append("Umiejetnosc aktywna, pozostalo " + l + " tur\n");
        if(c != null)
            text_zdarzenia.append("Sila czlowieka: " + c.GetSila() + "\n");
    }

    public void Save(int licz)
    {
        Sort();
        String nazwapliku = JOptionPane.showInputDialog("Podaj nazwe pliku do zapisu");

        try {
            File file = new File(nazwapliku);
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write(rozmiarx + " " + rozmiary + "\n");
            for (int i = 0; i < rozmiarx * rozmiary; i++) {
                if (tab_wszystkich[i] == null) {
                    break;
                } else {
                    if (tab_wszystkich[i].GetNazwa().equals("Czlowiek")) {
                        writer.write(tab_wszystkich[i].GetNazwa() + " " + tab_wszystkich[i].GetPolozenieX() + " " +
                                tab_wszystkich[i].GetPolozenieY() + " " + tab_wszystkich[i].GetSila() + " " +
                                tab_wszystkich[i].GetInicjatywa() + " " + tab_wszystkich[i].GetZnak_Gatunku() + " " +
                                tab_wszystkich[i].GetWiek() + " " + licz + "\n");
                    } else {
                        writer.write(tab_wszystkich[i].GetNazwa() + " " + tab_wszystkich[i].GetPolozenieX() + " " +
                                tab_wszystkich[i].GetPolozenieY() + " " + tab_wszystkich[i].GetSila() + " " +
                                tab_wszystkich[i].GetInicjatywa() + " " + tab_wszystkich[i].GetZnak_Gatunku() + " " +
                                tab_wszystkich[i].GetWiek() + "\n");
                    }
                }
            }
            writer.close();

            System.out.println("Zapisano gre do pliku " + nazwapliku);
        } catch (IOException e) {
            System.out.println("Wystapil blad podczas zapisywania do pliku.");
            e.printStackTrace();
        }
    }


    public void Load()
    {
        String nazwapliku = JOptionPane.showInputDialog("Podaj nazwe pliku do odczytu");

        try {
            File file = new File(nazwapliku);
            if(!file.exists())
            {
                System.out.println("Plik nie istnieje");
                return;
            }
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for(int i=0; i<rozmiarx*rozmiary; i++)
            {
                if(tab_wszystkich[i] != null)
                    tab_wszystkich[i] = null;
            }
            tab_wszystkich = null;

            for(int i=0; i<rozmiary; i++)
            {
                if(plansza[i] != null)
                    plansza[i] = null;
            }
            plansza = null;

            for(int i=0; i<rozmiary; i++)
            {
                for(int j=0; j<rozmiarx; j++)
                {
                    if(buttons[i][j] != null)
                        buttons[i][j] = null;
                }
                buttons[i] = null;
            }
            buttons = null;

            button_panel.removeAll();

            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");
            rozmiarx = Integer.parseInt(parts[0]);
            rozmiary = Integer.parseInt(parts[1]);
            System.out.println("Wczytano rozmiar planszy: " + rozmiarx + "x" + rozmiary);
            //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(rozmiarx * 150, rozmiary * 130);
            button_panel.setLayout(new GridLayout(rozmiary, rozmiarx));

            buttons = new JButton[rozmiary][];
            for(int i=0;i<rozmiary; i++)
            {
                buttons[i] = new JButton[rozmiarx];
                for(int j=0; j<rozmiarx; j++)
                {
                    buttons[i][j] = new JButton();
                    button_panel.add(buttons[i][j]);
                    buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth()/rozmiarx/5));
                    buttons[i][j].setFocusable(false);
                    buttons[i][j].setBackground(new Color(25, 25, 25));
                    buttons[i][j].addActionListener(this);
                    buttons[i][j].setText(Stale.ZIEMIA + "");
                }
            }

            running = true;
            linijka = 0;
            kierunekczlowieka = "zostaje";

            tab_wszystkich = new Organizm [rozmiarx * rozmiary];
            for (int i = 0; i < rozmiarx * rozmiary; i++)
            {
                tab_wszystkich[i] = null;
            }

            plansza = new char[rozmiary][];
            for (int i = 0; i < rozmiary; i++)
            {
                plansza[i] = new char[rozmiarx];
            }
            for (int i = 0; i < rozmiary; i++)
            {
                for (int j = 0; j < rozmiarx; j++)
                {
                    plansza[i][j] = Stale.ZIEMIA;
                }
            }

            String linia;
            int u = 0;
            while ((linia = bufferedReader.readLine()) != null) {
                String[] dane = linia.split(" ");
                String nazwa = dane[0];
                int x = Integer.parseInt(dane[1]);
                int y = Integer.parseInt(dane[2]);
                int sila = Integer.parseInt(dane[3]);
                int inicjatywa = Integer.parseInt(dane[4]);
                char znak = dane[5].charAt(0);
                int wiek = Integer.parseInt(dane[6]);
                System.out.println("Wczytano: " + nazwa + " " + x + " " + y + " " + sila + " " + inicjatywa + " " + znak + " " + wiek);
                if(nazwa.equals("Czlowiek"))
                {
                    int licz = Integer.parseInt(dane[7]);
                    c = new Czlowiek(x, y, sila, inicjatywa, znak, wiek, nazwa, licz, this);
                    tab_wszystkich[u] = c;
                }
                else if(nazwa.equals("Wilk"))
                {
                    tab_wszystkich[u] = new Wilk(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Owca"))
                {
                    tab_wszystkich[u] = new Owca(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Lis"))
                {
                    tab_wszystkich[u] = new Lis(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Zolw"))
                {
                    tab_wszystkich[u] = new Zolw(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Antylopa"))
                {
                    tab_wszystkich[u] = new Antylopa(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Trawa"))
                {
                    tab_wszystkich[u] = new Trawa(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Mlecz"))
                {
                    tab_wszystkich[u] = new Mlecz(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("Guarana"))
                {
                    tab_wszystkich[u] = new Guarana(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("WilczeJagody"))
                {
                    tab_wszystkich[u] = new WilczeJagody(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                else if(nazwa.equals("BarszczSosnowskiego"))
                {
                    tab_wszystkich[u] = new BarszczSosnowskiego(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                }
                u++;

            }
            Draw();

            bufferedReader.close();
            reader.close();

            System.out.println("Odczytano gre z pliku " + nazwapliku);
        } catch (IOException e) {
            System.out.println("Wystapil blad podczas odczytywania z pliku.");
            e.printStackTrace();
        }
    }

    public void Run()
    {
        int xc, yc;
        xc = GetXczlowieka();
        yc = GetYczlowieka();
        c = new Czlowiek(xc, yc, this);
        DodajCzlowieka(c);
        DrawMenu(c.GetLicznik());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e)
    {
        int licz = -1;
        if(c != null)
            licz = c.GetLicznik();
        switch(e.getKeyCode())
        {
            case 37: // left arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("lewo");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 38: // up arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("gora");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 39: // right arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("prawo");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 40: // down arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("dol");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 32:
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("zostaje");
                }
                Update();
                DrawMenu(licz);
                break;
            }
        }
    }
}
