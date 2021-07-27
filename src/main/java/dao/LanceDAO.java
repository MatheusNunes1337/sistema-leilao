package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Lance;


public class LanceDAO extends BaseDAO {
	private static List<Lance> getLances() {
		final String sql = "SELECT * FROM Lance";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		 	ResultSet rs = pstmt.executeQuery();
		)
		{
			List<Lance> lances = new ArrayList<>();
			while(rs.next()) {
				lances.add(resultSetToLance(rs));
			}
			return lances; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Lance getLanceById(int id) {
		final String sql = "SELECT * FROM Lance WHERE lance_id = ?";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Lance lance = new Lance();
			if(rs.next()) {
				lance = resultSetToLance(rs);
			}
			rs.close();
			return lance; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List<Lance> getLanceGreaterThan(double valor) {
		final String sql = "SELECT * FROM Lance WHERE valor > ?";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setDouble(1, valor);
			ResultSet rs = pstmt.executeQuery();
			List<Lance> lances = new ArrayList<>();
			while(rs.next()) {
				lances.add(resultSetToLance(rs));
			}
			return lances; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean registrarLance(double valor, int part_id, int item_id, boolean situacao) {
		final String sql = "INSERT INTO Lance (valor, hora, participante_id, item_id, situacao) VALUES (?, ?, ?, ?, ?)";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setDouble(1, valor);
			pstmt.setTime(2, Time.valueOf(LocalTime.now()));
			pstmt.setInt(3, part_id);
			pstmt.setInt(4, item_id);
			pstmt.setBoolean(5, situacao);
			int count = pstmt.executeUpdate();
			return count > 0;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}



	private static boolean updateLance(double valor, LocalTime hora, int part_id, int item_id, boolean situacao, int id) {
		final String sql = "UPDATE Lance SET valor = ?, hora = ?, participante_id = ?, item_id = ?, situacao = ? WHERE lance_id = ?";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setDouble(1, valor);
			pstmt.setTime(2, Time.valueOf(hora));
			pstmt.setInt(3, part_id);
			pstmt.setInt(4, item_id);
			pstmt.setBoolean(5, situacao);
			pstmt.setInt(6, id);
			int count = pstmt.executeUpdate();
			return count > 0;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean softDeleteLance(int id, boolean situacao) {
		final String sql = "UPDATE Lance SET situacao = ? WHERE lance_id = ?";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setBoolean(1, situacao);
			pstmt.setInt(2, id);
			int count = pstmt.executeUpdate();
			return count > 0;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//resultSet
	private static Lance resultSetToLance(ResultSet rs) throws SQLException {
		Lance l = new Lance();
		
		l.setId(rs.getInt("lance_id"));
		l.setValorLance(rs.getDouble("valorLance"));
		l.setHoraLance(rs.getTime("horaLance").toLocalTime());
		l.setParticipante(ParticipanteDAO.getParticipanteById(rs.getInt("participante_id")));
		//l.setItem(itemDAO.getItemById(rs.getInt("item_id")));
		l.setSituacao(rs.getBoolean("situacao"));
		return l;
	}

	public static void main(String[] args) {
		System.out.println(registrarLance(1500, 1, 1, true));
	}
}