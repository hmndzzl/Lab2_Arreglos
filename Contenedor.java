public class Contenedor {
    //Atributos 
    private String identificador;
    private String producto;
    private double peso;
    private String destino;

    //Constructor 
    public Contenedor(String identificador, String producto, double peso, String destino) {
        this.identificador = identificador;
        this.producto = producto;
        this.peso = peso;
        this.destino = destino;
    }

    // Getters y Setters
    public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getProducto() {
		return this.producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

    //Methods
    public String infoContenedor(){ //Obtener la información del contenedor
        return "ID: " + identificador + ", Producto: " + producto + ", Peso: " + peso + "kg, Destino: " + destino;
    }

}

