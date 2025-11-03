package ObjektAnzahlundBeans;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService {

    private final CounterService counterService;

    @Autowired
    public FirstService(CounterService counterService) {
        this.counterService = counterService;
    }

    public int doSomething() {
        // ruft CounterService.count() auf
        return counterService.count();
    }
}

