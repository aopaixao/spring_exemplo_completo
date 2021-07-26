package com.residencia.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.List;

@Entity
public class Cliente {
    private Integer clienteId;
    private String email;
    private String username;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private Calendar dataNascimento;
    private Endereco enderecoByEnderecoId;
    private List<Pedido> pedidosByClienteId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @Email
    @NotBlank(message = "O campo 'e-mail' não pode ser nulo ou vazio.")
    @Size(max = 50, message = "O tamanho do 'e-mail' não pode conter mais de 50 caracteres.")
    @Size(min = 11, message = "O tamanho do 'e-mail' não pode conter menos de 11 caracteres.")
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "O campo 'username' não pode ser nulo ou vazio.")
    @Size(min = 2, message = "O tamanho do 'username' não pode conter menos de 2 caracteres.")
    @Size(max = 15, message = "O tamanho do 'username' não pode conter mais de 15 caracteres.")
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "O campo 'senha' não pode ser nulo ou vazio.")
    @Size(min = 8, message = "A 'senha' deve ter mais de 8 caracteres.")
    @Column(name = "senha")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotBlank(message = "O campo 'cpf' não pode ser nulo ou vazio.")
    @Size(min = 11, max = 11, message = "O campo 'cpf' deve conter 11 caracteres.")
    @Column(name = "cpf")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "telefone")
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @NotNull(message = "O campo 'data nascimento' não pode ser nulo ou vazio.")
    @Column(name = "data_nascimento")
    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "endereco_id")
    public Endereco getEnderecoByEnderecoId() {
        return enderecoByEnderecoId;
    }

    public void setEnderecoByEnderecoId(Endereco enderecoByEnderecoId) {
        this.enderecoByEnderecoId = enderecoByEnderecoId;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "clienteByClienteId")
    public List<Pedido> getPedidosByClienteId() {
        return pedidosByClienteId;
    }

    public void setPedidosByClienteId(List<Pedido> pedidosByClienteId) {
        this.pedidosByClienteId = pedidosByClienteId;
    }


}
