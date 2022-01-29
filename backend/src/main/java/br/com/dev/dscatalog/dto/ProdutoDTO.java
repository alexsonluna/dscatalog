package br.com.dev.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.dev.dscatalog.entidades.Categoria;
import br.com.dev.dscatalog.entidades.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String imgUrl;
	private Instant data;

	private List<CategoriaDTO> lstCategoriaDTO = new ArrayList<>();

	public ProdutoDTO() {

	}

	public ProdutoDTO(Long id, String nome, String descricao, Double preco, String imgUrl, Instant data) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imgUrl = imgUrl;
		this.data = data;
	}

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.imgUrl = produto.getImgUrl();
		this.data = produto.getData();
	}

	public ProdutoDTO(Produto produto, Set<Categoria> categorias) {

		this(produto);
		categorias.forEach(cat -> this.lstCategoriaDTO.add(new CategoriaDTO(cat)));
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public List<CategoriaDTO> getLstCategoriaDTO() {
		return lstCategoriaDTO;
	}

	public void setLstCategoriaDTO(List<CategoriaDTO> lstCategoriaDTO) {
		this.lstCategoriaDTO = lstCategoriaDTO;
	}

}
