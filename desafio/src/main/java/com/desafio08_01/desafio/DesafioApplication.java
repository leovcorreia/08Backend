package com.desafio08_01.desafio;

import com.desafio08_01.entities.Order;
import com.desafio08_01.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan({"com.desafio08_01"})
public class DesafioApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {

		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("Código do pedido: ");
		Integer code = sc.nextInt();
		System.out.println("Valor básico: ");
		Double basic = sc.nextDouble();
		System.out.println("Porcentagem de desconto: ");
		Double discount = sc.nextDouble();

		Order order = new Order(code, basic, discount);

		System.out.printf("Pedido código %d \n", order.getCode());
		System.out.printf("Valor total pedido: R$ %.2f", orderService.total(order));

	}
}
