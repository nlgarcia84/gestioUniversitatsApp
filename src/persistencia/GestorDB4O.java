package persistencia;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.query.Predicate;
import exceptions.GestorUniversitatsException;
import model.Universitat;
import interfaces.ProveedorPersistencia;

/**
 *
 */
public class GestorDB4O implements ProveedorPersistencia {

	private ObjectContainer db;
	private Universitat universitat;

	public GestorDB4O() {
		this.universitat = new Universitat(null, null);
	}

	public Universitat getUniversitat() {
		return universitat;
	}

	public void setUniversitat(Universitat Universitat) {
		this.universitat = Universitat;
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: cap.
	 *
	 * Acció:
	 * 
	 * - Heu de crear / obrir la base de dades "EAC112425S2.db4o".
	 * 
	 * - Aquesta base de dades ha de permetre que Universitat s'actualitzi en
	 * cascada.
	 *
	 * Retorn: cap.
	 *
	 */
	public void estableixConnexio() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(Universitat.class).cascadeOnUpdate(true);
		db = Db4oEmbedded.openFile(config, "EAC112425S2.db4o");
	}

	public void tancaConnexio() {
		db.close();
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: el nom del fitxer i la universitat a desar.
	 * 
	 * El primer paràmetre NO s'utilitza (és null).
	 *
	 * Acció:
	 * 
	 * - Heu d'establir la connexio i al final tancar-la.
	 * 
	 * - Heu de desar l'objecte Universitat passat per paràmetre sobre la base de
	 * dades. Heu d'inserir si no existia ja a la base de dades, o actualitzar en
	 * l'altre cas.
	 * 
	 * - S'ha de fer la consulta de l'existència amb Predicate.
	 *
	 * Retorn: cap.
	 *
	 */

	public void desarUniversitat(String nomFitxer, Universitat universitat) {
		nomFitxer = null;
		// Conectem a la base de dades
		estableixConnexio();
		try {
			// Implementem Predicate per filtrar
			Predicate<Universitat> p = new Predicate<Universitat>() {
				@Override
				public boolean match(Universitat u) {
					return universitat.getNomUniversitat().equalsIgnoreCase(u.getNomUniversitat())
							&& universitat.getUbicacioSeu().equalsIgnoreCase(u.getUbicacioSeu());
				}
			};
			// Fem consulta pasantli el predicate perque filtri
			ObjectSet<Universitat> result = db.query(p);
			// Si el resultat no retorna res afegim la uni al store
			if (result.isEmpty()) {
				db.store(universitat);
				// Si en retorna perque existeix, actualitzem els camps nomUniversitat i
				// ubicacioSeu i afegim al store un cop fet el update
			} else {
				Universitat u = result.next();
				u.setNomUniversitat(universitat.getNomUniversitat());
				u.setUbicacioSeu(universitat.getUbicacioSeu());
				db.store(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Tanquem la conexió
		} finally {
			tancaConnexio();
		}
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: el nom de la universitat.
	 *
	 * Acció:
	 * 
	 * - Heu d'establir la connexio i al final tancar-la.
	 * 
	 * - Heu de carregar la universitat des de la base de dades assignant-la a
	 * l'atribut "universitat". Si no existeix, llanceu l'excepció
	 * GestorUniversitatException amb codi "GestorDB4O.noExisteix".
	 * 
	 * - S'ha de fer la consulta amb Predicate.
	 *
	 * Retorn: cap
	 *
	 */

	public void carregarUniversitat(String nomUniversitat) throws GestorUniversitatsException {
		estableixConnexio();
		try {
			Predicate<Universitat> p = new Predicate<Universitat>() {
				@Override
				public boolean match(Universitat u) {
					return u.getNomUniversitat().equalsIgnoreCase(nomUniversitat);
				}
			};
			// Fem consulta pasantli el predicate perque filtri
			ObjectSet<Universitat> result = db.query(p);
			if (!result.isEmpty()) {
				Universitat u = result.next();
				universitat.setNomUniversitat(u.getNomUniversitat());
				universitat.setUbicacioSeu(u.getUbicacioSeu());
			} else {
				throw new GestorUniversitatsException("GestorDB4O.noExisteix");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tancaConnexio();
		}
	}
}