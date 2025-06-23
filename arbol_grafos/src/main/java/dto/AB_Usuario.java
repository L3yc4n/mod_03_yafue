
package dto;
public class AB_Usuario {
    private String nombre;
    private String telefono;
   

    public AB_Usuario(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public String getTelefono() { 
        return telefono; 
    }
    
 

    public void setTelefono(String nuevoTelefono) {
        this.telefono = nuevoTelefono;
    }
    
  

    @Override
    public String toString() {
        return "Nombre: " + nombre + 
               ", Tel: " + telefono ; 
              
    }
}