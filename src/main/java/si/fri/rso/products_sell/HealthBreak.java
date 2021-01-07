package si.fri.rso.products_sell;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthBreak {
    private Boolean breakHealth = false;

    public void setBreakHealth(Boolean breakHealth) {
        this.breakHealth = breakHealth;
    }

    public Boolean getBreakHealth() {
        return breakHealth;
    }
}
