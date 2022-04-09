package main;

import java.util.ArrayList;
import java.util.Scanner;

import static global.Global.listToko;
import static global.Global.scan;
import static global.Global.clearScreen;
import static global.Global.enter;
import model.BukuDigital;
import model.BukuFisik;
import model.Toko;
import model.Transaksi;

public class Main {

	public void init() {
		listToko = new ArrayList<>();
		scan = new Scanner(System.in);
	}
	
	public Main() {
		init();
		boolean isRun = true;
		int input = -1;
		do{
			mainMenu();

			do{
				System.out.print(">> ");
				try {
					input = scan.nextInt();
				}
				catch(Exception E){
					input = -1;
				}
				scan.nextLine();

			}while(input < 1 || input > 5);
			
			switch(input){
				case 1 :
					//menu1
					listCabangToko();
				break;

				case 2 :
					//menu2
					menambahkanToko();
				break;

				case 3 :
					//menu3
					memperbaruiDataToko();
				break;

				case 4 : 
					//menu4
					menghapusToko();
				break;
				
				case 5:
					isRun = false;
				break;
			}

			
		}while(isRun);
	}

	public void mainMenu(){
		
		System.out.println("Toko SeeBook");
		System.out.println("=============");
		System.out.println("1. Melihat/Memilih List Cabang Toko");
		System.out.println("2. Menambahkan Toko ");
		System.out.println("3. Memperbarui Data Toko");
		System.out.println("4. Menghapus Toko");
		System.out.println("5. Keluar");
		System.out.println("=============");
		
	}

	public void listCabangToko(){
		clearScreen();
		
		int len = listToko.size();
		
		if(!cekListTokoKosong(len)){
			return;
		}
		
		System.out.println("List Toko");
		System.out.println("=============================================");
		System.out.println("");
		
		printListToko();
		
		int pilih = -1;
		
		do{
			System.out.print("Pilih Toko [1.." + len + "] (0 untuk kembali) : ");

			try {
				pilih = scan.nextInt();
				}
			catch(Exception E){
				pilih = -1;
			}
			scan.nextLine();

		}while(pilih < 0 || pilih > len);
		
		if(pilih == 0) {
			clearScreen();
			return;
		}

		//pindah ke menu buku
		clearScreen();
		
		new MenuBuku(pilih-1);
		clearScreen();
	}

	
	public void menambahkanToko(){
		clearScreen();
		String nama, lokasi;
		System.out.println("Tambah Toko");
		System.out.println("=============================================");
		System.out.println("");
		do{
			System.out.print("Masukkan Nama Toko : ");
			nama = scan.nextLine();
	
		}while(nama.length() == 0);

		do{
			System.out.print("Masukkan Lokasi Toko : ");
			lokasi = scan.nextLine();
	
		}while(lokasi.length() == 0);
		System.out.println("Toko Berhasil Ditambahkan!");
	
		listToko.add(new Toko(nama, lokasi, new ArrayList<BukuFisik>(), new ArrayList<BukuDigital>() , new ArrayList<Transaksi>()));
		enter();
	}

	public void menghapusToko(){
		clearScreen();
		
		int len = listToko.size();
		
		if(!cekListTokoKosong(len)){
			return;
		}
		
		System.out.println("Hapus Data Toko");
		System.out.println("=============================================");
		System.out.println("");
		
		printListToko();
		
		int pilih = pilihToko(len);
		
		System.out.println("Toko " + listToko.get(pilih-1).getNama() + " berhasil dihapus !");
		listToko.remove(pilih-1);
		enter();
	}

	public void memperbaruiDataToko(){
		
		clearScreen();
		
		
		
		int len = listToko.size();
		
		if(!cekListTokoKosong(len)){
			return;
		}
		
		System.out.println("Perbarui Data Toko");
		System.out.println("=============================================");
		System.out.println("");
		printListToko();
		
		int pilih = pilihToko(len) - 1;
		String nama, lokasi;
		
		do{
			System.out.print("Masukkan Nama Toko : ");
			nama = scan.nextLine();
	
		}while(nama.length() == 0);

		do{
			System.out.print("Masukkan Lokasi Toko : ");
			lokasi = scan.nextLine();
	
		}while(lokasi.length() == 0);
		
		listToko.get(pilih).setNama(nama);
		listToko.get(pilih).setLokasi(lokasi);
		
		System.out.println("Toko berhasil diperbarui !");
		enter();
	}
	
	public int pilihToko(int len) {
		int pilih = -1;
		
		do{
			System.out.print("Pilih Toko [1.." + len + "] : ");

			try {
				pilih = scan.nextInt();
				}
			catch(Exception E){
				pilih = -1;
			}
			scan.nextLine();

		}while(pilih < 1 || pilih > len);
		
		return pilih;
	}

	public boolean cekListTokoKosong(int len){
		
		if (len == 0 ){
			System.out.println("Tidak Ada Toko");
			enter();
			return false;
		}
		return true;
	}

	
	public void printListToko(){

		int len = listToko.size();
		for(int i = 0; i < len; i++){
			System.out.println("Toko - " + (i+1) );
			System.out.println("Nama Toko   : " +  listToko.get(i).getNama());
			System.out.println("Lokasi Toko : " + listToko.get(i).getLokasi());
			System.out.println("=============================================");
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
