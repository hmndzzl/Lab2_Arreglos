public class Seccion {
    //Atributos
    private String nombre;
    private double pesoMaximo;
    private Contenedor[][] contenedores;
    private double pesoTotal;

    //Constructor
    public Seccion(String nombre, double pesoMaximo) {
        this.nombre = nombre;
        this.pesoMaximo = pesoMaximo;
        this.contenedores = new Contenedor[10][5];  // 10 filas x 5 columnas
        this.pesoTotal = 0;
    }

    //Setters & getters
    public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPesoMaximo() {
		return this.pesoMaximo;
	}

	public void setPesoMaximo(double pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public Contenedor[][] getContenedores() {
		return this.contenedores;
	}

	public void setContenedores(Contenedor[][] contenedores) {
		this.contenedores = contenedores;
	}

	public double getPesoTotal() {
		return this.pesoTotal;
	}

	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

    //Methods 
    public boolean agregarContenedor(Contenedor contenedor) { //Método para agregar contenedor a la sección
        if (pesoTotal + contenedor.getPeso() > pesoMaximo) { //Verifica que no se exeda el peso máximo de la sección
            System.out.println("No se puede agregar: sobrepeso en la sección.");
            return false;
        }
        for (int i = 0; i < contenedores.length; i++) { //Agrega el contenedor en un espacio vacío.
            for (int j = 0; j < contenedores[i].length; j++) {
                if (contenedores[i][j] == null) {
                    contenedores[i][j] = contenedor;
                    pesoTotal += contenedor.getPeso(); //Suma el peso del contenedor al peso tortal
                    return true;
                }
            }
        }
        System.out.println("No hay espacio disponible en la sección.");
        return false;
    }

    public String listarContenedores() { //Listar Contenedores
        StringBuilder lista = new StringBuilder();
        for (Contenedor[] fila : contenedores) { //Filas 
            for (Contenedor cont : fila) { //Columnas
                if (cont != null) {
                    lista.append(cont.infoContenedor()).append("\n"); //Agregar contenedor a la lista
                }
            }
        }
        return lista.toString();
    }

}
