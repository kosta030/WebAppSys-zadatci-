package ObjektAnzahlundBeans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdService {

    private final CounterService counterService;

    @Autowired
    public ThirdService(CounterService counterService) {
        this.counterService = counterService;
    }

    public int doSomething() {
        return counterService.count();
    }
}
