package view;

import javax.swing.JOptionPane;
import app.Professor;
import cadastros.CadastroProfessor;
import exceptions.CampoEmBrancoException;

public class MenuProfessor {

    // Coleta dados do novo professor e cria uma instância de Professor
    public static Professor dadosNovoProfessor() {
        String nome = lerNome();
        String cpf = lerCPF();
        String email = lerEmail();
        String areaFormacao = lerAreaFormacao();
        String matriculaFUB = lerMatriculaFUB();

        try {
            return new Professor(nome, cpf, email, areaFormacao, matriculaFUB);
        } catch (CampoEmBrancoException e) {
            // Mostra mensagem de erro se algum campo estiver em branco
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Coleta o nome do professor
    private static String lerNome() {
        return JOptionPane.showInputDialog("Informe o nome do professor: ");
    }

    // Coleta o CPF do professor
    private static String lerCPF() {
        return JOptionPane.showInputDialog("Informe o CPF do professor: ");
    }

    // Coleta o email do professor
    private static String lerEmail() {
        return JOptionPane.showInputDialog("Informe o email do professor: ");
    }

    // Coleta a área de formação do professor
    private static String lerAreaFormacao() {
        return JOptionPane.showInputDialog("Informe a área de formação do professor: ");
    }

    // Coleta a matrícula FUB do professor
    private static String lerMatriculaFUB() {
        return JOptionPane.showInputDialog("Informe a matrícula FUB do professor: ");
    }

    // Exibe o menu para gerenciamento de professores
    public static void menuProfessor(CadastroProfessor cadProfessor) {
        String txt = "Informe a opção desejada \n"
                + "1 - Cadastrar professor\n"
                + "2 - Pesquisar professor\n"
                + "3 - Atualizar professor\n"
                + "4 - Remover professor\n"
                + "0 - Voltar para menu anterior";

        int opcao = -1;
        do {
            // Obtém a opção escolhida pelo usuário
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    // Cadastra um novo professor
                    Professor novoProfessor = dadosNovoProfessor();
                    if (novoProfessor != null) {
                        try {
                            cadProfessor.cadastrarProfessor(novoProfessor);
                            JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso.");
                        } catch (CampoEmBrancoException e) {
                            // Mostra mensagem de erro se algum campo estiver em branco
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 2:
                    // Pesquisa um professor pelo código da matrícula FUB
                    String matriculaFUB = lerMatriculaFUB();
                    Professor professor = cadProfessor.pesquisarProfessor(matriculaFUB);
                    if (professor != null) {
                        JOptionPane.showMessageDialog(null, professor.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                    }
                    break;

                case 3:
                    // Atualiza um professor existente
                    matriculaFUB = lerMatriculaFUB();
                    Professor novoCadastro = dadosNovoProfessor();
                    if (novoCadastro != null) {
                        try {
                            boolean atualizado = cadProfessor.atualizarProfessor(matriculaFUB, novoCadastro);
                            if (atualizado) {
                                JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                            }
                        } catch (CampoEmBrancoException e) {
                            // Mostra mensagem de erro se algum campo estiver em branco
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 4:
                    // Remove um professor pelo código da matrícula FUB
                    matriculaFUB = lerMatriculaFUB();
                    Professor remover = cadProfessor.pesquisarProfessor(matriculaFUB);
                    boolean removido = cadProfessor.removerProfessor(remover);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Professor removido do cadastro.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                    }
                    break;

                default:
                    break;
            }
        } while (opcao != 0); // Continua executando até que o usuário escolha sair (opção 0)
    }
}
