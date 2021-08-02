package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Leilao;

public class LeilaoDAO extends BaseDAO {
	public static List<Leilao> getLeiloesFinalizados() {
		final String sql = "SELECT * FROM Leilao";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		 	ResultSet rs = pstmt.executeQuery();
		)
		{
			List<Leilao> leiloes = new ArrayList<>();
			while(rs.next()) {
				leiloes.add(resultSetToLeilao(rs));
			}
			return leiloes; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Leilao> getLeiloesEmAndamento() {
		final String sql = "SELECT * FROM Leilao WHERE dataFinal IS NULL";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		 	ResultSet rs = pstmt.executeQuery();
		)
		{
			List<Leilao> leiloes = new ArrayList<>();
			while(rs.next()) {
				leiloes.add(resultSetToLeilaoEmAndamento(rs));
			}
			return leiloes; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Leilao getLeilaoById(int id) {
		final String sql = "SELECT * FROM Leilao WHERE leilao_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Leilao leilao = new Leilao();
			if(rs.next()) {
				leilao = resultSetToLeilao(rs);
			}
			rs.close();
			return leilao; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Leilao> getLeilaoByDate(String dataInicio) {
		final String sql = "SELECT * FROM Leilao WHERE dataInicio = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setString(1, dataInicio);
			ResultSet rs = pstmt.executeQuery();
			List<Leilao> leiloes = new ArrayList<>();
			while(rs.next()) {
				leiloes.add(resultSetToLeilao(rs));
			}
			return leiloes; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean iniciarLeilao(Leilao l) {
		final String sql = "INSERT INTO Leilao (dataInicio, horaInicio, situacao) VALUES (?, ?, ?)";
		try 
			(
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setDate(1, Date.valueOf(l.getDataInicio()));
			pstmt.setTime(2, Time.valueOf(l.getHoraInicio()));
			pstmt.setBoolean(3, l.getSituacao());
			int count = pstmt.executeUpdate();
			return count > 0;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean finalizarLeilao(LocalDate dataFinal, LocalTime horaFinal, int id) {
		final String sql = "UPDATE Leilao SET dataFinal = ?, horaFinal = ? WHERE leilao_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setDate(1, Date.valueOf(dataFinal));
			pstmt.setTime(2, Time.valueOf(horaFinal));
			pstmt.setInt(3, id);
			int count = pstmt.executeUpdate();
			return count > 0;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateLeilao(LocalDate dataInicio, LocalTime horaInicio, LocalDate dataFinal, LocalTime horaFinal, boolean situacao, int id) {
		final String sql = "UPDATE Leilao SET dataInicio = ?, horaInicio = ?, dataFinal = ?, horaFinal = ?, situacao = ? WHERE leilao_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setDate(1, Date.valueOf(dataInicio));
			pstmt.setTime(2, Time.valueOf(horaInicio));
			pstmt.setDate(3, Date.valueOf(dataFinal));
			pstmt.setTime(4, Time.valueOf(horaFinal));
			pstmt.setBoolean(5, situacao);
			pstmt.setInt(6, id);
			int count = pstmt.executeUpdate();
			return count > 0;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean softDeleteLeilao(int id, boolean situacao) {
		final String sql = "UPDATE Leilao SET situacao = ? WHERE leilao_id = ?";
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
	private static Leilao resultSetToLeilao(ResultSet rs) throws SQLException {
		Leilao l = new Leilao();
		l.setId(rs.getInt("leilao_id"));
		l.setDataInicio(rs.getDate("dataInicio").toLocalDate());
		l.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
		l.setDataFinal(rs.getDate("dataFinal").toLocalDate());
		l.setHoraFinal(rs.getTime("horaFinal").toLocalTime());
		l.setSituacao(rs.getBoolean("situacao"));
		return l;
	}
	
	private static Leilao resultSetToLeilaoEmAndamento(ResultSet rs) throws SQLException {
		Leilao l = new Leilao();
		l.setId(rs.getInt("leilao_id"));
		l.setDataInicio(rs.getDate("dataInicio").toLocalDate());
		l.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
		l.setSituacao(rs.getBoolean("situacao"));
		return l;
	}

	public static void main(String[] args) {
		//Leilao l = new Leilao(1, LocalDate.now(), LocalTime.now());
		//System.out.println(iniciarLeilao(l));
		System.out.println(updateLeilao(LocalDate.now().plusDays(2), LocalTime.now(), LocalDate.now().plusDays(3), LocalTime.now().plusHours(2), true, 1));
		
	}
}