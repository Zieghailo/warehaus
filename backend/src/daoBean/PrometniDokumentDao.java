package daoBean;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.PoslovnaGodina;
import model.PrometniDokument;
import model.StavkaPrometnogDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PrometniDokumentDaoLocal.class)
public class PrometniDokumentDao extends
		GenericDaoBean<PrometniDokument, Integer> implements
		PrometniDokumentDaoLocal {

	@EJB
	StavkaPrometnogDokumentaDaoLocal spdDao;
	
	@EJB
	PrometniDokumentDaoLocal dokumentDao;

	@EJB
	MagacinskaKarticaDaoLocal mkDao;


	@Override
	public PrometniDokument persistSaKreiranjemStavki(PrometniDokument entity) throws NoSuchFieldException {
		PrometniDokument retVal = null;
		try {
			retVal = persist(entity);

			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke()
					.iterator();
			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke
						.next();
				stavkaPrometnogDokumenta.setPrometniDokument(retVal);
				stavkaPrometnogDokumenta.setIdStavkaPrometnogDokumenta(0);
				spdDao.persist(stavkaPrometnogDokumenta);
			}
		} catch (NoSuchFieldException e) {
			throw e;
		}
		return retVal;
	}
	
	@Override
	public PrometniDokument persistSaKreiranjemStavkiDodavanjeBroja(PrometniDokument entity) throws NoSuchFieldException {
		PrometniDokument retVal = null;
		List<PrometniDokument> list = dokumentDao.findAll();
		int maxBr = 0;
		for (PrometniDokument pd : list) {
			if (pd.getBroj()>maxBr) {
				maxBr = pd.getBroj();
			}
		}
		entity.setBroj(++maxBr);
		try {

			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke()
					.iterator();
			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke
						.next();
				stavkaPrometnogDokumenta.setPrometniDokument(entity);
				spdDao.persist(stavkaPrometnogDokumenta);
			}
			retVal = persist(entity);
		} catch (NoSuchFieldException e) {
			throw e;
		}
		return retVal;
	}
	
	@Override
	public PrometniDokument proknjiziDokument(PrometniDokument entity) throws Exception {
		// reci da je proknjizen
		// izmeni magacinsku karticu za oba magacina dodaj karticu ako ne
		// postoji kartica za taj artikal u tom magacinu
		// dodaj novu analitiku magacinske kartice za oba magacina sa promenom
		// koja se desila nad magacinskim karticama oba magacina
			try {
			if (entity.getMagacin1() != null) {
			
		
				mkDao.update(entity, entity.getMagacin1(),1);
			
		}
			if(entity.getMagacin2() != null){
			mkDao.update(entity, entity.getMagacin2(),2);
		}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public boolean proveriPrometniDokument(PrometniDokument entity) {
		if (entity.getStavke().size()<=0) {
			return false;
		}
		if (entity.getPoslovnaGodina().getZakljucenaGodina()) {
			return false;
		} 
		Date now = new Date();
		if (entity.getDatumNastanka().after(now)) {
			return false;
		}
		if (entity.getVrstaDokumenta().getIdVrstaDokumenta()!=1) {
			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke().iterator();
			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke.next();
				//Porediti sa sadrzajem magacina
				//magacin-kartica-artikal-kolicina
			//	if (stavkaPrometnogDokumenta.getKolicinaPrDokumenta().compareTo())
				//spdDao.persist(stavkaPrometnogDokumenta);
			}
		}
		return false;
	}

	@Override
	public int findMaxRB() {
		Query q = em.createNamedQuery("PrometniDokument.findMaxRB");
		return (int) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findRBbyPG(PoslovnaGodina poslovnaGodina) {
		Query q = em.createNamedQuery("PrometniDokument.findMaxRBbyPG");
		q.setParameter("id", poslovnaGodina.getIdPoslovnaGodina());
		List<PrometniDokument> spisak = q.getResultList();
		int ret = 0;
		for(PrometniDokument p: spisak){
			if(p.getBroj() > ret){
				ret = p.getBroj();
			}
		}
		ret++;
		return ret;
	}
	

}
