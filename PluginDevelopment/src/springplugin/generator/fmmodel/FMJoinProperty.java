package springplugin.generator.fmmodel;

import springplugin.generator.fmmodel.enums.AnnotationType;
import springplugin.generator.fmmodel.enums.FetchType;

public class FMJoinProperty extends FMProperty {
    private boolean optional;
    private FetchType fetch;
    private AnnotationType annotation;
    private FMJoinProperty oppositeEnd;

    public FMJoinProperty(FMProperty fmProperty) {
        super(fmProperty.getName(), fmProperty.getType(), fmProperty.getVisibility(), fmProperty.getLower(),
                fmProperty.getUpper());
    }

    public FMJoinProperty() {
        super();
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public FetchType getFetch() {
        return fetch;
    }

    public void setFetch(FetchType fetch) {
        this.fetch = fetch;
    }

    public AnnotationType getAnnotation() {
        return annotation;
    }

    public void setAnnotation(AnnotationType annotation) {
        this.annotation = annotation;
    }

    public FMJoinProperty getOppositeEnd() {
        return oppositeEnd;
    }

    public void setOppositeEnd(FMJoinProperty oppositeEnd) {
        this.oppositeEnd = oppositeEnd;
    }
}
