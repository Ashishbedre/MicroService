package microService.example.microService.dto;

import java.util.ArrayList;

public class CompatibilityCheckResultdto {
    private ArrayList<CompatibilityCheckResult>  Compatibility;

    public CompatibilityCheckResultdto(ArrayList<CompatibilityCheckResult> compatibility) {
        Compatibility = compatibility;
    }

    public ArrayList<CompatibilityCheckResult> getCompatibility() {
        return Compatibility;
    }

    public void setCompatibility(ArrayList<CompatibilityCheckResult> compatibility) {
        Compatibility = compatibility;
    }
}
