package springplugin.generator.fmmodel;

import springplugin.generator.fmmodel.enums.GenerationType;

public class FMPersistentProperty extends FMProperty {
    private int length;
    private boolean isQueryable;

    public FMPersistentProperty(FMProperty fmProperty) {
        super(fmProperty.getName(), fmProperty.getType(), fmProperty.getVisibility(),
                fmProperty.getLower(), fmProperty.getUpper());
    }

    public FMPersistentProperty() {
        super();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isQueryable() {
        return isQueryable;
    }

    public void setQueryable(boolean queryable) {
        isQueryable = queryable;
    }
}
