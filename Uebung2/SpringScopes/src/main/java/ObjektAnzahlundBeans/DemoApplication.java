package ObjektAnzahlundBeans;

import java.util.HashMap;
import java.util.Map;

public class DemoApplication {

    private final Map<String, Object> instances = new HashMap<>();

    public static void main(String[] args) {
        new DemoApplication().run();
    }

    public void run() {
        try {
            Object first = create("ObjektAnzahlundBeans.FirstService");
            Object second = create("ObjektAnzahlundBeans.SecondService");
            Object third = create("ObjektAnzahlundBeans.ThirdService");
            Object counter = create("ObjektAnzahlundBeans.CounterService");

            System.out.println("Demo: Aufrufe in folgender Reihenfolge:");

            System.out.println("first -> " + invokeFirstAvailable(first, new String[] {"increment", "doSomething", "count", "incrementAndGet"}));
            System.out.println("second -> " + invokeFirstAvailable(second, new String[] {"doSomething", "increment", "count"}));
            System.out.println("third -> " + invokeFirstAvailable(third, new String[] {"doSomething", "increment", "count"}));

            // weitere Aufrufe zeigen kumulative Erhöhung
            System.out.println("first -> " + invokeFirstAvailable(first, new String[] {"doSomething", "increment", "count"}));
            System.out.println("second -> " + invokeFirstAvailable(second, new String[] {"doSomething", "increment", "count"}));

            System.out.println("Aktueller Zählerstand (aus CounterService): " +
                invokeFirstAvailable(counter, new String[] {"getCurrent", "getCount", "getValue", "current"}));

            System.out.println("\nHinweis: Shared CounterService wird nun zwischen Services geteilt.");
        } catch (Exception e) {
            System.err.println("Fehler: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Object create(String className) {
        if (instances.containsKey(className)) {
            return instances.get(className);
        }

        try {
            Class<?> cls = Class.forName(className);

            // Versuch: Default-Konstruktor
            try {
                Object inst = cls.getDeclaredConstructor().newInstance();
                instances.put(className, inst);
                return inst;
            } catch (NoSuchMethodException ns) {
                // kein Default-Konstruktor -> prüfen, ob Konstruktor(CounterService) existiert
                try {
                    Class<?> counterCls = Class.forName("ObjektAnzahlundBeans.CounterService");
                    java.lang.reflect.Constructor<?> ctor = cls.getDeclaredConstructor(counterCls);
                    // Erzeuge oder hole die einzige CounterService-Instanz
                    Object counter = create("ObjektAnzahlundBeans.CounterService");
                    Object inst = ctor.newInstance(counter);
                    instances.put(className, inst);
                    return inst;
                } catch (NoSuchMethodException ex) {
                    System.err.println("Keine passende Konstruktor gefunden für " + className + ": " + ex.getMessage());
                    return null;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Klasse nicht gefunden: " + className);
            return null;
        } catch (Exception e) {
            System.err.println("Fehler beim Erzeugen von " + className + ": " + e.getMessage());
            return null;
        }
    }

    private String invokeFirstAvailable(Object target, String[] candidates) {
        if (target == null) return "keine Instanz";
        for (String name : candidates) {
            try {
                java.lang.reflect.Method m = target.getClass().getMethod(name);
                Object r = m.invoke(target);
                return r == null ? "null" : r.toString();
            } catch (NoSuchMethodException ignored) {
                // nächster Kandidat
            } catch (Exception e) {
                return "Fehler beim Aufruf: " + e.getClass().getSimpleName();
            }
        }
        return "Methode nicht gefunden (Kandidaten ausprobiert)";
    }
}
