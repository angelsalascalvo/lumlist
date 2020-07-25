import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String args[]) {
		ArrayList<Integer> ListaAngel = new ArrayList<Integer>();
		ListaAngel.add(1);
		ListaAngel.add(4);
		ListaAngel.add(3);
		ListaAngel.add(2);
		ListaAngel.add(5);
		ListaAngel.add(2, 6);
		ListaAngel.remove(5);

		for (int i = 0; i < ListaAngel.size(); i++) {
			System.out.print(ListaAngel.get(i) + ", ");
		}
		// ListaAngel.clear();
		if (ListaAngel.isEmpty() == true) {
			System.out.println("\n LISTA VACIA");
		} else {
			System.out.println("\n Ultimo valor = " + ListaAngel.get(ListaAngel.size() - 1));
		}

		System.out.println(ListaAngel.indexOf(8)); // Busca la posicion del valor

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el nombre");
		String nombrescanner = teclado.nextLine();
		System.out.println("Introduce el sexo");
		String sexoescanner = teclado.nextLine();
		System.out.println("Introduce el telefono");
		int telefonoescanner = Integer.parseInt(teclado.nextLine());

		ArrayList<Contactos> ListaObjeto = new ArrayList<Contactos>();
		ListaObjeto.add(new Contactos("Pepe", "masc", 667548903));
		ListaObjeto.add(new Contactos("Mari", "fem", 663478302));
		ListaObjeto.add(new Contactos("Juan", "masc", 6426798));
		ListaObjeto.add(new Contactos("Antonio", "masc", 663736773));
		ListaObjeto.add(new Contactos(nombrescanner, sexoescanner, telefonoescanner));

		for (int i = 0; i < ListaObjeto.size(); i++) {
			System.out.println(ListaObjeto.get(i).getnombre() + "," + ListaObjeto.get(i).getsexo() + ","
					+ ListaObjeto.get(i).gettelefono());
		}
		System.out.println("Introduzca nombre a buscar");
		String numeronombre = teclado.nextLine().trim();
		System.out.println("\n" + BusquedaTelefono(ListaObjeto, numeronombre) + "\n");
		

	}

	public static int BusquedaTelefono(ArrayList<Contactos> telefono, String nombre) {
		System.out.println(nombre);
		for (int i = 0; i < telefono.size(); i++) {
			if (nombre.equals(telefono.get(i).getnombre())) {
				return telefono.get(i).gettelefono();
			}
			System.out.println(telefono.get(i).getnombre());
		}
		
		return -1;
	}
}