package com.company;

import java.io.*;
import java.util.Scanner;


class Auto implements Serializable
{
    String model, reg_num;
    int year, probeg, price;
}

public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        File f1 = new File("zadanie.txt");
        if (!f1.exists())
            f1.createNewFile();

        FileOutputStream fos = new FileOutputStream(f1);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        FileInputStream fis = new FileInputStream(f1);
        ObjectInputStream oin = new ObjectInputStream(fis);


        File f2 = new File("perezapis.txt");
        if (!f2.exists())
            f2.createNewFile();

        FileOutputStream fos2 = new FileOutputStream(f2);
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

        FileInputStream fis2 = new FileInputStream(f2);
        ObjectInputStream oin2 = new ObjectInputStream(fis2);

        Scanner sc = new Scanner(System.in, "cp1251");

        System.out.println("Введите количество автомобилей => ");
        int kol = sc.nextInt();
        sc.nextLine();

        Auto[] auto = new Auto[kol];
        System.out.println("Введите информацию о каждом из автомобилей: ");
        for (int i = 0; i < auto.length; i++)
        {
            auto[i] = new Auto();

            System.out.print("Введите модель " + (i + 1) + " автомобиля => ");
            auto[i].model = sc.nextLine();

            System.out.print("Введите его регистрационный номер => ");
            auto[i].reg_num = sc.nextLine();

            System.out.print("Введите его год => ");
            auto[i].year = sc.nextInt();
            sc.nextLine();
            if (auto[i].year >= 2020)
            {
                System.out.print("Год выпуска введён некорректно. Введите его еще раз! => ");
                auto[i].year = sc.nextInt();
                sc.nextLine();
            }

            System.out.print("Введите его пробег => ");
            auto[i].probeg = sc.nextInt();
            sc.nextLine();
            if (auto[i].probeg <= 0)
            {
                System.out.print("Пробег введён некорректно. Введите его еще раз! => ");
                auto[i].probeg = sc.nextInt();
                sc.nextLine();
            }

            System.out.print("Введите его цену => ");
            auto[i].price = sc.nextInt();
            sc.nextLine();
            if (auto[i].price < 0)
            {
                System.out.print("Цена авто введена некорректно. Введите цену авто еще раз! => ");
                auto[i].price = sc.nextInt();
                sc.nextLine();
            }

            oos.writeObject((auto[i]));

        }

        int g = 0;
        Auto[] p_auto = new Auto[kol];
        for (int i = 0; i < auto.length; i++) {
            p_auto[i] = (Auto) oin.readObject();
            if (p_auto[i].year > 2009){
                g++;
                oos2.writeObject((p_auto[i]));
            }
        }
        oos.flush();
        oos.close();

        Auto[] r_auto = new Auto[kol];
        for (int i = 0; i < g; i++) {
            r_auto[i] = (Auto) oin2.readObject();
            System.out.println(r_auto[i].model);
            System.out.println(r_auto[i].price);
        }

        oos2.flush();
        oos2.close();
}
}
