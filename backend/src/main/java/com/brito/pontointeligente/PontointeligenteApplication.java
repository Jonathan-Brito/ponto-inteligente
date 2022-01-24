package com.brito.pontointeligente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.brito.pontointeligente.api.entities.Empresa;
import com.brito.pontointeligente.api.repositories.EmpresaRepository;
import com.brito.pontointeligente.api.utils.SenhaUtils;

@SpringBootApplication
public class PontointeligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontointeligenteApplication.class, args);
	}
	
	@Autowired
	private EmpresaRepository empresaRepositoy;
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("JonathanJunior & Associados");
			empresa.setCnpj("74645215000104");
			
			this.empresaRepositoy.save(empresa);
			
			List<Empresa> empresas = empresaRepositoy.findAll();
			empresas.forEach(System.out::println);
			
			//Empresa empresaDB = empresaRepositoy.findOne(1L);
			//System.out.println("Empresa por ID: " + empresaDB);
			
			//empresaDB.setRazaoSocial("Jonathan Brito office");
			
			//this.empresaRepositoy.save(empresaDB);
			
			Empresa empresaCnpj = empresaRepositoy.findByCnpj("74645215000104");
			System.out.println("Empresa por CNPJ: " + empresaCnpj);
			
			this.empresaRepositoy.deleteById(1L);
			empresas = empresaRepositoy.findAll();
			System.out.println("Empresas : " + empresas.size());
			
			String senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded: " + senhaEncoded);
			
			senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: " + senhaEncoded);
			
			System.out.println("Senha válida: " + SenhaUtils.senhaValida("123456", 
					senhaEncoded));
		};
	}

}
