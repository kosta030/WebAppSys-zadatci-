package edu.fra.uas.v3autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MasterV3 {
    @Autowired
    JourneymanV2 journeyman2;

    public void delegateWork() {
        journeyman2.performWork();
    }
}
