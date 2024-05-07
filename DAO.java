/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raphael
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection conexao;

    public DAO() throws SQLException {
        this.conexao = ConnectionFactory.obtemConexao();
    }

    public void cadastrarPessoa(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (CPF, RG, nomeCompleto, dataNasc, email, telefone, endereco, bairro, complemento, numero, cidade, estado, pais, senha, tipoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pessoa.getCPF());
            stmt.setString(2, pessoa.getRG());
            stmt.setString(3, pessoa.getNomeCompleto());
            stmt.setString(4, pessoa.getDataNasc());
            stmt.setString(5, pessoa.getEmail());
            stmt.setString(6, pessoa.getTelefone());
            stmt.setString(7, pessoa.getEndereco());
            stmt.setString(8, pessoa.getBairro());
            stmt.setString(9, pessoa.getComplemento());
            stmt.setInt(10, pessoa.getNumero());
            stmt.setString(11, pessoa.getCidade());
            stmt.setString(12, pessoa.getEstado());
            stmt.setString(13, pessoa.getPais());
            stmt.setString(14, pessoa.getSenha());
            stmt.setString(15, pessoa.getTipoUsuario());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int codigo = rs.getInt(1);
                pessoa.setCodigo(codigo);
            }
        }
    }

    public Pessoa buscarPessoaPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return criarPessoa(rs);
            }
        }
        return null;
    }

    private Pessoa criarPessoa(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(rs.getInt("codigo"));
        pessoa.setCPF(rs.getString("CPF"));
        pessoa.setRG(rs.getString("RG"));
        pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
        pessoa.setDataNasc(rs.getString("dataNasc"));
        pessoa.setEmail(rs.getString("email"));
        pessoa.setTelefone(rs.getString("telefone"));
        pessoa.setEndereco(rs.getString("endereco"));
        pessoa.setBairro(rs.getString("bairro"));
        pessoa.setComplemento(rs.getString("complemento"));
        pessoa.setNumero(rs.getInt("numero"));
        pessoa.setCidade(rs.getString("cidade"));
        pessoa.setEstado(rs.getString("estado"));
        pessoa.setPais(rs.getString("pais"));
        pessoa.setSenha(rs.getString("senha"));
        pessoa.setTipoUsuario(rs.getString("tipoUsuario"));
        return pessoa;
    }


    public void atualizarPessoa(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoa SET RG = ?, nomeCompleto = ?, dataNasc = ?, email = ?, telefone = ?, endereco = ?, bairro = ?, complemento = ?, cidade = ?, estado = ?, pais = ?, senha = ?, tipoUsuario = ? WHERE CPF = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getRG());
            stmt.setString(2, pessoa.getNomeCompleto());
            stmt.setString(3, pessoa.getDataNasc());
            stmt.setString(4, pessoa.getEmail());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getEndereco());
            stmt.setString(7, pessoa.getBairro());
            stmt.setString(8, pessoa.getComplemento());
            stmt.setString(9, pessoa.getCidade());
            stmt.setString(10, pessoa.getEstado());
            stmt.setString(11, pessoa.getPais());
            stmt.setString(12, pessoa.getSenha());
            stmt.setString(13, pessoa.getTipoUsuario());
            stmt.setString(14, pessoa.getCPF());
            stmt.executeUpdate();
        }
    }

    public void apagarPessoa(String CPF) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE CPF = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, CPF);
            stmt.executeUpdate();
        }
    }

    public List<Pessoa> listarPessoas() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setCPF(rs.getString("CPF"));
                    pessoa.setRG(rs.getString("RG"));
                    pessoa.setNomeCompleto(rs.getString("nomeCompleto"));
                    pessoa.setDataNasc(rs.getString("dataNasc"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setTelefone(rs.getString("telefone"));
                    pessoa.setEndereco(rs.getString("endereco"));
                    pessoa.setBairro(rs.getString("bairro"));
                    pessoa.setComplemento(rs.getString("complemento"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setEstado(rs.getString("estado"));
                    pessoa.setPais(rs.getString("pais"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setTipoUsuario(rs.getString("tipoUsuario"));
                    pessoas.add(pessoa);
                }
            }
        }
        return pessoas;
    }
}

