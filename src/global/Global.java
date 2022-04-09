package global;

import java.util.ArrayList;
import java.util.Scanner;

import model.Toko;

public class Global {
	public static ArrayList<Toko> listToko;
	public static Scanner scan;
	
	public static void clearScreen(){
		for(int i = 0; i<30; i++){
			System.out.println("");	
		}
	}

	public static void enter(){
		System.out.println("Tekan enter untuk melanjutkan..");
		scan.nextLine();
		clearScreen();
	}
}
