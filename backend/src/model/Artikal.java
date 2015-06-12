package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the artikal database table.
 * 
 */
@Entity
@NamedQuery(name="Artikal.findAll", query="SELECT a FROM Artikal a")
public class Artikal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ARTIKAL")
	private int idArtikal;

	@Column(name="NAZIV_ARTIKLA")
	private String nazivArtikla;

	private BigDecimal pakovanje;

	@Column(name="SIFRA_ARTIKLA")
	private String sifraArtikla;

	//bi-directional many-to-one association to GrupaArtikala
	@ManyToOne
	@JoinColumn(name="ID_GRUPA_ARTIKALA")
	private GrupaArtikala grupaArtikala;

	//bi-directional many-to-one association to JedinicaMere
	@ManyToOne
	@JoinColumn(name="ID_JEDINICA_MERE")
	private JedinicaMere jedinicaMere;

	//bi-directional many-to-one association to MagacinskaKartica
	@OneToMany(mappedBy="artikal")
	private List<MagacinskaKartica> magacinskaKarticas;

	//bi-directional many-to-one association to StavkaPopisa
	@OneToMany(mappedBy="artikal")
	private List<StavkaPopisa> stavkaPopisas;

	//bi-directional many-to-one association to StavkaPrometnogDokumenta
	@OneToMany(mappedBy="artikal")
	private List<StavkaPrometnogDokumenta> stavkaPrometnogDokumentas;

	public Artikal() {
	}

	public int getIdArtikal() {
		return this.idArtikal;
	}

	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}

	public String getNazivArtikla() {
		return this.nazivArtikla;
	}

	public void setNazivArtikla(String nazivArtikla) {
		this.nazivArtikla = nazivArtikla;
	}

	public BigDecimal getPakovanje() {
		return this.pakovanje;
	}

	public void setPakovanje(BigDecimal pakovanje) {
		this.pakovanje = pakovanje;
	}

	public String getSifraArtikla() {
		return this.sifraArtikla;
	}

	public void setSifraArtikla(String sifraArtikla) {
		this.sifraArtikla = sifraArtikla;
	}

	public GrupaArtikala getGrupaArtikala() {
		return this.grupaArtikala;
	}

	public void setGrupaArtikala(GrupaArtikala grupaArtikala) {
		this.grupaArtikala = grupaArtikala;
	}

	public JedinicaMere getJedinicaMere() {
		return this.jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public List<MagacinskaKartica> getMagacinskaKarticas() {
		return this.magacinskaKarticas;
	}

	public void setMagacinskaKarticas(List<MagacinskaKartica> magacinskaKarticas) {
		this.magacinskaKarticas = magacinskaKarticas;
	}

	public MagacinskaKartica addMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		getMagacinskaKarticas().add(magacinskaKartica);
		magacinskaKartica.setArtikal(this);

		return magacinskaKartica;
	}

	public MagacinskaKartica removeMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		getMagacinskaKarticas().remove(magacinskaKartica);
		magacinskaKartica.setArtikal(null);

		return magacinskaKartica;
	}

	public List<StavkaPopisa> getStavkaPopisas() {
		return this.stavkaPopisas;
	}

	public void setStavkaPopisas(List<StavkaPopisa> stavkaPopisas) {
		this.stavkaPopisas = stavkaPopisas;
	}

	public StavkaPopisa addStavkaPopisa(StavkaPopisa stavkaPopisa) {
		getStavkaPopisas().add(stavkaPopisa);
		stavkaPopisa.setArtikal(this);

		return stavkaPopisa;
	}

	public StavkaPopisa removeStavkaPopisa(StavkaPopisa stavkaPopisa) {
		getStavkaPopisas().remove(stavkaPopisa);
		stavkaPopisa.setArtikal(null);

		return stavkaPopisa;
	}

	public List<StavkaPrometnogDokumenta> getStavkaPrometnogDokumentas() {
		return this.stavkaPrometnogDokumentas;
	}

	public void setStavkaPrometnogDokumentas(List<StavkaPrometnogDokumenta> stavkaPrometnogDokumentas) {
		this.stavkaPrometnogDokumentas = stavkaPrometnogDokumentas;
	}

	public StavkaPrometnogDokumenta addStavkaPrometnogDokumenta(StavkaPrometnogDokumenta stavkaPrometnogDokumenta) {
		getStavkaPrometnogDokumentas().add(stavkaPrometnogDokumenta);
		stavkaPrometnogDokumenta.setArtikal(this);

		return stavkaPrometnogDokumenta;
	}

	public StavkaPrometnogDokumenta removeStavkaPrometnogDokumenta(StavkaPrometnogDokumenta stavkaPrometnogDokumenta) {
		getStavkaPrometnogDokumentas().remove(stavkaPrometnogDokumenta);
		stavkaPrometnogDokumenta.setArtikal(null);

		return stavkaPrometnogDokumenta;
	}

}