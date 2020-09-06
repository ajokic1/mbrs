package springplugin.generator.fmmodel;

public class FMOwningProperty extends FMJoinProperty{
    private String referencedColumnName;
    private String joinColumnName;
    private String inverseJoinColumnName;
    private String joinTableName;

    public FMOwningProperty(FMJoinProperty fmJoinProperty) {
        super(fmJoinProperty);
        this.setOptional(fmJoinProperty.isOptional());
        this.setFetch(fmJoinProperty.getFetch());
        this.setAnnotation(fmJoinProperty.getAnnotation());
        this.setOppositeEnd(fmJoinProperty.getOppositeEnd());
        this.setParentEntity(fmJoinProperty.getParentEntity());
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

    public String getJoinTableName() {
        return joinTableName;
    }

    public void setJoinTableName(String joinTableName) {
        this.joinTableName = joinTableName;
    }
}
