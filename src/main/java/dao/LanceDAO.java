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
	public static List<Lance> getLances() {
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

	public static Lance getLanceById(int id) {
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

	public static List<Lance> getLanceGreaterThan(double valor) {
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

	public static boolean registrarLance(double valor, int part_id, int item_id, boolean situacao) throws Exception {
		final String sql = "INSERT INTO Lance (valor, hora, participante_id, item_id, situacao) VALUES (?, ?, ?, ?, ?)";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			if(valor < ItemDAO.getItemById(item_id).getLanceMinimo()) {
				throw new LanceBaixoException();
			}
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



	public static boolean updateLance(double valor, int id) {
		final String sql = "UPDATE Lance SET valor = ? WHERE lance_id = ?";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setDouble(1, valor);
			pstmt.setInt(2, id);
			int count = pstmt.executeUpdate();
			return count > 0;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean softDeleteLance(int id, boolean situacao) {
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
		l.setValorLance(rs.getDouble("valor"));
		l.setHoraLance(rs.getTime("hora").toLocalTime());
		l.setParticipante(ParticipanteDAO.getParticipanteById(rs.getInt("participante_id")));
		l.setItem(ItemDAO.getItemById(rs.getInt("item_id")));
		l.setSituacao(rs.getBoolean("situacao"));
		return l;
	}

	public static void main(String[] args) {
		System.out.println(getLanceById(2));
	}
}

class LanceBaixoException extends Exception {
	public LanceBaixoException() {
		super("Erro, o valor do lance informado é menor que o valor mínimo do item.");
	}
}