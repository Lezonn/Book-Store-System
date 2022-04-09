package model;

import java.util.ArrayList;

public class Toko {
	private String nama;
	private String lokasi;
	private ArrayList<BukuFisik> listBukuFisik;
	private ArrayList<BukuDigital> listBukuDigital;
	private ArrayList<Transaksi> listTransaksi;
	
	
	public Toko(String nama, String lokasi, ArrayList<BukuFisik> listBukuFisik,
			ArrayList<BukuDigital> listBukuDigital,ArrayList<Transaksi> listTransaksi ) {
		this.nama = nama;
		this.lokasi = lokasi;
		this.listBukuFisik = listBukuFisik;
		this.listBukuDigital = listBukuDigital;
		this.listTransaksi = listTransaksi;
	}
	
	public ArrayList<Transaksi> getListTransaksi() {
		return listTransaksi;
	}

	public void setListTransaksi(ArrayList<Transaksi> listTransaksi) {
		this.listTransaksi = listTransaksi;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
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
	
	public int getJumlahBuku(String tipe) {
		if(tipe.equals("Digital")) {
			return this.listBukuDigital.size();
		}
		return this.listBukuFisik.size();
	}
}
