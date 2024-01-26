package com.bosonit.formacion.block16springcloud;

import com.bosonit.formacion.block16springcloud.application.ClienteServicio;
import com.bosonit.formacion.block16springcloud.application.ClienteServicioImpl;
import com.bosonit.formacion.block16springcloud.application.ViajeServicio;
import com.bosonit.formacion.block16springcloud.application.ViajeServicioImpl;
import com.bosonit.formacion.block16springcloud.controller.dto.ClienteDto;
import com.bosonit.formacion.block16springcloud.controller.dto.ViajeDto;
import com.bosonit.formacion.block16springcloud.domain.Cliente;
import com.bosonit.formacion.block16springcloud.domain.Viaje;
import com.bosonit.formacion.block16springcloud.pool.MyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


import java.sql.*;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class Block16SpringCloudApplication {


	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Block16SpringCloudApplication.class, args);
		//testServicio();
	}

	/*public static void testServicio(){

		ClienteServicio servicioCliente = ClienteServicioImpl.getInstance();
		ViajeServicio servicioViaje =ViajeServicioImpl.getInstance();


		Cliente cliente = new Cliente( "Eduardo", "Jimeno", 43, "info@hangar-gym.com", 676685508);
		Cliente cliente2 = new Cliente( "Juan", "Santos", 43, "info@hangar-gym.com", 676685508);
		Viaje viaje = new Viaje("Madrid", "Barcelona", new Date(2024, 10, 1),  new Date(2024, 10, 1), true);
		try{
			servicioCliente.addCliente(cliente);
			servicioCliente.addCliente(cliente2);
			servicioViaje.addViaje(viaje);

			List<ClienteDto> clientes = servicioCliente.getAllClientes(1,4);
			if (clientes.isEmpty())
				System.out.println("No hay clientes");
			else
				clientes.forEach(System.out::println);

			ClienteDto c1 = servicioCliente.getClienteById(1);
			ViajeDto v1 = servicioViaje.getViajeById(1);
			//if-else para evitar nullpointerexception
			System.out.println("\n" + c1 + "\n");
			System.out.println("\n" + v1 + "\n");

			c1.setEdad(44);
			servicioCliente.updateCliente(c1);


			c1 = servicioCliente.getClienteById(1);
			System.out.println("\n" + c1 + "\n");

			servicioCliente.deleteCliente(2);
			clientes = servicioCliente.getAllClientes(1,4);
			if (clientes.isEmpty())
				System.out.println("No hay clientes");
			else
				clientes.forEach(System.out::println);


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }*/

	/*public static void testPool() throws SQLException {

		try (Connection conn = MyDataSource.getConnection()){
			DatabaseMetaData metaData = conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet tables = metaData.getTables(null, null, "%", types);
			while (tables.next()){
				System.out.println(tables.getString("TABLE_NAME"));
			}
		}*/
	}

