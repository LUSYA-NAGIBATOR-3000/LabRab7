package com.company;

import java.io.*;
import java.util.Scanner;

import static java.lang.String.valueOf;

class Auto
{
    String model, reg_num;
    int year, probeg, price;
}

public class Main{
    public static void main(String[] args){
        BufferedReader br = null;
        PrintWriter out= null;
        try{
            File f1 = new File("zadanie.txt");
            if (!f1.exists())
                f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");

            File f2 = new File("perezapis.txt");
            if (!f2.exists())
                f2.createNewFile();
            RandomAccessFile rf2= new RandomAccessFile(f1, "rw");

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



                rf.writeUTF(auto[i].model);
                rf.writeUTF(auto[i].reg_num);
                rf.writeInt(auto[i].year);
                rf.writeInt(auto[i].probeg);
                rf.writeInt(auto[i].price);
            }

            rf.seek(0);
            int k = 0;
            Auto p_auto = new Auto();
            for (int i1 = 0; i1 < kol; i1++)
            {
                p_auto.model = rf.readUTF();
                p_auto.reg_num = rf.readUTF();
                p_auto.year = rf.readInt();
                p_auto.probeg = rf.readInt();
                p_auto.price = rf.readInt();
                if (p_auto.year > 2009){
                    k++;
                    rf2.writeUTF(p_auto.model);
                    rf2.writeUTF(p_auto.reg_num);
                    rf2.writeInt(p_auto.year);
                    rf2.writeInt(p_auto.probeg);
                    rf2.writeInt(p_auto.price);
                    /*System.out.println("=" + p_auto.model);
                    System.out.println("=" + p_auto.reg_num);
                    System.out.println("=" + p_auto.year);
                    System.out.println("=" + p_auto.probeg);
                    System.out.println("=" + p_auto.price);*/
                }
            }
            for (int i2 = 0; i2 < k+1; i2++) {
                System.out.println(rf2.readUTF());
                System.out.println(rf2.readUTF());
                System.out.println(rf2.readInt());
                System.out.println(rf2.readInt());
                System.out.println(rf2.readInt());
            }
        } catch (IOException xe){
            //
        }
    }
}
