package persistencia;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.derby.tools.sysinfo;

import exceptions.GestorUniversitatsException;
import model.Universitat;
import model.Campus;
import interfaces.ProveedorPersistencia;
import interfaces.UnitatUniversitat;

/**
 *
 */
public class GestorJDBC implements ProveedorPersistencia {

	private Universitat universitat;

	private Connection connexio;

	public Universitat getUniversitat() {
		return universitat;
	}

	public void setUniversitat(Universitat universitat) {
		this.universitat = universitat;
	}

	/*
	 * PreparedStatement necessaris
	 */
	private PreparedStatement nomUniversitatSt;
	private PreparedStatement insereixUniversitatSt;
	private PreparedStatement actualitzaUniversitatSt;
	private PreparedStatement eliminaCampusSt;
	private PreparedStatement afegirCampusSt;
	private PreparedStatement seleccionaCampusSt;

	/*
	 * TODO
	 *
	 * Obtenir una universitat.
	 * 
	 * Sentència select de la taula "universitat".
	 * Columnes: Totes.
	 * Files: la de la universitat amb un nom que coincideixi amb el passat per
	 * paràmetre.
	 *
	 */
	private static String nomUniversitatSQL = "SELECT * FROM universitat WHERE nomUniversitat = ?";
	/*
	 * TODO
	 *
	 * Afegir una universitat.
	 * 
	 * Sentència per afegir una universitat en la taula "universitat". Els
	 * valors dels camps són els que es passaran per paràmetre.
	 *
	 */
	private static String insereixUniversitatSQL = "INSERT INTO universitat (nomUniversitat, ubicacioSeu) VALUES (?,?)";
	/*
	 * TODO
	 *
	 * Actualitzar una universitat.
	 * 
	 * Sentència per actualitzar una universitat de la taula "universitat".
	 * Files a actualitzar: la que corresponguin al nom passat per paràmetre.
	 * Columnes a actualitzar: nom i ubicació amb els valors passats per paràmetre.
	 *
	 */
	private static String actualitzaUniversitatSQL = "UPDATE universitat SET nomUniversitat=?,ubicacioSeu=? WHERE nomUniversitat=?";
	/*
	 * TODO
	 *
	 * Eliminar campus (donat el nom d'una universitat)
	 * 
	 * Sentència que elimina els campus de la taula "campus" relacionats
	 * amb una universitat.
	 * Files a eliminar: les que es corresponguin al nom de la universitat passat
	 * per paràmetre.
	 *
	 */
	private static String eliminaCampusSQL = "DELETE FROM campus WHERE campus.universitat = ?";
	/*
	 * TODO
	 *
	 * Afegir un campus.
	 * 
	 * Sentència que afegix un campus a la taula "campus". Els valors dels
	 * camps són els que es passaran per paràmetre.
	 *
	 */
	private static String afegirCampusSQL = "INSERT INTO campus VALUES (?,?,?)";
	/*
	 * TODO
	 *
	 * Seleccionar les campus d'una universitat.
	 * 
	 * Sentència que selecciona els campus de la taula "campus" d'una
	 * universitat determinada.
	 * Columnes: totes.
	 * Files: totes en les quals el nom de la universitat coincideixi amb
	 * el passat per paràmetre.
	 *
	 */
	private static String seleccionaCampusSQL = "SELECT * FROM campus where universitat = ?";
	/*
	 * TODO
	 * 
	 * Paràmetres: cap
	 *
	 * Acció:
	 * 
	 * - Heu d'establir la connexio JDBC amb la base de dades EAC112425S2.
	 * 
	 * - Heu de crear els objectes PrepareStatement declarats com a atributs
	 * d'aquesta
	 * classe amb els respectius SQL declarats com a atributs just sobre cadascun
	 * d'ells.
	 * 
	 * - Heu de fer el catch de les possibles excepcions (en aquest mètode no
	 * llanceu
	 * GestorUniversitatsException, simplement, mostreu el missatge a consola de
	 * l'excepció capturada).
	 *
	 * Retorn: cap.
	 *
	 */

	// Mètode per establir la conexió amn la base de dades
	public void estableixConnexio() {
		try {
			// Establim la conexió amb la base de dades
			String urlBaseDades = "jdbc:derby://localhost:1527/EAC112425S2";
			String usuari = "ioc2425s2";
			String contrasenya = "abc123";
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connexio = DriverManager.getConnection(urlBaseDades, usuari, contrasenya);

			// Inicialitzem els prepared statements
			nomUniversitatSt = connexio.prepareStatement(nomUniversitatSQL);
			actualitzaUniversitatSt = connexio.prepareStatement(actualitzaUniversitatSQL);
			eliminaCampusSt = connexio.prepareStatement(eliminaCampusSQL);
			insereixUniversitatSt = connexio.prepareStatement(insereixUniversitatSQL);
			afegirCampusSt = connexio.prepareStatement(afegirCampusSQL);
			seleccionaCampusSt = connexio.prepareStatement(seleccionaCampusSQL);

		} catch (Exception e) {
			e.printStackTrace(); // Tracem els missarges d'error
		}
	}

	public void tancaConnexio() throws SQLException {
		try {
			connexio.close();
		} finally {
			connexio = null;
		}
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
	 * - Heu de desar la universitat sobre la base de dades.
	 * 
	 * - La universitat s'ha de desar a la taula "universitat".
	 * 
	 * - Cada campus de la universitat, s'ha de desar com a registre de la
	 * taula "campus".
	 * 
	 * - Heu de tenir en compte que si la universitat ja existeix, heu de fer
	 * el següent:
	 * 
	 * - Actualitzar el registre universitat ja existent.
	 * 
	 * - Eliminar tots els campus d'aquesta universitat de la taula
	 * "campus" i després inserir els nous com si es tractes d'una nova
	 * universitat.
	 * 
	 * - Si al fer qualsevol operació es produeix una excepció, llavors heu de
	 * llançar l'excepció GestorUniversitatsException amb codi "GestorJDBC.desar".
	 *
	 * Retorn: cap.
	 *
	 */

	public void desarUniversitat(String nomFitxer, Universitat universitat) throws GestorUniversitatsException {
		nomFitxer = null;
		ResultSet res = null;
		try {
			estableixConnexio();
			nomUniversitatSt.setString(1, universitat.getNomUniversitat());
			res = nomUniversitatSt.executeQuery();

			if (res.next()) {

				actualitzaUniversitatSt.setString(1, universitat.getNomUniversitat());
				actualitzaUniversitatSt.setString(2, universitat.getUbicacioSeu());
				actualitzaUniversitatSt.setString(3, universitat.getNomUniversitat());
				actualitzaUniversitatSt.executeUpdate();
				eliminaCampusSt.setString(1, universitat.getNomUniversitat());
				eliminaCampusSt.executeUpdate();
			} else {
				insereixUniversitatSt.setString(1, universitat.getNomUniversitat());
				insereixUniversitatSt.setString(2, universitat.getUbicacioSeu());
				insereixUniversitatSt.executeUpdate();
			}
			for (Campus campus : universitat.getCampusList()) {
				afegirCampusSt.setString(1, campus.getNomCampus());
				afegirCampusSt.setString(2, campus.getUbicacio());
				afegirCampusSt.setString(3, universitat.getNomUniversitat());
				afegirCampusSt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GestorUniversitatsException("GestorJDBC.desar" + e.getMessage());
		} finally {
			// Cerrar recursos en el bloque finally
			if (res != null) {
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace(); // Si falla al cerrar, muestra el error
				}
			}
			try {
				tancaConnexio(); // Asegúrate de cerrar la conexión en finally
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: el nom de la universitat.
	 *
	 * Acció:
	 * 
	 * - Heu de carregar la universitat des de la base de dades.
	 * 
	 * - Per fer això, heu de cercar el registre universitat de la taula amb
	 * nom "universitat".
	 * 
	 * - A més, heu d'afegir els campus a la universitat a partir de la
	 * taula "campus".
	 * 
	 * - Si al fer qualsevol operació es dona una excepció, llavors heu de llançar
	 * l'excepció GestorUniversitatsException amb codi "GestorJDBC.carregar".
	 * 
	 * - Si el nomUniversitat donat no existeix a la taula campus,
	 * llavors heu de llançar l'excepció GestorUniversitatsException amb codi
	 * "GestorJDBC.noexist".
	 *
	 * Retorn: cap.
	 *
	 */

	public void carregarUniversitat(String nomUniversitat) throws GestorUniversitatsException {
		ResultSet resUnis = null;
		ResultSet resCampuses = null;
		try {
			estableixConnexio();
			// cercar el registre universitat de la taula amb nom "universitat".
			nomUniversitatSt.setString(1, nomUniversitat);
			resUnis = nomUniversitatSt.executeQuery();

			if (resUnis.next()) {
				universitat = new Universitat(
						resUnis.getString("nomUniversitat"),
						resUnis.getString("ubicacioSeu"));

				seleccionaCampusSt.setString(1, resUnis.getString("nomUniversitat"));
				resCampuses = seleccionaCampusSt.executeQuery();
				while (resCampuses.next()) {
					afegirCampusSt.setString(1, resCampuses.getString("nomCampus"));
					afegirCampusSt.setString(2, resCampuses.getString("ubicacio"));
					afegirCampusSt.setString(3, resCampuses.getString("universitat"));
					afegirCampusSt.executeUpdate();
				}
			} else {
				throw new GestorUniversitatsException("GestorJDBC.noexist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GestorUniversitatsException("GestorJDBC.carregar" + e.getMessage());
		} finally {
			try {
				if (resUnis != null)
					resUnis.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (resCampuses != null)
					resCampuses.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				tancaConnexio();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}