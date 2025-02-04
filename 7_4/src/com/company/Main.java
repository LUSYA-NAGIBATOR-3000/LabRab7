package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    static class Strana implements Serializable
    {
        String name;
        double square;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Scanner sc=new Scanner(System.in,"cp1251");
        File f = new File("C:\\My\\rt.txt");
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Strana strana = new Strana();
        System.out.println("Введите информацию о стране: ");
        System.out.print("Название  страны => ");
        strana.name=sc.nextLine();
        System.out.print("Площадь страны=>  ");
        strana.square=sc.nextDouble();
        oos.writeObject(strana);
        oos.flush();
        oos.close();
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
        strana = (Strana) oin.readObject();
        System.out.println("Название страны "+ strana.name);
        System.out.println("Её площадь = " + strana.square);
        oos.close();
    }
}
