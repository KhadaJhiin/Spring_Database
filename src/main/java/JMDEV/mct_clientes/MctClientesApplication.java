package JMDEV.mct_clientes;
import JMDEV.mct_clientes.Model.Cliente;
import JMDEV.mct_clientes.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class MctClientesApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(MctClientesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando aplicacion");
		SpringApplication.run(MctClientesApplication.class, args);
		logger.info("Finalizando aplicacion");
	}

	@Override
	public void run(String... args) throws Exception {

		clientesMercatodoApp();
	}

	private void clientesMercatodoApp(){
		boolean salir = false;
		Scanner consola = new Scanner(System.in);
		while (!salir){
			int opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
	}

	private int mostrarMenu(Scanner consola){

		logger.info("""
					\n**** Aplicacion clienter mercartodo ****
					
					1. Listar clientes
					2. Buscar cliente
					3. Agregar cliente
					4. Modificar cliente
					5. Eliminar cliente
					6. Salir
					Elige una opcion\s
					""");

		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		boolean salir = false;

		switch (opcion){
			case 1:
				logger.info(nl + "---Listado de clientes---"+nl);
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString()+nl));
				break;

			case 2:
				logger.info(nl+"---Buscar cliente por ID---"+nl);
				logger.info("Ingrese el ID del cliente: "+nl);
				int idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if (cliente != null){
					logger.info("Cliente encontrado: " + cliente + nl);
				}else{
					logger.info("Cliente NO encontrado"+nl);
				}
				break;
		}
		return salir;
	}

}
