package springplugin.generator.fmmodel;

public class FMOwningProperty extends FMJoinProperty{
    private String referencedColumnName;
    private String joinColumnName;
    private String inverseJoinColumnName;

    public FMOwningProperty(FMJoinProperty fmJoinProperty) {
        super(fmJoinProperty);
        this.setOptional(fmJoinProperty.isOptional());
        this.setFetch(fmJoinProperty.getFetch());
        this.setAnnotation(fmJoinProperty.getAnnotation());
    }

    public FMOwningProperty() {
        super();
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }

    public String getJoinColumnName() {
        return joinColumnName;
    }

    public void setJoinColumnName(String joinColumnName) {
        this.joinColumnName = joinColumnName;
    }

    public String getInverseJoinColumnName() {
        return inverseJoinColumnName;
    }

    public void setInverseJoinColumnName(String inverseJoinColumnName) {
        this.inverseJoinColumnName = inverseJoinColumnName;
    }
}
