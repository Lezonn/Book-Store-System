package model;

abstract public class Buku {
	protected String judul;
	protected String penulis;
	protected String tahunTerbit;
	protected int harga;
	
	public Buku(String judul, String penulis, String tahunTerbit, int harga) {
		this.judul = judul;
		this.penulis = penulis;
		this.tahunTerbit = tahunTerbit;
		this.harga = harga;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getPenulis() {
		return penulis;
	}

	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}

	public String getTahunTerbit() {
		return tahunTerbit;
	}

	public void setTahunTerbit(String tahunTerbit) {
		this.tahunTerbit = tahunTerbit;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}
}
