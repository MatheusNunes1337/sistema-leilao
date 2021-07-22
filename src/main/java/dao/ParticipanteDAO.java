package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Participante;

public class ParticipanteDAO extends BaseDAO {
	private static List<Participante> getParticipantes() {
		final String sql = "SELECT * FROM Participantes";
		try 
			(
				Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			 	ResultSet rs = pstmt.executeQuery();
			)
		{
			List<Participante> participantes = new ArrayList<>();
			while(rs.next()) {
				participantes.add(resultSetToParticipante(rs));
			}
			return participantes;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
	}

	private static Participante getParticipanteById(int id) {
		final String sql = "SELECT * FROM Participantes WHERE participante_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Participante participantes = new Participante();
			if(rs.next()) {
				participantes = resultSetToParticipante(rs);
			}
			rs.close();
			return participantes; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Participante getParticipanteByEmail(String email) {
		final String sql = "SELECT * FROM Participantes WHERE email = ?";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			Participante participantes = new Participante();
			if(rs.next()) {
				participantes = resultSetToParticipante(rs);
			}
			rs.close();
			return participantes; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean registrar(Participante p) {
		final String sql = "INSERT INTO Participantes (nome, login, senha, email, endereco, telefone) VALUES (?, ?, ?, ?, ?, ?)";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setString(1, p.getNome());
			pstmt.setString(2, p.getLogin());
			pstmt.setString(3, p.getSenha());
			pstmt.setString(4, p.getEmail());
			pstmt.setString(5, p.getEndereco());
			pstmt.setString(6, p.getTelefone());
			int count = pstmt.executeUpdate();
			return count > 0;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean updateParticipante(Participante p) {
		final String sql = "UPDATE Participantes SET nome = ?, login = ?, senha = ?, email = ?, endereco = ?, telefone = ?, situacao = ? WHERE participante_id = ?";
		try 
		
			(
	 			Connection conn = getConnection();
	 			PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		
		{
			pstmt.setString(1, p.getNome());
			pstmt.setString(2, p.getLogin());
			pstmt.setString(3, p.getSenha());
			pstmt.setString(4, p.getEmail());
			pstmt.setString(5, p.getEndereco());
			pstmt.setString(6, p.getTelefone());
			pstmt.setBoolean(7, p.getSituacao());
			pstmt.setInt(8, p.getId());
			int count = pstmt.executeUpdate();
			return count > 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean softDeleteParticipante(int id, boolean situacao) {
		final String sql = "UPDATE Participantes SET situacao = ? WHERE participante_id = ?";
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
	private static Participante resultSetToParticipante(ResultSet rs) throws SQLException {
		Participante p = new Participante();
		p.setId(rs.getInt("participante_id"));
		p.setNome(rs.getString("nome"));
		p.setLogin(rs.getString("login"));
		p.setSenha(rs.getString("senha"));
		p.setEmail(rs.getString("email"));
		p.setEndereco(rs.getString("endereco"));
		p.setTelefone(rs.getString("telefone"));
		p.setSituacao(rs.getBoolean("situacao"));
		return p;
	}

	public static void main(String[] args) {
		//ParticipanteDAO pDAO = new ParticipanteDAO();
		//Participante p = new Participante(1, "Matheus", "math", "abcdef", "math00@gmail.com", "Avenida 1", "991457812");
		System.out.println(softDeleteParticipante(1, false));
	}
}