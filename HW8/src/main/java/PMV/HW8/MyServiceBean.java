package PMV.HW8;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Timer(level = Level.WARN)
public class MyServiceBean {


    public  int method1(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        return  Arrays.stream(arr).sum();
    }
}
