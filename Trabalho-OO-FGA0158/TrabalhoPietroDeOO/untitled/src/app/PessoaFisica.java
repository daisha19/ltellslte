package app;

public class PessoaFisica {

    // Instâncias das Váriaveis

    private String nome, cpf, email;

    public PessoaFisica(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters

    public final String getNome() {
        return nome;
    }

    public final String getCpf() {
        return cpf;
    }

    public final String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "CPF: " + getCpf() + "\n" +
                "E-mail: " + getEmail() + " \n";
    }
}