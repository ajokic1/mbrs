package springplugin.generator.fmmodel;

import springplugin.generator.fmmodel.enums.GenerationType;

public class FMGeneratedValueProperty extends FMPersistentProperty {
    private GenerationType strategy;

    public FMGeneratedValueProperty(FMPersistentProperty fmPersistentProperty) {
        super(fmPersistentProperty);
        this.setLength(fmPersistentProperty.getLength());
        this.setQueryable(fmPersistentProperty.isQueryable());
    }

    public FMGeneratedValueProperty(){
        super();
    }

    public GenerationType getStrategy() {
        return strategy;
    }

    public void setStrategy(GenerationType strategy) {
        this.strategy = strategy;
    }
}
