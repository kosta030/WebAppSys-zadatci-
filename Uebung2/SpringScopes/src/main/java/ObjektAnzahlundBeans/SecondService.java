package ObjektAnzahlundBeans;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondService {

    private final CounterService counterService;

    @Autowired
    public SecondService(CounterService counterService) {
        this.counterService = counterService;
    }

    public int doSomething() {
        return counterService.count();
    }
}

