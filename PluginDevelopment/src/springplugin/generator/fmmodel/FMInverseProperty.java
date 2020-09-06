package springplugin.generator.fmmodel;

import springplugin.generator.fmmodel.enums.CascadeType;

public class FMInverseProperty extends FMJoinProperty {
    private String mappedBy;
    private CascadeType cascade;

    public FMInverseProperty(FMJoinProperty fmJoinProperty) {
        super(fmJoinProperty);
        this.setOptional(fmJoinProperty.isOptional());
        this.setFetch(fmJoinProperty.getFetch());
        this.setAnnotation(fmJoinProperty.getAnnotation());
        this.setOppositeEnd(fmJoinProperty.getOppositeEnd());
        this.setParentEntity(fmJoinProperty.getParentEntity());
    }

    public FMInverseProperty() {
        super();
    }


    public String getMappedBy() {
        return mappedBy;
    }

    public void setMappedBy(String mappedBy) {
        this.mappedBy = mappedBy;
    }

    public CascadeType getCascade() {
        return cascade;
    }

    public void setCascade(CascadeType cascade) {
        this.cascade = cascade;
    }
}
