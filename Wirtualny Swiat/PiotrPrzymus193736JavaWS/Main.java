package WSJAVA;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        int r_x, r_y;
        while (true)
        {
            r_x = Integer.valueOf(JOptionPane.showInputDialog("Podaj rozmiar x"));
            r_y = Integer.valueOf(JOptionPane.showInputDialog("Podaj rozmiar y"));
            if (r_x < 0 || r_y < 0 || r_x * r_y < 25)
            {
                JOptionPane.showMessageDialog(null, "Za male dane (<25)", "Sprobuj jeszcze raz", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                break;
            }
        }
        Swiat swiat = new Swiat(r_x, r_y);
        swiat.Run();
    }
}