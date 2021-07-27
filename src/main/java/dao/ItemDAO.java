package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ItemLeilao;

public class ItemDAO extends BaseDAO {
	private static List<ItemLeilao> getItens() {
		final String sql = "SELECT * FROM ItemLeilao";
		try 
		(
		 	Connection conn = getConnection();
		 	PreparedStatement pstmt = conn.prepareStatement(sql);
		 	ResultSet rs = pstmt.executeQuery();
		)
		{
			List<ItemLeilao> itens = new ArrayList<>();
			while(rs.next()) {
				itens.add(resultSetToItem(rs));
			}
			return itens; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ItemLeilao getItemById(int id) {
		final String sql = "SELECT * FROM ItemLeilao WHERE item_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			ItemLeilao item = new ItemLeilao();
			if(rs.next()) {
				item = resultSetToItem(rs);
			}
			rs.close();
			return item; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List<ItemLeilao> getItensByStatus(boolean status) {
		final String sql = "SELECT * FROM ItemLeilao WHERE arrematado = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setBoolean(1, status);
			ResultSet rs = pstmt.executeQuery();
			List<ItemLeilao> itens = new ArrayList<>();
			while(rs.next()) {
				itens.add(resultSetToItem(rs));
			}
			return itens; 	
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean registrarItem(ItemLeilao i, int leilaoId) {
		final String sql = "INSERT INTO ItemLeilao (leilao_id, titulo, descricao, lanceMinimo, arrematado, situacao) VALUES (?, ?, ?, ?, ?, ?)";
		try 
			(
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setInt(1, leilaoId);
			pstmt.setString(2, i.getTitulo());
			pstmt.setString(3, i.getDescricao());
			pstmt.setDouble(4, i.getLanceMinimo());
			pstmt.setBoolean(5, i.isArrematado());
			pstmt.setBoolean(6, i.getSituacao());
			int count = pstmt.executeUpdate();
			return count > 0;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean arrematarItem(int itemId) {
		final String sql = "UPDATE ItemLeilao SET arrematado = ? WHERE leilao_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, itemId);
			int count = pstmt.executeUpdate();
			return count > 0;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean updateItem(ItemLeilao i) {
		final String sql = "UPDATE ItemLeilao SET titulo = ?, descricao = ?, lanceMinimo = ?, arrematado = ?, situacao = ? WHERE leilao_id = ?";
		try 
			(
			 	Connection conn = getConnection();
			 	PreparedStatement pstmt = conn.prepareStatement(sql);
			)
		{
			pstmt.setString(1, i.getTitulo());
			pstmt.setString(2, i.getDescricao());
			pstmt.setDouble(3, i.getLanceMinimo());
			pstmt.setBoolean(4, i.isArrematado());
			pstmt.setBoolean(5, i.getSituacao());
			pstmt.setInt(6, i.getId());
			int count = pstmt.executeUpdate();
			return count > 0;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean softDeleteItem(int id, boolean situacao) {
		final String sql = "UPDATE ItemLeilao SET situacao = ? WHERE item_id = ?";
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
	private static ItemLeilao resultSetToItem(ResultSet rs) throws SQLException {
		ItemLeilao i = new ItemLeilao();
		i.setId(rs.getInt("item_id"));
		i.setTitulo(rs.getString("titulo"));
		i.setDescricao(rs.getString("descricao"));
		i.setLanceMinimo(rs.getDouble("lanceMinimo"));
		i.arrematarItem(rs.getBoolean("arrematado"));
		i.setSituacao(rs.getBoolean("situacao"));
		return i;
	}

	public static void main(String[] args) {
		ItemLeilao i = new ItemLeilao(1, "Quadro", "Quadro do Van Gogh", 20000, false);
		//System.out.println(registrarItem(i, 1));
		System.out.println(updateItem(i));
		
	}
}