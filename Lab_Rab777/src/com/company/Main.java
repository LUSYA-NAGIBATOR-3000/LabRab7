package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try
        {
            File f1 = new File("rec_RAF.txt");
            if (!f1.exists()) f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1,"rw");

            File f2 = new File("rec_RAF2.txt");
            if (!f2.exists()) f2.createNewFile();
            RandomAccessFile rf2 = new RandomAccessFile(f2,"rw");

            long fSize = rf.length();
            long fSize2 = rf2.length();

            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Введите  количество  авто  для  записи  в  файл\n"+ "=>  ");
            int kol = sc.nextInt();
            sc.nextLine();
            String model, reg_num;
            int year, probeg, price;
            for(int i = 0; i < kol; i++)
            {
                System.out.print("Введите модель " + (i + 1) + " авто => ");
                model= sc.next();
                rf.seek(rf.length());
                rf.writeUTF(model);
                for(int j= 0; j< 20 -model.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите его рег номер => ");
                reg_num = sc.next();
                rf.writeUTF(reg_num);
                for (int j = 0; j < 20 -reg_num.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите его год => ");
                year= sc.nextInt();
                sc.nextLine();
                rf.writeInt(year);
                System.out.print("Введите его пробег => ");
                probeg= sc.nextInt();
                sc.nextLine();
                rf.writeInt(probeg);
                System.out.print("Введите его цену => ");
                price= sc.nextInt();
                sc.nextLine();
                rf.writeInt(price);
            }
            rf.close();
            int k = 0;
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);
            for(int i = 0; i < kol; i++) {
                model = rf.readUTF();
                for (int j = 0; j < 20 - model.length(); j++)
                    rf.readByte();
                reg_num = rf.readUTF();
                for (int j = 0; j < 20 - reg_num.length(); j++)
                    rf.readByte();
                year = rf.readInt();
                probeg = rf.readInt();
                price = rf.readInt();
                if (year > 2009) {
                    System.out.println("Gotcha!");
                    k++;
                    rf2.seek(rf2.length());
                    rf2.writeUTF(model);
                    for (int j = 0; j < 20 - model.length(); j++)
                        rf2.writeByte(1);
                    rf2.writeUTF(reg_num);
                    for (int j = 0; j < 20 - reg_num.length(); j++)
                        rf2.writeByte(1);
                    rf2.writeInt(year);
                    rf2.writeInt(probeg);
                    rf2.writeInt(price);
                }
            }

            rf2.seek(0);
            System.out.println("Информация об отобранных авто");
            System.out.println("модель \t\t  рег номер \t\t   год \t\t   Пробег \t\t Цена");
            for(int i1 = 0; i1 < k; i1++)
            {
                model = rf2.readUTF();
                for (int j = 0; j < 20 -model.length(); j++)
                    rf2.readByte();
                reg_num = rf2.readUTF();
                for (int j = 0; j < 20 -reg_num.length(); j++)
                    rf2.readByte();
                year = rf2.readInt();
                probeg = rf2.readInt();
                price = rf2.readInt();
                System.out.println(i1);
                System.out.println(model + "\t\t\t" + reg_num + "\t\t\t" + year + "\t\t\t" + probeg + "\t\t\t" + price);
        }
            rf.close();
            rf2.close();

        }catch (IOException e){
            System.out.println("End of file  " + e);
        }
    }
}
