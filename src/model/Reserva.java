package model;

import java.time.LocalDate;

public class Reserva {
    private int id = 0;
    private Livro livro = new Livro();
    private Usuario usuario = new Usuario();
    private LocalDate dataReserva = LocalDate.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Reserva(Livro livro, Usuario usuario, LocalDate dataReserva) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataReserva = dataReserva;
    }

    public Reserva() {
    }

    @Override
    public String toString() {
        return "<--------->\nCódigo: " + getId() + "\nLivro: " + getLivro().getTitulo() + "\nUsuario: " + getUsuario().getNome() + "\nData da retirada: " + getDataReserva();
    }
}
