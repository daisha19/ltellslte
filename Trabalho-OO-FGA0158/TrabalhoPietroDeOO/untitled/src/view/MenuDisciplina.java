package view;

import javax.swing.JOptionPane;
import app.Disciplina;
import cadastros.CadastroDisciplina;
import exceptions.CampoEmBrancoException;

public class MenuDisciplina {

    // Coleta dados do usuário e cria um novo objeto Disciplina
    public static Disciplina dadosNovaDisciplina() {
        String codigo = lerCodigo();
        String nome = lerNome();
        int cargaHoraria = lerCargaHoraria();
        try {
            // Tenta criar uma nova disciplina e retorna o objeto
            return new Disciplina(codigo, nome, cargaHoraria);
        } catch (CampoEmBrancoException e) {
            // Exibe mensagem de erro se algum campo estiver em branco
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Solicita o código da disciplina ao usuário
    private static String lerCodigo() {
        return JOptionPane.showInputDialog("Informe o código da disciplina: ");
    }

    // Solicita o nome da disciplina ao usuário
    private static String lerNome() {
        return JOptionPane.showInputDialog("Informe o nome da disciplina: ");
    }

    // Solicita a carga horária da disciplina ao usuário e converte para inteiro
    private static int lerCargaHoraria() {
        String cargaHorariaStr = JOptionPane.showInputDialog("Informe a carga horária da disciplina (em horas): ");
        return Integer.parseInt(cargaHorariaStr);
    }

    // Exibe o menu de opções e realiza ações baseadas na escolha do usuário
    public static void menuDisciplina(CadastroDisciplina cadDisciplina) {
        String txt = "Informe a opção desejada \n"
                + "1 - Cadastrar disciplina\n"
                + "2 - Pesquisar disciplina\n"
                + "3 - Atualizar disciplina\n"
                + "4 - Remover disciplina\n"
                + "0 - Voltar para menu anterior";

        int opcao = -1;
        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    // Cadastra uma nova disciplina
                    Disciplina novaDisciplina = dadosNovaDisciplina();
                    if (novaDisciplina != null) {
                        try {
                            cadDisciplina.cadastrarDisciplina(novaDisciplina);
                            JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso.");
                        } catch (CampoEmBrancoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 2:
                    // Pesquisa uma disciplina pelo código
                    String codigo = lerCodigo();
                    Disciplina disciplina = cadDisciplina.pesquisarDisciplina(codigo);
                    if (disciplina != null) {
                        JOptionPane.showMessageDialog(null, disciplina.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
                    }
                    break;

                case 3:
                    // Atualiza os dados de uma disciplina existente
                    codigo = lerCodigo();
                    Disciplina novaDisc = dadosNovaDisciplina();
                    if (novaDisc != null) {
                        try {
                            boolean atualizado = cadDisciplina.atualizarDisciplina(codigo, novaDisc);
                            if (atualizado) {
                                JOptionPane.showMessageDialog(null, "Disciplina atualizada.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
                            }
                        } catch (CampoEmBrancoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 4:
                    // Remove uma disciplina pelo código
                    codigo = lerCodigo();
                    Disciplina remover = cadDisciplina.pesquisarDisciplina(codigo);
                    boolean removido = cadDisciplina.removerDisciplina(remover);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Disciplina removida do cadastro.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
                    }
                    break;

                default:
                    // Para opções inválidas, apenas sai do switch sem fazer nada
                    break;
            }
        } while (opcao != 0); // Continua executando até que o usuário escolha sair (opção 0)
    }
}
