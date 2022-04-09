package model;

public class BukuFisik extends Buku {
	private int stok;
	
	public BukuFisik(String judul, String penulis, String jumlahHalaman, String tahunTerbit, int harga,
			 int stok) {
		super(judul, penulis, tahunTerbit, harga);
		this.stok = stok;
	}
	
	public BukuFisik(Buku buku, int stok) {
		super(buku.getJudul(), buku.getPenulis(), buku.getTahunTerbit(), buku.getHarga());
		this.stok = stok;
	}

	public int getStok() {
		return stok;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}
	
	public void addStok() {
		this.stok++;
	}
	
	public void addStok(int qty) {
		this.stok += qty;
	}
}
