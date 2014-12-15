package br.edu.utfpr.cp.web.sigecom.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seqIDEq", sequenceName = "seqIDEq")
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIDEq")
	private Long id;
	@Column(length = 20)
	private String nome;
	@Column(length = 20)
	private String tipo;
	@Column(length = 20)
	private String marca;
	@Column(length = 20)
	private String modelo;

	@ManyToMany(mappedBy = "equipamento", fetch = FetchType.EAGER)
	private List<Operadores> operadores;

	@OneToMany(mappedBy = "idEquipamento")
	private List<Manutencao> manutencao;

	public List<Manutencao> getManutencao() {
		return manutencao;
	}

	public void setManutencao(List<Manutencao> manutencao) {
		this.manutencao = manutencao;
	}

	public List<Operadores> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<Operadores> operadores) {
		this.operadores = operadores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((manutencao == null) ? 0 : manutencao.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((operadores == null) ? 0 : operadores.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manutencao == null) {
			if (other.manutencao != null)
				return false;
		} else if (!manutencao.equals(other.manutencao))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (operadores == null) {
			if (other.operadores != null)
				return false;
		} else if (!operadores.equals(other.operadores))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
