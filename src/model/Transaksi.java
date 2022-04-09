package model;

import java.util.ArrayList;

public class Transaksi {
	private int totalHarga;
	private ArrayList<BukuFisik> listBukuFisik;
	private ArrayList<BukuDigital> listBukuDigital;
	private Pembeli pembeli;
	
	public Transaksi(int totalHarga, ArrayList<BukuFisik> listBukuFisik, ArrayList<BukuDigital> listBukuDigital, Pembeli pembeli) {
		this.totalHarga = totalHarga;
		this.listBukuFisik = listBukuFisik;
		this.listBukuDigital = listBukuDigital;
		this.pembeli = pembeli;
	}

	public int getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(int totalHarga) {
		this.totalHarga = totalHarga;
	}

	public ArrayList<BukuFisik> getListBukuFisik() {
		return listBukuFisik;
	}

	public void setListBukuFisik(ArrayList<BukuFisik> listBukuFisik) {
		this.listBukuFisik = listBukuFisik;
	}

	public ArrayList<BukuDigital> getListBukuDigital() {
		return listBukuDigital;
	}

	public void setListBukuDigital(ArrayList<BukuDigital> listBukuDigital) {
		this.listBukuDigital = listBukuDigital;
	}
	
	public void setPembeli(Pembeli pembeli){
		this.pembeli = pembeli;
	}
	
	public Pembeli getPembeli(){
		return this.pembeli;
	}
	
	public void setHitungTotalHarga() {
		for(BukuDigital digital : listBukuDigital) {
			this.totalHarga += digital.getHarga();
		}
		
		
		
		for(BukuFisik fisik : listBukuFisik){
			this.totalHarga += fisik.getHarga();
		}
		 
	}
}
