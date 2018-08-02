package org.postgre.maven.Eli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  // faz o JPA saiba que esta deve ser manipulada por ele
@Table(name = "teste") //define que esta classe possui uma tabela no banco com esse nome(teste)
public class Teste {
	
	@Id //o ID é uma chave primaria sendo necessario a anotação como @Id
	private int id;
	
	@Column // significa que essas propriedades possuem uma coluna no banco com esse nome
	private String nome;
	
	@Column //Relacioan o atributo do objeto com o campo no banco
	private int idade;
	
	
	public int getId(){
		return id;
	}
	
	public void setId (int id){
		this.id = id;
	}
	
	public String getNome(){
		return nome;
	}
	public void setNome (String nome){
		this.nome=nome;
		
	}
	public int getIdade (){
		return idade;
	}
	public void setIdade (int idade){
		this.idade =idade;
	}
	
	
}