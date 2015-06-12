package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the jedinica_mere database table.
 * 
 */
@Entity
@Table(name="jedinica_mere")
@NamedQuery(name="JedinicaMere.findAll", query="SELECT j FROM JedinicaMere j")
public class JedinicaMere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_JEDINICA_MERE")
	private int idJedinicaMere;

	@Column(name="NAZIV_JEDINICE_MERE")
	private String nazivJediniceMere;

	@Column(name="SIFRA_JEDINICE_MERE")
	private String sifraJediniceMere;

	//bi-directional many-to-one association to Artikal
	@OneToMany(mappedBy="jedinicaMere")
	private List<Artikal> artikals;

	public JedinicaMere() {
	}

	public int getIdJedinicaMere() {
		return this.idJedinicaMere;
	}

	public void setIdJedinicaMere(int idJedinicaMere) {
		this.idJedinicaMere = idJedinicaMere;
	}

	public String getNazivJediniceMere() {
		return this.nazivJediniceMere;
	}

	public void setNazivJediniceMere(String nazivJediniceMere) {
		this.nazivJediniceMere = nazivJediniceMere;
	}

	public String getSifraJediniceMere() {
		return this.sifraJediniceMere;
	}

	public void setSifraJediniceMere(String sifraJediniceMere) {
		this.sifraJediniceMere = sifraJediniceMere;
	}

	public List<Artikal> getArtikals() {
		return this.artikals;
	}

	public void setArtikals(List<Artikal> artikals) {
		this.artikals = artikals;
	}

	public Artikal addArtikal(Artikal artikal) {
		getArtikals().add(artikal);
		artikal.setJedinicaMere(this);

		return artikal;
	}

	public Artikal removeArtikal(Artikal artikal) {
		getArtikals().remove(artikal);
		artikal.setJedinicaMere(null);

		return artikal;
	}

}