package microService.example.microService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {


    @Value("${global.variable}")
    private String globalVariable;

    public String getGlobalVariable() {
        return globalVariable;
    }

    public void setGlobalVariable(String newGlobalVariable) {
        this.globalVariable = newGlobalVariable;
    }
}
