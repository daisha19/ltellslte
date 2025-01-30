package view;

import javax.swing.JOptionPane;
import app.Aluno;
import cadastros.CadastroAluno;
import exceptions.CampoEmBrancoException;

public class MenuAluno {

    // Coleta dados do usuário e cria um novo objeto Aluno
    public static Aluno dadosNovoAluno() {
        String nome = lerNome();
        String cpf = lerCPF();
        String email = lerEmail();
        String matricula = lerMatricula();
        String curso = lerCurso();

        try {
            // Tenta criar um novo aluno e retorna o objeto
            return new Aluno(nome, cpf, email, matricula, curso);
        } catch (CampoEmBrancoException e) {
            // Exibe mensagem de erro se algum campo estiver em branco
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Solicita o nome do aluno ao usuário
    private static String lerNome() {
        return JOptionPane.showInputDialog("Informe o nome do aluno: ");
    }

    // Solicita o CPF do aluno ao usuário
    private static String lerCPF() {
        return JOptionPane.showInputDialog("Informe o CPF do aluno: ");
    }

    // Solicita o email do aluno ao usuário
    private static String lerEmail() {
        return JOptionPane.showInputDialog("Informe o email do aluno: ");
    }

    // Solicita a matrícula do aluno ao usuário
    private static String lerMatricula() {
        return JOptionPane.showInputDialog("Informe a matrícula do aluno: ");
    }

    // Solicita o curso do aluno ao usuário
    private static String lerCurso() {
        return JOptionPane.showInputDialog("Informe o curso do aluno: ");
    }

    // Exibe o menu de opções e realiza ações baseadas na escolha do usuário
    public static void menuAluno(CadastroAluno cadAluno) {
        String txt = "Informe a opção desejada \n"
                + "1 - Cadastrar aluno\n"
                + "2 - Pesquisar aluno\n"
                + "3 - Atualizar aluno\n"
                + "4 - Remover aluno\n"
                + "0 - Voltar para menu anterior";

        int opcao = -1;
        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    // Cadastra um novo aluno
                    Aluno novoAluno = dadosNovoAluno();
                    if (novoAluno != null) {
                        try {
                            cadAluno.cadastrarAluno(novoAluno);
                            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");
                        } catch (CampoEmBrancoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 2:
                    // Pesquisa um aluno pelo número da matrícula
                    String matricula = lerMatricula();
                    Aluno aluno = cadAluno.pesquisarAluno(matricula);
                    if (aluno != null) {
                        JOptionPane.showMessageDialog(null, aluno.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                    }
                    break;

                case 3:
                    // Atualiza os dados de um aluno existente
                    matricula = lerMatricula();
                    Aluno novoCadastro = dadosNovoAluno();
                    if (novoCadastro != null) {
                        try {
                            boolean atualizado = cadAluno.atualizarAluno(matricula, novoCadastro);
                            if (atualizado) {
                                JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                            }
                        } catch (CampoEmBrancoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 4:
                    // Remove um aluno pelo número da matrícula
                    matricula = lerMatricula();
                    Aluno remover = cadAluno.pesquisarAluno(matricula);
                    boolean removido = cadAluno.removerAluno(remover);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Aluno removido do cadastro.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                    }
                    break;

                default:
                    // Para opções inválidas, apenas sai do switch sem fazer nada
                    break;
            }
        } while (opcao != 0); // Continua executando até que o usuário escolha sair (opção 0)
    }
}
