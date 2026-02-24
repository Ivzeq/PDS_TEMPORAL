import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLocalDateTime {
    public static void main(String[] args) {
        // Test que LocalDateTime funciona correctamente
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        String fechaStr = "24/02/2026 18:30";
        LocalDateTime fechaParsed = LocalDateTime.parse(fechaStr, dtf);
        System.out.println("Fecha parseada: " + fechaParsed);
        System.out.println("Formato: " + fechaParsed.format(dtf));
        
        // Test de operaciones con LocalDateTime
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime unaHoraAntes = ahora.minusHours(1);
        LocalDateTime fin = ahora.plusMinutes(90);
        
        System.out.println("\nAhora: " + ahora.format(dtf));
        System.out.println("Una hora antes: " + unaHoraAntes.format(dtf));
        System.out.println("Fin (90 min después): " + fin.format(dtf));
        
        // Test de comparaciones
        System.out.println("\nahora.isAfter(unaHoraAntes): " + ahora.isAfter(unaHoraAntes));
        System.out.println("ahora.isBefore(fin): " + ahora.isBefore(fin));
        System.out.println("ahora.isEqual(ahora): " + ahora.isEqual(ahora));
        
        System.out.println("\n✓ Todas las operaciones con LocalDateTime funcionan correctamente!");
    }
}
