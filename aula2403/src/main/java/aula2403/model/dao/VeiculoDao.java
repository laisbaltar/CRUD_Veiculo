package aula2403.model.dao;

import aula2403.model.entity.Veiculo;
import aula2403.model.jdbc.MinhaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeiculoDao {

    //criar um objeto Connection para receber a conexão
    Connection con;

    public VeiculoDao(){
        con = MinhaConexao.conexao();
    }

    public List<Veiculo> buscarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();

        try {
            String sql = "select * from veiculo";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getLong("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setPreco(rs.getDouble("preco"));
                v.setAnoFabricacao(rs.getInt("anoFabricacao"));
                veiculos.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculos;
    }

    public boolean save(Veiculo veiculo) {
        try {
            String sql = "insert into veiculo (marca, modelo, preco, anoFabricacao) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setDouble(3, veiculo.getPreco());
            ps.setInt(4, veiculo.getAnoFabricacao());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean update(Veiculo veiculo) {
        try {
            String sql = "update veiculo SET marca=?, modelo=?, preco=?, anoFabricacao=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setDouble(3, veiculo.getPreco());
            ps.setInt(4, veiculo.getAnoFabricacao());
            ps.setLong(5, veiculo.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Veiculo buscarVeiculo(Long id) {
        try {
            String sql = "select * from veiculo where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getLong("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setPreco(rs.getDouble("preco"));
                v.setAnoFabricacao(rs.getInt("anoFabricacao"));
                return v;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean remove(Long id) {
        try {
            //comando sql
            String sql = "delete from veiculo where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
