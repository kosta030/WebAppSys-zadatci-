package ObjektAnzahlundBeans;


import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private int count = 0;

    /**
     * Erhöht den Zähler bei jedem Aufruf um 1 und gibt den neuen Wert zurück.
     */
    public int count() {
        count++;
        return count;
    }

    // Für Diagnosezwecke
    public int getCurrent() {
        return count;
    }
}
