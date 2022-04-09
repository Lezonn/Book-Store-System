package model;

public class BukuDigital extends Buku {
	private String DOI;

	public BukuDigital(String judul, String penulis, String jumlahHalaman, String tahunTerbit, int harga,
			String dOI) {
		super(judul, penulis, tahunTerbit, harga);
		DOI = dOI;
	}
	
	public BukuDigital(Buku buku, String DOI) {
		super(buku.getJudul(), buku.getPenulis(), buku.getTahunTerbit(), buku.getHarga());
		this.DOI = DOI;
	}

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String dOI) {
		DOI = dOI;
	}
	
	@Override
	public int getHarga() {
		return (int) Math.round(this.harga * 0.9);
	}
}
