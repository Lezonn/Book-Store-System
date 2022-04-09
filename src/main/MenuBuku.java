package main;

import model.Buku;
import model.BukuDigital;
import model.BukuFisik;
import model.Pembeli;
import model.Transaksi;

import static global.Global.listToko;
import static global.Global.scan;

import java.util.ArrayList;

import static global.Global.clearScreen;
import static global.Global.enter;

public class MenuBuku {
	
	private int idxToko;

	public MenuBuku(int index) {
		this.idxToko = index;
		
		boolean isRun = true;
		int input = -1;
		do{
			System.out.println("Nama Toko : " + listToko.get(idxToko).getNama());
			System.out.println("Lokasi	  : " + listToko.get(idxToko).getLokasi());
			mainMenuBuku();

			do{
				System.out.print(">> ");
				try {
					input = scan.nextInt();
				}
				catch(Exception E){
					input = -1;
				}
				scan.nextLine();

			}while(input < 1 || input > 7);
			
			switch(input){
				case 1 :
					//menu1
					clearScreen();
					listBuku();
				break;

				case 2 :
					//menu2
					clearScreen();
					menambahkanBuku();
				break;

				case 3 :
					//menu3
					clearScreen();
					memperbaruiDataBuku();
				break;

				case 4 : 
					//menu4
					clearScreen();
					menghapusBuku();
				break;
				
				case 5:
					//menu5
					clearScreen();
					inputTransaksi();
				break;
				
				case 6:
					clearScreen();
					lihatListTransaksi();
				break;
			
				case 7:
					isRun = false;
				break;
			}

			
		}while(isRun);

	}

	public void mainMenuBuku() {
		
		System.out.println("============================");
		System.out.println("1. Melihat List Buku");
		System.out.println("2. Menambahkan Buku ");
		System.out.println("3. Memperbarui Data Buku");
		System.out.println("4. Menghapus Buku");
		System.out.println("5. Menambahkan Transaksi");
		System.out.println("6. Melihat List Transaksi");
		System.out.println("7. Kembali");
		System.out.println("============================");
	}
	
	public void listBuku() {
		int lenBukuDigital = listToko.get(idxToko).getJumlahBuku("Digital");
		int lenBukuFisik = listToko.get(idxToko).getJumlahBuku("Fisik");
		
		if(!cekBukuKosong(lenBukuDigital, lenBukuFisik)) {
			return;
		}
		
		printSemuaBuku(lenBukuDigital, lenBukuFisik);
		enter();

	}
	
	public void menambahkanBuku() {
		System.out.println("Menambahkan buku");
		System.out.println("================");
		
		String tipe;
		
		tipe = masukanTipeBuku();
		
		Buku tempBuku;
		if(tipe.equals("Fisik")) {
			tempBuku = new BukuFisik("", "", "", "", 0, 0);
		}
			
		else {
			tempBuku = new BukuDigital("", "", "", "", 0, "");
		}
			
		
		do {
			System.out.print("Input Judul Buku: ");
			tempBuku.setJudul(scan.nextLine());
		} while(tempBuku.getJudul().length() == 0);
		
		do {
			System.out.print("Input Penulis: ");
			tempBuku.setPenulis(scan.nextLine());
		} while (tempBuku.getPenulis().length() == 0);
		
		do {
			System.out.print("Input Tahun Terbit: ");
			tempBuku.setTahunTerbit(scan.nextLine());
		} while(tempBuku.getTahunTerbit().length() == 0);
		
		int harga = 0;
		do{
			System.out.print("Input Harga: ");
			try {
				harga = scan.nextInt();
				 
			} catch (Exception e) {
				harga = 0;
			}
			tempBuku.setHarga(harga);
			scan.nextLine();
		} while(harga <= 0);
		
		if(tipe.equals("Fisik")) {
			BukuFisik tempBukuFisik = (BukuFisik) tempBuku;
			do {
				System.out.print("Input Stok: ");
				
				try {
					tempBukuFisik.setStok(scan.nextInt()); 
				} catch (Exception e) {
					tempBukuFisik.setStok(0);
				}
				scan.nextLine();
			} while(tempBukuFisik.getStok() <= 0);
			listToko.get(idxToko).getListBukuFisik().add(tempBukuFisik);
		}
			
		else {
			BukuDigital tempBukuDigital = (BukuDigital) tempBuku;
			
			
			do {
				System.out.print("Input DOI: ");
				tempBukuDigital.setDOI(scan.nextLine());
			} while(tempBukuDigital.getDOI().length() == 0);
			listToko.get(idxToko).getListBukuDigital().add(tempBukuDigital);
		}
		
		System.out.println("Penambahan buku berhasil !:)");
		enter();
	}
	

	
	public void memperbaruiDataBuku() {
		int lenBukuDigital = listToko.get(idxToko).getJumlahBuku("Digital");
		int lenBukuFisik = listToko.get(idxToko).getJumlahBuku("Fisik");
		
		if(!cekBukuKosong(lenBukuDigital, lenBukuFisik)) {
			return;
		}
		
		String tipe = masukanTipeBuku();

		int choose = -1;
		if(tipe.equals("Digital") && lenBukuDigital != 0) {
			
			printBukuDigital(lenBukuDigital);
			
			do {
				System.out.print("Input Nomor Buku : ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					choose = -1;
				}
				scan.nextLine();
			} while(choose < 1 || choose > lenBukuDigital);
			
			
		}
		
		else if(tipe.equals("Fisik") && lenBukuFisik != 0){
			printBukuFisik(lenBukuFisik);
			do {
				System.out.print("Input Nomor Buku : ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					choose = -1;
				}
				scan.nextLine();
			} while(choose < 1 || choose > lenBukuFisik);
		}
		
		Buku tempBuku;
		if(tipe.equals("Fisik")) {
			tempBuku = new BukuFisik("", "", "", "", 0, 0);
		}
			
		else {
			tempBuku = new BukuDigital("", "", "", "", 0, "");
		}
			
		
		do {
			System.out.print("Input Judul Buku: ");
			tempBuku.setJudul(scan.nextLine());
		} while(tempBuku.getJudul().length() == 0);
		
		do {
			System.out.print("Input Penulis: ");
			tempBuku.setPenulis(scan.nextLine());
		} while (tempBuku.getPenulis().length() == 0);
		
		do {
			System.out.print("Input Tahun Terbit: ");
			tempBuku.setTahunTerbit(scan.nextLine());
		} while(tempBuku.getTahunTerbit().length() == 0);
		
		do{
			System.out.print("Input Harga: ");
			try {
				tempBuku.setHarga(scan.nextInt()); 
			} catch (Exception e) {
				tempBuku.setHarga(0);
			}
			scan.nextLine();
		} while(tempBuku.getHarga() <= 0);
		
		if(tipe.equals("Fisik")) {
			BukuFisik tempBukuFisik = (BukuFisik) tempBuku;
			do {
				System.out.print("Input Stok: ");
				
				try {
					tempBukuFisik.setStok(scan.nextInt()); 
				} catch (Exception e) {
					tempBukuFisik.setStok(0);
				}
				scan.nextLine();
			} while(tempBukuFisik.getStok() <= 0);
			listToko.get(idxToko).getListBukuFisik().set(choose - 1, tempBukuFisik);
		}
			
		else {
			BukuDigital tempBukuDigital = (BukuDigital) tempBuku;
			
			
			do {
				System.out.print("Input DOI: ");
				tempBukuDigital.setDOI(scan.nextLine());
			} while(tempBukuDigital.getDOI().length() == 0);
			listToko.get(idxToko).getListBukuDigital().set(choose - 1, tempBukuDigital);
		}
		

		
		System.out.println("Buku berhasil diupdate !");
		enter();
		
	}
	
	
	public void menghapusBuku() {
		int lenBukuDigital = listToko.get(idxToko).getJumlahBuku("Digital");
		int lenBukuFisik = listToko.get(idxToko).getJumlahBuku("Fisik");
		
		if(!cekBukuKosong(lenBukuDigital, lenBukuFisik)) {
			return;
		}
		
		String tipe = masukanTipeBuku();
		
		int choose = -1;
		if(tipe.equals("Digital") && lenBukuDigital != 0) {
			
			printBukuDigital(lenBukuDigital);
			
			do {
				System.out.print("Input Nomor Buku : ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					choose = -1;
				}
				scan.nextLine();
			} while(choose < 1 || choose > lenBukuDigital);
			
			listToko.get(idxToko).getListBukuDigital().remove(choose-1);
			
			
			
		}
		
		else if(tipe.equals("Fisik") && lenBukuFisik != 0){
			printBukuFisik(lenBukuFisik);
			do {
				System.out.print("Input Nomor Buku : ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					choose = -1;
				}
				scan.nextLine();
			} while(choose < 1 || choose > lenBukuFisik);
			
			listToko.get(idxToko).getListBukuFisik().remove(choose-1);
			
		}
		
		else {
			System.out.println("Tidak ada buku " + tipe);
			enter();
			return;
		}
		System.out.println("Buku berhasil dihapus!");
		enter();
	}
	
	public void inputTransaksi() {
		int lenBukuDigital = listToko.get(idxToko).getJumlahBuku("Digital");
		int lenBukuFisik = listToko.get(idxToko).getJumlahBuku("Fisik");
		
		if(!cekBukuKosong(lenBukuDigital, lenBukuFisik)) {
			return;
		}
		
		System.out.println("Menambahkan Transaksi");
		System.out.println("=====================");
		
		Pembeli pembeli = new Pembeli("", "");
		String tipe;
		int choose = -1;
		
		do {
			System.out.print("Nama pembeli: ");
			pembeli.setNama(scan.nextLine());
		}while(pembeli.getNama().length() == 0);
		
		do {
			System.out.print("Email pembeli: ");
			pembeli.setEmail(scan.nextLine());
		}while(pembeli.getEmail().length() == 0);
		
		Transaksi tempTransaksi = new Transaksi(0, new ArrayList<>(), new ArrayList<>(), new Pembeli("", ""));
		
		tempTransaksi.setPembeli(pembeli);
		
		do{
			tipe = masukanTipeBukuTransaksi();
			
			if(tipe.equals("Digital") && lenBukuDigital != 0) {
				
				printBukuDigital(lenBukuDigital);
				
				do {
					System.out.print("Input Nomor Buku : ");
					try {
						choose = scan.nextInt();
					} catch (Exception e) {
						choose = -1;
					}
					scan.nextLine();
				} while(choose < 1 || choose > lenBukuDigital);
				
				tempTransaksi.getListBukuDigital().add(listToko.get(idxToko).getListBukuDigital().get(choose-1));
				System.out.println("Buku berhasil ditambahkan");
			}
			
			else if(tipe.equals("Fisik") && lenBukuFisik != 0) {
				ArrayList<BukuFisik> tempBukuFisik = getBukuFisikDenganStok(lenBukuFisik);
				if(tempBukuFisik.isEmpty()) {
					System.out.println("Tidak ada buku " + tipe);
					continue;
				}
//				printBukuFisikTransaksi(tempBukuFisik.size(), tempBukuFisik);
				printBukuFisik(lenBukuFisik);
				
				do {
					System.out.print("Input Nomor Buku : ");
					try {
						choose = scan.nextInt();
					} catch (Exception e) {
						choose = -1;
					}
					scan.nextLine();
				} while(choose < 1 || choose > lenBukuFisik || listToko.get(idxToko).getListBukuFisik().get(choose-1).getStok() == 0);
				tempTransaksi.getListBukuFisik().add(listToko.get(idxToko).getListBukuFisik().get(choose-1));
				
				int stokLama = listToko.get(idxToko).getListBukuFisik().get(choose-1).getStok();
				//int idx = listToko.get(idxToko).getListBukuFisik().indexOf(tempBukuFisik.get(choose-1).getJudul());
				listToko.get(idxToko).getListBukuFisik().get(choose-1).setStok(stokLama - 1);
				
				System.out.println("Buku berhasil ditambahkan");
			}
			
			else if(!tipe.equals("0")) {
				System.out.println("Tidak ada buku " + tipe);
				continue;
			}
		}while(!tipe.equals("0"));
		
		tempTransaksi.setHitungTotalHarga();
		
		listToko.get(idxToko).getListTransaksi().add(tempTransaksi);
		
		
		System.out.println("Transaksi berhasil ditambahkan!");
		enter();
	}
	
	public void lihatListTransaksi(){
		clearScreen();
		if(listToko.get(idxToko).getListTransaksi().isEmpty()) {
			System.out.println("Tidak ada transaksi");
			enter();
			return;
		}
		
		printTransaksi();
		enter();
	}
	
	public void printTransaksi(){
		int size = listToko.get(idxToko).getListTransaksi().size();
		ArrayList<Transaksi> listTransaksi = listToko.get(idxToko).getListTransaksi();
		for(int i = 0; i < size; i++ ){
			System.out.println("Transaksi - " + (i+1));
			System.out.println("Nama Pembeli  : " + listTransaksi.get(i).getPembeli().getNama());
			System.out.println("Email Pembeli : " + listTransaksi.get(i).getPembeli().getEmail());
			System.out.println("");
			if(!listTransaksi.get(i).getListBukuDigital().isEmpty()) {
				System.out.println("Buku Digital  : ");
				for(BukuDigital digital : listTransaksi.get(i).getListBukuDigital()) {
					System.out.println("- " + digital.getJudul() + " -> " + digital.getHarga());
				}
			}
			System.out.println("");
			if(!listTransaksi.get(i).getListBukuFisik().isEmpty()) {
				System.out.println("Buku Fisik    : ");
				for(BukuFisik fisik : listTransaksi.get(i).getListBukuFisik()){
					System.out.println("- " + fisik.getJudul() + " -> " + fisik.getHarga());
				}
			}
			System.out.println("");
			System.out.println("Total harga   : " + listTransaksi.get(i).getTotalHarga());
			
			System.out.println("=================================================");
			System.out.println("");
		}
	}
	
	public String masukanTipeBuku() {
		String tipe;
		do {
			System.out.print("Pilih jenis buku [Fisik | Digital]: ");
			tipe = scan.nextLine();
		} while(!tipe.equals("Fisik") && !tipe.equals("Digital"));
		return tipe;
	}
	
	public String masukanTipeBukuTransaksi() {
		String tipe;
		do {
			System.out.print("Pilih jenis buku [Fisik | Digital] (0 untuk menyelesaikan transaksi): ");
			tipe = scan.nextLine();
		} while(!tipe.equals("Fisik") && !tipe.equals("Digital") && !tipe.equals("0"));
		return tipe;
	}
	
	public void printSemuaBuku(int lenBukuDigital, int lenBukuFisik){
		
		if(lenBukuDigital > 0){
			System.out.println("Buku Digital");
			System.out.println("=================================================");
			for(int i = 0; i < lenBukuDigital; i++) {
				System.out.println("Buku Digital - " + (i+1));
				System.out.println("Judul        : " + listToko.get(idxToko).getListBukuDigital().get(i).getJudul() );
				System.out.println("Penulis      : " + listToko.get(idxToko).getListBukuDigital().get(i).getPenulis());
				System.out.println("Tahun Terbit : " + listToko.get(idxToko).getListBukuDigital().get(i).getTahunTerbit());
				System.out.println("Harga        : " + listToko.get(idxToko).getListBukuDigital().get(i).getHarga() );
				System.out.println("DOI          : " + listToko.get(idxToko).getListBukuDigital().get(i).getDOI());
				System.out.println("");
				System.out.println("");
			}
			System.out.println("=================================================");
		}
		System.out.println("");
		if(lenBukuFisik > 0) {
			System.out.println("Buku Fisik");
			System.out.println("=================================================");
			for(int i = 0; i < lenBukuFisik; i++) {
				System.out.println("Buku Fisik - " + (i+1));
				System.out.println("Judul        : " + listToko.get(idxToko).getListBukuFisik().get(i).getJudul() );
				System.out.println("Penulis      : " + listToko.get(idxToko).getListBukuFisik().get(i).getPenulis() );
				System.out.println("Tahun Terbit : " + listToko.get(idxToko).getListBukuFisik().get(i).getTahunTerbit() );
				System.out.println("Harga        : " + listToko.get(idxToko).getListBukuFisik().get(i).getHarga() );
				System.out.println("Stok         : " + listToko.get(idxToko).getListBukuFisik().get(i).getStok() );
				System.out.println("");
				System.out.println("");
			}
			System.out.println("=================================================");
		}
	}
	
	public void printBukuDigital(int lenBukuDigital) {
		System.out.println("Buku Digital");
		System.out.println("=================================================");
		for(int i = 0; i < lenBukuDigital; i++) {
			System.out.println("Buku Digital - " + (i+1));
			System.out.println("Judul        : " + listToko.get(idxToko).getListBukuDigital().get(i).getJudul() );
			System.out.println("Penulis      : " + listToko.get(idxToko).getListBukuDigital().get(i).getPenulis());
			System.out.println("Tahun Terbit : " + listToko.get(idxToko).getListBukuDigital().get(i).getTahunTerbit());
			System.out.println("Harga        : " + listToko.get(idxToko).getListBukuDigital().get(i).getHarga() );
			System.out.println("DOI          : " + listToko.get(idxToko).getListBukuDigital().get(i).getDOI());
			System.out.println("");
			System.out.println("");
		}
		System.out.println("=================================================");
	
	}
	
	public void printBukuFisik(int lenBukuFisik) {
		System.out.println("Buku Fisik");
		System.out.println("=================================================");
		for(int i = 0; i < lenBukuFisik; i++) {
			System.out.println("Buku Fisik - " + (i+1));
			System.out.println("Judul        : " + listToko.get(idxToko).getListBukuFisik().get(i).getJudul() );
			System.out.println("Penulis      : " + listToko.get(idxToko).getListBukuFisik().get(i).getPenulis() );
			System.out.println("Tahun Terbit : " + listToko.get(idxToko).getListBukuFisik().get(i).getTahunTerbit() );
			System.out.println("Harga        : " + listToko.get(idxToko).getListBukuFisik().get(i).getHarga() );
			System.out.println("Stok         : " + listToko.get(idxToko).getListBukuFisik().get(i).getStok() );
			System.out.println("");
			System.out.println("");
		}
			System.out.println("=================================================");
	}
	
	public void printBukuFisikTransaksi(int lenBukuFisik, ArrayList<BukuFisik> bukuFisik ) {
		System.out.println("Buku Fisik");
		System.out.println("=================================================");
		for(int i = 0; i < lenBukuFisik; i++) {
			System.out.println("Buku Fisik - " + (i+1));
			System.out.println("Judul        : " + bukuFisik.get(i).getJudul() );
			System.out.println("Penulis      : " + bukuFisik.get(i).getPenulis() );
			System.out.println("Tahun Terbit : " + bukuFisik.get(i).getTahunTerbit() );
			System.out.println("Harga        : " + bukuFisik.get(i).getHarga() );
			System.out.println("Stok         : " + bukuFisik.get(i).getStok() );
			System.out.println("");
			System.out.println("");
		}
			System.out.println("=================================================");
	}
	
	public ArrayList<BukuFisik> getBukuFisikDenganStok(int lenBukuFisik) {
		ArrayList<BukuFisik> temp = new ArrayList<>();
		for(int i = 0; i < lenBukuFisik; i++) {
			if(listToko.get(idxToko).getListBukuFisik().get(i).getStok() <= 0) {
				continue;
			}
			temp.add(listToko.get(idxToko).getListBukuFisik().get(i));
		}
		return temp;
	}
	
	public boolean cekBukuKosong(int lenBukuDigital, int lenBukuFisik){
		
		if(lenBukuDigital == 0 && lenBukuFisik == 0) {
			System.out.println("Tidak ada buku");
			enter();
			return false;
		}
		
		return true;
	}
}
