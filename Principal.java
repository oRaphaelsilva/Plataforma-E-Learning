import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        DAO pessoaDAO;
        try {
            pessoaDAO = new DAO();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String menu = "Selecione uma opção:\n" +
                "1. Cadastrar Pessoa\n" +
                "2. Atualizar Cadastro\n" +
                "3. Apagar Cadastro\n" +
                "4. Listar Pessoas\n" +
                "5. Sair";

        while (true) {
            int opcao;
            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarPessoa(pessoaDAO);
                    break;
                case 2:
                    atualizarCadastro(pessoaDAO);
                    break;
                case 3:
                    apagarCadastro(pessoaDAO);
                    break;
                case 4:
                    listarPessoas(pessoaDAO);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }
    }

    private static void cadastrarPessoa(DAO pessoaDAO) {
        Pessoa pessoa = preencherDadosPessoa();
        try {
            pessoaDAO.cadastrarPessoa(pessoa);
            JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pessoa: " + e.getMessage());
        }
    }

 private static void atualizarCadastro(DAO pessoaDAO) {
    try {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da pessoa que deseja atualizar:"));
        Pessoa pessoaExistente = pessoaDAO.buscarPessoaPorCodigo(codigo); 
        if (pessoaExistente != null) {
            Pessoa pessoaAtualizada = preencherDadosPessoa();
            pessoaAtualizada.setCodigo(codigo);
            pessoaDAO.atualizarPessoa(pessoaAtualizada);
            JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Pessoa com código " + codigo + " não encontrada.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, insira um código válido.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar cadastro: " + e.getMessage());
    }
}
    private static void apagarCadastro(DAO pessoaDAO) {
        String CPF = JOptionPane.showInputDialog("Digite o CPF da pessoa que deseja apagar:");
        try {
            pessoaDAO.apagarPessoa(CPF);
            JOptionPane.showMessageDialog(null, "Cadastro apagado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar cadastro: " + e.getMessage());
        }
    }

    private static void listarPessoas(DAO pessoaDAO) {
        try {
            List<Pessoa> pessoas = pessoaDAO.listarPessoas();
            StringBuilder lista = new StringBuilder("Lista de Pessoas:\n");
            for (Pessoa pessoa : pessoas) {
                lista.append(pessoa.getNomeCompleto()).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pessoas: " + e.getMessage());
        }
    }
    private static Pessoa preencherDadosPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCPF(JOptionPane.showInputDialog("CPF:"));
        pessoa.setRG(JOptionPane.showInputDialog("RG:"));
        pessoa.setNomeCompleto(JOptionPane.showInputDialog("Nome Completo:"));
        pessoa.setDataNasc(JOptionPane.showInputDialog("Data de Nascimento:"));
        pessoa.setEmail(JOptionPane.showInputDialog("E-mail:"));
        pessoa.setTelefone(JOptionPane.showInputDialog("Telefone:"));
        pessoa.setEndereco(JOptionPane.showInputDialog("Endereço:"));
        pessoa.setBairro(JOptionPane.showInputDialog("Bairro:"));
        pessoa.setComplemento(JOptionPane.showInputDialog("Complemento:"));
        pessoa.setCidade(JOptionPane.showInputDialog("Cidade:"));
        pessoa.setEstado(JOptionPane.showInputDialog("Estado:"));
        pessoa.setPais(JOptionPane.showInputDialog("País:"));
        pessoa.setSenha(JOptionPane.showInputDialog("Senha:"));
        pessoa.setTipoUsuario(JOptionPane.showInputDialog("Tipo de Usuário:"));
        return pessoa;
    }
}
