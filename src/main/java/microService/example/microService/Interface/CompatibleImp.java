package microService.example.microService.Interface;

import microService.example.microService.dto.CompatibilityCheckResult;
import microService.example.microService.dto.VersionSetProductDto;

public interface CompatibleImp {

    public CompatibilityCheckResult checkBothCompatibilityProductVersions(VersionSetProductDto product1
            , VersionSetProductDto product2);
}
