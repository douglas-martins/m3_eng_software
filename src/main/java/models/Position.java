package models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Position {

    Integer calculateAbility();

    default Integer validateAttributeInRange(Integer attribute){
        return validateAttributeInRange(attribute, 100);
    }

    default Integer validateAttributeInRange(Integer attribute, int max){
        if (attribute <= 0) return 1;
        if (attribute > max) return max;
        return round(Double.valueOf(attribute));
    }

    default Integer round(Double value){
        BigDecimal bigDecimal = new BigDecimal(value)
                .setScale(0, RoundingMode.HALF_EVEN);
        return bigDecimal.intValue();
    }
}
