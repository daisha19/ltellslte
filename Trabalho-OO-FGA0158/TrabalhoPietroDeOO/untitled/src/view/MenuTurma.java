package view;

import javax.swing.JOptionPane;
import app.Aluno;
import app.Turma;
import app.Professor;
import app.Disciplina;
import cadastros.CadastroTurma;
import cadastros.CadastroDisciplina;
import cadastros.CadastroProfessor;
import cadastros.CadastroAluno;
import exceptions.CampoEmBrancoException;
import exceptions.DisciplinaNaoAtribuidaException;
import exceptions.ProfessorNaoAtribuidoException;

import java.util.ArrayList;
import java.util.List;

public class MenuTurma {

    // Cria uma nova turma com base nas informações fornecidas pelo usuário e cadastros disponíveis
    public static Turma dadosNovaTurma(CadastroProfessor cadProfessor, CadastroDisciplina cadDisciplina, CadastroAluno cadAluno) {
        // Coleta dados para criar uma nova turma
        String codigo = lerCodigo();
        String nome = lerNome();
        Professor professor = lerProfessor(cadProfessor);
        List<Disciplina> disciplinas = lerDisciplinas(cadDisciplina);
        List<Aluno> alunos = lerAlunos(cadAluno);

        try {
            // Cria a nova turma e adiciona disciplinas e alunos
            Turma turma = new Turma(codigo, nome, professor);
            for (Disciplina d : disciplinas) {
                turma.adicionarDisciplina(d);
            }
            for (Aluno a : alunos) {
                turma.adicionarAluno(a);
            }
            return turma;
        } catch (CampoEmBrancoException | ProfessorNaoAtribuidoException | DisciplinaNaoAtribuidaException e) {
            // Mostra uma mensagem de erro se ocorrer uma exceção
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Coleta o código da turma a partir da entrada do usuário
    private static String lerCodigo() {
        return JOptionPane.showInputDialog("Informe o código da turma: ");
    }

    // Coleta o nome da turma a partir da entrada do usuário
    private static String lerNome() {
        return JOptionPane.showInputDialog("Informe o nome da turma: ");
    }

    // Coleta o professor associado à turma a partir da matrícula fornecida
    private static Professor lerProfessor(CadastroProfessor cadProfessor) {
        String matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor: ");
        Professor professor = cadProfessor.pesquisarProfessor(matriculaFUB);
        if (professor == null) {
            JOptionPane.showMessageDialog(null, "Professor não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return professor;
    }

    // Coleta uma lista de disciplinas a partir dos códigos fornecidos
    private static List<Disciplina> lerDisciplinas(CadastroDisciplina cadDisciplina) {
        List<Disciplina> disciplinas = new ArrayList<>();
        String resposta;
        do {
            String codigoDisciplina = JOptionPane.showInputDialog("Informe o código da disciplina a ser adicionada (ou 'fim' para terminar): ");
            if ("fim".equalsIgnoreCase(codigoDisciplina)) {
                break;
            }
            Disciplina disciplina = cadDisciplina.pesquisarDisciplina(codigoDisciplina);
            if (disciplina != null) {
                disciplinas.add(disciplina);
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
            }
        } while (true);
        return disciplinas;
    }

    // Coleta uma lista de alunos a partir das matrículas fornecidas
    private static List<Aluno> lerAlunos(CadastroAluno cadAluno) {
        List<Aluno> alunos = new ArrayList<>();
        String resposta;
        do {
            String matriculaAluno = JOptionPane.showInputDialog("Informe a matrícula do aluno a ser adicionada (ou 'fim' para terminar): ");
            if ("fim".equalsIgnoreCase(matriculaAluno)) {
                break;
            }
            Aluno aluno = cadAluno.pesquisarAluno(matriculaAluno);
            if (aluno != null) {
                alunos.add(aluno);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            }
        } while (true);
        return alunos;
    }

    // Exibe o menu de opções para o gerenciamento de turmas
    public static void menuTurma(CadastroTurma cadTurma, CadastroProfessor cadProfessor, CadastroDisciplina cadDisciplina, CadastroAluno cadAluno) {
        String txt = "Informe a opção desejada \n"
                + "1 - Cadastrar turma\n"
                + "2 - Pesquisar turma\n"
                + "3 - Atualizar turma\n"
                + "4 - Remover turma\n"
                + "5 - Imprimir lista de presença\n"
                + "0 - Voltar para menu anterior";

        int opcao = -1;
        do {
            // Obtém a opção escolhida pelo usuário
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            // Processa a opção escolhida
            switch (opcao) {
                case 1:
                    // Cadastra uma nova turma
                    Turma novaTurma = dadosNovaTurma(cadProfessor, cadDisciplina, cadAluno);
                    if (novaTurma != null) {
                        try {
                            cadTurma.cadastrarTurma(novaTurma);
                            JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso.");
                        } catch (CampoEmBrancoException | DisciplinaNaoAtribuidaException | ProfessorNaoAtribuidoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 2:
                    // Pesquisa uma turma pelo código
                    String codigo = lerCodigo();
                    Turma turma = cadTurma.pesquisarTurma(codigo);
                    if (turma != null) {
                        JOptionPane.showMessageDialog(null, turma.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                    }
                    break;

                case 3:
                    // Atualiza uma turma existente
                    codigo = lerCodigo();
                    Turma novaCadastro = dadosNovaTurma(cadProfessor, cadDisciplina, cadAluno);
                    if (novaCadastro != null) {
                        try {
                            boolean atualizado = cadTurma.atualizarTurma(codigo, novaCadastro);
                            if (atualizado) {
                                JOptionPane.showMessageDialog(null, "Turma atualizada com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                            }
                        } catch (CampoEmBrancoException | DisciplinaNaoAtribuidaException | ProfessorNaoAtribuidoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 4:
                    // Remove uma turma pelo código
                    codigo = lerCodigo();
                    Turma turmaRemover = cadTurma.pesquisarTurma(codigo);
                    if (turmaRemover != null) {
                        boolean removido = cadTurma.removerTurma(turmaRemover);
                        if (removido) {
                            JOptionPane.showMessageDialog(null, "Turma removida do cadastro.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                    }
                    break;

                case 5:
                    // Imprime a lista de presença de uma turma
                    codigo = lerCodigo();
                    Turma turmaImprimir = cadTurma.pesquisarTurma(codigo);
                    if (turmaImprimir != null) {
                        turmaImprimir.imprimirListaPresenca();
                    } else {
                        JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                    }
                    break;

                default:
                    break;
            }
        } while (opcao != 0); // Continua executando até que o usuário escolha sair (opção 0)
    }
}
